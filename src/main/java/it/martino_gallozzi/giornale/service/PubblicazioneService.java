package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.repository.PubblicazioneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PubblicazioneService {
    private final PubblicazioneRepository pubblicazioneRepository;

    //CREATE
    public Pubblicazione insertPubblicazione(Pubblicazione pubblicazione) {
        pubblicazioneRepository.findById(pubblicazione.getId()).ifPresentOrElse(s -> {
            System.out.println("Publication " + s + " already exists");
        }, () -> {
            System.out.println("Inserting publication " + pubblicazione);
            pubblicazioneRepository.insert(pubblicazione);
        });
        return pubblicazione;
    }
    //READ
    public Pubblicazione getUtentetById(String pubblicazioneId) {
        Optional<Pubblicazione> pubblicazione = pubblicazioneRepository.findById(pubblicazioneId);
        return pubblicazione.orElse(null);
    }
    //UPDATE

    //DELETE
    public String deletepubblicazioneById(String pubblicazioneId) {
        if (pubblicazioneRepository.existsById(pubblicazioneId)) {
            pubblicazioneRepository.deleteById(pubblicazioneId);
            return "Publication " + pubblicazioneId + " deleted";
        } else return "Publication id is not present in database";
    }
}
