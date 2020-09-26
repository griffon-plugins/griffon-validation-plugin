/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2014-2020 The author and/or original authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package griffon.plugins.validation;

import griffon.annotations.core.Nonnull;
import griffon.annotations.core.Nullable;

/**
 * @author Andres Almiray
 */
public interface Property {
    /**
     * Returns the name of the property
     *
     * @return The property name
     */
    @Nonnull
    String getName();

    /**
     * Returns the type for the class
     *
     * @return The property type
     */
    @Nonnull
    Class<?> getType();

    @Nullable
    Object getValue(@Nonnull Object owner);

    void setValue(@Nonnull Object owner, @Nullable Object value);

    @Nonnull
    Class<?> getOwningType();
}
