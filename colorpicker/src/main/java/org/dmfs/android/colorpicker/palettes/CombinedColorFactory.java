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

package org.dmfs.android.colorpicker.palettes;

/**
 * A Factory that combines multiple factories into one, by concatenation of the palettes.
 *
 * @author Marten Gajda
 */
public final class CombinedColorFactory implements ColorFactory
{
    private final ColorFactory[] mFactories;


    public CombinedColorFactory(ColorFactory... factories)
    {
        mFactories = factories;
    }


    @Override
    public int colorAt(int index, int count)
    {
        int factoryCount = mFactories.length;
        return mFactories[(index * factoryCount) / count].colorAt(index % (count / factoryCount), count / factoryCount);
    }

}
