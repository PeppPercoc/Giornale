package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.repository.GiornalistaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GiornalistaService {
    private final GiornalistaRepository giornalistaRepository;

    //CREATE
    public Giornalista insertGiornalista(Giornalista giornalista) {
        giornalistaRepository.findById(giornalista.getId()).ifPresentOrElse(s -> {
            System.out.println("Journalist " + s + " already exists");
        }, () -> {
            System.out.println("Inserting journalist " + giornalista);
            giornalistaRepository.insert(giornalista);
        });
        return giornalista;
    }
    //READ
    public Giornalista getGiornalistatById(String giornalistaId) {
        Optional<Giornalista> giornalista = giornalistaRepository.findById(giornalistaId);
        return giornalista.orElse(null);
    }

    public List<Giornalista> getGiornalistatByName(String giornalistaNome) {
        Optional<List<Giornalista>> listaGiornalista = giornalistaRepository.findGiornalistaByNome(giornalistaNome);
        return listaGiornalista.orElse(null);
    }
    //UPDATE

    //DELETE
    public String deleteGiornalistaById(String giornalistaId) {
        if (giornalistaRepository.existsById(giornalistaId)) {
            giornalistaRepository.deleteById(giornalistaId);
            return "Journalist " + giornalistaId + " deleted";
        } else return "Journalist id is not present in database";
    }
}
