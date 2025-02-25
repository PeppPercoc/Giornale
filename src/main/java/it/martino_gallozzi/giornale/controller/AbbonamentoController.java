package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Abbonamento;
import it.martino_gallozzi.giornale.response.GenericResponse;
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
    public GenericResponse<Abbonamento> insertAbbonamento(@RequestBody Map<String, String> params) {
        return abbonamentoService.insertAbbonamento(params.get("abbonamentoId"), params.get("periodicita"));
    }

    @GetMapping("get-by-id/{abbonamentoId}")
    public GenericResponse<Abbonamento> getAbbonamentoById(@PathVariable String abbonamentoId){
        return abbonamentoService.getAbbonamentoById(abbonamentoId);
    }

    @DeleteMapping("delete")
    public GenericResponse<Abbonamento> deleteAbbonamento(@RequestBody Map<String, String> params) {
        return abbonamentoService.deleteAbbonamentoById(params.get("abbonamentoId"));
    }

    @PutMapping("update")
    public GenericResponse<Abbonamento> updateAbbonamento(@RequestBody Abbonamento abbonamento) {
        return abbonamentoService.updateAbbonamento(abbonamento);
    }

    @PutMapping("add-subscription")
    public GenericResponse<Abbonamento> addSubscriptionById(@RequestBody Map<String, String> params) {
        return abbonamentoService.addSubscriptionById(params.get("abbonamentoId"), params.get("utenteId"));
    }

    @DeleteMapping("cancel-subscription")
    public GenericResponse<Abbonamento> cancelSubscriptionById(@RequestBody Map<String, String> params){
        return abbonamentoService.cancelSubscriptionById(params.get("abbonamentoId"), params.get("utenteId"));
    }

    @GetMapping("get-subscribers/{abbonamentoId}")
    public GenericResponse<List<String>> getSubscribersListByAbbonamentoId(@PathVariable String abbonamentoId){
        return abbonamentoService.getSubscribersListByArgomento(abbonamentoId);
    }

}
