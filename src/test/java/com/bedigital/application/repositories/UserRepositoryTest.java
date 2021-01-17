package com.bedigital.application.repositories;

import com.bedigital.application.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final Long ID= 1L;
    private final User STARTER_USER = new User(ID, "Edcleidson", "1234");
    private final User UPDATED_USER = new User(ID, "Edcleidson Junior", "2134");

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(userRepository);
    }

    @Test
    public void insertUser() {
        User insertedUser = userRepository.save(STARTER_USER);
        assertEquals(STARTER_USER, insertedUser);
    }

    @Test
    public void getUser() {
        User user = userRepository.findById(ID).orElse(null);
        assertEquals(STARTER_USER, user);
    }

    @Test
    public void updateUser() {
        User user = userRepository.save(UPDATED_USER);
        assertEquals(UPDATED_USER, user);
    }

    @Test
    public void deleteUser() {
        userRepository.deleteById(ID);
        User user = userRepository.findById(ID).orElse(null);
        assertEquals(null, user);
    }


}
