/*
 * Copyright (C) 2014 Marten Gajda <marten@dmfs.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package org.dmfs.android.colorpicker;

import org.dmfs.android.colorpicker.palettes.AbstractPalette;
import org.dmfs.android.retentionmagic.SupportFragment;
import org.dmfs.android.retentionmagic.annotations.Retain;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;


/**
 * A fragment that shows a color palette.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class PaletteFragment extends SupportFragment implements OnItemClickListener
{
	public interface OnColorSelectedListener
	{
		public void onColorSelected(int color, String paletteId, String colorName, String paletteName);
	}

	/**
	 * The palette to show.
	 */
	@Retain
	private AbstractPalette mPalette;

	/**
	 * An adapter for the palette.
	 */
	private PaletteGridAdapter mAdapter;


	public void setPalette(AbstractPalette palette)
	{
		mPalette = palette;
	}


	@Override
	public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		/*
		 * TODO: build the layout programmatically to get rid of the resources, so we can distribute this in a single jar
		 */
		final ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.org_dmfs_colorpickerdialog_palette_grid, container, false);
		final GridView gridview = (GridView) rootView.findViewById(android.R.id.content);

		mAdapter = new PaletteGridAdapter(getActivity(), mPalette);
		gridview.setAdapter(mAdapter);
		gridview.setOnItemClickListener(this);
		gridview.setNumColumns(mAdapter.getNumColumns());

		/*
		 * Adjust the layout of the gridview to a square.
		 * 
		 * Inspired by Bill Lahti, see http://blahti.wordpress.com/2012/07/23/three-variations-of-image-squares/
		 */
		gridview.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
		{
			@SuppressLint("NewApi")
			public void onGlobalLayout()
			{
				int parentHeight = rootView.getHeight() - rootView.getPaddingTop() - rootView.getPaddingBottom();
				int parentWidth = rootView.getWidth() - rootView.getPaddingLeft() - rootView.getPaddingRight();

				int gridWidth = Math.min(parentWidth, parentHeight);

				int columnSpacing;
				if (android.os.Build.VERSION.SDK_INT >= 16)
				{
					columnSpacing = gridview.getHorizontalSpacing() * (mAdapter.getNumColumns() - 1);
				}
				else
				{
					/*
					 * TODO: getHorizontalSpacing() has been introduced in SDK level 16. We need to find a way to get get the actual spacing. Until then we use
					 * a hard coded value of 8 dip.
					 * 
					 * One way would be to use a dimension in the layout. That would allow us to resolve the dimension here. However, that would be one step
					 * away from a library without resource dependencies. Maybe there is an Android dimension resource with a reasonable value?
					 */
					DisplayMetrics metrics = inflater.getContext().getResources().getDisplayMetrics();
					if (android.os.Build.VERSION.SDK_INT > 10)
					{
						columnSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, metrics) * (mAdapter.getNumColumns() - 1);
					}
					else
					{
						// Android 2 seems to add spacing around the entire gridview
						columnSpacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, metrics) * mAdapter.getNumColumns();
					}
				}

				// width of a single column
				int columnWidth = (gridWidth - columnSpacing) / mAdapter.getNumColumns();

				// estimated width of the grid
				int actualGridWidth = mAdapter.getNumColumns() * columnWidth + columnSpacing;

				// add padding to center the grid if we don't use the entire space due to rounding errors
				if (actualGridWidth < gridWidth - 1)
				{
					int padding = (gridWidth - actualGridWidth) / 2;
					if (padding > 0)
					{
						gridview.setPadding(padding, padding, padding, padding);

					}
				}
				else
				{
					// no padding needed
					gridview.setPadding(0, 0, 0, 0);
				}

				// set the column width
				gridview.setColumnWidth(columnWidth);

				android.view.ViewGroup.LayoutParams params = gridview.getLayoutParams();
				if (params == null || params.height != gridWidth) // avoid unnecessary updates
				{
					LayoutParams lparams = new LinearLayout.LayoutParams(gridWidth, gridWidth);
					gridview.setLayoutParams(lparams);
				}
			}
		});
		return rootView;
	}


	@Override
	public void onItemClick(AdapterView<?> gridView, View View, int position, long id)
	{
		// pass the click event to the parent fragment
		Fragment parent = getParentFragment();
		if (parent instanceof OnColorSelectedListener)
		{
			OnColorSelectedListener listener = (OnColorSelectedListener) parent;
			listener.onColorSelected(mPalette.getColor(position), mPalette.getId(), mPalette.getColorName(position), mPalette.getName());
		}
	}
}
