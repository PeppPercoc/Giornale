package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.service.UtenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/utente")
@AllArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;

    @PostMapping("insert-utente")
    public Utente insertUtente(@RequestBody Utente utente) {
        return utenteService.insertUtente(utente);
    }

    @GetMapping("get-by-id/{utenteId}")
    public Utente getUtenteById(@PathVariable String utenteId) {

        Utente utente = utenteService.getUtenteById(utenteId);
        if(utente!=null){
            return utente;
        }else return null;
    }

    @GetMapping("get-by-id/{utenteEmail}")
    public Utente getUtenteByEmail(@PathVariable String utenteEmail) {

        Utente utente = utenteService.getUtenteByEmail(utenteEmail);
        if(utente!=null){
            return utente;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deleteUtente(@RequestBody Map<String, String> id) {
        return utenteService.deleteUtenteById(id.get("id"));
    }

}
