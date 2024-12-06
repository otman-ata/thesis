package com.tp.etronSystem.repository;

import com.tp.etronSystem.model.Abonnement;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AbonnementRepository extends CrudRepository<Abonnement, Long>{
    Optional<Abonnement> findByEmailClient(String emailClient);

}