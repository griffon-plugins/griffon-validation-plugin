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
package griffon.plugins.validation;

import griffon.plugins.validation.constraints.ConstraintDef;
import org.codehaus.griffon.runtime.validation.AbstractValidateable;

import java.util.List;
import java.util.Map;

import static griffon.plugins.validation.constraints.Constraints.blank;
import static griffon.plugins.validation.constraints.Constraints.list;
import static griffon.plugins.validation.constraints.Constraints.map;
import static griffon.plugins.validation.constraints.Constraints.size;

public class Credentials extends AbstractValidateable {
    private String username;
    private String password;

    public static final Map<String, List<ConstraintDef>> CONSTRAINTS = map()
        .e("username", list(blank(false), size(3, 10)))
        .e("password", list(blank(false), size(8, 10)));

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
