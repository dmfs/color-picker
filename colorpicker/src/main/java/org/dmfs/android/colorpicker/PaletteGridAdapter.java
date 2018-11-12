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

package org.dmfs.android.colorpicker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.ArcShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.dmfs.android.colorpicker.palettes.Palette;


/**
 * Adapter for a single palette.
 *
 * @author Marten Gajda
 */
public final class PaletteGridAdapter extends BaseAdapter
{

    /**
     * The palette to adapt.
     */
    private final Palette mPalette;

    /**
     * A {@link LayoutInflater} to use.
     */
    private final LayoutInflater mLayoutInflater;


    public PaletteGridAdapter(@NonNull Context context, @NonNull Palette palette)
    {
        mPalette = palette;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount()
    {
        return mPalette.numberOfColors();
    }


    @NonNull
    @Override
    public Object getItem(int position)
    {
        return mPalette.colorAt(position);
    }


    @Override
    public long getItemId(int position)
    {
        return position;
    }


    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            view = mLayoutInflater.inflate(R.layout.org_dmfs_colorpickerdialog_palette_field, null);
        }

        // set the background to a colored circle
        // TODO: allow to customize the shape
        Shape shape = new ArcShape(0, 360);
        ShapeDrawable bg = new ShapeDrawable(shape);
        bg.getPaint().setColor(mPalette.colorAt(position));

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
        return mPalette.numberOfColumns();
    }
}
