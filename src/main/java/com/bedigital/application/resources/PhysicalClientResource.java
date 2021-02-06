package com.bedigital.application.resources;

import com.bedigital.application.domain.PhysicalClient;
import com.bedigital.application.services.PhysicalClientService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/physical-clients")
@Api(description = "Physical Client Endpoint", tags = "Physical Clients")
public class PhysicalClientResource {

    @Autowired
    private PhysicalClientService physicalClientService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<PhysicalClient> clients = physicalClientService.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        PhysicalClient client = physicalClientService.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid PhysicalClient client) {
        final PhysicalClient insertedClient = physicalClientService.save(client);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(insertedClient.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody @Valid PhysicalClient client) {
        final PhysicalClient updatedClient = physicalClientService.update(id, client);
        if (updatedClient == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(updatedClient);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        physicalClientService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
