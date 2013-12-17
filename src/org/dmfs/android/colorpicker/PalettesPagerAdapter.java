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

import org.dmfs.android.colorpicker.palettes.AbstractPalette;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


/**
 * Pager adapter for the {@link PaletteFragment}s.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class PalettesPagerAdapter extends FragmentStatePagerAdapter
{

	private final static int FACTOR = 100;

	private AbstractPalette[] mPalettes;


	public PalettesPagerAdapter(FragmentManager fm, AbstractPalette[] palettes)
	{
		super(fm);
		mPalettes = palettes;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentStatePagerAdapter#getItem(int)
	 */
	@Override
	public Fragment getItem(int position)
	{
		PaletteFragment fragment = new PaletteFragment();
		fragment.setPalette(mPalettes[mapPosition(position)]);
		return fragment;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return mPalettes.length > 1 ? mPalettes.length * FACTOR : 1;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.view.PagerAdapter#getPageTitle(int)
	 */
	@Override
	public CharSequence getPageTitle(int position)
	{
		return mPalettes[mapPosition(position)].getName();
	}


	/**
	 * Get the actial postion from the "infinite" position.
	 * 
	 * @param position
	 *            The position in the "infinite" list of palettes.
	 * @return The actual position.
	 */
	private int mapPosition(int position)
	{
		return position % mPalettes.length;
	}
}
