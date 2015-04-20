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

package org.dmfs.android.colorpicker;

import org.dmfs.android.colorpicker.ColorPickerDialogFragment.ColorDialogResultListener;
import org.dmfs.android.colorpicker.palettes.AbstractPalette;
import org.dmfs.android.colorpicker.palettes.ColorFactory;
import org.dmfs.android.colorpicker.palettes.ColorFactory.CombinedColorFactory;
import org.dmfs.android.colorpicker.palettes.FactoryPalette;
import org.dmfs.android.retentionmagic.FragmentActivity;
import org.dmfs.android.retentionmagic.annotations.Retain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * An activity that prompts the user to pick a color.
 * <p>
 * To use it include the following XML fragment in your <code>AndroidManifest.xml</code>
 * </p>
 * 
 * <pre>
 *        &lt;activity android:name="org.dmfs.android.colorpicker.ColorPickerActivity" >
 *             &lt;intent-filter>
 *                 &lt;action android:name="org.openintents.action.PICK_COLOR" />
 * 
 *                 &lt;category android:name="android.intent.category.DEFAULT" />
 *             &lt;/intent-filter>
 *         &lt;/activity>
 * </pre>
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class ColorPickerActivity extends FragmentActivity implements ColorDialogResultListener
{

	/**
	 * Color picker action.
	 */
	public final static String ACTION_PICK_COLOR = "org.openintents.action.PICK_COLOR";

	/**
	 * The extra that contains the picked color.
	 */
	public final static String EXTRA_COLOR = "org.openintents.extra.COLOR";

	/**
	 * The palettes to show.
	 */
	private final static AbstractPalette[] PALETTES = { new FactoryPalette("rainbow", "Rainbow", ColorFactory.RAINBOW, 16),
		new FactoryPalette("dark_rainbow", "Dark Rainbow", new ColorFactory.RainbowColorFactory(0.5f, 0.5f), 16),
		new FactoryPalette("red", "Red", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(340), ColorFactory.RED), 16),
		new FactoryPalette("orange", "Orange", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(18), ColorFactory.ORANGE), 16),
		new FactoryPalette("yellow", "Yellow", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(53), ColorFactory.YELLOW), 16),
		new FactoryPalette("green", "Green", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(80), ColorFactory.GREEN), 16),
		new FactoryPalette("cyan", "Cyan", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(150), ColorFactory.CYAN), 16),
		new FactoryPalette("blue", "Blue", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(210), ColorFactory.BLUE), 16),
		new FactoryPalette("purple", "Purple", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(265), ColorFactory.PURPLE), 16),
		new FactoryPalette("pink", "Pink", new CombinedColorFactory(new ColorFactory.ColorShadeFactory(300), ColorFactory.PINK), 16),
		new FactoryPalette("grey", "Grey", ColorFactory.GREY, 16), new FactoryPalette("pastel", "Pastel", ColorFactory.PASTEL, 16) };

	/**
	 * The id of the palette
	 */
	@Retain(classNS = "ColorPickerActivity", key = "palette", permanent = true)
	private String mPaletteId = null;


	/**
	 * Start the {@link ColorPickerActivity} with the given request code.
	 * 
	 * @param context
	 *            A {@link Context}.
	 * @param requestCode
	 *            The request code.
	 */
	public static void start(android.app.Activity context, int requestCode)
	{
		Intent intent = new Intent(ACTION_PICK_COLOR);
		context.startActivityForResult(intent, requestCode);
	}


	/**
	 * Returns the color from the given result intent or <code>null</code> if there was no color.
	 * 
	 * @param result
	 *            The intent to get the color from.
	 * @return the color or <code>null</code>.
	 */
	public static Integer getColor(Intent result)
	{
		return result != null && result.hasExtra(EXTRA_COLOR) ? result.getIntExtra(EXTRA_COLOR, 0) : null;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		showColorPickerDialog();

	}


	private void showColorPickerDialog()
	{
		ColorPickerDialogFragment d = new ColorPickerDialogFragment();
		d.setPalettes(PALETTES);
		d.setTitle(R.string.org_dmfs_colorpicker_pick_a_color);
		d.selectPaletteId(mPaletteId);
		d.show(getSupportFragmentManager(), "");
	}


	@Override
	public void onColorChanged(int color, String paletteId, String colorName, String paletteName)
	{
		mPaletteId = paletteId;
		Intent intent = getIntent();
		intent.putExtra(EXTRA_COLOR, color);
		setResult(RESULT_OK, intent);
		finish();
	}


	@Override
	public void onColorDialogCancelled()
	{
		setResult(RESULT_CANCELED);
		finish();
	}
}
