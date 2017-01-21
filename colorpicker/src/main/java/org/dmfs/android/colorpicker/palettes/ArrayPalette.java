/*
 * Copyright 2017 dmfs GmbH
 *
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
 * @author Marten Gajda
 */
public final class ArrayPalette implements Palette
{

    public static final Parcelable.Creator<ArrayPalette> CREATOR = new Parcelable.Creator<ArrayPalette>()
    {
        @Override
        public ArrayPalette createFromParcel(Parcel in)
        {
            return new ArrayPalette(in.readString(), in.readString(), in.createIntArray(), in.readInt(), in.createStringArray());
        }


        @Override
        public ArrayPalette[] newArray(int size)
        {
            return new ArrayPalette[size];
        }
    };

    /**
     * The name of this palette.
     */
    private final String mPaletteName;
    /**
     * The Id of this palette.
     */
    private final String mPaletteId;
    /**
     * The colors in this palette.
     */
    private final int[] mColors;
    /**
     * The names of the colors.
     */
    private final String[] mColorNames;
    /**
     * The number of columns to use for the layout of this palette.
     */
    private final int mColumns;


    /**
     * Build a new palette from arrays.
     *
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         The name of the palette.
     * @param colors
     *         An array of colors.
     * @param columns
     *         The number of columns to show when determining the layout for this palette.
     * @param names
     *         The names of the colors, or <code>null</code> if the colors don't have names.
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
     *         An identifier for this palette.
     * @param paletteName
     *         The name of the palette.
     * @param colors
     *         An array of colors.
     * @param names
     *         The names of the colors, or <code>null</code> if the colors don't have names.
     */
    public ArrayPalette(String id, String paletteName, int[] colors, String[] names)
    {
        this(id, paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), names);
    }


    /**
     * Build a new palette from arrays.
     *
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         The name of the palette.
     * @param colors
     *         An array of colors.
     * @param columns
     *         The number of columns to show when determining the layout for this palette.
     */
    public ArrayPalette(String id, String paletteName, int[] colors, int columns)
    {
        this(id, paletteName, colors, columns, null);
    }


    /**
     * Build a new palette from arrays using a square layout if possible.
     *
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         The name of the palette.
     * @param colors
     *         An array of colors.
     */
    public ArrayPalette(String id, String paletteName, int[] colors)
    {
        this(id, paletteName, colors, (int) Math.floor(Math.sqrt(colors.length)), null);
    }


    /**
     * Get an {@link ArrayPalette} from {@link Resources}.
     *
     * @param resources
     *         The {@link Resources}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     * @param columns
     *         The number of columns to use for the layout.
     * @param colorNameArray
     *         A string array resource for the color names.
     *
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
     *         The {@link Resources}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     * @param colorNameArray
     *         A string array resource for the color names.
     *
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
     *         The {@link Resources}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     *
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
     *         A {@link Context}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     * @param columns
     *         The number of columns to use for the layout.
     * @param colorNameArray
     *         A string array resource for the color names.
     *
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
     *         A {@link Context}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     * @param colorNameArray
     *         A string array resource for the color names.
     *
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
     *         A {@link Context}.
     * @param id
     *         An identifier for this palette.
     * @param paletteName
     *         A string resource id for the palette name.
     * @param colorArray
     *         The integer array resource id for the colors.
     *
     * @return An {@link ArrayPalette} instance.
     */
    public static ArrayPalette fromResources(Context context, String id, int paletteName, int colorArray)
    {
        return fromResources(context.getResources(), id, paletteName, colorArray);
    }


    @Override
    public String name()
    {
        return mPaletteName;
    }


    @Override
    public String id()
    {
        return mPaletteId;
    }


    @Override
    public int numberOfColors()
    {
        return mColors.length;
    }


    @Override
    public int colorAt(int index)
    {
        return mColors[index];
    }


    @Override
    public String nameOfColorAt(int index)
    {
        if (mColorNames != null)
        {
            return mColorNames[index];
        }
        return null;
    }


    @Override
    public int numberOfColumns()
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
        dest.writeString(mPaletteId);
        dest.writeString(mPaletteName);
        dest.writeIntArray(mColors);
        dest.writeInt(mColumns);
        dest.writeStringArray(mColorNames);
    }
}
