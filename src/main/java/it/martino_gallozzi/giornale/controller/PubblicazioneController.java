package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import it.martino_gallozzi.giornale.service.PubblicazioneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("get-by-id/{pubblicazioneId}")
    public Pubblicazione getPubblicazioneById(@PathVariable String pubblicazioneId) {

        Pubblicazione pubblicazione = pubblicazioneService.getPubblicazioneById(pubblicazioneId);
        if(pubblicazione!=null){
            return pubblicazione;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deletePubblicazione(@RequestBody Map<String, String> id) {
        return pubblicazioneService.deletePubblicazioneById(id.get("id"));
    }

}
