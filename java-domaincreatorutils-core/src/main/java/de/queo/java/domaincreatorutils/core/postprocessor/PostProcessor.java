package de.queo.java.domaincreatorutils.core.postprocessor;

import de.queo.java.domaincreatorutils.core.builder.Builder;

/**
 * After a instance is built by a {@link Builder} it performs the {@link #postCreate} for
 * example to be persisted in the database.  
 */
public interface PostProcessor {

	/**
	 * Do something after the given {@code entity} is created by a {@link Builder}.
	 * 
	 * @param <T> the type of the entity
	 * @param entity the entity instance
	 * @return the post processed entity
	 */
	<T> T postCreate(T entity);
	
}
