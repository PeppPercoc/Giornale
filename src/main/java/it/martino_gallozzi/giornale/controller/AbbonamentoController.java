package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.service.AbbonamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/abbonamento")
@AllArgsConstructor
public class AbbonamentoController {
    private final AbbonamentoService abbonamentoService;

    @PostMapping("insert-abbonamento")
    public Abbonamento insertAbbonamento(@RequestBody Abbonamento abbonamento) {
        return abbonamentoService.insertAbbonamento(abbonamento);
    }

    @GetMapping("get-by-argomento/{abbonamentoArgomento}")
    public Abbonamento getAbbonamentoByArgomento(@PathVariable String abbonamentoArgomento){

        Abbonamento abbonamento = abbonamentoService.getAbbonamentoByArgomento(abbonamentoArgomento);
        if(abbonamento!=null){
            return abbonamento;
        }else return null;
    }

    @GetMapping("get-users-by-argomento/{abbonamentoArgomento}")
    public List<String> getUsersByArgomento(@PathVariable String abbonamentoArgomento) {

        List<String> listaAbbonamentiId = abbonamentoService.getUsersByArgomento(abbonamentoArgomento);
        if(listaAbbonamentiId!=null){
            return listaAbbonamentiId;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deleteAbbonamento(@RequestBody Map<String, String> id) {
        return abbonamentoService.deleteAbbonamentoById(id.get("id"));
    }

    @PutMapping("update")
    public Abbonamento updateAbbonamento(@RequestBody Abbonamento abbonamento) {
        return abbonamentoService.updateAbbonamento(abbonamento);
    }

    @PutMapping("add-subscription/{abbonamentoArgomento}/{utenteId}")
    public Abbonamento addSubscriptionById(String abbonamentoArgomento, String utenteId) {

        boolean success = abbonamentoService.addSubscriptionById(abbonamentoArgomento, utenteId);

        if (success) {
            return abbonamentoService.getAbbonamentoByArgomento(abbonamentoArgomento);
        } else {
            return null;
        }
    }

    @DeleteMapping("cancel-subscription/{abbonamentoArgomento}/{utenteId}")
    public Abbonamento cancelSubscriptionById(String abbonamentoArgomento, String utenteId) {

        boolean success = abbonamentoService.cancelSubscriptionById(abbonamentoArgomento, utenteId);

        if (success) {
            return abbonamentoService.getAbbonamentoByArgomento(abbonamentoArgomento);
        } else {
            return null;
        }
    }

    @GetMapping("get-subscribers/{abbonamentoArgomento}")
    public List<String> getSubscribersListById(String abbonamentoArgomento){
        return abbonamentoService.getSubscribersListById(abbonamentoArgomento);
    }
}
