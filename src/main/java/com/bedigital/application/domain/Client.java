package com.bedigital.application.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @NotNull
    protected Date birthDate;

    @NotNull
    @Column(length = 70)
    protected String name;
    protected String email;

    protected String phone;

    @NotNull
    protected String address;

    public Client(@Valid String name, @Valid Date birthDate, @Valid String email, @Valid String phone,
                  @Valid String address) {
        this.name = name;
        this.birthDate = birthDate;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client() {
    }

    public void setBirthDate(String date) {
        this.birthDate = new Date(date);
    }

}
