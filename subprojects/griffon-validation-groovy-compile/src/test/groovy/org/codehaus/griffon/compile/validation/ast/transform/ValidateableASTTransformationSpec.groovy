/*
 * Copyright 2014-2015 the original author or authors.
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
package org.codehaus.griffon.compile.validation.ast.transform

import griffon.plugins.validation.Validateable
import spock.lang.Specification

import java.lang.reflect.Method

class ValidateableASTTransformationSpec extends Specification {
    def 'ValidateableAwareASTTransformation is applied to a bean via @Validateable'() {
        given:
        GroovyShell shell = new GroovyShell()

        when:
        def bean = shell.evaluate('''
        import griffon.transform.Validateable

        @Validateable
        class Employee {
            Long id
            String name
            String lastname

            static final CONSTRAINTS = {
                name(blank: false)
                lastname(blank: false)
            }

            String toString() {
                "Employee[id=$id, name=$name, lastname=$lastname]"
            }
        }
        new Employee()
        ''')

        then:
        bean instanceof Validateable
    }

    def 'ValidateableAwareASTTransformation is applied to a bean via @Validateable (2)'() {
        given:
        GroovyShell shell = new GroovyShell()

        when:
        def bean = shell.evaluate('''
        import griffon.transform.Validateable

        class Person {
            Long id
        }

        @Validateable
        class Employee extends Person {
            String name
            String lastname

            static final CONSTRAINTS = {
                name(blank: false)
                lastname(blank: false)
            }

            String toString() {
                "Employee[id=$id, name=$name, lastname=$lastname]"
            }
        }
        new Employee()
        ''')

        then:
        bean instanceof Validateable
        Validateable.methods.every { Method target ->
            bean.class.declaredMethods.find { Method candidate ->
                candidate.name == target.name &&
                    candidate.returnType == target.returnType &&
                    candidate.parameterTypes == target.parameterTypes &&
                    candidate.exceptionTypes == target.exceptionTypes
            }
        }
    }
}
