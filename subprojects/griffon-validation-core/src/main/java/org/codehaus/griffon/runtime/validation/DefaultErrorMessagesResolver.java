/*
 * Copyright $today.year the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.griffon.runtime.validation;

import griffon.core.i18n.MessageSource;
import griffon.core.i18n.NoSuchMessageException;
import griffon.plugins.validation.ErrorMessagesResolver;
import griffon.plugins.validation.Errors;
import griffon.plugins.validation.FieldObjectError;
import griffon.plugins.validation.ObjectError;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class DefaultErrorMessagesResolver implements ErrorMessagesResolver {
    @Inject @Named("applicationMessageSource")
    private MessageSource messageSource;

    @Override
    @Nonnull
    public Map<String, Collection<String>> resolveAllMessages(@Nonnull Errors errors) {
        requireNonNull(errors, "Argument 'errors' must not be null");

        Map<String, Collection<String>> messages = new LinkedHashMap<>();

        OUTER:
        for (ObjectError error : errors.getAllErrors()) {
            String key = GLOBAL;
            if (error instanceof FieldObjectError) {
                key = ((FieldObjectError) error).getFieldName();
            }

            for (String code : error.getCodes()) {
                String message = null;
                try {
                    message = messageSource.getMessage(code, error.getArguments()).trim();
                } catch (NoSuchMessageException e) {
                    // try next code
                    continue;
                }
                if (!message.contains(code)) {
                    // message was successfully resolved
                    addMessage(key, message, messages);
                    continue OUTER;
                }
            }
            addMessage(key, messageSource.formatMessage(error.getDefaultMessage(), error.getArguments()), messages);
        }

        return messages;
    }

    private static void addMessage(@Nonnull String key, @Nonnull String message, @Nonnull Map<String, Collection<String>> map) {
        Collection<String> strings = map.get(key);
        if (strings == null) {
            strings = new ArrayList<>();
            map.put(key, strings);
        }
        strings.add(message);
    }
}
