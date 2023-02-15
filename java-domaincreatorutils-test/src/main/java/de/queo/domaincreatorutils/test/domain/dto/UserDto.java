package de.queo.domaincreatorutils.test.domain.dto;

import java.util.Objects;

public class UserDto {

    private String fullName;

    private int age;

    public UserDto() {
        super();
    }

    public UserDto(final String fullName, final int age) {
        this.fullName = fullName;
        this.age = age;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(final int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "EntityBDto [fullName=" + this.fullName + ", age=" + this.age + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.age, this.fullName);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        UserDto other = (UserDto) obj;
        return (this.age == other.age) && Objects.equals(this.fullName, other.fullName);
    }

}
