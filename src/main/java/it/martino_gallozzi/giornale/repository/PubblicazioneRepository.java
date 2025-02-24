package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Pubblicazione;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PubblicazioneRepository extends MongoRepository<Pubblicazione, String> {

}
