package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
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
    public Pubblicazione getPubblicazioneByTitolo(String pubblicazioneTitolo) {
        Optional<Pubblicazione> pubblicazione = pubblicazioneRepository.findPubblicazioneByTitolo(pubblicazioneTitolo);
        return pubblicazione.orElse(null);
    }
    //UPDATE
    public Pubblicazione updatePubblicazione(Pubblicazione pubblicazione) {
        Optional<Pubblicazione> existingPubblicazione = pubblicazioneRepository.findById(pubblicazione.getId());

        if (existingPubblicazione.isPresent()) {
            System.out.println("Student " + pubblicazione.getId() + " updated");
            return pubblicazioneRepository.save(pubblicazione);
        } else {
            return null;
        }
    }
    //DELETE
    public String deletePubblicazioneById(String pubblicazioneId) {
        if (pubblicazioneRepository.existsById(pubblicazioneId)) {
            pubblicazioneRepository.deleteById(pubblicazioneId);
            return "Publication " + pubblicazioneId + " deleted";
        } else return "Publication id is not present in database";
    }

    //todo: add a Utente to buyers list
    //todo: stampa utenti
    //todo: controllare che quando inserisco una pubblicazione la lista degli utenti sia vuota
    //todo: controllare che quando inserisco una pubblicazione tutti gli id di articolo esistano
    //todo: read the list of the Articoli inside the Pubblicazione
}
