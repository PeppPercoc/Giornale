package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbbonamentoRepository extends MongoRepository<Abbonamento, String> {
}
