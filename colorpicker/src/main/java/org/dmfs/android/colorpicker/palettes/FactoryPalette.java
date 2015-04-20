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

package org.dmfs.android.colorpicker.palettes;

import android.os.Parcel;
import android.os.Parcelable;


public class FactoryPalette extends AbstractPalette implements Parcelable
{

	/**
	 * The display name of this palette.
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
	 * The number of columns to use for the layout of this palette.
	 */
	private int mColumns;


	/**
	 * Private constructor for unparcelling;
	 */
	private FactoryPalette()
	{
	}


	/**
	 * Build a new palette with a custom {@link ColorFactory}.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param name
	 *            The name of the palette.
	 * @param colorProvider
	 *            An instance of {@link ColorFactory} that can return a color for each index.
	 * @param count
	 *            The number of colors to generate in this palette.
	 * 
	 * @param columns
	 *            The number of columns to use in the layout
	 */
	public FactoryPalette(String id, String name, ColorFactory colorProvider, int count, int columns)
	{
		mPaletteId = id;
		mName = name;
		int[] values = new int[count];
		for (int i = 0; i < count; ++i)
		{
			values[i] = colorProvider.getColor(i, count);
		}
		mValues = values;
		mColumns = columns;
	}


	/**
	 * Build a new palette with a custom {@link ColorFactory}.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param name
	 *            The name of the palette.
	 * @param colorProvider
	 *            An instance of {@link ColorFactory} that can return a color for each index.
	 * @param count
	 *            The number of colors to generate in this palette.
	 */
	public FactoryPalette(String id, String name, ColorFactory colorProvider, int count)
	{
		this(id, name, colorProvider, count, (int) Math.floor(Math.sqrt(count)));
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
		return mColumns;
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
		dest.writeInt(mColumns);
	}


	public void readFromParcel(Parcel in)
	{
		mName = in.readString();
		mValues = in.createIntArray();
		mColumns = in.readInt();
	}

	public static final Parcelable.Creator<FactoryPalette> CREATOR = new Parcelable.Creator<FactoryPalette>()
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#createFromParcel(android.os.Parcel)
		 */
		@Override
		public FactoryPalette createFromParcel(Parcel in)
		{
			final FactoryPalette state = new FactoryPalette();
			state.readFromParcel(in);
			return state;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#newArray(int)
		 */
		@Override
		public FactoryPalette[] newArray(int size)
		{
			return new FactoryPalette[size];
		}
	};
}
