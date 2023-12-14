package de.queo.java.domaincreatorutils.core.builder;

import java.util.Objects;
import java.util.function.Consumer;

import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;

public abstract class Builder<TEntity, TEntityBuilder extends Builder<TEntity, TEntityBuilder>> {

    /**
     * The post processor that is invoked after the entity is built (see {@link #build()}.
     */
    private PostProcessor postProcessor;

    public Builder(final PostProcessor postProcessor) {
        Objects.requireNonNull(postProcessor);

        this.postProcessor = postProcessor;
    }

    protected abstract TEntity buildNew();

    public TEntity build() {
        return this.postProcessor.postCreate(buildNew());
    }

    public TEntity build(final Consumer<TEntityBuilder> modification) {
        Objects.requireNonNull(modification);

        set(modification);
        return build();
    }

    /**
     * Noisy method thats intend is to handle auto-formatting without weird line breaks.  
     * 
     * @param modification the modification of the builder
     * @return the builder for chaining
     */
    public TEntityBuilder set(final Consumer<TEntityBuilder> modification) {
        Objects.requireNonNull(modification);

        modification.accept(getBuilder());
        return getBuilder();

    }

    @SuppressWarnings("unchecked")
    public TEntityBuilder getBuilder() {
        return (TEntityBuilder) this;
    }

    public PostProcessor getPostProcessor() {
        return postProcessor;
    }

}
