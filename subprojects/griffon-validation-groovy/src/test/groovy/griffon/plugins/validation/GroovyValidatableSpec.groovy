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
package griffon.plugins.validation

import griffon.core.injection.Module
import griffon.test.core.GriffonUnitRule
import org.codehaus.griffon.runtime.core.injection.AbstractModule
import org.junit.Rule
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject

@Unroll
class GroovyValidatableSpec extends Specification {
    static {
        System.setProperty('org.slf4j.simpleLogger.defaultLogLevel', 'trace')
    }

    @Rule
    public final GriffonUnitRule griffon = new GriffonUnitRule()

    @Inject
    private GroovyCredentials credentials

    void "Credentials with username='#username' and password='#password' validate to #result (implicit properties)"() {
        when:
        credentials.username = username
        credentials.password = password
        credentials.validate()

        then:
        result == credentials.validate()

        where:
        username | password || result
        null     | null     || true
        ''       | 'foo'    || false
        ' '      | 'foo'    || false
        'foo'    | ''       || false
        'foo'    | ' '      || false
    }

    void "Credentials with username='#username' and password='#password' validate to #result (explicit properties)"() {
        when:
        credentials.username = username
        credentials.password = password
        credentials.validate()

        then:
        result == credentials.validate('username', 'password')

        where:
        username | password || result
        null     | null     || true
        ''       | 'foo'    || false
        ' '      | 'foo'    || false
        'foo'    | ''       || false
        'foo'    | ' '      || false
    }

    void "Credentials with username='#username' and password='#password' validate to #result (properties list)"() {
        when:
        credentials.username = username
        credentials.password = password
        credentials.validate()

        then:
        result == credentials.validate(['username', 'password'])

        where:
        username | password || result
        null     | null     || true
        ''       | 'foo'    || false
        ' '      | 'foo'    || false
        'foo'    | ''       || false
        'foo'    | ' '      || false
    }

    List<Module> moduleOverrides() {
        [
            new AbstractModule() {
                @Override
                protected void doConfigure() {
                    bind(GroovyCredentials)
                }
            }
        ]
    }
}
