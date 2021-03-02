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
package griffon.plugins.validation.constraints;

import griffon.annotations.core.Nonnull;
import griffon.plugins.validation.types.CharRange;
import griffon.plugins.validation.types.DoubleRange;
import griffon.plugins.validation.types.EnumRange;
import griffon.plugins.validation.types.FloatRange;
import griffon.plugins.validation.types.IntRange;
import griffon.plugins.validation.types.LongRange;
import griffon.util.CollectionUtils;
import org.codehaus.griffon.runtime.validation.constraints.BlankConstraint;
import org.codehaus.griffon.runtime.validation.constraints.CreditCardConstraint;
import org.codehaus.griffon.runtime.validation.constraints.DateConstraint;
import org.codehaus.griffon.runtime.validation.constraints.EmailConstraint;
import org.codehaus.griffon.runtime.validation.constraints.InListConstraint;
import org.codehaus.griffon.runtime.validation.constraints.MatchesConstraint;
import org.codehaus.griffon.runtime.validation.constraints.MaxConstraint;
import org.codehaus.griffon.runtime.validation.constraints.MaxSizeConstraint;
import org.codehaus.griffon.runtime.validation.constraints.MinConstraint;
import org.codehaus.griffon.runtime.validation.constraints.MinSizeConstraint;
import org.codehaus.griffon.runtime.validation.constraints.NotEqualConstraint;
import org.codehaus.griffon.runtime.validation.constraints.NullableConstraint;
import org.codehaus.griffon.runtime.validation.constraints.RangeConstraint;
import org.codehaus.griffon.runtime.validation.constraints.ScaleConstraint;
import org.codehaus.griffon.runtime.validation.constraints.SizeConstraint;
import org.codehaus.griffon.runtime.validation.constraints.UrlConstraint;
import org.codehaus.griffon.runtime.validation.constraints.ValidatorConstraint;

import java.util.Collections;
import java.util.List;

/**
 * @author Andres Almiray
 */
public final class Constraints {
    private Constraints() {

    }

    @Nonnull
    public static CollectionUtils.MapBuilder<String, List<ConstraintDef>> map() {
        return CollectionUtils.map();
    }

    @Nonnull
    public static CollectionUtils.ListBuilder<ConstraintDef> list() {
        return CollectionUtils.list();
    }

    @Nonnull
    public static CollectionUtils.ListBuilder<ConstraintDef> list(@Nonnull ConstraintDef... defs) {
        CollectionUtils.ListBuilder<ConstraintDef> list = CollectionUtils.list();
        Collections.addAll(list, defs);
        return list;
    }

    @Nonnull
    public static CollectionUtils.SetBuilder<PropertyConstraintDef> set() {
        return CollectionUtils.set();
    }

    @Nonnull
    public static ConstraintDef blank(boolean value) {
        return new ConstraintDef(BlankConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef creditCard(boolean value) {
        return new ConstraintDef(CreditCardConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef email(boolean value) {
        return new ConstraintDef(EmailConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef inList(@Nonnull List<?> elements) {
        return new ConstraintDef(InListConstraint.VALIDATION_DSL_NAME, elements);
    }

    @Nonnull
    public static ConstraintDef matches(@Nonnull String pattern) {
        return new ConstraintDef(MatchesConstraint.VALIDATION_DSL_NAME, pattern);
    }

    @Nonnull
    public static ConstraintDef max(@Nonnull Object value) {
        return new ConstraintDef(MaxConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef maxSize(int value) {
        return new ConstraintDef(MaxSizeConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef min(Object value) {
        return new ConstraintDef(MinConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef minSize(int value) {
        return new ConstraintDef(MinSizeConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef notEqual(@Nonnull Object value) {
        return new ConstraintDef(NotEqualConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef nullable(boolean value) {
        return new ConstraintDef(NullableConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef range(int from, int to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new IntRange(from, to));
    }

    @Nonnull
    public static ConstraintDef range(long from, long to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new LongRange(from, to));
    }

    @Nonnull
    public static ConstraintDef range(double from, double to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new DoubleRange(from, to));
    }

    @Nonnull
    public static ConstraintDef range(float from, float to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new FloatRange(from, to));
    }

    @Nonnull
    public static ConstraintDef range(char from, char to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new CharRange(from, to));
    }

    @Nonnull
    public static ConstraintDef range(Enum from, Enum to) {
        return new ConstraintDef(RangeConstraint.VALIDATION_DSL_NAME, new EnumRange<>(from, to));
    }

    @Nonnull
    public static ConstraintDef scale(int scale) {
        return new ConstraintDef(ScaleConstraint.VALIDATION_DSL_NAME, scale);
    }

    @Nonnull
    public static ConstraintDef size(int from, int to) {
        return new ConstraintDef(SizeConstraint.VALIDATION_DSL_NAME, new IntRange(from, to));
    }

    @Nonnull
    public static ConstraintDef url(boolean value) {
        return new ConstraintDef(UrlConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef url(@Nonnull String pattern) {
        return new ConstraintDef(UrlConstraint.VALIDATION_DSL_NAME, pattern);
    }

    @Nonnull
    public static ConstraintDef url(@Nonnull List<?> pattern) {
        return new ConstraintDef(UrlConstraint.VALIDATION_DSL_NAME, pattern);
    }

    @Nonnull
    public static ConstraintDef date(@Nonnull String value) {
        return new ConstraintDef(DateConstraint.VALIDATION_DSL_NAME, value);
    }

    @Nonnull
    public static ConstraintDef validator(@Nonnull PropertyValidator<?> validator) {
        return new ConstraintDef(ValidatorConstraint.VALIDATION_DSL_NAME, validator);
    }

    @Nonnull
    public static ConstraintDef validator(@Nonnull ObjectValidator<?, ?> validator) {
        return new ConstraintDef(ValidatorConstraint.VALIDATION_DSL_NAME, validator);
    }

    /*
    @Nonnull
    public static ConstraintDef shared(@Nonnull String value) {
        return new ConstraintDef("shared", value);
    }

    @Nonnull
    public static ConstraintDef unique(boolean value) {
        return new ConstraintDef("unique", value);
    }
    */
}
