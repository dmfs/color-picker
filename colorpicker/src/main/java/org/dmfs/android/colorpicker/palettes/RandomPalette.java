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

import android.os.Parcel;
import android.os.Parcelable;


/**
 * A palette of random colors.
 *
 * @author Marten Gajda
 */
public final class RandomPalette implements Palette
{

    public static final Parcelable.Creator<RandomPalette> CREATOR = new Parcelable.Creator<RandomPalette>()
    {
        @Override
        public RandomPalette createFromParcel(Parcel in)
        {
            final RandomPalette state = new RandomPalette();
            state.readFromParcel(in);
            return state;
        }


        @Override
        public RandomPalette[] newArray(int size)
        {
            return new RandomPalette[size];
        }
    };
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
     *         An identifier for this palette.
     * @param name
     *         The name of this palette.
     * @param count
     *         The number of colors in this palette.
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


    @Override
    public String name()
    {
        return mName;
    }


    @Override
    public String id()
    {
        return mPaletteId;
    }


    @Override
    public int numberOfColors()
    {
        return mValues.length;
    }


    @Override
    public int colorAt(int index)
    {
        return mValues[index];
    }


    @Override
    public String nameOfColorAt(int index)
    {
        return null;
    }


    @Override
    public int numberOfColumns()
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
}
