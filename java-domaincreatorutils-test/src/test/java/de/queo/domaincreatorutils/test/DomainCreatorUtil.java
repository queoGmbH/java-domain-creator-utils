package de.queo.domaincreatorutils.test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import de.queo.domaincreatorutils.test.builder.BuildingBuilder;
import de.queo.domaincreatorutils.test.builder.UserBuilder;
import de.queo.domaincreatorutils.test.builder.UserDtoBuilder;
import de.queo.java.domaincreatorutils.core.postprocessor.PersistentPostProcessor;
import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;
import de.queo.java.domaincreatorutils.core.postprocessor.TransientPostProcessor;

public abstract class DomainCreatorUtil {

    public BuildingBuilder getBuildingBuilder() {
        return new BuildingBuilder(getPostProcessor())
                .set(b -> b.name.setValue("Great Wall of China"))
                .set(b -> b.architect.setBuilder(getUserBuilder()));
    }

    public UserBuilder getUserBuilder() {
        return new UserBuilder(getPostProcessor())
                .set(b -> b.fullName.setValue("Qin Shi Huang"))
                .set(b -> b.age.setValue(13));
    }

    public UserDtoBuilder getUserDtoBuilder() {
        return new UserDtoBuilder(getPostProcessor())
                .set(b -> b.fullName.setValue("Qin Shi Huang"))
                .set(b -> b.age.setValue(18));
    }

    protected abstract PostProcessor getPostProcessor();

    public static class Transient extends DomainCreatorUtil {

        @Override
        protected PostProcessor getPostProcessor() {
            return new TransientPostProcessor();
        }
    }

    @Service
    public static class Persistent extends DomainCreatorUtil {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        protected PostProcessor getPostProcessor() {
            return new PersistentPostProcessor(this.entityManager);
        }
    }
}
