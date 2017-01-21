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

import android.graphics.Color;


/**
 * A Factory that returns colors with a specific HUE value. This factory leaves out the edge cases - pure black and pure white.
 *
 * @author Marten Gajda
 */
public final class ColorShadeFactory implements ColorFactory
{
    private final float[] mHSL = new float[] { 0, 0, 0 };


    public ColorShadeFactory(float hue)
    {
        mHSL[0] = hue;
    }


    @Override
    public int colorAt(int index, int count)
    {
        index++;
        count++;
        float[] hsl = mHSL;

        if (index <= count / 2)
        {
            hsl[1] = 1f;
            hsl[2] = index * 2f / count;
        }
        else
        {
            hsl[1] = 2f - index * 2f / count;
            hsl[2] = 1f;
        }
        return Color.HSVToColor(255, hsl);
    }
}
