package de.queo.java.domaincreatorutils.core.postprocessor;

public class TransientPostProcessor implements PostProcessor {

	/**
	 * Just returns the transient entity.
	 * 
	 * @return the transient entity
	 */
	public <T> T postCreate(T entity) {
		return entity;
	}
	
}
