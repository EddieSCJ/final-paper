package com.bedigital.application.domain;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.Entity;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class PhysicalClient extends Client {

    @NotNull
    @CPF
    private String cpf;

    public PhysicalClient(@Valid String name, @Valid Date birthDate, @Valid String cpf,
                          @Valid String email, @Valid String phone, @Valid String address) {
        super(name, birthDate, email, phone, address);
        this.cpf = cpf;
    }

    public PhysicalClient() {
        super();
    }

}
