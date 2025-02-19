package it.martino_gallozzi.giornale.service;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.repository.AbbonamentoRepository;
import it.martino_gallozzi.giornale.repository.UtenteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AbbonamentoService {
    private final AbbonamentoRepository abbonamentoRepository;
    private final UtenteRepository utenteRepository;

    //CREATE
    public Abbonamento insertAbbonamento(Abbonamento abbonamento) {
        if(! abbonamento.getListaUtentiId().isEmpty())
            abbonamento.getListaUtentiId().clear();

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

    public List<String> getUsersByArgomento(String abbonamentoArgomento){
        Optional<List<String>> listaUtentiId = abbonamentoRepository.findUsersByArgomento(abbonamentoArgomento);
        return listaUtentiId.orElse(null);
    }

    //UPDATE
    public Abbonamento updateAbbonamento(Abbonamento abbonamento) {
        Optional<Abbonamento> existingAbbonamento = abbonamentoRepository.findById(abbonamento.getArgomento());

        if (existingAbbonamento.isPresent()) {
            if(existingAbbonamento.get().getListaUtentiId().equals(abbonamento.getListaUtentiId())) {
                System.out.println("Subscription " + abbonamento.getArgomento() + " updated");
                return abbonamentoRepository.save(abbonamento);
            }
            else{
                System.out.println("Update denied: impossible to modify users list");
                return null;
            }
        } else {
            System.out.println("Update denied: subscription not found");
            return null;
        }
    }
    //DELETE
    public String deleteAbbonamentoById(String abbonamentoArgomento) {
        if (abbonamentoRepository.existsById(abbonamentoArgomento)) {
            abbonamentoRepository.deleteById(abbonamentoArgomento);
            return "Subscription " + abbonamentoArgomento + " deleted";
        } else return "Subscription arguments is not present in database";
    }

    // Aggiungi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public String addSubscriptionById (String abbonamentoArgomento, String utenteId){
        Optional<Abbonamento> abbonamento = abbonamentoRepository.findById(abbonamentoArgomento);

        if(abbonamento.isEmpty())
            return "Subscription not found, action denied";

        if(abbonamento.get().getListaUtentiId().contains(utenteId))
            return "User already subscribed, action denied";

        abbonamento.get().getListaUtentiId().add(utenteId);
        abbonamentoRepository.save(abbonamento.get());
        return "Subscription successful";
    }


    // Rimuovi un utente dalla lista degli abbonati di un abbonamento
    @Transactional
    public String cancelSubscriptionById(String abbonamentoArgomento, String utenteId){
       Optional<Abbonamento> abbonamento = abbonamentoRepository.findById(abbonamentoArgomento);

       if(abbonamento.isEmpty())
           return "Subscription not found, cancellation denied";

       if(! abbonamento.get().getListaUtentiId().contains(utenteId))
           return "User not subscribed, cancellation denied";

        abbonamento.get().getListaUtentiId().remove(utenteId);
        abbonamentoRepository.save(abbonamento.get());
        return "Subscription cancelled";
    }
}
