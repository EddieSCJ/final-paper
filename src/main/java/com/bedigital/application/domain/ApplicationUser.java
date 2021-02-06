package com.bedigital.application.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(unique = true)
    @NotNull
    protected String username;

    @NotNull
    protected String password;

    public ApplicationUser(String username, String password) {
        this.id = null;
        this.username = username;
        this.password = password;
    }

    public ApplicationUser() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationUser)) return false;
        ApplicationUser applicationUser = (ApplicationUser) o;
        return id.equals(applicationUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
