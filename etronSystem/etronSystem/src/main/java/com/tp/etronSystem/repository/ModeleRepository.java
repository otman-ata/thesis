package com.tp.etronSystem.repository;

import com.tp.etronSystem.model.Modele;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ModeleRepository extends CrudRepository<Modele, Long> {

    List<Modele> findByModele(String modele);
    List<Modele> findByAnnee(int annee);
    Optional<Modele> findByModeleAndAnnee(String modele, int annee);

}
