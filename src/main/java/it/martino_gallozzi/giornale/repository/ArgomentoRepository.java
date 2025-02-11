package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Argomento;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArgomentoRepository extends MongoRepository<Argomento, String> {
}
