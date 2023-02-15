# Java DomainCreatorUtils

This library helps to simplify the creation of entities in tests. This is achieved by a `DomainCreatorUtil` that provides preconfigured builders for each entity, so that a test case only needs to set up the fields it needs for its test.

## Install

### Dependency

```
<dependencyManagement>
	<dependencies>

		<dependency>
			<groupId>de.queo</groupId>
			<artifactId>java-domaincreatorutils-bom</artifactId>
			<version>${version}</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
	
<dependencies>
	<dependency>
		<groupId>de.queo</groupId>
		<artifactId>java-domaincreatorutils-core</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

### Builder

```
import de.queo.domaincreatorutils.test.domain.Building;
import de.queo.domaincreatorutils.test.domain.User;
import de.queo.java.domaincreatorutils.core.builder.Builder;
import de.queo.java.domaincreatorutils.core.field.EntityField;
import de.queo.java.domaincreatorutils.core.field.Field;
import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;

public class BuildingBuilder extends Builder<Building, BuildingBuilder> {

    public Field<String, BuildingBuilder> name = new Field<>(this);

    public EntityField<User, BuildingBuilder, UserBuilder> architect = new EntityField<>(this);

    public BuildingBuilder(final PostProcessor postProcessor) {
        super(postProcessor);
    }

    @Override
    protected Building buildNew() {
        return new Building(this.name.getValue(), this.architect.getValue());
    }

}
```

### Domain Creator Util

```
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
```

### Test Case

```
@Autowired
private DomainCreatorUtil.Persistent domainCreatorUtil;


@Test
public void testPersistent() {
	Building building = this.domainCreatorUtil.getBuildingBuilder().build();
	[...]
}
```

## Dependencies

This library internally uses `org.hibernate::hibernate-core`.
