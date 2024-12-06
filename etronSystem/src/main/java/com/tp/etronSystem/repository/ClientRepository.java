package com.tp.etronSystem.repository;


import com.tp.etronSystem.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findByEmail(String email);

}