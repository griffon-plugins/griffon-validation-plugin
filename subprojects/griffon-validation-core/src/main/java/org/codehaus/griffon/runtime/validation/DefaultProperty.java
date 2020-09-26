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
package org.codehaus.griffon.runtime.validation;

import griffon.annotations.core.Nonnull;
import griffon.annotations.core.Nullable;
import griffon.plugins.validation.Property;

import java.beans.PropertyDescriptor;

import static griffon.util.GriffonClassUtils.getPropertyValue;
import static griffon.util.GriffonClassUtils.setPropertyValue;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class DefaultProperty implements Property {
    private final PropertyDescriptor propertyDescriptor;
    private final Class<?> owningType;

    public DefaultProperty(@Nonnull PropertyDescriptor propertyDescriptor, @Nonnull Class<?> owningType) {
        this.propertyDescriptor = requireNonNull(propertyDescriptor, "Argument 'propertyDescriptor' must not be null");
        this.owningType = requireNonNull(owningType, "Argument 'owningType' must not be null");
    }

    @Nonnull
    public String getName() {
        return propertyDescriptor.getName();
    }

    @Nonnull
    public Class<?> getType() {
        return propertyDescriptor.getPropertyType();
    }

    @Nullable
    public Object getValue(@Nonnull Object owner) {
        return getPropertyValue(owner, getName());
    }

    public void setValue(@Nonnull Object owner, @Nullable Object value) {
        setPropertyValue(owner, propertyDescriptor.getName(), value);
    }

    @Nonnull
    @Override
    public Class<?> getOwningType() {
        return owningType;
    }
}
