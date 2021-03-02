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
package griffon.plugins.validation.constraints;

import griffon.annotations.core.Nonnull;
import griffon.plugins.validation.Property;

import java.util.Map;

/**
 * Evaluates and returns constraints.
 *
 * @author Graeme Rocher (Grails 2.0)
 */
public interface ConstraintsEvaluator {
    String PROPERTY_NAME = "CONSTRAINTS";

    /**
     * Evaluate constraints for the given class
     *
     * @param cls The class to evaluate constraints for
     * @return A map of constrained properties
     */
    @Nonnull
    Map<String, ConstrainedProperty> evaluate(@Nonnull Class<?> cls);

    /**
     * Evaluate constraints for the given object and properties
     *
     * @param object     The object
     * @param properties The validatable class properties
     * @return A map of constraints
     */
    @Nonnull
    Map<String, ConstrainedProperty> evaluate(@Nonnull Object object, @Nonnull Property[] properties);

    /**
     * Evaluate constraints for the given Class and properties
     *
     * @param cls        The object
     * @param properties The validatable class properties
     * @return A map of constraints
     */
    @Nonnull
    Map<String, ConstrainedProperty> evaluate(@Nonnull Class<?> cls, @Nonnull Property[] properties);
}
