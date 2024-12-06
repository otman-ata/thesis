package com.tp.etronSystem.service;

import com.tp.etronSystem.model.Client;
import com.tp.etronSystem.model.Modele;
import com.tp.etronSystem.repository.ModeleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModeleService {
    @Autowired
    private ModeleRepository modeleRepository;

    @Autowired
    private ClientService clientService;

    public Iterable<Modele> getAllModeles() {
        return this.modeleRepository.findAll();
    }

    public Optional<Modele> getModeleById(Long id) {
        Optional<Modele> mdl = modeleRepository.findById(id);
        if (mdl.isEmpty()) {
            throw new RuntimeException("Aucun modele avec l'id " + id);
        } else
            return mdl;
    }

    @Transactional
    public List<Modele> searchByModele(String modele) {
        List<Modele> list = modeleRepository.findByModele(modele);
        if (list.isEmpty()) {
            throw new RuntimeException("Aucun modele nommé " + modele);
        } else
            return list;
    }

    @Transactional
    public List<Modele> searchByAnnee(int annee) {
        List<Modele> list = modeleRepository.findByAnnee(annee);
        if (list.isEmpty()) {
            throw new RuntimeException("Aucun modele avec l'annee " + annee);
        } else
            return list;
    }

    @Transactional
    public Optional<Modele> searchByModeleAndAnnee(String modele, int annee) {

        Optional<Modele> mdl = modeleRepository.findByModeleAndAnnee(modele, annee);
        if (mdl.isEmpty()) {
            throw new RuntimeException("Aucun modele " + modele + " avec l'annee " + annee);
        } else
            return mdl;
    }


    public Modele createModele(Modele modele) {
        if (modeleRepository.findByModeleAndAnnee(modele.getModele(), modele.getAnnee()).isPresent())
            throw new RuntimeException("Modele existe deja");
        return this.modeleRepository.save(modele);
    }


    public Modele updateModele(Long id, Modele updatedModele) {

        if (modeleRepository.existsById(id) && modeleRepository.findByModeleAndAnnee(updatedModele.getModele(), updatedModele.getAnnee()).isEmpty()) {
            updatedModele.setId(id);
            return modeleRepository.save(updatedModele);
        }else if(!modeleRepository.existsById(id)){
            throw new RuntimeException("Aucun modele avec cet id");
        }else if(modeleRepository.findByModeleAndAnnee(updatedModele.getModele(), updatedModele.getAnnee()).isPresent()){
            throw new RuntimeException("ce modelle et annee existent deja");
        }
        return null;
    }

    public boolean deleteModele(Long id) {
        if (modeleRepository.existsById(id)) {
            modeleRepository.deleteById(id);
            return true;
        }else
            throw new RuntimeException("Aucun modele avec cet id");
    }

    //more methods

    public Optional<Modele> getModeleByClientId(Long id){
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()){
            Long idModele = client.get().getIdModele();
            return this.getModeleById(idModele);
        }
        throw new RuntimeException("Aucun client avec cet id");
    }


}