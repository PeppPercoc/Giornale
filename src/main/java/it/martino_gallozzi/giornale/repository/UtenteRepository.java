package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Utente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtenteRepository extends MongoRepository<Utente, String> {

    Optional<Utente> findUtenteByEmail(String email);
    boolean existsUtenteByEmail(String email);
}
