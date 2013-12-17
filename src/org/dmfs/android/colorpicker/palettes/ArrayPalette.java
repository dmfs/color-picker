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
	public static ArrayPalette fromResources(Resources resources, int paletteName, int colorArray, int columns, int colorNameArray)
	{
		return new ArrayPalette(resources.getString(paletteName), resources.getIntArray(colorArray), columns, resources.getStringArray(colorNameArray));
	}


	/**
	 * Get an {@link ArrayPalette} from {@link Resources}.
	 * 
	 * @param resources
	 *            The {@link Resources}.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Resources resources, int paletteName, int colorArray, int colorNameArray)
	{
		return new ArrayPalette(resources.getString(paletteName), resources.getIntArray(colorArray), resources.getStringArray(colorNameArray));
	}


	/**
	 * Get an {@link ArrayPalette} from {@link Resources}.
	 * 
	 * @param resources
	 *            The {@link Resources}.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Resources resources, int paletteName, int colorArray)
	{
		return new ArrayPalette(resources.getString(paletteName), resources.getIntArray(colorArray));
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
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
	public static ArrayPalette fromResources(Context context, int paletteName, int colorArray, int columns, int colorNameArray)
	{
		return fromResources(context.getResources(), paletteName, colorArray, columns, colorNameArray);
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @param colorNameArray
	 *            A string array resource for the color names.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Context context, int paletteName, int colorArray, int colorNameArray)
	{
		return fromResources(context.getResources(), paletteName, colorArray, colorNameArray);
	}


	/**
	 * Get an {@link ArrayPalette} from the resources.
	 * 
	 * @param Context
	 *            A {@link Context}.
	 * @param paletteName
	 *            A string resource id for the palette name.
	 * @param colorArray
	 *            The integer array resource id for the colors.
	 * @return An {@link ArrayPalette} instance.
	 */
	public static ArrayPalette fromResources(Context context, int paletteName, int colorArray)
	{
		return fromResources(context.getResources(), paletteName, colorArray);
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
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param columns
	 *            The number of columns to show when determining the layout for this palette.
	 * @param names
	 *            The names of the colors, or <code>null</code> if the colors don't have names.
	 */
	public ArrayPalette(String paletteName, int[] colors, int columns, String[] names)
	{
		mPaletteName = paletteName;
		mColors = colors;
		mColorNames = names;
		mColumns = columns;
	}


	/**
	 * Build a new palette from arrays using a square layout if possible.
	 * 
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param names
	 *            The names of the colors, or <code>null</code> if the colors don't have names.
	 */
	public ArrayPalette(String paletteName, int[] colors, String[] names)
	{
		this(paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), names);
	}


	/**
	 * Build a new palette from arrays.
	 * 
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 * @param columns
	 *            The number of columns to show when determining the layout for this palette.
	 */
	public ArrayPalette(String paletteName, int[] colors, int columns)
	{
		this(paletteName, colors, columns, null);
	}


	/**
	 * Build a new palette from arrays using a square layout if possible.
	 * 
	 * @param paletteName
	 *            The name of the palette.
	 * @param colors
	 *            An array of colors.
	 */
	public ArrayPalette(String paletteName, int[] colors)
	{
		this(paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), null);
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
