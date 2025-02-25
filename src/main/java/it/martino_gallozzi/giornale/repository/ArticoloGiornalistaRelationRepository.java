package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.relation.ArticoloGiornalistaRelation;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticoloGiornalistaRelationRepository extends MongoRepository<ArticoloGiornalistaRelation, String> {
    public List<ArticoloGiornalistaRelation> findGiornalistaArticoloRelationsByArticoloId(@NonNull String articoloId);
    public List<ArticoloGiornalistaRelation> findGiornalistaArticoloRelationsByGiornalistaId(@NonNull String giornalistaId);
    public void deleteArticoloGiornalistaRelationsByArticoloId(@NonNull String articoloId);
    public void deleteArticoloGiornalistaRelationsByGiornalistaId(@NonNull String giornalistaId);
}
