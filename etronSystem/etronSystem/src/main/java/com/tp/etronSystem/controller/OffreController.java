package com.tp.etronSystem.controller;

import com.tp.etronSystem.model.Offre;
import com.tp.etronSystem.service.OffreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/offres")
public class OffreController {

    @Autowired
    private OffreService offreService;

    @GetMapping
    public Iterable<Offre> getAllOffres() {
        return this.offreService.getAllOffres();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getOffreById(@PathVariable String id) {
        try {
            Optional<Offre> offre = this.offreService.getOffreById(id);
            return ResponseEntity.ok(offre);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PostMapping("/ajouter")
    public ResponseEntity<?> createModele(@RequestBody Offre offre) {
        try {
            return ResponseEntity.ok(this.offreService.createOffre(offre));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("modifier/{id}")
    public ResponseEntity<?> updateOffre(@PathVariable String id, @RequestBody Offre offre) {
        try {
            return ResponseEntity.ok(this.offreService.updateOffre(id, offre));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }



    @DeleteMapping("supprimer/{id}")
    public ResponseEntity<?> deleteOffre(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.offreService.deleteOffre(id));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }




}
