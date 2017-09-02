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
package org.codehaus.griffon.runtime.validation.constraints;

import griffon.plugins.validation.Errors;
import griffon.plugins.validation.constraints.ObjectValidator;
import griffon.plugins.validation.constraints.PropertyValidator;

import javax.annotation.Nonnull;

import static griffon.util.GriffonClassUtils.requireState;
import static griffon.util.GriffonNameUtils.isBlank;
import static java.util.Objects.requireNonNull;

/**
 * Implements a validator value constraint.
 *
 * @author Andres Almiray
 */
public class ValidatorConstraint extends AbstractConstraint {
    public static final String VALIDATION_DSL_NAME = "validator";
    public static final String DEFAULT_INVALID_PROPERTY_VALIDATOR_MESSAGE_CODE = "default.invalid.property.validator.message";
    public static final String DEFAULT_INVALID_PROPERTY_VALIDATOR_MESSAGE = "Property [{0}] of class [{1}] with value [{2}] is invalid";
    public static final String DEFAULT_INVALID_OBJECT_VALIDATOR_MESSAGE_CODE = "default.invalid.object.validator.message";
    public static final String DEFAULT_INVALID_OBJECT_VALIDATOR_MESSAGE = "Property [{0}] of class [{1}] with value [{2}] is invalid";

    private PropertyValidator<?> propertyValidator;
    private ObjectValidator<?, ?> objectValidator;

    public PropertyValidator<?> getPropertyValidator() {
        return propertyValidator;
    }

    public ObjectValidator<?, ?> getObjectValidator() {
        return objectValidator;
    }

    @Override
    public boolean supports(@Nonnull Class<?> type) {
        return true;
    }

    @Override
    protected boolean skipNullValues() {
        return false;
    }

    @Override
    protected boolean skipBlankValues() {
        return false;
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public void setParameter(@Nonnull Object constraintParameter) {
        requireNonNull(constraintParameter, "Parameter for constraint [" +
            VALIDATION_DSL_NAME + "] of property [" +
            constraintPropertyName + "] of class [" + constraintOwningClass + "] must not be null");

        requireState(constraintParameter instanceof PropertyValidator || constraintParameter instanceof ObjectValidator,
            "Parameter for constraint [" +
                VALIDATION_DSL_NAME + "] of property [" + constraintPropertyName +
                "] of class [" + constraintOwningClass + "] must be one of [" + PropertyValidator.class.getName() + "," +
                ObjectValidator.class.getName() + "]");

        super.setParameter(constraintParameter);
    }

    @Nonnull
    public String getName() {
        return VALIDATION_DSL_NAME;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void processValidate(@Nonnull Object target, Object propertyValue, @Nonnull Errors errors) {
        String errorCode = DEFAULT_INVALID_PROPERTY_VALIDATOR_MESSAGE_CODE;
        if (constraintParameter instanceof PropertyValidator) {
            errorCode = ((PropertyValidator) constraintParameter).validate(propertyValue);
        } else if (constraintParameter instanceof ObjectValidator) {
            errorCode = ((ObjectValidator) constraintParameter).validate(propertyValue, target);
        }

        if (!isBlank(errorCode)) {
            Object[] args = new Object[]{constraintPropertyName, constraintOwningClass, propertyValue};
            rejectValue(target, errors, errorCode,
                VALIDATION_DSL_NAME + INVALID_SUFFIX, args);
        }
    }
}
