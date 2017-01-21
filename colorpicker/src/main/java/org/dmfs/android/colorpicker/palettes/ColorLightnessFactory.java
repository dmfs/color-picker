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
 * A Factory that returns colors with a specific hue and saturation. The lightness of all colors is spread evenly across the entire palette.
 *
 * @author Marten Gajda
 */
public final class ColorLightnessFactory implements ColorFactory
{
    private final float[] mHSL = new float[] { 0, 0, 0 };


    public ColorLightnessFactory(float hue, float saturation)
    {
        mHSL[0] = hue;
        mHSL[1] = saturation;
    }


    @Override
    public int colorAt(int index, int count)
    {
        if (count <= 1)
        {
            return Color.WHITE;
        }

        float[] hsl = mHSL;

        hsl[2] = (float) index / (count - 1);

        return Color.HSVToColor(255, hsl);
    }
}
