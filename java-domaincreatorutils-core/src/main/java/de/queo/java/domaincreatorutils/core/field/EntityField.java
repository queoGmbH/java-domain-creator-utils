package de.queo.java.domaincreatorutils.core.field;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import de.queo.java.domaincreatorutils.core.builder.Builder;

/**
 * A field to build entities not only from plain values but from builders if necessary. 
 *
 * @param <TValue> the value of the entity instance
 * @param <TInvokingBuilder> the builder that invokes the build process 
 * @param <TValueBuilder> the builder that builds the entity instance
 */
public class EntityField<TValue, TInvokingBuilder, TValueBuilder extends Builder<TValue, TValueBuilder>> {

	/**
	 * The possible value of the entity.
	 * Either this or {@link #valueBuilder} is set.
	 */
	private Optional<TValue> value;
	
	/**
	 * The possible builder that can build the entity.
	 * Either this or {@link #value} is set.
	 */
	private Optional<TValueBuilder> valueBuilder = Optional.empty();
	
	/**
	 * The builder that invokes the build process.
	 * This is most likely not the builder that can build the entity, see test cases or documentation.
	 */
	private TInvokingBuilder invokingBuilder;
	
	public EntityField(final TInvokingBuilder invokingBuilder) {
		Objects.requireNonNull(invokingBuilder);
		
		this.invokingBuilder = invokingBuilder;
	}
	
	public TInvokingBuilder setBuilder(final TValueBuilder valueBuilder) {
		Objects.requireNonNull(valueBuilder);
		
		this.value = Optional.empty();
		this.valueBuilder = Optional.of(valueBuilder);
		
		return this.invokingBuilder;
	}
	
	public TInvokingBuilder setValue(final TValue value) {
		Objects.requireNonNull(value);
		
		this.value = Optional.of(value);
		this.valueBuilder = Optional.empty();
		
		return this.invokingBuilder;
	}
	
	public TValue getValue() {
		return this.value.orElseGet(() -> this.valueBuilder.get().build());
	}
	
	/**
	 * Modify the current builder instance. Can only be invoked if a builder and not a value is set.
	 * 
	 * @param modification the modification of the builder
	 * @return the invoking builder for chaining
	 */
	public TInvokingBuilder modify(final Consumer<TValueBuilder> modification) {
		Objects.requireNonNull(modification);
		
		if (this.valueBuilder.isEmpty()) {
			throw new IllegalStateException("Cannot modify a EntityField without a builder being set up.");
		}
		
		modification.accept(this.valueBuilder.get());
		
		return this.invokingBuilder;
	}
	

}
