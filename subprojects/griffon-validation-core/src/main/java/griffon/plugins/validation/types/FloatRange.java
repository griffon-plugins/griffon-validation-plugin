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
package griffon.plugins.validation.types;

import griffon.annotations.core.Nonnull;

import java.util.Iterator;

/**
 * @author Andres Almiray
 */
public class FloatRange extends Range<Float> {
    public FloatRange(@Nonnull Float from, @Nonnull Float to) {
        super(from, to);
    }

    @Override
    public boolean contains(Float value) {
        float from = isReverse() ? getTo() : getFrom();
        float to = isReverse() ? getFrom() : getTo();
        return value != null && from <= value && value <= to;
    }

    @Override
    public Range<Float> reverse() {
        return new FloatRange(getTo(), getFrom());
    }

    @Override
    public boolean isReverse() {
        return getTo() <= getFrom();
    }

    @Override
    public Iterator<Float> iterator() {
        return new Iterator<Float>() {
            private float cursor = getFrom();

            @Override
            public boolean hasNext() {
                return isReverse() ? cursor > getTo() - 1.0f : cursor < getTo() + 1.0f;
            }

            @Override
            public Float next() {
                Float value = cursor;
                cursor += (isReverse() ? -1.0f : 1.0f);
                return value;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
