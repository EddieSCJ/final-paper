package com.bedigital.application.services;

import com.bedigital.application.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserServiceTest {

    private final Long ID = 1L;
    private final User STARTER_USER = new User(ID, "Edcleidson", "1234");
    private final User UPDATED_USER = new User(ID, "Edcleidson Junior", "2134");
    @SpyBean
    private UserService userService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(userService);
    }

    @Test
    public void insertUser() {
        User insertedUser = userService.save(STARTER_USER);
        assertEquals(STARTER_USER, insertedUser);
    }

    @Test
    public void getUser() {
        User user = userService.findById(ID);
        assertEquals(STARTER_USER, user);
    }

    @Test
    public void updateUser() {
        User user = userService.save(UPDATED_USER);
        assertEquals(UPDATED_USER, user);
    }

    @Test
    public void deleteUser() {
        userService.deleteById(ID);
        User user = userService.findById(ID);
        assertEquals(null, user);
    }


}
