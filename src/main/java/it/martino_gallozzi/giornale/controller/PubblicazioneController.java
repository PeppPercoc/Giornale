package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.dto.PubblicazioneRegistration;
import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.response.GenericResponse;
import it.martino_gallozzi.giornale.service.PubblicazioneService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pubblicazione")
@AllArgsConstructor
public class PubblicazioneController {
    private final PubblicazioneService pubblicazioneService;

    @PostMapping("insert-pubblicazione")
    public GenericResponse<Pubblicazione> insertPubblicazione(@RequestBody PubblicazioneRegistration registration) {
        return pubblicazioneService.insertPubblicazione(registration);
    }

    @GetMapping("get-by-id/{pubblicazioneId}")
    public GenericResponse<Pubblicazione> getPubblicazioneById(@PathVariable String pubblicazioneId) {
        return pubblicazioneService.getPubblicazioneById(pubblicazioneId);
    }

    @DeleteMapping("delete")
    public GenericResponse<Pubblicazione> deletePubblicazione(@RequestBody Map<String, String> id) {
        return pubblicazioneService.deletePubblicazioneById(id.get("pubblicazioneId"));
    }

    @PutMapping("update")
    public GenericResponse<Pubblicazione> updatePubblicazione(@RequestBody Pubblicazione pubblicazione) {
        return pubblicazioneService.updatePubblicazione(pubblicazione);
    }

    @PutMapping("add-utente")
    public GenericResponse<Pubblicazione> addUtenteById(@RequestBody Map<String, String> ids) {
        val pubblicazioneId = ids.get("pubblicazioneId");
        val utenteId = ids.get("utenteId");
        return pubblicazioneService.addUtenteById(pubblicazioneId, utenteId);
    }

    @GetMapping("get-users/{pubblicazioneId}")
    public GenericResponse<List<String>> getUsersListById(@PathVariable String pubblicazioneId){
        return pubblicazioneService.getUsersListById(pubblicazioneId);
    }

    @GetMapping("get-articles/{pubblicazioneId}")
    public GenericResponse<List<String>> getArticlesListById(@PathVariable String pubblicazioneId){
        return pubblicazioneService.getArticlesListById(pubblicazioneId);
    }
}
