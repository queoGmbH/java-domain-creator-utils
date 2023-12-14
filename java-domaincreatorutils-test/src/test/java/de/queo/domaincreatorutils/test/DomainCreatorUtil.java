package de.queo.domaincreatorutils.test;

import java.util.Optional;

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
                .set(b -> {
                    b.name.setValue("Great Wall of China");
                    b.architect.setBuilder(getUserBuilder());
                    b.reviewer.setEmptyBuilder(getUserBuilder());
                });
    }

    public UserBuilder getUserBuilder() {
        return new UserBuilder(getPostProcessor())
                .set(b -> {
                    b.fullName.setValue("Qin Shi Huang");
                    b.age.setValue(13);
                    b.heightInCm.setValue(Optional.empty());
                });
    }

    public UserDtoBuilder getUserDtoBuilder() {
        return new UserDtoBuilder(getPostProcessor())
                .set(b -> {
                    b.fullName.setValue("Qin Shi Huang");
                    b.age.setValue(18);
                    b.heightInCm.setValue(Optional.empty());
                });
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
