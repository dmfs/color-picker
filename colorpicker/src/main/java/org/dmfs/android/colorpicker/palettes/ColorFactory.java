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

/**
 * A factory for colors.
 *
 * @author Marten Gajda
 */
public interface ColorFactory
{
    /**
     * Grey scale from black to white
     */
    ColorFactory GREY = new ColorLightnessFactory(0, 0);
    /**
     * Shades of red (0°).
     */
    ColorFactory RED = new ColorShadeFactory(0);
    /**
     * Shades of orange (37°).
     */
    ColorFactory ORANGE = new ColorShadeFactory(37f);
    /**
     * Shades of yellow (60°).
     */
    ColorFactory YELLOW = new ColorShadeFactory(60f);
    /**
     * Shades of green (120°).
     */
    ColorFactory GREEN = new ColorShadeFactory(120f);
    /**
     * Shades of cyan (180°).
     */
    ColorFactory CYAN = new ColorShadeFactory(180f);
    /**
     * Shades of blue (240°).
     */
    ColorFactory BLUE = new ColorShadeFactory(240f);
    /**
     * Shades of purple (280°).
     */
    ColorFactory PURPLE = new ColorShadeFactory(280f);
    /**
     * Shades of pink (320°).
     */
    ColorFactory PINK = new ColorShadeFactory(320f);
    /**
     * Rainbow colors.
     */
    ColorFactory RAINBOW = new RainbowColorFactory(1f, 1f);
    /**
     * Pastel colors (same as {@link #RAINBOW} just with a saturation of 50%).
     */
    ColorFactory PASTEL = new RainbowColorFactory(0.5f, 1f);

    /**
     * Return a color for the given index into a palette of <code>count</code> colors.
     *
     * @param index
     *         The index of the color.
     * @param count
     *         The total number of colors in this palette.
     *
     * @return The color.
     */
    int colorAt(int index, int count);

}