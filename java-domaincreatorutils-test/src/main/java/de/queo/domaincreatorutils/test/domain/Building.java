package de.queo.domaincreatorutils.test.domain;

import java.util.Objects;
import java.util.Optional;

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

    @ManyToOne
    private User reviewer;

    public Building() {
        super();
    }

    public Building(final String name, final User architect, final Optional<User> reviewer) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(architect);

        this.name = name;
        this.architect = architect;
        this.reviewer = reviewer.orElse(null);
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

    public Optional<User> getReviewer() {
        return Optional.ofNullable(this.reviewer);
    }

}
