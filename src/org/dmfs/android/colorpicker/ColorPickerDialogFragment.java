/*
 * Copyright (C) 2013 Marten Gajda <marten@dmfs.org>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA
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
		// set dialog title if we're shown in a dialog
		Dialog dialog = getDialog();
		if (dialog != null)
		{
			dialog.setTitle(title);
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
		// set dialog title if we're shown in a dialog
		Dialog dialog = getDialog();
		if (dialog != null)
		{
			dialog.setTitle(title);
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

		Dialog dialog = getDialog();
		if (dialog != null)
		{
			if (mTitleId != 0)
			{
				dialog.setTitle(mTitleId);
			}
			else if (mTitle != null)
			{
				dialog.setTitle(mTitle);
			}
		}
		dialog.setOnCancelListener(this);

		return view;
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