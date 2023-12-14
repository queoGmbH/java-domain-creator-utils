package de.queo.domaincreatorutils.test.domain.dto;

import java.util.Optional;

public class UserDto {

    private String fullName;

    private int age;

    private Optional<Integer> heightInCm;

    public UserDto() {
        super();
    }

    public UserDto(final String fullName, final int age, final Optional<Integer> heightInCm) {
        this.fullName = fullName;
        this.age = age;
        this.heightInCm = heightInCm;
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

    public Optional<Integer> getHeightInCm() {
        return heightInCm;
    }

    public void setHeightInCm(final Optional<Integer> heightInCm) {
        this.heightInCm = heightInCm;
    }

    @Override
    public String toString() {
        return "UserDto [fullName=" + fullName + ", age=" + age + ", heightInCm=" + heightInCm + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + age;
        result = prime * result + ((heightInCm == null) ? 0 : heightInCm.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserDto other = (UserDto) obj;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (age != other.age)
            return false;
        if (heightInCm == null) {
            if (other.heightInCm != null)
                return false;
        } else if (!heightInCm.equals(other.heightInCm))
            return false;
        return true;
    }

}
