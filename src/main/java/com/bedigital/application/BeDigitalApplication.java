package com.bedigital.application;

import com.bedigital.application.domain.User;
import com.bedigital.application.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeDigitalApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BeDigitalApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
		User user  = new User(null, "Edcleidson", "1234");
		userRepository.save(user);
	}
}
