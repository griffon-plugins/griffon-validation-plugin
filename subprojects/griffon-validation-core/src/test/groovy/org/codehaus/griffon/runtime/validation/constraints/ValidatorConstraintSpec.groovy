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
package org.codehaus.griffon.runtime.validation.constraints

import griffon.annotations.core.Nonnull
import griffon.annotations.core.Nullable
import griffon.plugins.validation.Errors
import griffon.plugins.validation.constraints.Constraint
import griffon.plugins.validation.constraints.ObjectValidator
import griffon.plugins.validation.constraints.PropertyValidator
import griffon.test.core.GriffonUnitRule
import org.codehaus.griffon.runtime.validation.DefaultErrors
import org.junit.Rule
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class ValidatorConstraintSpec extends Specification {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'error')
    }

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    void "ValidatorConstraint supports '#type' = #support"() {
        given:
        Constraint constraint = new ValidatorConstraint()

        expect:
        constraint.supports(type) == support

        where:
        type    | support
        Object  | true
        String  | true
        GString | true
    }

    void "Can set '#parameter' as parameter on ValidatorConstraint"() {
        given:
        Constraint constraint = new ValidatorConstraint()
        constraint.parameter = parameter

        expect:
        constraint.parameter == parameter

        where:
        parameter << [
            { val -> } as PropertyValidator,
            { val, obj -> } as ObjectValidator,
        ]
    }

    void "Invalid parameter '#parameter' throws exception"() {
        given:
        Constraint constraint = new ValidatorConstraint()

        when:
        constraint.parameter = parameter

        then:
        thrown(IllegalStateException)

        where:
        parameter << [1, new Object(), "true"]
    }

    void "Validate ValidatorConstraint with parameter '#parameter' and value '#propertyValue' yields #success"() {
        given:
        Bean instance = new Bean()
        Constraint constraint = new ValidatorConstraint()
        constraint.parameter = parameter
        constraint.constraintOwningClass = instance.class
        constraint.constraintPropertyName = 'blankProperty'
        Errors errors = new DefaultErrors(instance.class)

        when:
        constraint.validate(instance, propertyValue, errors)

        then:
        errors.hasErrors() == !success

        where:
        parameter               | propertyValue | success
        simplePropertyValidator | null          | false
        simplePropertyValidator | 'bar'         | false
        simplePropertyValidator | 'foo'         | true
        simpleObjectValidator   | null          | false
        simpleObjectValidator   | 'bar'         | false
        simpleObjectValidator   | 'foo'         | true
    }

    private static PropertyValidator<String> simplePropertyValidator = new PropertyValidator<String>() {
        @Override
        String validate(@Nullable String value) {
            'foo' == value ? '' : 'error'
        }
    }

    private static ObjectValidator<String, Bean> simpleObjectValidator = new ObjectValidator<String, Bean>() {
        @Override
        String validate(@Nullable String value, @Nonnull Bean bean) {
            'foo' == value ? '' : 'error'
        }
    }
}
