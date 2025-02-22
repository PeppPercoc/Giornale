package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.service.PubblicazioneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/pubblicazione")
@AllArgsConstructor
public class PubblicazioneController {
    private final PubblicazioneService pubblicazioneService;

    @PostMapping("insert-pubblicazione")
    public Pubblicazione insertPubblicazione(@RequestBody Pubblicazione pubblicazione) {
        return pubblicazioneService.insertPubblicazione(pubblicazione);
    }

    @GetMapping("get-by-id/{pubblicazioneTitolo}")
    public Pubblicazione getPubblicazioneByTitolo(@PathVariable String pubblicazioneTitolo) {

        Pubblicazione pubblicazione = pubblicazioneService.getPubblicazioneByTitolo(pubblicazioneTitolo);
        if(pubblicazione!=null){
            return pubblicazione;
        }else{
            return null;
        }
    }

    @DeleteMapping("delete")
    public String deletePubblicazione(@RequestBody Map<String, String> id) {
        return pubblicazioneService.deletePubblicazioneById(id.get("id"));
    }

    @PutMapping("update")
    public Pubblicazione updatePubblicazione(@RequestBody Pubblicazione pubblicazione) {
        return pubblicazioneService.updatePubblicazione(pubblicazione);
    }

    @PutMapping("add-utente/{pubblicazioneId}/{utenteId}")
    public Pubblicazione addUtenteById(String pubblicazioneId, String utenteId) {

        boolean success = pubblicazioneService.addUtenteById(pubblicazioneId, utenteId);

        if (success) {
            return pubblicazioneService.getPubblicazioneById(pubblicazioneId);
        } else {
            return null;
        }
    }

    @GetMapping("get-users/{pubblicazioneId}")
    public List<String> getUsersListById(String pubblicazioneId){
        return pubblicazioneService.getUsersListById(pubblicazioneId);
    }

    @GetMapping("get-articles/{pubblicazioneId}")
    public List<String> getArticlesListById(String pubblicazioneId){
        return pubblicazioneService.getArticlesListById(pubblicazioneId);
    }
}
