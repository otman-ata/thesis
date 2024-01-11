package com.tp.etronSystem.service;

import com.tp.etronSystem.model.Client;
import com.tp.etronSystem.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Iterable<Client> getAllClients() {
        return this.clientRepository.findAll();
    }


    public Optional<Client> getClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new RuntimeException("Aucun client avec l'id " + id);
        } else
            return client;
    }

    public Client createClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent())
            throw new RuntimeException("Email existe deja");
        return this.clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {

        if (clientRepository.existsById(id) && clientRepository.findByEmail(updatedClient.getEmail()).isEmpty()) {
            updatedClient.setId(id);
            return clientRepository.save(updatedClient);
        }else if(!clientRepository.existsById(id)){
            throw new RuntimeException("Aucun client avec cet id");
        }else if(clientRepository.findByEmail(updatedClient.getEmail()).isPresent()){
            throw new RuntimeException("Email existe deja");
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }else
            throw new RuntimeException("Aucun client avec cet id");
    }




}
