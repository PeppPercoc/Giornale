package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.ArticoloRegistration;
import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticoloService {
    private final ArticoloRepository articoloRepository;
    private final GiornalistaRepository giornalistaRepository;

    private boolean existJournalists(List<String> listaGiornalistiId) {
        if (listaGiornalistiId == null || listaGiornalistiId.isEmpty()) {
            return false; // Non accettiamo articoli senza giornalisti
        }

        // Conta quanti ID esistono nel database
        long count = giornalistaRepository.countByIdIn(listaGiornalistiId);

        // Se il numero di ID trovati è uguale alla lista passata, significa che tutti gli ID esistono
        return count == listaGiornalistiId.size();
    }

     //rispetta proprietà acid
     //CREATE
    @Transactional
    public GenericResponse<Articolo> insertArticolo(ArticoloRegistration registration) throws Exception {

        if (!existJournalists(registration.getListaGiornalistiId())) {
            throw new Exception("Giornalista not found");
        }

        val articolo = new Articolo(registration);
        articoloRepository.insert(articolo);
        return new GenericResponse<>(articolo, null, HttpStatus.OK.value());
    }
    //READ
    public GenericResponse<List<Articolo>> getArticoloByTitolo(String articoloTitolo) throws Exception {
        return articoloRepository.findArticoloByTitolo(articoloTitolo)
                .map(l -> new GenericResponse<>(l, null, HttpStatus.OK.value()))
                .orElseThrow(() -> new Exception("Articolo not found"));
    }

    //UPDATE
    @Transactional
    public GenericResponse<Articolo> updateArticolo(Articolo articolo) throws Exception {
        return articoloRepository.findById(articolo.getId())
                .map(a -> {
                    articoloRepository.save(articolo);
                    return new GenericResponse<>(articolo, null, HttpStatus.OK.value());
                })
                .orElseThrow(() -> new Exception("Articolo ID not found"));
    }


    //DELETE
    public GenericResponse<Articolo> deleteArticoloById(String articoloId) throws Exception {
        return articoloRepository.findById(articoloId)
                .map(a -> {
                    articoloRepository.deleteById(articoloId);
                    return new GenericResponse<>(a, null, HttpStatus.OK.value());
                })
                .orElseThrow(() -> new Exception("Articolo ID not found"));
    }
}
