package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Articolo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ArticoloRepository extends MongoRepository<Articolo, String> {
    Optional<List<Articolo>> findArticoloByTitolo(String titolo);
    Optional<List<String>> findlistaGiornalistiIdbyTitolo(String titolo);
}