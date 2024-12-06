package com.tp.etronSystem.controller;



import com.tp.etronSystem.service.AbonnementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/abonnements")
public class AbonnementController {
    @Autowired
    private AbonnementService abonnementService;

    @PostMapping("/ajouterOuModifier")
    public ResponseEntity<?> createOrUpdateAbonnement(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody String offre) {
        try {
            return ResponseEntity.ok(this.abonnementService.createOrUpdateAbonnement(token , offre));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/resilier")
    public ResponseEntity<?> deleteAbonnement(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            return ResponseEntity.ok(this.abonnementService.deleteAbonnement(token));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping("/prolonger")
    public ResponseEntity<?> prolonger(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            return ResponseEntity.ok(this.abonnementService.prolonger(token));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping("/consulter")
    public ResponseEntity<?> consulter(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        try {
            return ResponseEntity.ok(this.abonnementService.consulter(token));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }



}
