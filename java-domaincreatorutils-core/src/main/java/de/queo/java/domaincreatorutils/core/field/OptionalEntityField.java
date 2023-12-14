package de.queo.java.domaincreatorutils.core.field;

import java.util.Optional;
import java.util.function.Consumer;

import de.queo.java.domaincreatorutils.core.builder.Builder;

public class OptionalEntityField<TValue, TBuilder, TValueBuilder extends Builder<TValue, TValueBuilder>>
        extends OptionalField<TValue, TBuilder> {

    private Optional<TValueBuilder> builder = Optional.empty();

    /**
     * The optional entity field has a bit of weird api because it needs the builder even
     * when the builder should not be used in order to make {@link #modify} work.
     * For that reason this field is set to true if {@link #modify} or {@link #build} is used and then
     * the builder is applied, otherwise it is not. 
     */
    private boolean isPresent = false;

    public OptionalEntityField(TBuilder builder) {
        super(builder);
    }

    /**
     * Other than {@link #setEmptyBuilder(Builder)} this will be used to build a optional entity through
     * its domain creator util by default.
     * 
     * @param builder the builder to build the entity
     * @return the builder for chaining
     */
    public TBuilder setBuilder(final TValueBuilder builder) {
        this.builder = Optional.of(builder);
        this.isPresent = true;
        return getBuilder();
    }

    /**
     * Other than {@link #setBuilder(Builder)} this will not be used to build a optional entity through
     * its domain creator util by default. It will only be used when a {@link #modify(Consumer)} or 
     * {@link #build()} is called.
     * 
     * @param builder the builder to build the entity
     * @return the builder for chaining
     */
    public TBuilder setEmptyBuilder(final TValueBuilder builder) {
        this.builder = Optional.of(builder);
        this.isPresent = false;

        super.setValue(Optional.empty());
        return getBuilder();
    }

    public TBuilder modify(final Consumer<TValueBuilder> modification) {
        if (this.builder.isEmpty()) {
            throw new IllegalArgumentException("Cannot modify without a builder.");
        }

        modification.accept(this.builder.get());
        this.isPresent = true;
        return getBuilder();
    }

    @Override
    public TBuilder setValue(TValue value) {
        this.builder = Optional.empty();
        return super.setValue(value);
    }

    @Override
    public TBuilder setValue(Optional<TValue> value) {
        this.builder = Optional.empty();
        return super.setValue(value);
    }

    @Override
    public Optional<TValue> getValue() {
        if (this.builder.isPresent() && this.isPresent) {
            return Optional.of(this.builder.get().build());
        } else {
            return super.getValue();
        }
    }

    public TBuilder build() {
        this.isPresent = true;
        return getBuilder();
    }

}
