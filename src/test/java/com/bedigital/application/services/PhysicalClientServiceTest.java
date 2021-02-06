package com.bedigital.application.services;

import com.bedigital.application.domain.Client;
import com.bedigital.application.domain.PhysicalClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class PhysicalClientServiceTest {

    private Long TMP_ID = 1L;
    private final PhysicalClient STARTER_CLIENT = new PhysicalClient("Eddie", new Date("09/11/2001"), "13030931463", "eddiescj10@gmail.com",  "+55 79 998968393", "Rua estancia 1292, SE, Brasil");
    private final PhysicalClient UPDATED_CLIENT = new PhysicalClient("Eddiescj", new Date("09/11/2001"), "13030931463",  "+55 79 998968393", "eddiescj0@gmail.com", "Rua 1292, SE, Brasil");

    @SpyBean
    private PhysicalClientService physicalClientService;

    @BeforeEach
    public void insertPreviousData() {
        TMP_ID = physicalClientService.save(STARTER_CLIENT).getId();
        UPDATED_CLIENT.setId(TMP_ID);
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(physicalClientService);
    }

    @Test
    public void shouldInsertAnClient() {
        Client insertedClient = physicalClientService.save(STARTER_CLIENT);
        assertEquals(STARTER_CLIENT, insertedClient);
    }

    @Test
    public void shouldGetAnClientById() {
        Client client = physicalClientService.findById(TMP_ID);
        assertEquals(STARTER_CLIENT, client);
    }

    @Test
    public void shouldUpdateAnClient() {
        Client client = physicalClientService.save(UPDATED_CLIENT);
        assertEquals(UPDATED_CLIENT, client);
    }

    @Test
    public void shouldDeleteAnClient() {
        physicalClientService.deleteById(TMP_ID);
        Client client = physicalClientService.findById(TMP_ID);
        assertEquals(null, client);
    }


}
