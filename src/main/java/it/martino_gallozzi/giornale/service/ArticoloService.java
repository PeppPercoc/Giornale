package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ArticoloService {
    private final ArticoloRepository articoloRepository;
}
