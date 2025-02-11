package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PubblicazioneRepository extends MongoRepository<Pubblicazione, String> {
    @Override
    Optional<Pubblicazione> findById(String s);
}
