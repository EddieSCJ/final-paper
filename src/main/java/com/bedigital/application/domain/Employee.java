package com.bedigital.application.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Employee extends ApplicationUser {
    @NotNull
    private String name;
    private String phone;
    private String email;

    @NotNull
    private Boolean active;

    public Employee(String username, String password, String name, String email, String phone, Boolean active) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.active = active;
    }

    public Employee() {
        super();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", active=" + active +
                "} " + super.toString();
    }
}
