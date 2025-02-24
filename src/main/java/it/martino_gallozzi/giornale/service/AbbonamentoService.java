package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.repository.AbbonamentoRepository;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbbonamentoService {
    private final AbbonamentoRepository abbonamentoRepository;
    private final UtenteRepository utenteRepository;

    //CREATE
    public GenericResponse<Abbonamento> insertAbbonamento(String argomento, String periodicita) {
        Abbonamento abbonamento = new Abbonamento(argomento, periodicita);

        return abbonamentoRepository.findByArgomento(abbonamento.getArgomento())
                    .map(a -> new GenericResponse<>((Abbonamento)null, "Abbonamento already exists", HttpStatus.NOT_ACCEPTABLE.value()))
                    .orElse(new GenericResponse<>(null, null, HttpStatus.OK.value()));

    }

    //READ
    public GenericResponse<Abbonamento> getAbbonamentoByArgomento(String abbonamentoArgomento) {
        return abbonamentoRepository.findById(abbonamentoArgomento)
                .map(a -> new GenericResponse<>(a, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Articolo not found", HttpStatus.NOT_FOUND.value()));
    }

    public GenericResponse<List<String>> getUsersByArgomento(String abbonamentoArgomento) {
        return abbonamentoRepository.findByArgomento(abbonamentoArgomento)
                .map(a -> new GenericResponse<>(a.getListaUtentiId(), null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Argomento abbonamento not found", HttpStatus.NOT_FOUND.value()));
    }

    //UPDATE
    public GenericResponse<Abbonamento> updateAbbonamento(Abbonamento abbonamento) {
        return abbonamentoRepository.findById(abbonamento.getArgomento())
                .map(a -> {
                    abbonamentoRepository.save(abbonamento);
                    return new GenericResponse<>((Abbonamento) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Abbonamento not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    public GenericResponse<Abbonamento> deleteAbbonamentoById(String abbonamentoArgomento) {
        return abbonamentoRepository.findById(abbonamentoArgomento)
                .map(a -> {
                    abbonamentoRepository.deleteById(abbonamentoArgomento);
                    return new GenericResponse<>((Abbonamento) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Abbonamento ID not found", HttpStatus.NOT_FOUND.value()));
    }

    // Aggiungi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public GenericResponse<Abbonamento> addSubscriptionById (String abbonamentoArgomento, String utenteId){
        val abbonamentoOpt = abbonamentoRepository.findById(abbonamentoArgomento);
        val utenteOpt = utenteRepository.findById(utenteId);

        if (abbonamentoOpt.isEmpty()) {
            return new GenericResponse<>(null, "Abbonamento argomento not found", HttpStatus.NOT_FOUND.value());
        }
        else if (utenteOpt.isEmpty()) {
            return new GenericResponse<>(null, "Utente ID not found", HttpStatus.NOT_FOUND.value());
        }
        else {
            val abbonamento = abbonamentoOpt.get();
            val utente = utenteOpt.get();
            abbonamento.getListaUtentiId().add(utente.getId());
            abbonamentoRepository.save(abbonamento);
            return new GenericResponse<>(null, null, HttpStatus.OK.value());
        }
    }

    // Rimuovi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public GenericResponse<Abbonamento> cancelSubscriptionById(String abbonamentoArgomento, String utenteId){
        val abbonamentoOpt = abbonamentoRepository.findById(abbonamentoArgomento);
        val utenteOpt = utenteRepository.findById(utenteId);

        if (abbonamentoOpt.isEmpty()) {
            return new GenericResponse<>(null, "Abbonamento argomento not found", HttpStatus.NOT_FOUND.value());
        }
        else if (utenteOpt.isEmpty()) {
            return new GenericResponse<>(null, "Utente ID not found", HttpStatus.NOT_FOUND.value());
        }
        else {
            val abbonamento = abbonamentoOpt.get();
            val utente = utenteOpt.get();
            abbonamento.getListaUtentiId().remove(utente.getId());
            abbonamentoRepository.save(abbonamento);
            return new GenericResponse<>(null, null, HttpStatus.OK.value());
        }
    }

    public GenericResponse<List<String>> getSubscribersListByArgomento(String abbonamentoArgomento){
        return abbonamentoRepository.findById(abbonamentoArgomento)
                .map(a -> new GenericResponse<>(a.getListaUtentiId(), null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Argomento abbonamento not found", HttpStatus.NOT_FOUND.value()));
    }
}
