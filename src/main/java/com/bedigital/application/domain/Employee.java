package com.bedigital.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Employee extends ApplicationUser {
    private String name;

    @ElementCollection
    @CollectionTable(name = "phones")
    private Set<String> phones = new HashSet<String>();
    private String email;
    private Boolean active;

    public Employee(String username, String password, String name, String email, Boolean active) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.active = active;
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", phones=" + phones +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} " + super.toString();
    }
}
