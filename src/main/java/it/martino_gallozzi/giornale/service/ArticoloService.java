package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticoloService {
    private final ArticoloRepository articoloRepository;

    //CREATE
    public Articolo insertArticolo(Articolo articolo) {
        articoloRepository.findArticoloByTitolo(articolo.getTitolo()).ifPresentOrElse(s -> {
            System.out.println("Article " + s + " already exists");
        }, () -> {
            System.out.println("Inserting article " + articolo);
            articoloRepository.insert(articolo);
        });
        return articolo;
    }
    //READ
    public Articolo getArticoloByTitolo(String articoloTitolo){
        Optional<Articolo> articolo = articoloRepository.findArticoloByTitolo(articoloTitolo);
        return articolo.orElse(null);
    }

    //UPDATE

    //DELETE
    public String deleteArticoloById(String articoloTitolo) {
        if (articoloRepository.existsById(articoloTitolo)) {
            articoloRepository.deleteById(articoloTitolo);
            return "Article " + articoloTitolo + " deleted";
        } else return "Article title is not present in database";
    }

    //todo: read Articolo.testo
    //todo: add Giornalista
}
