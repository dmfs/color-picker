# color-picker

__Just another color picker for Android__

This is a nice and simple color picker for Android. It allows to show any number of different palettes with any (reasonable) number of colors. Palettes can be stored in an array or generated on the fly.


## Requirements

* Android support library 23 or newer
* [Retention Magic](https://github.com/dmfs/retention-magic)
* [DrawablePagerTitleStrip](https://github.com/dmfs/DrawablePagerTitleStrip)

## Example code

### Use as a dialog

To show the dialog, just get an instance, add a number of palettes and call the show method.
```java
		ColorPickerDialogFragment d = new ColorPickerDialogFragment();

		// set the palettes
		d.setPalettes(new AbstractPalette[]
		{
			ArrayPalette.fromResources(this, "basecolors", R.string.base_palette_name, R.array.base_palette_colors, R.array.base_palette_color_names),
			new FactoryPalette("rainbow", "Rainbow", ColorFactory.RAINBOW, 16)
		});
		
		d.show(getSupportFragmentManager(), tag);
```

The calling activity or fragment should implement `ColorPickerDialogFragment.ColorDialogResultListener` to get the result like:
```java
		@Override
		public void onColorChanged(int color, String colorName, String paletteName)
		{
			// do something with color
		}

		@Override
		public void onColorDialogCancelled()
		{
			// handle cancelled color picker dialog
		}
```
### Use via Intent

Alternatively you can use the picker via `Intent`. To do so just include the following dependency
 
```
implementation "com.github.dmfs.color-picker:colorpicker-activity:1.1"
```

and call the dialog like:
```java
		ColorPickerActivity.start(context, CODE_PICK_COLOR);
```
This will call an intent with the action `org.openintents.action.PICK_COLOR` allowing the user to use his preferred color picker (if it supports the Open Intent).

In `onActivityResult(int, int, Intent)` you can get the picked color like this:
```java
		@Override
		protected void onActivityResult(int code, int result, Intent data)
		{
			if (result == RESULT_OK && code == CODE_PICK_COLOR)
			{
				Integer color = ColorPickerActivity.getColor(data);
			}
		}
```

## Screen shots

![](https://raw.github.com/dmfs/color-picker/master/screenshots/actionshot.png "Action shot")


## TODO

* fix landscape mode on smaller screens
* fix selected/focused backgrounds
* allow to customize color shapes
* allow to preselect a specific color
* add support for other fragment types, like hex input or color wheels

## License

Copyright (c) dmfs GmbH 2017, licensed under Apache 2 (see `LICENSE`).
