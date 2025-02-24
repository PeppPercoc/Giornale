package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.PubblicazioneRegistration;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import it.martino_gallozzi.giornale.repository.PubblicazioneRepository;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NonNull;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PubblicazioneService {
    private final PubblicazioneRepository pubblicazioneRepository;
    private final ArticoloRepository articoloRepository;
    private final UtenteRepository utenteRepository;

    //CREATE
    @Transactional
    public GenericResponse<Pubblicazione> insertPubblicazione(PubblicazioneRegistration registration) {
        if(existArticles(registration.getListaArticoliId())) {
            pubblicazioneRepository.insert(new Pubblicazione(registration));
            return new GenericResponse<>(null , null, HttpStatus.OK.value());
        }
        else {
            return new GenericResponse<>(null, "Articolo ID not found", HttpStatus.NOT_FOUND.value());
        }
    }

    private boolean existArticles(@NonNull List<String> listArticlesId){
        if (listArticlesId.isEmpty())
            return false;

        long count = articoloRepository.countByIdIn(listArticlesId);
        return count == listArticlesId.size();
    }

    public GenericResponse<Pubblicazione> getPubblicazioneById(String pubblicazioneId){
        return pubblicazioneRepository.findById(pubblicazioneId)
            .map(p -> new GenericResponse<>(p, null, HttpStatus.OK.value()))
            .orElse(new GenericResponse<>(null, "Pubblicazione not found", HttpStatus.NOT_FOUND.value()));
    }

    //UPDATE
    public GenericResponse<Pubblicazione> updatePubblicazione(Pubblicazione pubblicazione) {
        return pubblicazioneRepository.findById(pubblicazione.getId())
                .map(p -> {
                    pubblicazioneRepository.save(pubblicazione);
                    return new GenericResponse<>((Pubblicazione)null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    @Transactional
    public GenericResponse<Pubblicazione> deletePubblicazioneById(String pubblicazioneId) {
        return pubblicazioneRepository.findById(pubblicazioneId)
                .map(p -> {
                    pubblicazioneRepository.deleteById(pubblicazioneId);
                    return new GenericResponse<>((Pubblicazione)null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value()));
    }

    @Transactional
    public GenericResponse<Pubblicazione> addUtenteById(@NonNull String pubblicazioneId, @NonNull String utenteId) {
        val pubblicazioneOpt = pubblicazioneRepository.findById(pubblicazioneId);
        val utenteOpt = utenteRepository.findById(utenteId);

        if(pubblicazioneOpt.isEmpty()){
            return new GenericResponse<>(null, "Pubblicazione not found", HttpStatus.NOT_FOUND.value());
        }
        else if(utenteOpt.isEmpty()) {
            return new GenericResponse<>(null, "Utente not found", HttpStatus.NOT_FOUND.value());
        }
        else {
            val pubblicazione = pubblicazioneOpt.get();
            val utente = utenteOpt.get();
            pubblicazione.getListaUtentiId().add(utente.getId());
            pubblicazioneRepository.save(pubblicazione);
            return new GenericResponse<>(null, null, HttpStatus.OK.value());
        }
    }

    public GenericResponse<List<String>> getUsersListById(String pubblicazioneId){
        return pubblicazioneRepository.findById(pubblicazioneId)
            .map(p -> new GenericResponse<>(p.getListaUtentiId(), null, HttpStatus.OK.value()))
            .orElse(new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value()));
    }

    public GenericResponse<List<String>> getArticlesListById(String pubblicazioneId){
        return pubblicazioneRepository.findById(pubblicazioneId)
                .map(p -> new GenericResponse<>(p.getListaArticoliId(), null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value()));
    }
}