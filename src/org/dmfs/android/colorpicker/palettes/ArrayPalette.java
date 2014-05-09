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

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;


/**
 * A palette that gets colors and names from arrays. If no column number is specified, this class uses the next integer below the square root fo the number of
 * colors.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ArrayPalette extends AbstractPalette implements Parcelable
{

	/**
	 * The name of this palette.
	 */
	private String mPaletteName;

	/**
	 * The Id of this palette.
	 */
	private String mPaletteId;

	/**
	 * The colors in this palette.
	 */
	private int[] mColors;

	/**
	 * The names of the colors.
	 */
	private String[] mColorNames;

	/**
	 * The number of columns to use for the layout of this palette.
	 */
	private int mColumns;


	/**
	 * Get an {@link ArrayPalette} from {@link Resources}.
	 * 
	 * @param resources
	 *            The {@link Resources}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param columns
	 *            The number of columns to use for the layout.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Resources resources, String id, int paletteName, int colorArray, int columns, int colorNameArray)
	{
		return new ArrayPalette(id, resources.getString(paletteName), resources.getIntArray(colorArray), columns, resources.getStringArray(colorNameArray));
	}


	/**
	 * Get an {@link ArrayPalette} from {@link Resources}.
	 * 
	 * @param resources
	 *            The {@link Resources}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Resources resources, String id, int paletteName, int colorArray, int colorNameArray)
	{
		return new ArrayPalette(id, resources.getString(paletteName), resources.getIntArray(colorArray), resources.getStringArray(colorNameArray));
	}


	/**
	 * Get an {@link ArrayPalette} from {@link Resources}.
	 * 
	 * @param resources
	 *            The {@link Resources}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Resources resources, String id, int paletteName, int colorArray)
	{
		return new ArrayPalette(id, resources.getString(paletteName), resources.getIntArray(colorArray));
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param columns
	 *            The number of columns to use for the layout.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Context context, String id, int paletteName, int colorArray, int columns, int colorNameArray)
	{
		return fromResources(context.getResources(), id, paletteName, colorArray, columns, colorNameArray);
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Context context, String id, int paletteName, int colorArray, int colorNameArray)
	{
		return fromResources(context.getResources(), id, paletteName, colorArray, colorNameArray);
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Context context, String id, int paletteName, int colorArray)
	{
		return fromResources(context.getResources(), id, paletteName, colorArray);
	}


	/**
	 * Private constructor for unparcelling;
	 */
	private ArrayPalette()
	{
	}


	/**
	 * Build a new palette from arrays.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param columns
	 *            The number of columns to show when determining the layout for this palette.
	 * @param names
	 *            The names of the colors, or <code>null</code> if the colors don't have names.
	 */
	public ArrayPalette(String id, String paletteName, int[] colors, int columns, String[] names)
	{
		mPaletteId = id;
		mPaletteName = paletteName;
		mColors = colors;
		mColorNames = names;
		mColumns = columns;
	}


	/**
	 * Build a new palette from arrays using a square layout if possible.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param names
	 *            The names of the colors, or <code>null</code> if the colors don't have names.
	 */
	public ArrayPalette(String id, String paletteName, int[] colors, String[] names)
	{
		this(id, paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), names);
	}


	/**
	 * Build a new palette from arrays.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param columns
	 *            The number of columns to show when determining the layout for this palette.
	 */
	public ArrayPalette(String id, String paletteName, int[] colors, int columns)
	{
		this(id, paletteName, colors, columns, null);
	}


	/**
	 * Build a new palette from arrays using a square layout if possible.
	 * 
	 * @param id
	 *            An identifier for this palette.
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 */
	public ArrayPalette(String id, String paletteName, int[] colors)
	{
		this(id, paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), null);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getName()
	 */
	@Override
	public String getName()
	{
		return mPaletteName;
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
		return mColors.length;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getColor(int)
	 */
	@Override
	public int getColor(int index)
	{
		return mColors[index];
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getColorName(int)
	 */
	@Override
	public String getColorName(int index)
	{
		if (mColorNames != null)
		{
			return mColorNames[index];
		}
		return null;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dmfs.android.colorpicker.palettes.AbstractPalette#getNumberOfColumns()
	 */
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
		dest.writeString(mPaletteName);
		dest.writeIntArray(mColors);
		dest.writeStringArray(mColorNames);
		dest.writeInt(mColumns);
	}


	public void readFromParcel(Parcel in)
	{
		mPaletteName = in.readString();
		mColors = in.createIntArray();
		mColorNames = in.createStringArray();
		mColumns = in.readInt();
	}

	public static final Parcelable.Creator<ArrayPalette> CREATOR = new Parcelable.Creator<ArrayPalette>()
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#createFromParcel(android.os.Parcel)
		 */
		@Override
		public ArrayPalette createFromParcel(Parcel in)
		{
			final ArrayPalette state = new ArrayPalette();
			state.readFromParcel(in);
			return state;
		}


		/*
		 * (non-Javadoc)
		 * 
		 * @see android.os.Parcelable.Creator#newArray(int)
		 */
		@Override
		public ArrayPalette[] newArray(int size)
		{
			return new ArrayPalette[size];
		}
	};
}
