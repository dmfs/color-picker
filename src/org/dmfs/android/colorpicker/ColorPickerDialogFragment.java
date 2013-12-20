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
import org.dmfs.android.instancestatehelper.SupportDialogFragment;
import org.dmfs.android.instancestatehelper.annotations.Retain;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ColorPickerDialogFragment extends SupportDialogFragment implements OnColorSelectedListener
{
	public interface OnColorChangedListener
	{
		public void onColorChanged(int color, String colorName, String paletteName);
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
	 * Set the palettes to show.
	 * 
	 * @param palettes
	 *            An array of {@link AbstractPalette}s.
	 */
	public void setPalettes(AbstractPalette[] palettes)
	{
		mPalettes = palettes;
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
		super.onCreateView(inflater, container, savedInstanceState);

		View view = inflater.inflate(R.layout.org_dmfs_colorpickerdialog_fragment, container);

		mPager = (ViewPager) view.findViewById(R.id.pager);
		mPagerAdapter = new PalettesPagerAdapter(getChildFragmentManager(), mPalettes);
		mPagerAdapter.notifyDataSetChanged();
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(mPagerAdapter.getCount() / 2);

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
		return view;
	}


	@Override
	public void onColorSelected(int color, String colorName, String paletteName)
	{
		OnColorChangedListener listener = null;
		Fragment parentFragment = getParentFragment();
		Activity parentActivity = getActivity();

		if (parentFragment instanceof OnColorChangedListener)
		{
			listener = (OnColorChangedListener) parentFragment;
		}
		else if (parentActivity instanceof OnColorChangedListener)
		{
			listener = (OnColorChangedListener) parentActivity;
		}

		if (listener != null)
		{
			listener.onColorChanged(color, colorName, paletteName);
		}

		dismiss();
	}
}