package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GiornalistaService {
    private final GiornalistaRepository giornalistaRepository;
}
