package de.queo.domaincreatorutils.test.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private User architect;

    public Building() {
        super();
    }

    public Building(final String name, final User architect) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(architect);

        this.name = name;
        this.architect = architect;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public User getArchitect() {
        return this.architect;
    }

}
