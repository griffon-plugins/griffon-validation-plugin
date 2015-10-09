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

import griffon.core.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.Objects.requireNonNull;

/**
 * @author Andres Almiray
 */
public class ConstraintUtils {
    private static final Logger LOG = LoggerFactory.getLogger(ConstraintUtils.class);
    private static final String KEY_DEFAULT_CONSTRAINTS = "griffon.validation.default.constraints";

    private static Map<String, Object> defaultConstraintsMap;
    private static volatile boolean defaultConstraintsInitialized;
    private static final ReentrantReadWriteLock DEFAULT_CONSTRAINTS_LOCK = new ReentrantReadWriteLock();

    @Nonnull
    public static Map<String, List<ConstraintDef>> getDefaultConstraints(@Nonnull Configuration configuration) {
        requireNonNull(configuration, "Argument 'configuration' must not be null");
        return configuration.get(KEY_DEFAULT_CONSTRAINTS, Collections.<String, List<ConstraintDef>>emptyMap());
    }
}
