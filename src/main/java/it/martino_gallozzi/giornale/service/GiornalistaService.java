package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.dto.GiornalistaRegistration;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import it.martino_gallozzi.giornale.response.GenericResponse;
import lombok.AllArgsConstructor;
import lombok.var;
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
        var giornalista = new Giornalista(registartion);
        giornalistaRepository.insert(giornalista);
        return new GenericResponse<>(giornalista, null, HttpStatus.OK.value());
    }
    //READ
    public GenericResponse<Giornalista> getGiornalistaById(String giornalistaId) throws Exception {
        return giornalistaRepository.findById(giornalistaId)
                .map(g -> new GenericResponse<>(g, null, HttpStatus.OK.value()))
                .orElseThrow(() -> new Exception("Giornalista not found"));
    }

    public GenericResponse<List<Giornalista>> getGiornalistaByName(String giornalistaNome) throws Exception {
        return giornalistaRepository.findGiornalistaByNome(giornalistaNome)
                .map(l -> new GenericResponse<>(l, null, HttpStatus.OK.value()))
                .orElseThrow(() -> new Exception("Giornalista not found"));
    }
    //UPDATE
    @Transactional
    public GenericResponse<Giornalista> updateGiornalista(Giornalista giornalista) throws Exception {
        return giornalistaRepository.findById(giornalista.getId())
                .map(g -> {
                    giornalistaRepository.save(g);
                    return new GenericResponse<>(g, null, HttpStatus.OK.value());
                })
                .orElseThrow(() -> new Exception("Giornalista ID not found"));
    }
    //DELETE
    @Transactional
    public GenericResponse<Giornalista> deleteGiornalistaById(String giornalistaId) throws Exception {
        return giornalistaRepository.findById(giornalistaId)
                .map(g -> new GenericResponse<>(g, null, HttpStatus.OK.value()))
                .orElseThrow(() -> new Exception("Giornalista ID not found"));
    }
}
