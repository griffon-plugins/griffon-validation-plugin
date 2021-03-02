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
package griffon.plugins.validation.types;

import griffon.annotations.core.Nonnull;

import java.util.Iterator;

/**
 * @author Andres Almiray
 */
public class CharRange extends Range<Character> {
    public CharRange(@Nonnull Character from, @Nonnull Character to) {
        super(from, to);
    }

    @Override
    public boolean contains(Character value) {
        char from = isReverse() ? getTo() : getFrom();
        char to = isReverse() ? getFrom() : getTo();
        return value != null && from <= value && value <= to;
    }

    @Override
    public Range<Character> reverse() {
        return new CharRange(getTo(), getFrom());
    }

    @Override
    public boolean isReverse() {
        return getTo() < getFrom();
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<Character>() {
            private char cursor = getFrom();

            @Override
            public boolean hasNext() {
                return isReverse() ? cursor > getTo() - 1 : cursor < getTo() + 1;
            }

            @Override
            public Character next() {
                return isReverse() ? cursor-- : cursor++;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
