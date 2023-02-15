package de.queo.domaincreatorutils.test.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import de.queo.domaincreatorutils.test.domain.dto.UserDto;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullName;

    private int age;

    public User() {
        super();
    }

    public User(final UserDto userDto) {
        Objects.requireNonNull(userDto);

        this.fullName = userDto.getFullName();
        this.age = userDto.getAge();
    }

    public Long getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public int getAge() {
        return this.age;
    }

}
