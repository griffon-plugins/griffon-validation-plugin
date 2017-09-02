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
package griffon.types;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Iterator;

import static griffon.util.GriffonClassUtils.invokeExactStaticMethod;

/**
 * @author Andres Almiray
 */
@ThreadSafe
public class EnumRange<E extends Enum> extends Range<E> {
    public EnumRange(@Nonnull E from, @Nonnull E to) {
        super(from, to);
    }

    @Override
    public boolean contains(E value) {
        int from = (isReverse() ? getTo() : getFrom()).ordinal();
        int to = (isReverse() ? getFrom() : getTo()).ordinal();
        return value != null && from <= value.ordinal() && value.ordinal() <= to;
    }

    @Override
    public Range<E> reverse() {
        return new EnumRange<>(getTo(), getFrom());
    }

    @Override
    public boolean isReverse() {
        return getTo().ordinal() <= getFrom().ordinal();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = getFrom().ordinal();

            @Override
            public boolean hasNext() {
                return isReverse() ? cursor > getTo().ordinal() - 1 : cursor < getTo().ordinal() + 1;
            }

            @Override
            @SuppressWarnings("unchecked")
            public E next() {
                E[] values = (E[]) invokeExactStaticMethod(getType(), "values");
                return values[isReverse() ? cursor-- : cursor++];
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
