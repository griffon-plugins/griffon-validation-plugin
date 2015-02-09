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
package griffon.plugins.validation.constraints;

import griffon.plugins.validation.Errors;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Marker interface for vetoing constraint.
 * <p/>
 * Vetoing constraints are those which might return 'true' from validateWithVetoing method to prevent any additional
 * validation of the property. These constraints are proceeded before any other constraints, and validation continues
 * only if no one of vetoing constraint hadn't vetoed.
 *
 * @author Sergey Nebolsin (<a href="mailto:nebolsin@gmail.com"/>)
 */
public interface VetoingConstraint extends Constraint {
    boolean validateWithVetoing(@Nonnull Object target, @Nullable Object propertyValue, @Nonnull Errors errors);
}
