package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.dto.UtenteRegistration;
import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.response.GenericResponse;
import it.martino_gallozzi.giornale.service.UtenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/utente")
@AllArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;

    @PostMapping("register-utente")
    public GenericResponse<Utente> register(@RequestBody UtenteRegistration registration) {
        return utenteService.registerUtente(registration);
    }

    @GetMapping("get-by-id/{utenteId}")
    public GenericResponse<Utente> getUtenteById(@PathVariable String utenteId) {

        return utenteService.getUtenteById(utenteId);
    }

    @GetMapping("get-by-email/{utenteEmail}")
    public GenericResponse<Utente> getUtenteByEmail(@PathVariable String utenteEmail) {

        return utenteService.getUtenteByEmail(utenteEmail);
    }

    @DeleteMapping("delete")
    public GenericResponse<Utente> deleteUtente(@RequestBody Map<String, String> id) {
        return utenteService.deleteUtenteById(id.get("utenteId"));
    }

    @PutMapping("update")
    public GenericResponse<Utente> updateUtente(@RequestBody Utente utente) {
        return utenteService.updateUtente(utente);
    }

}
