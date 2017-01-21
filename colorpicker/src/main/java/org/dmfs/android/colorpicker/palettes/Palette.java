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

import android.os.Parcelable;


/**
 * Interface of a color palette.
 *
 * @author Marten Gajda
 */
public interface Palette extends Parcelable
{
    /**
     * Returns the display name of the palette.
     *
     * @return The name of the palette.
     */
    String name();

    /**
     * Returns the id of the palette.
     *
     * @return An Id of the palette.
     */
    String id();

    /**
     * Get the number of colors in this palette.
     *
     * @return The number of colors.
     */
    int numberOfColors();

    /**
     * Get the color at the specified index.
     *
     * @param index
     *         The index of the color.
     *
     * @return The color at <code>index</code>
     */
    int colorAt(int index);

    /**
     * Get the the name of the color at the specified index.
     *
     * @param index
     *         The index of the color.
     *
     * @return The name of the color at <code>index</code> or <code>null</code> if the color has no name.
     */
    String nameOfColorAt(int index);

    /**
     * Get the number of columns to use when creating the layout for this palette.
     *
     * @return The number of columns.
     */
    int numberOfColumns();
}
