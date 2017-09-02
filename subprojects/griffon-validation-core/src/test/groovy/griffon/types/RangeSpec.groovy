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
package griffon.types

import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class RangeSpec extends Specification {
    void "Verify range #range1"() {
        expect:
        range1.from == from
        range1.to == to
        range1 == range1
        range1 == range2
        range1 != range3
        range1 != range4
        range1 != range5
        range1 != range6
        range1.hashCode() == range2.hashCode()
        range1.hashCode() != range3.hashCode()
        range1.type == type
        range1.toString() == literal
        !range1.contains(e1)
        range1.contains(e2)
        !range1.contains(e3)
        !range1.contains(null)
        range1.join(', ') == content

        where:
        range1 << [new IntRange(5, 10),
                   new LongRange(5L, 10L),
                   new CharRange('e' as char, 'j' as char),
                   new EnumRange(Char.E, Char.J),
                   new FloatRange(5f, 10f),
                   new DoubleRange(5d, 10d)]
        from << [5, 5L, 'e' as char, Char.E, 5f, 5d]
        to << [10, 10L, 'j' as char, Char.J, 10f, 10d]
        type << [Integer, Long, Character, Char, Float, Double]
        range2 << [new IntRange(5, 10),
                   new LongRange(5L, 10L),
                   new CharRange('e' as char, 'j' as char),
                   new EnumRange(Char.E, Char.J),
                   new FloatRange(5f, 10f),
                   new DoubleRange(5d, 10d)]
        range3 << [new IntRange(6, 10),
                   new LongRange(6L, 10L),
                   new CharRange('f' as char, 'j' as char),
                   new EnumRange(Char.F, Char.J),
                   new FloatRange(6f, 10f),
                   new DoubleRange(6d, 10d)]
        range4 << [new IntRange(5, 11),
                   new LongRange(5L, 11L),
                   new CharRange('e' as char, 'k' as char),
                   new EnumRange(Char.E, Char.K),
                   new FloatRange(5f, 11f),
                   new DoubleRange(5d, 11d)]
        range5 << [new LongRange(6L, 10L),
                   new CharRange('f' as char, 'j' as char),
                   new IntRange(6, 10),
                   new EnumRange(Char.F, Char.K),
                   new DoubleRange(6d, 10d),
                   new FloatRange(6f, 10f)]
        range6 << [null, null, null, null, null, null]
        literal << ['[5..10]', '[5..10]', '[e..j]', '[E..J]', '[5.0..10.0]', '[5.0..10.0]']
        e1 << [3, 3L, 'c' as char, Char.C, 3f, 3d]
        e2 << [8, 8L, 'g' as char, Char.G, 8f, 8d]
        e3 << [12, 12L, 'k' as char, Char.K, 12f, 12d]
        content << [
            '5, 6, 7, 8, 9, 10',
            '5, 6, 7, 8, 9, 10',
            'e, f, g, h, i, j',
            'E, F, G, H, I, J',
            '5.0, 6.0, 7.0, 8.0, 9.0, 10.0',
            '5.0, 6.0, 7.0, 8.0, 9.0, 10.0']
    }

    void "Verify reverse range #range1"() {
        expect:
        range1.from == from
        range1.to == to
        range1 == range1
        range1 == range2
        range1 != range3
        range1 != range4
        range1 != range10
        range1 != range9
        range1.hashCode() == range2.hashCode()
        range1.hashCode() != range3.hashCode()
        range1.type == type
        range1.toString() == literal
        !range1.contains(e1)
        range1.contains(e2)
        !range1.contains(e3)
        !range1.contains(null)
        range1.join(', ') == content

        where:
        range1 << [new IntRange(10, 5),
                   new LongRange(10L, 5L),
                   new CharRange('j' as char, 'e' as char),
                   new EnumRange(Char.J, Char.E),
                   new FloatRange(10f, 5f),
                   new DoubleRange(10d, 5d)]
        from << [10, 10L, 'j' as char, Char.J, 10f, 10d]
        to << [5, 5L, 'e' as char, Char.E, 5f, 5d]
        type << [Integer, Long, Character, Char, Float, Double]
        range2 << [new IntRange(10, 5),
                   new LongRange(10L, 5L),
                   new CharRange('j' as char, 'e' as char),
                   new EnumRange(Char.J, Char.E),
                   new FloatRange(10f, 5f),
                   new DoubleRange(10d, 5d)]
        range3 << [new IntRange(9, 5),
                   new LongRange(9L, 5L),
                   new CharRange('i' as char, 'e' as char),
                   new EnumRange(Char.I, Char.E),
                   new FloatRange(9f, 5f),
                   new DoubleRange(9d, 5d)]
        range4 << [new IntRange(10, 4),
                   new LongRange(10L, 4L),
                   new CharRange('j' as char, 'd' as char),
                   new EnumRange(Char.J, Char.D),
                   new FloatRange(10f, 4f),
                   new DoubleRange(10d, 4d)]
        range10 << [new LongRange(9L, 5L),
                    new CharRange('i' as char, 'd' as char),
                    new IntRange(9, 5),
                    new EnumRange(Char.I, Char.D),
                    new DoubleRange(9d, 5d),
                    new FloatRange(9f, 5f)]
        range9 << [null, null, null, null, null, null]
        literal << ['[10..5]', '[10..5]', '[j..e]', '[J..E]', '[10.0..5.0]', '[10.0..5.0]']
        e1 << [3, 3L, 'c' as char, Char.C, 3f, 3d]
        e2 << [8, 8L, 'g' as char, Char.G, 8f, 8d]
        e3 << [12, 12L, 'k' as char, Char.K, 12f, 12d]
        content << [
            '10, 9, 8, 7, 6, 5',
            '10, 9, 8, 7, 6, 5',
            'j, i, h, g, f, e',
            'J, I, H, G, F, E',
            '10.0, 9.0, 8.0, 7.0, 6.0, 5.0',
            '10.0, 9.0, 8.0, 7.0, 6.0, 5.0']
    }

    void "Verify range #range1 as reverse is equal to #range2"() {
        expect:
        range1.reverse() == range2

        where:
        range1 << [new IntRange(5, 10),
                   new LongRange(5L, 10L),
                   new CharRange('e' as char, 'j' as char),
                   new EnumRange(Char.E, Char.J),
                   new FloatRange(5f, 10f),
                   new DoubleRange(5d, 10d)]
        range2 << [new IntRange(10, 5),
                   new LongRange(10L, 5L),
                   new CharRange('j' as char, 'e' as char),
                   new EnumRange(Char.J, Char.E),
                   new FloatRange(10f, 5f),
                   new DoubleRange(10d, 5d)]
    }

    void "Can't remove elements from range #range"() {
        when:
        range.iterator().remove()

        then:
        thrown(UnsupportedOperationException)

        where:
        range << [
            new IntRange(5, 10),
            new LongRange(5L, 10L),
            new CharRange('e' as char, 'j' as char),
            new EnumRange(Char.E, Char.J),
            new FloatRange(5f, 10f),
            new DoubleRange(5d, 10d)
        ]
    }
}
