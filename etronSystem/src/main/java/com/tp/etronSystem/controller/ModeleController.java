package com.tp.etronSystem.controller;


import com.tp.etronSystem.model.Modele;
import com.tp.etronSystem.service.ModeleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/modeles")
public class ModeleController {

    @Autowired
    private ModeleService modeleService;


    @GetMapping
    public Iterable<Modele> getAllModeles() {
        return this.modeleService.getAllModeles();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getModeleById(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            Optional<Modele> mdl = this.modeleService.getModeleById(longId);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("modele/{modele}")
    public ResponseEntity<?> searchByModele(@PathVariable String modele) {
        try {
            List<Modele> mdl = this.modeleService.searchByModele(modele);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("annee/{annee}")
    public ResponseEntity<?> searchByAnnee(@PathVariable String annee) {
        try {
            int intAnnee = Integer.parseInt(annee);
            List<Modele> mdl = this.modeleService.searchByAnnee(intAnnee);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("modele-annee/{modele}/{annee}")
    public ResponseEntity<?> searchByModeleAndAnnee(@PathVariable String modele, @PathVariable String annee) {
        try {
            int intAnnee = Integer.parseInt(annee);
            Optional<Modele> mdl = this.modeleService.searchByModeleAndAnnee(modele, intAnnee);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> createModele(@RequestBody Modele voiture) {
        try {
            Modele mdl = this.modeleService.createModele(voiture);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<?> updateModele(@PathVariable String id, @RequestBody Modele modele) {
        try {
            Long longId = Long.parseLong(id);
            Modele mdl = this.modeleService.updateModele(longId, modele);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> deleteModele(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            return ResponseEntity.ok(this.modeleService.deleteModele(longId));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    //more methods

    @GetMapping("client/{id}")
    public ResponseEntity<?> getModeleByClientId(@PathVariable String id) {
        try {
            Long longId = Long.parseLong(id);
            Optional<Modele> mdl = this.modeleService.getModeleByClientId(longId);
            return ResponseEntity.ok(mdl);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


}
