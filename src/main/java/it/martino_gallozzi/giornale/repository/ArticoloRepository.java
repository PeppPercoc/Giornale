package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Articolo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ArticoloRepository extends MongoRepository<Articolo, String> {
    Optional<Articolo> findArticoloByTitolo(String titolo);
}