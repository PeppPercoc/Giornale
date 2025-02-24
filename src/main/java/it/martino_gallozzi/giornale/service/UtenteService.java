package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.UtenteRegistration;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

    //CREATE
    public GenericResponse<Utente> registerUtente(UtenteRegistration registration) {
        val utente = new Utente(registration);
        utenteRepository.insert(utente);
        return new GenericResponse<>(null, null, HttpStatus.OK.value());
    }
    //READ
    public GenericResponse<Utente> getUtenteById(String utenteId) {
        return utenteRepository.findById(utenteId)
                .map(u -> new GenericResponse<>(u, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Utente not found", HttpStatus.NOT_FOUND.value()));
    }

    public GenericResponse<Utente> getUtenteByEmail(String utenteEmail) {
        return utenteRepository.findUtenteByEmail(utenteEmail)
                .map(u -> new GenericResponse<>(u, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Utente not found", HttpStatus.NOT_FOUND.value()));
    }

    //UPDATE
    public GenericResponse<Utente> updateUtente(Utente utente) {
        return utenteRepository.findById(utente.getId())
                .map(u -> {
                    utenteRepository.save(utente);
                    return new GenericResponse<>((Utente)null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Utente not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    public GenericResponse<Utente> deleteUtenteById(String utenteId) {
        return utenteRepository.findById(utenteId)
                .map(u -> {
                    utenteRepository.deleteById(utenteId);
                    return new GenericResponse<>((Utente) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Utente ID not found", HttpStatus.NOT_FOUND.value()));
    }

}
