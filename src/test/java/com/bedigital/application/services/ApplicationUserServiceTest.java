package com.bedigital.application.services;

import com.bedigital.application.domain.ApplicationUser;
import com.bedigital.application.domain.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class ApplicationUserServiceTest {
    private static final ApplicationUser APPLICATION_USER = new ApplicationUser("eddie", "1234");


    @SpyBean
    private ApplicationUserService applicationUserService;

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(applicationUserService);
    }

    @Test
    public void shouldInsertAnApplicationUser() {
        ApplicationUser insertedApplicationUser = applicationUserService.save(APPLICATION_USER);
        assertEquals(APPLICATION_USER.getUsername(), insertedApplicationUser.getUsername());
    }

}
