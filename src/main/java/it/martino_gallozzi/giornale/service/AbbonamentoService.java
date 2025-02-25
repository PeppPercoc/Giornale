package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.relation.UtenteAbbonamentoRelation;
import it.martino_gallozzi.giornale.repository.AbbonamentoRepository;
import it.martino_gallozzi.giornale.repository.UtenteAbbonamentoRelationRepository;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class AbbonamentoService {
    private final AbbonamentoRepository abbonamentoRepository;
    private final UtenteRepository utenteRepository;
    private final UtenteAbbonamentoRelationRepository utenteRelationRepository;

    //CREATE
    public GenericResponse<Abbonamento> insertAbbonamento(String argomento, String periodicita) {
        if(abbonamentoRepository.findByArgomento(argomento).isPresent())
            return new GenericResponse<>(null, "Abbonamento already exists", HttpStatus.NOT_ACCEPTABLE.value());
        Abbonamento abbonamento = new Abbonamento(argomento, periodicita);
        abbonamentoRepository.insert(abbonamento);
        return new GenericResponse<>(null, null, HttpStatus.OK.value());
    }

    //READ
    public GenericResponse<Abbonamento> getAbbonamentoById(String abbonamentoId) {
        return abbonamentoRepository.findById(abbonamentoId)
                .map(a -> new GenericResponse<>(a, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Abbonamento not found", HttpStatus.NOT_FOUND.value()));
    }

    //UPDATE
    @Transactional
    public GenericResponse<Abbonamento> updateAbbonamento(Abbonamento abbonamento) {
        return abbonamentoRepository.findById(abbonamento.getId())
                .map(a -> {
                    abbonamentoRepository.save(abbonamento);
                    return new GenericResponse<>((Abbonamento) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Abbonamento not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    @Transactional
    public GenericResponse<Abbonamento> deleteAbbonamentoById(String abbonamentoId) {
        return abbonamentoRepository.findById(abbonamentoId)
                .map(a -> {
                    utenteRelationRepository.deleteUtenteAbbonamentoRelationsByAbbonamentoId(abbonamentoId);
                    abbonamentoRepository.deleteById(abbonamentoId);
                    return new GenericResponse<>((Abbonamento) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Abbonamento ID not found", HttpStatus.NOT_FOUND.value()));
    }

    // Aggiungi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public GenericResponse<Abbonamento> addSubscriptionById (String abbonamentoId, String utenteId){
        val abbonamentoOpt = abbonamentoRepository.findById(abbonamentoId);
        val utenteOpt = utenteRepository.findById(utenteId);

        if (abbonamentoOpt.isEmpty()) {
            return new GenericResponse<>(null, "Abbonamento Id not found", HttpStatus.NOT_FOUND.value());
        }
        else if (utenteOpt.isEmpty()) {
            return new GenericResponse<>(null, "Utente ID not found", HttpStatus.NOT_FOUND.value());
        }
        else {
            val utente = utenteOpt.get();
            val abbonamento = abbonamentoOpt.get();
            if(utenteRelationRepository.existsUtenteAbbonamentoRelationByUtenteIdAndAbbonamentoId(utente.getId(), abbonamento.getId())) {
                return new GenericResponse<>(null, "User already subscribed", HttpStatus.NOT_ACCEPTABLE.value());
            }
            else {
                utenteRelationRepository.insert(new UtenteAbbonamentoRelation(utente.getId(), abbonamento.getId()));
                return new GenericResponse<>(null, null, HttpStatus.OK.value());
            }
        }
    }

    // Rimuovi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public GenericResponse<Abbonamento> cancelSubscriptionById(String abbonamentoId, String utenteId){
        val abbonamentoOpt = abbonamentoRepository.findById(abbonamentoId);
        val utenteOpt = utenteRepository.findById(utenteId);

        if (abbonamentoOpt.isEmpty()) {
            return new GenericResponse<>(null, "Abbonamento Id not found", HttpStatus.NOT_FOUND.value());
        }
        else if (utenteOpt.isEmpty()) {
            return new GenericResponse<>(null, "Utente ID not found", HttpStatus.NOT_FOUND.value());
        }
        else {
            val abbonamento = abbonamentoOpt.get();
            val utente = utenteOpt.get();
            if(utenteRelationRepository.existsUtenteAbbonamentoRelationByUtenteIdAndAbbonamentoId(utente.getId(), abbonamento.getId())) {
                utenteRelationRepository.deleteUtenteAbbonamentoRelationByUtenteIdAndAbbonamentoId(utente.getId(), abbonamento.getId());
                return new GenericResponse<>(null, null, HttpStatus.OK.value());
            }
            else {
                return new GenericResponse<>(null, "User already subscribed", HttpStatus.NOT_ACCEPTABLE.value());

            }
        }
    }

    @Transactional
    public GenericResponse<List<String>> getSubscribersListByArgomento(String abbonamentoId) {
        if(abbonamentoRepository.existsById(abbonamentoId)) {
            val usersList = utenteRelationRepository.findUtenteAbbonamentoRelationsByAbbonamentoId(abbonamentoId).stream()
                    .map(UtenteAbbonamentoRelation::getUtenteId)
                    .toList();
            return new GenericResponse<>(usersList, null, HttpStatus.OK.value());
        }
        else {
            return new GenericResponse<>(null, "Abbonamento ID not found", HttpStatus.NOT_FOUND.value());
        }
    }

}
