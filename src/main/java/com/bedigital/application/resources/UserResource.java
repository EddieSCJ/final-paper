package com.bedigital.application.resources;

import com.bedigital.application.domain.User;
import com.bedigital.application.repositories.UserRepository;
import com.bedigital.application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

}