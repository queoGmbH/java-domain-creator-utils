package de.queo.domaincreatorutils.test.builder;

import de.queo.domaincreatorutils.test.domain.Building;
import de.queo.domaincreatorutils.test.domain.User;
import de.queo.java.domaincreatorutils.core.builder.Builder;
import de.queo.java.domaincreatorutils.core.field.EntityField;
import de.queo.java.domaincreatorutils.core.field.Field;
import de.queo.java.domaincreatorutils.core.field.OptionalEntityField;
import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;

public class BuildingBuilder extends Builder<Building, BuildingBuilder> {

    public Field<String, BuildingBuilder> name = new Field<>(this);

    public EntityField<User, BuildingBuilder, UserBuilder> architect = new EntityField<>(this);

    public OptionalEntityField<User, BuildingBuilder, UserBuilder> reviewer = new OptionalEntityField<>(this);

    public BuildingBuilder(final PostProcessor postProcessor) {
        super(postProcessor);
    }

    @Override
    protected Building buildNew() {
        return new Building(this.name.getValue(), this.architect.getValue(), this.reviewer.getValue());
    }

}
