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

package org.dmfs.android.colorpicker.demo;

import java.util.ArrayList;

import org.dmfs.android.colorpicker.ColorPickerDialogFragment;
import org.dmfs.android.colorpicker.ColorPickerDialogFragment.ColorDialogResultListener;
import org.dmfs.android.colorpicker.palettes.AbstractPalette;
import org.dmfs.android.colorpicker.palettes.ArrayPalette;
import org.dmfs.android.colorpicker.palettes.ColorFactory;
import org.dmfs.android.colorpicker.palettes.ColorFactory.CombinedColorFactory;
import org.dmfs.android.colorpicker.palettes.FactoryPalette;
import org.dmfs.android.colorpicker.palettes.RandomPalette;
import org.dmfs.android.retentionmagic.FragmentActivity;
import org.dmfs.android.retentionmagic.annotations.Retain;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class DemoActivity extends FragmentActivity implements ColorDialogResultListener
{
	private final static int[] COLORS = new int[] { 0xff000000, 0xff0000ff, 0xff00ff00, 0xffff0000, 0xffffff00, 0xff00ffff, 0xffff00ff, 0xff404040, 0xff808080,
		0xff8080ff, 0xff80ff80, 0xffff8080, 0xffffff80, 0xff80ffff, 0xffff80ff, 0xffffffff, };

	private TextView mTextView;
	private View mColorBox;

	@Retain(permanent = true, classNS = "DemoActivity")
	private String mSelectedPalette = null;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo);
		mTextView = (TextView) findViewById(R.id.textView2);
		mColorBox = findViewById(R.id.colorbox);
	}


	public void onClick(View view)
	{
		ColorPickerDialogFragment d = new ColorPickerDialogFragment();

		ArrayList<AbstractPalette> palettes = new ArrayList<AbstractPalette>();

		// add a palette from the resources
		palettes.add(ArrayPalette.fromResources(this, "base", R.string.base_palette_name, R.array.base_palette_colors, R.array.base_palette_color_names));

		palettes.add(new ArrayPalette("base2", "Base 2", COLORS));

		// Add a palette with rainbow colors
		palettes.add(new FactoryPalette("rainbow", "Rainbow", ColorFactory.RAINBOW, 16));

		// Add a palette with many darker rainbow colors
		palettes.add(new FactoryPalette("rainbow2", "Dirty Rainbow", new ColorFactory.RainbowColorFactory(0.5f, 0.5f), 16));

		// Add a palette with pastel colors
		palettes.add(new FactoryPalette("pastel", "Pastel", ColorFactory.PASTEL, 16));

		// Add a palette with red+orange colors
		palettes.add(new FactoryPalette("red/orange", "Red/Orange", new CombinedColorFactory(ColorFactory.RED, ColorFactory.ORANGE), 16));

		// Add a palette with yellow+green colors
		palettes.add(new FactoryPalette("yellow/green", "Yellow/Green", new CombinedColorFactory(ColorFactory.YELLOW, ColorFactory.GREEN), 16));

		// Add a palette with cyan+blue colors
		palettes.add(new FactoryPalette("cyan/blue", "Cyan/Blue", new CombinedColorFactory(ColorFactory.CYAN, ColorFactory.BLUE), 16));

		// Add a palette with purple+pink colors
		palettes.add(new FactoryPalette("purble/pink", "Purple/Pink", new CombinedColorFactory(ColorFactory.PURPLE, ColorFactory.PINK), 16));

		// Add a palette with red colors
		palettes.add(new FactoryPalette("red", "Red", ColorFactory.RED, 16));
		// Add a palette with green colors
		palettes.add(new FactoryPalette("green", "Green", ColorFactory.GREEN, 16));
		// Add a palette with blue colors
		palettes.add(new FactoryPalette("blue", "Blue", ColorFactory.BLUE, 16));

		// Add a palette with few random colors
		palettes.add(new RandomPalette("random1", "Random 1", 1));
		// Add a palette with few random colors
		palettes.add(new RandomPalette("random4", "Random 4", 4));
		// Add a palette with few random colors
		palettes.add(new RandomPalette("random9", "Random 9", 9));
		// Add a palette with few random colors
		palettes.add(new RandomPalette("random16", "Random 16", 16));

		// Add a palette with random colors
		palettes.add(new RandomPalette("random25", "Random 25", 25));

		// Add a palette with many random colors
		palettes.add(new RandomPalette("random81", "Random 81", 81));

		// Add a palette with secondary colors
		palettes.add(new FactoryPalette("secondary1", "Secondary 1", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(18),
			new ColorFactory.ColorShadeFactory(53), new ColorFactory.ColorShadeFactory(80), new ColorFactory.ColorShadeFactory(140)), 16, 4));

		// Add another palette with secondary colors
		palettes.add(new FactoryPalette("secondary2", "Secondary 2", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(210),
			new ColorFactory.ColorShadeFactory(265), new ColorFactory.ColorShadeFactory(300), new ColorFactory.ColorShadeFactory(340)), 16, 4));

		// set the palettes
		d.setPalettes(palettes.toArray(new AbstractPalette[palettes.size()]));

		// set a title
		d.setTitle(R.string.dialog_title_pick_a_color);

		// set the initial palette
		d.selectPaletteId(mSelectedPalette);

		// show the fragment
		d.show(getSupportFragmentManager(), "");
	}


	@Override
	public void onColorChanged(int color, String paletteId, String colorName, String paletteName)
	{
		mColorBox.setBackgroundColor(color);
		mTextView.setText(colorName == null ? " from \"" + paletteName + "\"" : "\"" + colorName + "\" from \"" + paletteName + "\"");
		mSelectedPalette = paletteId;
	}


	@Override
	public void onColorDialogCancelled()
	{
	}

}
