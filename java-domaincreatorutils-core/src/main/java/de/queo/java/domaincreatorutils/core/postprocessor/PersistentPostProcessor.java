package de.queo.java.domaincreatorutils.core.postprocessor;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Hibernate;

public class PersistentPostProcessor implements PostProcessor {

	@PersistenceContext
	private EntityManager entityManager;

	public PersistentPostProcessor(final EntityManager entityManager) {
		Objects.requireNonNull(entityManager);

		this.entityManager = entityManager;
	}

	/**
	 * Persist the given {@code entity} if it is a {@link Entity}.
	 */
	@SuppressWarnings("unchecked")
	public <T> T postCreate(final T entity) {
		Objects.requireNonNull(entity);

		if (Hibernate.getClass(entity).getAnnotation(Entity.class) != null) {
			this.entityManager.persist(entity);
		}

		return entity;
	}

}
