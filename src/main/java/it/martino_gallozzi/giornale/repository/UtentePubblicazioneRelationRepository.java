package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.relation.UtentePubblicazioneRelation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtentePubblicazioneRelationRepository extends MongoRepository<UtentePubblicazioneRelation, String> {
    public List<UtentePubblicazioneRelation> findUtentePubblicazioneRelationsByUtenteId(String utenteId);
    public List<UtentePubblicazioneRelation> findUtentePubblicazioneRelationsByPubblicazioneId(String pubblicazioneId);
    public void deleteUtentePubblicazioneRelationsByUtenteId(String utenteId);
    public void deleteUtentePubblicazioneRelationsByPubblicazioneId(String pubblicazioneId);
}
