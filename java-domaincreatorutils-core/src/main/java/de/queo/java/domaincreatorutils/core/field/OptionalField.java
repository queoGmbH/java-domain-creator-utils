package de.queo.java.domaincreatorutils.core.field;

import java.util.Optional;

public class OptionalField<TValue, TBuilder> {

    private Optional<TValue> value;

    private TBuilder builder;

    public OptionalField(final TBuilder builder) {
        this.builder = builder;
    }

    public TBuilder setValue(final TValue value) {
        this.value = Optional.of(value);
        return this.builder;
    }

    public TBuilder setValue(final Optional<TValue> value) {
        this.value = value;
        return this.builder;
    }

    public Optional<TValue> getValue() {
        return this.value;
    }

    protected TBuilder getBuilder() {
        return this.builder;
    }

    public Field<TValue, TBuilder> toField() {
        Field<TValue, TBuilder> field = new Field<>(this.builder);
        field.setValue(this.value.get());
        return field;
    }
}
