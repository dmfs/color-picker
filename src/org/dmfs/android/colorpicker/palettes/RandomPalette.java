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

package org.dmfs.android.colorpicker.palettes;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * A palette of random colors.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class RandomPalette extends AbstractPalette
{

	/**
	 * The palette name.
	 */
	private String mName;

	/**
	 * The Id of this palette.
	 */
	private String mPaletteId;

	/**
	 * The colors in this palette.
	 */
	private int[] mValues;


	/**
	 * Private constructor for unparcelling;
	 */
	private RandomPalette()
	{
	}


	/**
	 * Create a palette with <code>count</code> random colors.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param name
	 *            The name of this palette.
	 * @param count
	 *            The number of colors in this palette.
	 */
	public RandomPalette(String id, String name, int count)
	{
		mPaletteId = id;
		mName = name;
		int[] values = new int[count];
		for (int i = 0; i < count; ++i)
		{
			values[i] = 0xff000000 | (int) (Math.random() * 0x1000000);
		}
		mValues = values;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getName()
	 */
	@Override
	public String getName()
	{
		return mName;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getId()
	 */
	@Override
	public String getId()
	{
		return mPaletteId;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getNumberOfColors()
	 */
	@Override
	public int getNumberOfColors()
	{
		return mValues.length;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getColor(int)
	 */
	@Override
	public int getColor(int index)
	{
		return mValues[index];
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getColorName(int)
	 */
	@Override
	public String getColorName(int index)
	{
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getNumberOfColumns()
	 */
	@Override
	public int getNumberOfColumns()
	{
		return (int) Math.floor(Math.sqrt(mValues.length));
	}


	@Override
	public int describeContents()
	{
		return 0;
	}


	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(mName);
		dest.writeIntArray(mValues);
	}


	public void readFromParcel(Parcel in)
	{
		mName = in.readString();
		mValues = in.createIntArray();
	}

	public static final Parcelable.Creator<RandomPalette> CREATOR = new Parcelable.Creator<RandomPalette>()
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#createFromParcel(android.os.Parcel)
		 */
		@Override
		public RandomPalette createFromParcel(Parcel in)
		{
			final RandomPalette state = new RandomPalette();
			state.readFromParcel(in);
			return state;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#newArray(int)
		 */
		@Override
		public RandomPalette[] newArray(int size)
		{
			return new RandomPalette[size];
		}
	};
}
