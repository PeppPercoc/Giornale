package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AbbonamentoRepository extends MongoRepository<Abbonamento, String> {
    Optional<Abbonamento> findByArgomento(String argomento);
}
