package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.entity.Utente;
import it.martino_gallozzi.giornale.service.AbbonamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/v1/abbonamento")
@AllArgsConstructor
public class AbbonamentoController {
    private final AbbonamentoService abbonamentoService;

    @PostMapping("insert-abbonamento")
    public Abbonamento insertGiornalista(@RequestBody Abbonamento abbonamento) {
        return abbonamentoService.insertAbbonamento(abbonamento);
    }

    @GetMapping("get-by-argomento/{gabbonamentoArgomento}")
    public Abbonamento getGiornalistaById(@PathVariable String abbonamentoArgomento){

        Abbonamento abbonamento = abbonamentoService.getAbbonamentoByArgomento(abbonamentoArgomento);
        if(abbonamento!=null){
            return abbonamento;
        }else return null;
    }

    @GetMapping("get-users-by-argomento/{gabbonamentoArgomento}")
    public Map<Utente, LocalDateTime> getUsersByArgomento(@PathVariable String abbonamentoArgomento) {

        Map<Utente, LocalDateTime> listaAbbonamenti = abbonamentoService.getUsersByArgomento(abbonamentoArgomento);
        if(listaAbbonamenti!=null){
            return listaAbbonamenti;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deleteAbbonamento(@RequestBody Map<String, String> id) {
        return abbonamentoService.deleteAbbonamentoById(id.get("id"));
    }
}
