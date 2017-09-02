/*
 * Copyright 2014-2017 the original author or authors.
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
package org.codehaus.griffon.runtime.validation.constraints

import griffon.core.test.GriffonUnitRule
import griffon.plugins.validation.Errors
import griffon.plugins.validation.constraints.Constraint
import org.codehaus.griffon.runtime.validation.DefaultErrors
import org.junit.Rule
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class DateConstraintSpec extends Specification {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'error')
    }

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    void "DateConstraint supports '#type' = #support"() {
        given:
        Constraint constraint = new DateConstraint()

        expect:
        constraint.supports(type) == support

        where:
        type    | support
        Object  | false
        String  | true
        GString | true
    }

    void "Can set '#parameter' as parameter on DateConstraint"() {
        given:
        Constraint constraint = new DateConstraint()
        constraint.parameter = parameter

        expect:
        constraint.parameter == parameter

        where:
        parameter << ["yyyy-MM-dd"]
    }

    void "Invalid parameter '#parameter' throws exception"() {
        given:
        Constraint constraint = new DateConstraint()

        when:
        constraint.parameter = parameter

        then:
        thrown(IllegalStateException)

        where:
        parameter << [1, new Object(), "garbage"]
    }

    void "Validate DateConstraint with parameter '#parameter' and value '#propertyValue' yields #success"() {
        given:
        Bean instance = new Bean()
        Constraint constraint = new DateConstraint()
        constraint.parameter = parameter
        constraint.constraintOwningClass = instance.class
        constraint.constraintPropertyName = 'blankProperty'
        Errors errors = new DefaultErrors(instance.class)

        when:
        constraint.validate(instance, propertyValue, errors)

        then:
        errors.hasErrors() == !success

        where:
        parameter    | propertyValue | success
        'yyyy-MM-dd' | '1970-01-01'  | true
        'yyyy-MM-dd' | 'A1970-01-01' | false
    }
}
