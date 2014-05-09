# color-picker

__Just another color picker for Android__

This is a nice and simple color picker for Android. It allows to show any number of different palettes with any (reasonable) number of colors. Palettes can be stored in an array or generated on the fly.


## Requirements

* Android support library 19 or newer
* [Retention Magic](https://github.com/dmfs/retention-magic)
* [DrawablePagerTitleStrip](https://github.com/dmfs/DrawablePagerTitleStrip)

## Example code

### Use as a dialog

To show the dialog, just get an instance, add a number of palettes and call the show method.

		ColorPickerDialogFragment d = new ColorPickerDialogFragment();

		// set the palettes
		d.setPalettes(new AbstractPalette[]
		{
			ArrayPalette.fromResources(this, "basecolors", R.string.base_palette_name, R.array.base_palette_colors, R.array.base_palette_color_names),
			new FactoryPalette("rainbow", "Rainbow", ColorFactory.RAINBOW, 16)
		});
		
		d.show(getSupportFragmentManager(), tag);


The calling activity or fragment should implement `ColorPickerDialogFragment.OnColorChangedListener` to get the result like:

		@Override
		public void onColorChanged(int color, String colorName, String paletteName)
		{
			// do something with color
		}


### Use via Intent

Alternatively include the following XML snippet in your `AndroidManifest.xml`

		<activity android:name="org.dmfs.android.colorpicker.ColorPickerActivity" >
		    <intent-filter>
		        <action android:name="org.openintents.action.PICK_COLOR" />

		        <category android:name="android.intent.category.DEFAULT" />
		    </intent-filter>
		</activity>

and call the dialog like:

		ColorPickerActivity.start(context, CODE_PICK_COLOR);

This will call an intent with the action `org.openintents.action.PICK_COLOR` allowing the user to use his preferred color picker (if it supports the Open Intent).

In `onActivityResult(int, int, Intent)` you can get the picked color like this:

		@Override
		protected void onActivityResult(int code, int result, Intent data)
		{
			if (result == RESULT_OK && code == CODE_PICK_COLOR)
			{
				Integer color = ColorPickerActivity.getColor(data);
			}
		}

## Screen shots

![](https://raw.github.com/dmfs/color-picker/master/demo/screenshots/screenshot_1.png "Screenshot 1")

![](https://raw.github.com/dmfs/color-picker/master/demo/screenshots/screenshot_2.png "Screenshot 2")

![](https://raw.github.com/dmfs/color-picker/master/demo/screenshots/screenshot_3.png "Screenshot 3")


## TODO

* fix landscape mode on smaller screens
* fix selected/focused backgrounds
* allow to customize color shapes
* allow to preselect a specific color
* add support for other fragment types, like hex input or color wheels

## License

Copyright (c) Marten Gajda 2014, licensed under Apache 2 (see `LICENSE`).
