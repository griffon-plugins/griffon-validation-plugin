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
import griffon.plugins.validation.Errors;
import griffon.plugins.validation.Validateable;
import griffon.plugins.validation.constraints.ConstrainedProperty;
import griffon.plugins.validation.constraints.ConstraintsEvaluator;
import griffon.plugins.validation.constraints.ConstraintsValidator;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Andres Almiray
 */
public abstract class AbstractValidateable implements Validateable {
    @Inject
    protected ConstraintsValidator constraintsValidator;

    @Inject
    protected ConstraintsEvaluator constraintsEvaluator;

    private final Errors errors = new DefaultErrors(getClass());

    private final Map<String, ConstrainedProperty> constrainedProperties = new LinkedHashMap<>();

    @PostConstruct
    private void initialize() {
        if (constraintsEvaluator != null) {
            constrainedProperties.putAll(constraintsEvaluator.evaluate(getClass()));
        }
    }

    public boolean validate(String... properties) {
        return constraintsValidator == null || constraintsValidator.evaluate(this, properties);
    }

    public boolean validate(@Nonnull List<String> properties) {
        return constraintsValidator == null || constraintsValidator.evaluate(this, properties);
    }

    @Nonnull
    public Errors getErrors() {
        return errors;
    }

    @Nonnull
    public Map<String, ConstrainedProperty> constrainedProperties() {
        return constrainedProperties;
    }
}
