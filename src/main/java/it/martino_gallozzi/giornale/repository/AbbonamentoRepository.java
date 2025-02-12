package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

public interface AbbonamentoRepository extends MongoRepository<Abbonamento, String> {
    Optional<Abbonamento> findByArgomento(String argomento);

    Optional<Map<Utente, LocalDateTime>> findUsersByArgomento (String argomento);
}
