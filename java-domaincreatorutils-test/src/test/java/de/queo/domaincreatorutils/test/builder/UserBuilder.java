package de.queo.domaincreatorutils.test.builder;

import de.queo.domaincreatorutils.test.domain.User;
import de.queo.domaincreatorutils.test.domain.dto.UserDto;
import de.queo.java.domaincreatorutils.core.builder.Builder;
import de.queo.java.domaincreatorutils.core.field.Field;
import de.queo.java.domaincreatorutils.core.postprocessor.PostProcessor;

public class UserBuilder extends Builder<User, UserBuilder> {

    public Field<String, UserBuilder> fullName = new Field<>(this);

    public Field<Integer, UserBuilder> age = new Field<>(this);

    public UserBuilder(final PostProcessor postProcessor) {
        super(postProcessor);
    }

    @Override
    protected User buildNew() {
        UserDto userDto = new UserDtoBuilder(getPostProcessor())
                .set(b -> b.fullName.setValue(this.fullName.getValue()))
                .set(b -> b.age.setValue(this.age.getValue()))
                .build();

        return new User(userDto);
    }

}
