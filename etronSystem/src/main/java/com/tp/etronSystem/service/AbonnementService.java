package com.tp.etronSystem.service;

import com.tp.etronSystem.model.Abonnement;
import com.tp.etronSystem.repository.AbonnementRepository;
import com.tp.etronSystem.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Autowired
    private JwtService jwtService;

    public Abonnement createOrUpdateAbonnement(String token, String offre) {

        token = token.substring(7);
        String email = jwtService.extractUsername(token);

        Abonnement abonnement = new Abonnement();

        abonnement.setEmailClient(email);
        abonnement.setIdOffre(offre);
        abonnement.setDateDebut(LocalDate.now());
        abonnement.setDateFin(LocalDate.now().plusMonths(1));

        if (abonnementRepository.findByEmailClient(email).isPresent()) {
            Abonnement abonnementActuelle = abonnementRepository.findByEmailClient(email).get();
            if(abonnementActuelle.getIdOffre().equals(offre))
                throw new RuntimeException("vous avez deja cet offre!");
            abonnement.setId(abonnementActuelle.getId());
        }
        return this.abonnementRepository.save(abonnement);
    }

    public String deleteAbonnement(String token) {

        token = token.substring(7);
        String email = jwtService.extractUsername(token);

        if (abonnementRepository.findByEmailClient(email).isPresent()) {
            Abonnement abonnementActuelle = abonnementRepository.findByEmailClient(email).get();
            abonnementRepository.deleteById(abonnementActuelle.getId());
            return "Abonnement résilié";
        }else{
            throw new RuntimeException("Aucun abbonnement à résilier");
        }
    }

    public Abonnement prolonger(String token) {

        token = token.substring(7);
        String email = jwtService.extractUsername(token);

        if (abonnementRepository.findByEmailClient(email).isPresent()) {
            Abonnement abonnementActuelle = abonnementRepository.findByEmailClient(email).get();
            abonnementActuelle.setDateFin(LocalDate.now().plusMonths(1));
            return this.abonnementRepository.save(abonnementActuelle);
        }
        throw new RuntimeException("Aucun abbonnement à prolonger");
    }

    public String consulter(String token) {

        token = token.substring(7);
        String email = jwtService.extractUsername(token);

        if (abonnementRepository.findByEmailClient(email).isPresent()) {
            Abonnement abonnementActuelle = abonnementRepository.findByEmailClient(email).get();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

            return "*************Votre Abonnement Actuelle*************" +
                    "\nOffre : " + abonnementActuelle.getIdOffre() +
                    "\nDate de debut : " + abonnementActuelle.getDateDebut().format(formatter) +
                    "\nDate de fin : " + abonnementActuelle.getDateFin().format(formatter);


        }
        throw new RuntimeException("Aucun abbonnement à consulter");
    }


}
