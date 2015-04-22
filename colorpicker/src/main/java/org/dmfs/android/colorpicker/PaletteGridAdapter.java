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

import org.dmfs.android.colorpicker.palettes.AbstractPalette;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


/**
 * Adapter for a single palette.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class PaletteGridAdapter extends BaseAdapter
{

	/**
	 * The palette to adapt.
	 */
	private final AbstractPalette mPalette;

	/**
	 * A {@link LayoutInflater} to use.
	 */
	private final LayoutInflater mLayoutInflater;


	public PaletteGridAdapter(Context context, AbstractPalette palette)
	{
		mPalette = palette;
		mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return mPalette.getNumberOfColors();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position)
	{
		return mPalette.getColor(position);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView;
		if (view == null)
		{
			/*
			 * TODO: build the layout programmatically
			 */
			view = mLayoutInflater.inflate(R.layout.org_dmfs_colorpickerdialog_palette_field, null);
		}

		// set the background to a colored circle
		// TODO: allow to customize the shape
		Shape shape = new ArcShape(0, 360);
		ShapeDrawable bg = new ShapeDrawable(shape);
		bg.getPaint().setColor(mPalette.getColor(position));

		if (android.os.Build.VERSION.SDK_INT < 16)
		{
			view.setBackgroundDrawable(bg);
		}
		else
		{
			view.setBackground(bg);
		}

		return view;

	}


	public int getNumColumns()
	{
		return mPalette.getNumberOfColumns();
	}

}
