package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Giornalista;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface GiornalistaRepository extends MongoRepository<Giornalista, String> {
    Optional<List<Giornalista>> findGiornalistaByNome(String nome);
}
