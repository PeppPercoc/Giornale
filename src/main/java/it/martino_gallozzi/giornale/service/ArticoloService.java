package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.ArticoloRegistration;
import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.repository.ArticoloRepository;
import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticoloService {
    private final ArticoloRepository articoloRepository;
    private final GiornalistaRepository giornalistaRepository;

    private boolean existJournalists(@NonNull List<String> listaGiornalistiId) {
        if (listaGiornalistiId.isEmpty()) {
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
    public GenericResponse<Articolo> insertArticolo(ArticoloRegistration registration) {

        if (!existJournalists(registration.getListaGiornalistiId())) {
            return new GenericResponse<>(null, "Giornalista not found", HttpStatus.NOT_FOUND.value());
        }

        val articolo = new Articolo(registration);
        articoloRepository.insert(articolo);
        return new GenericResponse<>(null, null, HttpStatus.OK.value());
    }

    public GenericResponse<Articolo> getArticoloById(String articoloId) {
        return articoloRepository.findById(articoloId)
                .map(a -> new GenericResponse<>(a, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Articolo not found", HttpStatus.NOT_FOUND.value()));
    }

    //READ
    public GenericResponse<List<Articolo>> getArticoloByTitolo(String articoloTitolo) {
        val articoloList = articoloRepository.findArticoloByTitolo(articoloTitolo);
        return new GenericResponse<>(articoloList, null, HttpStatus.OK.value());
    }

    //UPDATE
    @Transactional
    public GenericResponse<Articolo> updateArticolo(Articolo articolo) {
        return articoloRepository.findById(articolo.getId())
                .map(a -> {
                    articoloRepository.save(articolo);
                    return new GenericResponse<>((Articolo) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null,"Articolo ID not found", HttpStatus.NOT_FOUND.value()));
    }

    //DELETE
    public GenericResponse<Articolo> deleteArticoloById(String articoloId) {
        return articoloRepository.findById(articoloId)
                .map(a -> {
                    articoloRepository.deleteById(articoloId);
                    return new GenericResponse<>((Articolo) null, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Articolo ID not found", HttpStatus.NOT_FOUND.value()));
    }
}
