package it.martino_gallozzi.giornale.repository;

import it.martino_gallozzi.giornale.entity.Giornalista;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GiornalistaRepository extends MongoRepository<Giornalista, String> {
    long countByIdIn(List<String> listaGiornalistiId);

    boolean existsAllById(List<String> ids);

    List<Giornalista> findGiornalistasByNome(String nome);


}
