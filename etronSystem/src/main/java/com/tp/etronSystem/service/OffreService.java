package com.tp.etronSystem.service;

import com.tp.etronSystem.model.Offre;
import com.tp.etronSystem.repository.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OffreService {
    @Autowired
    private OffreRepository offreRepository;

    public Iterable<Offre> getAllOffres() {
        return this.offreRepository.findAll();
    }

    public Optional<Offre> getOffreById(String id) {
        Optional<Offre> offre = offreRepository.findById(id);
        if (offre.isEmpty()) {
            throw new RuntimeException("Aucun offre avec l'id " + id);
        } else
            return offre;
    }

    public Offre createOffre(Offre offre) {
        if (offreRepository.findById(offre.getId()).isPresent())
            throw new RuntimeException("Offre existe deja");
        return this.offreRepository.save(offre);
    }

    public Offre updateOffre(String id, Offre updatedOffre) {

        if (offreRepository.existsById(id)) {
            updatedOffre.setId(id);
            return offreRepository.save(updatedOffre);
        }else
            throw new RuntimeException("Offre n'existe pas");
    }

    public boolean deleteOffre(String id) {
        if (offreRepository.existsById(id)) {
            offreRepository.deleteById(id);
            return true;
        }else
            throw new RuntimeException("Offre n'existe pas");
    }


}
