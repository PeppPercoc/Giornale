package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.service.GiornalistaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/giornalista")
@AllArgsConstructor
public class GiornalistaController {
    private final GiornalistaService giornalistaService;
}
