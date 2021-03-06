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
package org.codehaus.griffon.runtime.validation.constraints

import griffon.plugins.validation.Errors
import griffon.plugins.validation.constraints.Constraint
import griffon.test.core.GriffonUnitRule
import org.codehaus.griffon.runtime.validation.DefaultErrors
import org.junit.Rule
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class BlankConstraintSpec extends Specification {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'error')
    }

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    void "BlankConstraint supports '#type' = #support"() {
        given:
        Constraint constraint = new BlankConstraint()

        expect:
        constraint.supports(type) == support

        where:
        type    | support
        Object  | false
        String  | true
        GString | true
    }

    void "Can set '#parameter' as parameter on BlankConstraint"() {
        given:
        Constraint constraint = new BlankConstraint()
        constraint.parameter = parameter

        expect:
        constraint.parameter == parameter

        where:
        parameter << [true, false]
    }

    void "Invalid parameter '#parameter' throws exception"() {
        given:
        Constraint constraint = new BlankConstraint()

        when:
        constraint.parameter = parameter

        then:
        thrown(IllegalStateException)

        where:
        parameter << [1, new Object(), "true"]
    }

    void "Validate BlankConstraint with parameter '#parameter' and value '#propertyValue' yields #success"() {
        given:
        Bean instance = new Bean()
        Constraint constraint = new BlankConstraint()
        constraint.parameter = parameter
        constraint.constraintOwningClass = instance.class
        constraint.constraintPropertyName = 'blankProperty'
        Errors errors = new DefaultErrors(instance.class)

        when:
        constraint.validate(instance, propertyValue, errors)

        then:
        errors.hasErrors() == !success
        constraint.blank == parameter

        where:
        parameter | propertyValue | success
        false     | null          | true
        false     | ''            | false
        false     | ' '           | false
        false     | 'foo'         | true
        false     | []            | true
        true      | ''            | true
        true      | ' '           | true
    }
}
