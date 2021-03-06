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
package griffon.plugins.validation;

import griffon.annotations.core.Nonnull;
import griffon.plugins.validation.constraints.ConstraintDef;
import griffon.plugins.validation.constraints.PropertyConstraintDef;
import org.codehaus.griffon.runtime.validation.AbstractValidateable;

import java.util.Collection;
import java.util.List;

import static griffon.plugins.validation.constraints.Constraints.blank;
import static griffon.plugins.validation.constraints.Constraints.list;
import static griffon.plugins.validation.constraints.Constraints.set;
import static griffon.plugins.validation.constraints.Constraints.size;

public class Credentials2 extends AbstractValidateable {
    private String username;
    private String password;

    private static PropertyConstraintDef username(@Nonnull List<ConstraintDef> constraints) {
        return new PropertyConstraintDef("username", constraints);
    }

    private static PropertyConstraintDef password(@Nonnull List<ConstraintDef> constraints) {
        return new PropertyConstraintDef("password", constraints);
    }

    public static final Collection<PropertyConstraintDef> CONSTRAINTS = set()
        .e(username(list(blank(false), size(3, 10))))
        .e(password(list(blank(false), size(8, 10))));

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
