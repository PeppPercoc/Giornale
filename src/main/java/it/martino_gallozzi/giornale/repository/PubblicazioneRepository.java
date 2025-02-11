package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PubblicazioneRepository extends MongoRepository<Pubblicazione, String> {
}
