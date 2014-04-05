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

import org.dmfs.android.view.ViewPager;

import android.content.Context;
import android.util.AttributeSet;


/**
 * A ViewPager that shows the content in a square area.
 * 
 * @author Marten Gajda <marten@dmfs.org>
 */
public class SquareViewPager extends ViewPager
{
	public SquareViewPager(Context context)
	{
		super(context);
	}


	public SquareViewPager(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}


	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);

		if (getChildCount() <= 1)
		{
			// only the pagetitlestrip is present, nothing to do.
			return;
		}

		int titleStripHeight = getChildAt(0).getMeasuredHeight();

		int width = getMeasuredWidth();

		if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST)
		{
			widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
		}

		// the new height is the height of the PagerTitleStrip + the width of the content.
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(titleStripHeight + width, MeasureSpec.EXACTLY);

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
