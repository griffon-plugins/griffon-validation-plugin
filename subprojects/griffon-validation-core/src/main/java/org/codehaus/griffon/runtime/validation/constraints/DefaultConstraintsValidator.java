/*
 * SPDX-License-Identifier: Apache-2.0
 *
 * Copyright 2014-2021 The author and/or original authors.
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
package org.codehaus.griffon.runtime.validation.constraints;

import griffon.annotations.core.Nonnull;
import griffon.annotations.core.Nullable;
import griffon.plugins.validation.Validateable;
import griffon.plugins.validation.constraints.ConstrainedProperty;
import griffon.plugins.validation.constraints.ConstraintsValidator;
import griffon.util.GriffonClassUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static griffon.util.GriffonNameUtils.requireNonBlank;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class DefaultConstraintsValidator implements ConstraintsValidator {
    private static final String ERROR_VALIDATEABLE_NULL = "Argument 'validateable' must not be null";

    @Override
    public boolean evaluate(@Nonnull Validateable validateable, @Nonnull List<String> properties) {
        requireNonNull(properties, "Argument 'properties' must not be null");
        return evaluate(validateable, properties.toArray(new String[properties.size()]));
    }

    @Override
    public boolean evaluate(@Nonnull Validateable validateable, @Nullable String... properties) {
        requireNonNull(validateable, ERROR_VALIDATEABLE_NULL);
        Map<String, ConstrainedProperty> constrainedProperties = new LinkedHashMap<>();

        if (properties == null || properties.length == 0) {
            constrainedProperties.putAll(validateable.constrainedProperties());
        } else {
            for (String property : properties) {
                constrainedProperties.put(property, validateable.constrainedProperties().get(property));
            }
        }

        for (Map.Entry<String, ConstrainedProperty> entry : constrainedProperties.entrySet()) {
            ConstrainedProperty constrainedProperty = entry.getValue();
            constrainedProperty.validate(validateable, getPropertyValue(validateable, entry.getKey()), validateable.getErrors());
        }

        return !validateable.getErrors().hasErrors();
    }

    @Nullable
    protected Object getPropertyValue(@Nonnull Validateable validateable, @Nonnull String propertyName) {
        requireNonNull(validateable, ERROR_VALIDATEABLE_NULL);
        requireNonBlank(propertyName, "Argument 'propertyName' must not be blank");
        return GriffonClassUtils.getPropertyValue(validateable, propertyName);
    }
}
