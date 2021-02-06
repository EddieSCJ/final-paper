package com.bedigital.application;

import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.repositories.ApplicationUserRepository;
import com.bedigital.application.services.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BeDigitalApplication implements CommandLineRunner{

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(BeDigitalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String password = UtilsService.hashPassword("1234");
        ApplicationUser applicationUser = new ApplicationUser("eddie", password);
        applicationUserRepository.save(applicationUser);
    }
}
