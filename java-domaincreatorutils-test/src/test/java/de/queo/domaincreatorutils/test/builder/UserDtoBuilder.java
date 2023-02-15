package de.queo.domaincreatorutils.test.builder;

import de.queo.domaincreatorutils.test.domain.dto.UserDto;
import de.queo.java.domaincreatorutils.core.builder.Builder;
import de.queo.java.domaincreatorutils.core.field.Field;
import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;

public class UserDtoBuilder extends Builder<UserDto, UserDtoBuilder> {

    public Field<String, UserDtoBuilder> fullName = new Field<>(this);

    public Field<Integer, UserDtoBuilder> age = new Field<>(this);

    public UserDtoBuilder(final PostProcessor postProcessor) {
        super(postProcessor);
    }

    @Override
    protected UserDto buildNew() {
        return new UserDto(this.fullName.getValue(), this.age.getValue());
    }

}
