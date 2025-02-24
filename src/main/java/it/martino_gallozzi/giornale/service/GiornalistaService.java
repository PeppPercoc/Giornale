package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.GiornalistaRegistration;
import it.martino_gallozzi.giornale.entity.Giornalista;
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
public class GiornalistaService {
    private final GiornalistaRepository giornalistaRepository;

    //CREATE
    public GenericResponse<Giornalista> registerGiornalista(GiornalistaRegistration registartion) {
        val giornalista = new Giornalista(registartion);
        giornalistaRepository.insert(giornalista);
        return new GenericResponse<>(giornalista, null, HttpStatus.OK.value());
    }
    //READ
    public GenericResponse<Giornalista> getGiornalistaById(String giornalistaId) {
        return giornalistaRepository.findById(giornalistaId)
                .map(g -> new GenericResponse<>(g, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Giornalista not found", HttpStatus.NOT_FOUND.value()));
    }

    public GenericResponse<List<Giornalista>> getGiornalistaByName(String giornalistaNome) {
        return giornalistaRepository.findGiornalistaByNome(giornalistaNome)
                .map(l -> new GenericResponse<>(l, null, HttpStatus.OK.value()))
                .orElse(new GenericResponse<>(null, "Giornalista not found", HttpStatus.NOT_FOUND.value()));
    }
    //UPDATE
    @Transactional
    public GenericResponse<Giornalista> updateGiornalista(Giornalista giornalista) {
        return giornalistaRepository.findById(giornalista.getId())
                .map(g -> {
                    giornalistaRepository.save(giornalista);
                    return new GenericResponse<>(giornalista, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Giornalista ID not found", HttpStatus.NOT_FOUND.value()));
    }
    //DELETE
    @Transactional
    public GenericResponse<Giornalista> deleteGiornalistaById(String giornalistaId) {
        return giornalistaRepository.findById(giornalistaId)
                .map(g -> {
                    giornalistaRepository.deleteById(giornalistaId);
                    return new GenericResponse<>(g, null, HttpStatus.OK.value());
                })
                .orElse(new GenericResponse<>(null, "Giornalista ID not found", HttpStatus.NOT_FOUND.value()));
    }
}
