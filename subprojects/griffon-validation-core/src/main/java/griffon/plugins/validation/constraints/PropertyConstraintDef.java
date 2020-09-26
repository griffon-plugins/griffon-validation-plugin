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
package griffon.plugins.validation.constraints;

import griffon.annotations.core.Nonnull;

import java.util.ArrayList;
import java.util.List;

import static griffon.util.GriffonNameUtils.requireNonBlank;
import static java.util.Collections.unmodifiableList;
import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public final class PropertyConstraintDef {
    private final String name;
    private final List<ConstraintDef> constraints = new ArrayList<>();

    public PropertyConstraintDef(@Nonnull String name, @Nonnull List<ConstraintDef> constraints) {
        this.name = requireNonBlank(name, "Argument 'name' must not be blank");
        this.constraints.addAll(requireNonNull(constraints, "Argument 'constraints' mut not be null"));
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public List<ConstraintDef> getConstraints() {
        return unmodifiableList(constraints);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropertyConstraintDef that = (PropertyConstraintDef) o;

        return name.equals(that.name) && constraints.equals(that.constraints);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + constraints.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PropertyConstraintDef{");
        sb.append("name='").append(name).append('\'');
        sb.append(", constraints=").append(constraints);
        sb.append('}');
        return sb.toString();
    }
}
