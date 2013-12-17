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

package org.dmfs.android.colorpicker;

import org.dmfs.android.colorpicker.palettes.AbstractPalette;

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
