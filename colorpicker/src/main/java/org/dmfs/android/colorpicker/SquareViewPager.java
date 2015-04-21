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
