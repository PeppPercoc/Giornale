package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtenteRepository extends MongoRepository<Utente, String> {

    Optional<Utente> findUtenteByEmail(String email);
}
