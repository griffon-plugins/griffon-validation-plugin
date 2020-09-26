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

import griffon.core.injection.Module;
import griffon.plugins.validation.ErrorMessagesResolver;
import griffon.plugins.validation.constraints.ConstrainedPropertyAssembler;
import griffon.plugins.validation.constraints.ConstraintsEvaluator;
import griffon.plugins.validation.constraints.ConstraintsValidator;
import org.codehaus.griffon.runtime.core.injection.AbstractModule;
import org.codehaus.griffon.runtime.validation.constraints.DefaultConstrainedPropertyAssembler;
import org.codehaus.griffon.runtime.validation.constraints.DefaultConstraintsEvaluator;
import org.codehaus.griffon.runtime.validation.constraints.DefaultConstraintsValidator;
import org.kordamp.jipsy.ServiceProviderFor;

import javax.inject.Named;

/**
 * @author Andres Almiray
 */
@Named("validation")
@ServiceProviderFor(Module.class)
public class ValidationModule extends AbstractModule {
    @Override
    protected void doConfigure() {
        // tag::bindings[]
        bind(ConstraintsEvaluator.class)
            .to(DefaultConstraintsEvaluator.class)
            .asSingleton();

        bind(ConstraintsValidator.class)
            .to(DefaultConstraintsValidator.class)
            .asSingleton();

        bind(ConstrainedPropertyAssembler.class)
            .to(DefaultConstrainedPropertyAssembler.class);

        bind(ErrorMessagesResolver.class)
            .to(DefaultErrorMessagesResolver.class)
            .asSingleton();
        // end::bindings[]
    }
}
