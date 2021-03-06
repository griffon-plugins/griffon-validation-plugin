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
class MinSizeConstraintSpec extends Specification {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'error')
    }

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    void "MinSizeConstraint supports '#type' = #support"() {
        given:
        Constraint constraint = new MinSizeConstraint()

        expect:
        constraint.supports(type) == support

        where:
        type                | support
        Object              | false
        String              | true
        List                | true
        new Object[0].class | true
    }

    void "Can set '#parameter' as parameter on MinSizeConstraint"() {
        given:
        Constraint constraint = new MinSizeConstraint()
        constraint.parameter = parameter

        expect:
        constraint.parameter == parameter

        where:
        parameter << [3]
    }

    void "Invalid parameter '#parameter' throws exception"() {
        given:
        Constraint constraint = new MinSizeConstraint()

        when:
        constraint.parameter = parameter

        then:
        thrown(IllegalStateException)

        where:
        parameter << [new Object(), "1..10"]
    }

    void "Validate MinSizeConstraint with parameter '#parameter' and value '#propertyValue' yields #success"() {
        given:
        Bean instance = new Bean()
        Constraint constraint = new MinSizeConstraint()
        constraint.parameter = parameter
        constraint.constraintOwningClass = instance.class
        constraint.constraintPropertyName = propertyName
        Errors errors = new DefaultErrors(instance.class)

        when:
        constraint.validate(instance, propertyValue, errors)

        then:
        errors.hasErrors() == !success
        constraint.minSize == parameter

        where:
        parameter | propertyName         | propertyValue            | success
        3         | 'sizeStringProperty' | '123'                    | true
        3         | 'sizeListProperty'   | [1, 2, 3]                | true
        3         | 'sizeArrayProperty'  | [1, 2, 3] as int[]       | true
        3         | 'sizeStringProperty' | '1234'                   | true
        3         | 'sizeListProperty'   | [1, 2, 3, 4]             | true
        3         | 'sizeArrayProperty'  | [1, 2, 3, 4] as int[]    | true
        3         | 'sizeStringProperty' | '12345'                  | true
        3         | 'sizeListProperty'   | [1, 2, 3, 4, 5]          | true
        3         | 'sizeArrayProperty'  | [1, 2, 3, 4, 5] as int[] | true
        3         | 'sizeStringProperty' | '12'                     | false
        3         | 'sizeListProperty'   | [1, 2]                   | false
        3         | 'sizeArrayProperty'  | [1, 2] as int[]          | false
    }
}
