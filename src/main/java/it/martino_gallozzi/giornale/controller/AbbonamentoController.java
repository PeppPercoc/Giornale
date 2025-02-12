package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.service.AbbonamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/abbonamento")
@AllArgsConstructor
public class AbbonamentoController {
    private final AbbonamentoService abbonamentoService;
}
