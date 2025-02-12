package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.repository.AbbonamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbbonamentoService {
    private final AbbonamentoRepository abbonamentoRepository;

    //CREATE
    public Abbonamento insertAbbonamento(Abbonamento abbonamento) {
        abbonamentoRepository.findByArgomento(abbonamento.getArgomento()).ifPresentOrElse(s -> {
            System.out.println("Subscription " + s + " already exists");
        }, () -> {
            System.out.println("Inserting Subscription " + abbonamento);
            abbonamentoRepository.insert(abbonamento);
        });
        return abbonamento;
    }
    //READ
    public Abbonamento getAbbonamentoByArgomento(String abbonamentoArgomento){
        Optional<Abbonamento> abbonamento = abbonamentoRepository.findByArgomento(abbonamentoArgomento);
        return abbonamento.orElse(null);
    }

    public Map<Utente, LocalDateTime> getUsersByArgomento(String abbonamentoArgomento){
        Optional<Map<Utente,LocalDateTime>> listaUtenti = abbonamentoRepository.findUsersByArgomento(abbonamentoArgomento);
        return listaUtenti.orElse(null);
    }

    //UPDATE

    //DELETE
    public String deleteAbbonamentoById(String abbonamentoArgomento) {
        if (abbonamentoRepository.existsById(abbonamentoArgomento)) {
            abbonamentoRepository.deleteById(abbonamentoArgomento);
            return "Subscription " + abbonamentoArgomento + " deleted";
        } else return "Subscription arguments is not present in database";
    }
}
