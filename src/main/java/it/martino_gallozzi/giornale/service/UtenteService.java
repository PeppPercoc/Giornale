package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

    //CREATE
    public Utente insertUtente(Utente utente) {
        utenteRepository.findUtenteByEmail(utente.getEmail()).ifPresentOrElse(s -> {
            System.out.println("User " + s + " already exists");
        }, () -> {
            System.out.println("Inserting user " + utente);
            utenteRepository.insert(utente);
        });
        return utente;
    }
    //READ
    public Utente getUtenteById(String utenteId) {
        Optional<Utente> utente = utenteRepository.findById(utenteId);
        return utente.orElse(null);
    }

    public Utente getUtenteByEmail(String utenteEmail) {
        Optional<Utente> utente = utenteRepository.findUtenteByEmail(utenteEmail);
        return utente.orElse(null);
    }
    //UPDATE

    //DELETE
    public String deleteUtenteById(String utenteId) {
        if (utenteRepository.existsById(utenteId)) {
            utenteRepository.deleteById(utenteId);
            return "User " + utenteId + " deleted";
        } else return "User id is not present in database";
    }

    //todo: login
}
