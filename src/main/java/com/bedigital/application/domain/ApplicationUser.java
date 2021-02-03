package com.bedigital.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;

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
