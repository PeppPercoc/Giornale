package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.service.AbbonamentoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public Map<String, LocalDateTime> getUsersByArgomento(@PathVariable String abbonamentoArgomento) {

        Map<String, LocalDateTime> listaAbbonamentiId = abbonamentoService.getUsersByArgomento(abbonamentoArgomento);
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

}
