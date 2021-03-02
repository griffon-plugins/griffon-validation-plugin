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
import griffon.annotations.core.Nullable;
import griffon.core.properties.PropertySource;

import java.util.List;

/**
 * @author Andres Almiray
 */
public interface Errors extends PropertySource {
    String HAS_ERRORS_PROPERTY = "hasErrors";
    String ERROR_COUNT_PROPERTY = "errorCount";

    @Nonnull
    String getObjectName();

    boolean hasErrors();

    boolean hasGlobalErrors();

    boolean hasFieldErrors();

    boolean hasFieldErrors(@Nonnull String field);

    @Nullable
    FieldObjectError getFieldError(@Nonnull String field);

    @Nonnull
    List<FieldObjectError> getFieldErrors(@Nonnull String field);

    int getFieldErrorCount(@Nonnull String field);

    @Nonnull
    List<ObjectError> getGlobalErrors();

    int getGlobalErrorCount();

    @Nullable
    ObjectError getGlobalError();

    int getErrorCount();

    @Nonnull
    List<ObjectError> getAllErrors();

    void addError(@Nonnull ObjectError objectError);

    @Nonnull
    String[] resolveMessageCodes(@Nonnull String code);

    @Nonnull
    String[] resolveMessageCodes(@Nonnull String code, @Nonnull String field, @Nonnull Class<?> fieldType);

    void reject(@Nonnull String code, @Nonnull Object[] args, @Nonnull String defaultMessage);

    void reject(@Nonnull String code, @Nonnull String defaultMessage);

    void rejectField(@Nonnull String field, @Nonnull String code, @Nonnull Object[] args, @Nonnull String defaultMessage);

    void rejectField(@Nonnull String field, @Nonnull String code, @Nonnull String defaultMessage);

    void rejectField(@Nonnull String field, Object rejectedValue, @Nonnull String code, @Nonnull Object[] args, @Nonnull String defaultMessage);

    void rejectField(@Nonnull String field, Object rejectedValue, @Nonnull String code, @Nonnull String defaultMessage);

    void clearGlobalErrors();

    void clearFieldErrors();

    void clearFieldErrors(@Nonnull String field);

    void clearAllErrors();
}
