package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UtenteService {
    private final UtenteRepository utenteRepository;

}
