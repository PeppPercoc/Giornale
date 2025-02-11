package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AbbonamentoRepository extends MongoRepository<Abbonamento, String> {
    Optional<Abbonamento> findByArgomento(String argomento);
}
