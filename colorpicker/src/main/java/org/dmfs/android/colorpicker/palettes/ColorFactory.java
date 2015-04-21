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

import android.graphics.Color;


/**
 * A factory for colors.
 */
public interface ColorFactory
{

	/**
	 * A Factory that returns colors with a specific hue and saturation. The lightness of all colors is spread evenly across the entire palette.
	 * 
	 * @author Marten Gajda <marten@dmfs.org>
	 */
	public class ColorLigthnessFactory implements ColorFactory
	{
		private final float[] mHSL = new float[] { 0, 0, 0 };


		public ColorLigthnessFactory(float hue, float saturation)
		{
			mHSL[0] = hue;
			mHSL[1] = saturation;
		}


		@Override
		public int getColor(int index, int count)
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

	/**
	 * A Factory that returns colors with a specific HUE value. This factory leaves out the edge cases - pure black and pure white.
	 * 
	 * @author Marten Gajda <marten@dmfs.org>
	 */
	public class ColorShadeFactory implements ColorFactory
	{
		private final float[] mHSL = new float[] { 0, 0, 0 };


		public ColorShadeFactory(float hue)
		{
			mHSL[0] = hue;
		}


		@Override
		public int getColor(int index, int count)
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

	/**
	 * A factory that returns the entire palette with a specific saturation and lightness value.
	 * 
	 * @author Marten Gajda <marten@dmfs.org>
	 */
	public class RainbowColorFactory implements ColorFactory
	{
		private final float[] mHSL = new float[] { 0, 0, 0 };


		public RainbowColorFactory(float saturation, float lightness)
		{
			mHSL[1] = saturation;
			mHSL[2] = lightness;
		}


		@Override
		public int getColor(int index, int count)
		{
			count += 1;
			float[] hsl = mHSL;

			hsl[0] = index * 360f / count;

			return Color.HSVToColor(255, hsl);
		}
	}

	/**
	 * A Factory that combines multiple factories into one, by concatenation of the palettes.
	 * 
	 * @author Marten Gajda <marten@dmfs.org>
	 */
	public class CombinedColorFactory implements ColorFactory
	{

		private final ColorFactory[] mFactories;


		public CombinedColorFactory(ColorFactory... factories)
		{
			mFactories = factories;
		}


		@Override
		public int getColor(int index, int count)
		{
			int factoryCount = mFactories.length;
			return mFactories[(index * factoryCount) / count].getColor(index % (count / factoryCount), count / factoryCount);
		}

	}

	/**
	 * Grey scale from black to white
	 */
	public final static ColorFactory GREY = new ColorLigthnessFactory(0, 0);

	/**
	 * Shades of red (0°).
	 */
	public final static ColorFactory RED = new ColorShadeFactory(0);

	/**
	 * Shades of orange (37°).
	 */
	public final static ColorFactory ORANGE = new ColorShadeFactory(37f);

	/**
	 * Shades of yellow (60°).
	 */
	public final static ColorFactory YELLOW = new ColorShadeFactory(60f);

	/**
	 * Shades of green (120°).
	 */
	public final static ColorFactory GREEN = new ColorShadeFactory(120f);

	/**
	 * Shades of cyan (180°).
	 */
	public final static ColorFactory CYAN = new ColorShadeFactory(180f);

	/**
	 * Shades of blue (240°).
	 */
	public final static ColorFactory BLUE = new ColorShadeFactory(240f);

	/**
	 * Shades of purple (280°).
	 */
	public final static ColorFactory PURPLE = new ColorShadeFactory(280f);

	/**
	 * Shades of pink (320°).
	 */
	public final static ColorFactory PINK = new ColorShadeFactory(320f);

	/**
	 * Rainbow colors.
	 */
	public final static ColorFactory RAINBOW = new RainbowColorFactory(1f, 1f);

	/**
	 * Pastel colors (same as {@link #RAINBOW} just with a saturation of 50%).
	 */
	public final static ColorFactory PASTEL = new RainbowColorFactory(0.5f, 1f);


	/**
	 * Return a color for the given index into a palette of <code>count</code> colors.
	 * 
	 * @param index
	 *            The index of the color.
	 * @param count
	 *            The total number of colors in this palette.
	 * @return The color.
	 */
	public int getColor(int index, int count);
}