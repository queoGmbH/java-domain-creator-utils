package de.queo.java.domaincreatorutils.core.field;

import java.util.Objects;

import de.queo.java.domaincreatorutils.core.builder.Builder;

/**
 * Simple primitive value field that can be built by a {@link Builder}. 
 *
 * @param <TValue> the type of the value that is being build
 * @param <TBuilder> the builder that invokes the build process
 */
public class Field<TValue, TBuilder> {

	private TValue value;
	
	private TBuilder builder;

	public Field(final TBuilder builder) {
		Objects.requireNonNull(builder);
		
		this.builder = builder;
	}
	
	public TBuilder setValue(final TValue value) {
		Objects.requireNonNull(value);
		
		this.value = value;
		return this.builder;
	}
	
	public TValue getValue() {
		return this.value;
	}
	
	protected TBuilder getBuilder() {
		return this.builder;
	}
	
	
}
