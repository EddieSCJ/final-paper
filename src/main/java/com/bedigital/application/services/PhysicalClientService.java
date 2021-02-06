package com.bedigital.application.services;

import com.bedigital.application.domain.Client;
import com.bedigital.application.domain.PhysicalClient;
import com.bedigital.application.repositories.PhysicalClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysicalClientService {

    @Autowired
    private PhysicalClientRepository physicalClientRepository;

    public List<PhysicalClient> findAll() {
        return physicalClientRepository.findAll();
    }

    public PhysicalClient findById(Long id) {
        return physicalClientRepository.findById(id).orElse(null);
    }

    public PhysicalClient update(Long id, PhysicalClient client) {
        client.setId(id);
        return physicalClientRepository.save(client);
    }

    public PhysicalClient save(PhysicalClient client) {
        return physicalClientRepository.save(client);
    }

    public void deleteById(Long id) {
        physicalClientRepository.deleteById(id);
    }
}
