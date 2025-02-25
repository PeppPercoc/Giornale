package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Articolo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticoloRepository extends MongoRepository<Articolo, String> {
    List<Articolo> findArticoloByTitolo(String titolo);
    List<Articolo> findArticolosByIdIn(Collection<String> ids);
    List<Articolo> findArticolosByPubblicazioneID(String pubblicazioneId);
    boolean existsArticolosById(List<String> ids);
}