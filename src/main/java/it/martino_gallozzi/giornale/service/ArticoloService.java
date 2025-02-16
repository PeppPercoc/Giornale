package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticoloService {
    private final ArticoloRepository articoloRepository;
    private final GiornalistaRepository giornalistaRepository;

    //CREATE
    public boolean verificaGiornalistiEsistenti(List<String> listaGiornalistiId) {
        if (listaGiornalistiId == null || listaGiornalistiId.isEmpty()) {
            return false; // Non accettiamo articoli senza giornalisti
        }

        // Conta quanti ID esistono nel database
        long count = giornalistaRepository.countByIdIn(listaGiornalistiId);

        // Se il numero di ID trovati è uguale alla lista passata, significa che tutti gli ID esistono
        return count == listaGiornalistiId.size();
    }

    @Transactional //rispetta proprietà acid
    public Articolo insertArticolo(Articolo articolo) {
        boolean giornalistiValidi = verificaGiornalistiEsistenti(articolo.getListaGiornalistiId());

        if (!giornalistiValidi) {
            return null;
        }

        articoloRepository.findArticoloByTitolo(articolo.getTitolo()).ifPresentOrElse(s -> {
            System.out.println("Article " + s + " already exists");
        }, () -> {
            System.out.println("Inserting article " + articolo);
            articoloRepository.insert(articolo);
        });
        return articolo;
    }
    //READ
    public List<Articolo> getArticoloByTitolo(String articoloTitolo){
        Optional<List<Articolo>> listaArticolo = articoloRepository.findArticoloByTitolo(articoloTitolo);
        return listaArticolo.orElse(null);
    }

    //UPDATE
    public Articolo updateArticolo(Articolo articolo) {
        Optional<Articolo> existingArticolo = articoloRepository.findById(articolo.getId());

        boolean giornalistiValidi = verificaGiornalistiEsistenti(articolo.getListaGiornalistiId());

        if (!giornalistiValidi) {
            return null;
        }

        if (existingArticolo.isPresent()) {
            System.out.println("Student " + articolo.getId() + " updated");
            return articoloRepository.save(articolo);
        } else {
            return null;
        }
    }

    //DELETE
    public String deleteArticoloById(String articoloTitolo) {
        if (articoloRepository.existsById(articoloTitolo)) {
            articoloRepository.deleteById(articoloTitolo);
            return "Article " + articoloTitolo + " deleted";
        } else return "Article title is not present in database";
    }

    //todo: add Giornalista (tescnicamente si puo fare con update)
}
