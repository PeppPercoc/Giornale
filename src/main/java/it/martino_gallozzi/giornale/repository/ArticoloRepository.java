package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Articolo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticoloRepository extends MongoRepository<Articolo, String> {
    List<Articolo> findArticoloByTitolo(String titolo);
    long countByIdIn (List<String> listaArticoliId);
}