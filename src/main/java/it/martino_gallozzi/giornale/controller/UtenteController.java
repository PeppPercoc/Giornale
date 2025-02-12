package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.service.UtenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/utente")
@AllArgsConstructor
public class UtenteController {
    private final UtenteService utenteService;
}
