package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.service.ArticoloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/articolo")
@AllArgsConstructor
public class ArticoloController {
    private final ArticoloService articoloService;
}
