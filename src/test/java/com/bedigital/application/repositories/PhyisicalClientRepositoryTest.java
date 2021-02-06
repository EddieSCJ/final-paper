package com.bedigital.application.repositories;

import com.bedigital.application.domain.Client;
import com.bedigital.application.domain.PhysicalClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PhyisicalClientRepositoryTest {

    private final PhysicalClient STARTER_CLIENT = new PhysicalClient("Eddie", new Date("09/11/2001"), "13030931463", "eddiescj10@gmail.com", "+55 79 998968393", "Rua estancia 1292, SE, Brasil");
    private final PhysicalClient UPDATED_CLIENT = new PhysicalClient("Eddiescj", new Date("09/11/2001"), "13030931463", "eddiescj0@gmail.com", "+55 79 998968393", "Rua 1292, SE, Brasil");
    private Long TMP_ID = 1L;
    @Autowired
    private PhysicalClientRepository physicalClientRepository;

    @BeforeEach
    public void insertPreviousData() {
        TMP_ID = physicalClientRepository.save(STARTER_CLIENT).getId();
        UPDATED_CLIENT.setId(TMP_ID);
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(physicalClientRepository);
    }

    @Test
    public void shouldInsertAnClient() {
        final PhysicalClient INSERT = new PhysicalClient("mariana", new Date("09/11/2001"), "13030931463", "Mariana@gmail.com",  "+55 79 998968393", "Rua estancia 1292, SE, Brasil");
        Client insertedClient = physicalClientRepository.save(INSERT);
        assertEquals(INSERT, insertedClient);
    }

    @Test
    public void shouldGetAnClientById() {
        Client client = physicalClientRepository.findById(TMP_ID).orElse(null);
        assertEquals(STARTER_CLIENT, client);
    }

    @Test
    public void shouldReturnAllClients() {
        List<PhysicalClient> clients = physicalClientRepository.findAll();
        assertTrue(clients.size() > 0);
    }

    @Test
    public void shouldUpdateAnClientById() {
        PhysicalClient client = physicalClientRepository.save(UPDATED_CLIENT);
        assertEquals(UPDATED_CLIENT, client);
    }

    @Test
    public void shouldDeleteAnClientById() {
        physicalClientRepository.deleteById(TMP_ID);
        PhysicalClient client = physicalClientRepository.findById(TMP_ID).orElse(null);
        assertEquals(null, client);
    }

}
