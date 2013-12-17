# color-picker

__Just another color picker for Android__

This is a nice and simple color picker for Android. It allows to show any number of different palettes with any (reasonable) number of colors. Palettes can be stored in an array or generated on the fly.


## Requirements

The Android support library 19 or newer.

## Example code

To show the dialog, just get an instance, add a number of palettes and call the show method.

		ColorPickerDialogFragment d = new ColorPickerDialogFragment();

		// set the palettes
		d.setPalettes(new AbstractPalette[]
		{
			ArrayPalette.fromResources(this, R.string.base_palette_name, R.array.base_palette_colors, R.array.base_palette_color_names),
			new FactoryPalette("Rainbow", ColorFactory.RAINBOW, 16)
		});
		
		d.show(getSupportFragmentManager(), tag);


The calling activity or fragment should implement `ColorPickerDialogFragment.OnColorChangedListener` to get the result like:

		@Override
		public void onColorChanged(int color, String colorName, String paletteName)
		{
			// do something with color
		}

## Screen shots



## TODO

* fix landscape mode on smaller screens
* fix selected/focused backgrounds
* allow to customize color shapes
* allow to preselect a specific palette and/or color
* show a palette preview in title strip
* add support for other fragment types, like hex input or color wheels

## License

Copyright (c) Marten Gajda 2012, licensed under GPL version 2 or newer (see `LICENSE`).
