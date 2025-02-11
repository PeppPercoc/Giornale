package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Giornalista;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GiornalistaRepository extends MongoRepository<Giornalista, String> {
}
