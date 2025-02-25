package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.PubblicazioneRegistration;
import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.relation.UtentePubblicazioneRelation;
import it.martino_gallozzi.giornale.repository.*;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PubblicazioneService {
    private final PubblicazioneRepository pubblicazioneRepository;
    private final AbbonamentoRepository abbonamentoRepository;
    private final ArticoloRepository articoloRepository;
    private final UtenteRepository utenteRepository;
    private final UtentePubblicazioneRelationRepository utenteRelationRepository;

    //CREATE
    @Transactional(rollbackFor = Exception.class)
    public GenericResponse<Pubblicazione> insertPubblicazione(PubblicazioneRegistration registration) throws Exception {
        if(!abbonamentoRepository.existsAbbonamentoByArgomento(registration.getArgomento())) {
            return new GenericResponse<>(null, "Argomento does not exists", HttpStatus.NOT_FOUND.value());
        }
        if(articoloRepository.existsArticolosById(registration.getListaArticoliId())) {
            val pubblicazione = new Pubblicazione(registration.getPrezzo(), registration.getArgomento());
            for(Articolo articolo : articoloRepository.findArticolosById(registration.getListaArticoliId())) {
                if(articolo.getPubblicazioneID() == null) {
                    throw new Exception("Articolo already assigned to a Pubblicazione");
                }
                else {
                    articolo.setPubblicazioneID(pubblicazione.getId());
                    articoloRepository.save(articolo);
                }
            }
            pubblicazioneRepository.insert(pubblicazione);
            return new GenericResponse<>(null , null, HttpStatus.OK.value());
        }
        else {
            return new GenericResponse<>(null, "Articolo ID not found", HttpStatus.NOT_FOUND.value());
        }
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
                    if(!p.getAbbonamentoId().equals(pubblicazione.getAbbonamentoId()) && !abbonamentoRepository.existsAbbonamentoByArgomento(pubblicazione.getAbbonamentoId())) {
                        return new GenericResponse<>((Pubblicazione)null, "Argomento does not exist", HttpStatus.NOT_FOUND.value());
                    }
                    else {
                        pubblicazioneRepository.save(pubblicazione);
                        return new GenericResponse<>((Pubblicazione)null, null, HttpStatus.OK.value());
                    }
                })
                .orElse(new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    @Transactional
    public GenericResponse<Pubblicazione> deletePubblicazioneById(String pubblicazioneId) {
        return pubblicazioneRepository.findById(pubblicazioneId)
                .map(p -> {
                    utenteRelationRepository.deleteUtentePubblicazioneRelationsByPubblicazioneId(pubblicazioneId);
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
            pubblicazioneRepository.save(pubblicazione);
            utenteRelationRepository.insert(new UtentePubblicazioneRelation(utente.getId(), pubblicazione.getId()));
            return new GenericResponse<>(null, null, HttpStatus.OK.value());
        }
    }

    @Transactional
    public GenericResponse<List<String>> getUsersListById(String pubblicazioneId){
        if(!pubblicazioneRepository.existsById(pubblicazioneId)) {
            return new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value());
        }
        val usersList =  utenteRelationRepository.findUtentePubblicazioneRelationsByPubblicazioneId(pubblicazioneId).stream()
                .map(UtentePubblicazioneRelation::getUtenteId)
                .toList();
        return new GenericResponse<>(usersList, null, HttpStatus.OK.value());
    }

    public GenericResponse<List<String>> getArticlesListById(String pubblicazioneId){
        if(!pubblicazioneRepository.existsById(pubblicazioneId)) {
            return new GenericResponse<>(null, "Pubblicazione ID not found", HttpStatus.NOT_FOUND.value());
        }
        val articlesList = articoloRepository.findArticolosByPubblicazioneID(pubblicazioneId).stream()
                .map(Articolo::getId)
                .toList();
        return new GenericResponse<>(articlesList, null, HttpStatus.OK.value());
    }

    @Transactional
    public GenericResponse<List<String>> getAllByAbbonamento(String abbonamentoId) {
        return abbonamentoRepository.findById(abbonamentoId)
                .map(a -> {
                    val pubList = pubblicazioneRepository.findPubblicazionesByAbbonamentoId(a.getId()).stream()
                            .map(Pubblicazione::getId)
                            .toList();
                    return new GenericResponse<>(pubList, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Abbonamento ID not found", HttpStatus.NOT_FOUND.value()));
    }

}