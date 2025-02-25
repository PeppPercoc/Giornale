package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.relation.UtenteAbbonamentoRelation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UtenteAbbonamentoRelationRepository extends MongoRepository<UtenteAbbonamentoRelation, String> {
    List<UtenteAbbonamentoRelation> findUtenteAbbonamentoRelationsByAbbonamentoId(String abbonamentoId);
    boolean existsUtenteAbbonamentoRelationByUtenteIdAndAbbonamentoId(String utenteId, String abbonamentoId);
    void deleteUtenteAbbonamentoRelationsByAbbonamentoId(String abbonamentoId);
    void deleteUtenteAbbonamentoRelationByUtenteIdAndAbbonamentoId(String utenteId, String abbonamentoId);
    void deleteUtenteAbbonamentoRelationsByUtenteId(String utenteId);
}
