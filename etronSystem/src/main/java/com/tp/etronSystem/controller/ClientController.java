package com.tp.etronSystem.controller;

import com.tp.etronSystem.model.Client;
import com.tp.etronSystem.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public Iterable<Client> getAllClients() {
        return this.clientService.getAllClients();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getClientById(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            Optional<Client> client = this.clientService.getClientById(longId);
            return ResponseEntity.ok(client);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @PostMapping("/ajouter")
    public ResponseEntity<?> createClient(@RequestBody Client client) {
        try {
            Client clt = this.clientService.createClient(client);
            return ResponseEntity.ok(clt);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<?> updateClient(@PathVariable String id, @RequestBody Client client) {
        try {
            Long longId = Long.parseLong(id);
            Client clt = this.clientService.updateClient(longId, client);
            return ResponseEntity.ok(clt);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            return ResponseEntity.ok(this.clientService.deleteClient(longId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }




}