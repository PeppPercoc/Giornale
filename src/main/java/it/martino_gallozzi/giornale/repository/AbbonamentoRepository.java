package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AbbonamentoRepository extends MongoRepository<Abbonamento, String> {
    Optional<Abbonamento> findByArgomento(String argomento);
    boolean existsAbbonamentoByArgomento(String argomento);
}
