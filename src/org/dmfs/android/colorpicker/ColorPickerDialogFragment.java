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

import org.dmfs.android.colorpicker.PaletteFragment.OnColorSelectedListener;
import org.dmfs.android.colorpicker.palettes.AbstractPalette;
import org.dmfs.android.retentionmagic.SupportDialogFragment;
import org.dmfs.android.retentionmagic.annotations.Retain;
import org.dmfs.android.view.ViewPager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


/**
 * A fragment that shows a color picker with multiple palettes to choose from.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ColorPickerDialogFragment extends SupportDialogFragment implements OnColorSelectedListener
{
	public interface ColorDialogResultListener
	{
		/**
		 * Called when a color has been picked in the dialog.
		 * 
		 * @param color
		 *            The color.
		 * @param paletteId
		 *            The id of the palette.
		 * @param colorName
		 *            The name of the color or <code>null</code>.
		 * @param paletteName
		 *            The name of the palette or <code>null</code>.
		 */
		public void onColorChanged(int color, String paletteId, String colorName, String paletteName);


		/**
		 * Called when the color picker dialog is cancelled without picking a color.
		 */
		public void onColorDialogCancelled();
	}

	private ViewPager mPager;
	private TextView mTitleView;
	private PalettesPagerAdapter mPagerAdapter;

	@Retain
	private AbstractPalette[] mPalettes;
	@Retain
	private CharSequence mTitle = null;
	@Retain
	private int mTitleId = 0;

	/**
	 * The index of the selected palette.
	 */
	private int mSelected = 0;


	/**
	 * Set the palettes to show.
	 * 
	 * @param palettes
	 *            An array of {@link AbstractPalette}s.
	 */
	public void setPalettes(AbstractPalette... palettes)
	{
		mPalettes = palettes;
	}


	/**
	 * Switch to a specific palette with the given ID. Has no effect if no palette with that id is found.
	 * 
	 * @param id
	 *            The id of the palette to select.
	 */
	public void selectPaletteId(String id)
	{
		if (mPalettes == null || id == null)
		{
			return;
		}

		int index = 0;
		for (AbstractPalette palette : mPalettes)
		{
			if (TextUtils.equals(id, palette.getId()))
			{
				mSelected = index;
				if (mPager != null && mPagerAdapter != null)
				{
					mPager.setCurrentItem(mPagerAdapter.getCount() / 2 + mSelected);
				}
				return;
			}
			++index;
		}
	}


	/**
	 * Set the dialog title.
	 * 
	 * @param title
	 *            The title of the dialog.
	 */
	public void setTitle(CharSequence title)
	{
		mTitle = title;
		if (mTitleView != null)
		{
			mTitleView.setText(title);
		}
	}


	/**
	 * Set the dialog title.
	 * 
	 * @param title
	 *            The resource id of a string resource with the title.
	 */
	public void setTitle(int title)
	{
		mTitleId = title;
		if (mTitleView != null)
		{
			mTitleView.setText(title);
		}
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.org_dmfs_colorpickerdialog_fragment, container);

		mPager = (ViewPager) view.findViewById(R.id.pager);
		mPagerAdapter = new PalettesPagerAdapter(getResources(), getChildFragmentManager(), mPalettes);
		mPagerAdapter.notifyDataSetChanged();
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(mPagerAdapter.getCount() / 2 + mSelected);

		mTitleView = (TextView) view.findViewById(android.R.id.title);

		if (mTitleId != 0)
		{
			mTitleView.setText(mTitleId);
		}
		else if (mTitle != null)
		{
			mTitleView.setText(mTitle);
		}

		return view;
	}


	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState)
	{
		Dialog result = super.onCreateDialog(savedInstanceState);
		result.requestWindowFeature(Window.FEATURE_NO_TITLE);
		result.setOnCancelListener(this);
		return result;
	}


	@Override
	public void onColorSelected(int color, String paletteId, String colorName, String paletteName)
	{
		ColorDialogResultListener listener = getListener();

		if (listener != null)
		{
			listener.onColorChanged(color, paletteId, colorName, paletteName);
		}

		dismiss();
	}


	@Override
	public void onCancel(DialogInterface dialog)
	{
		ColorDialogResultListener listener = getListener();

		if (listener != null)
		{
			listener.onColorDialogCancelled();
		}
	}


	/**
	 * Get a {@link ColorDialogResultListener}. This should be either the parent {@link android.app.Fragment} or the parent {@link Activity}.
	 * 
	 * @return A {@link ColorDialogResultListener} or <code>null</code> if neither the parent {@link Activity} nor the parent {@link android.app.Fragment}
	 *         implement this interface.
	 */
	private ColorDialogResultListener getListener()
	{
		ColorDialogResultListener listener = null;
		Fragment parentFragment = getParentFragment();
		Activity parentActivity = getActivity();

		if (parentFragment instanceof ColorDialogResultListener)
		{
			listener = (ColorDialogResultListener) parentFragment;
		}
		else if (parentActivity instanceof ColorDialogResultListener)
		{
			listener = (ColorDialogResultListener) parentActivity;
		}
		return listener;
	}
}