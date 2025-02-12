package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.service.PubblicazioneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pubblicazione")
@AllArgsConstructor
public class PubblicazioneController {
    private final PubblicazioneService pubblicazioneService;
}
