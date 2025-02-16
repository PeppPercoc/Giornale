package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.service.ArticoloService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/articolo")
@AllArgsConstructor
public class ArticoloController {
    private final ArticoloService articoloService;

    @PostMapping("insert-articolo")
    public Articolo insertArticolo(@RequestBody Articolo articolo) {
        return articoloService.insertArticolo(articolo);
    }

    @GetMapping("get-by-titolo/{articoloTitolo}")
    public List<Articolo> getArticoloById(@PathVariable String articoloTitolo) {

        List<Articolo> listaArticoli = articoloService.getArticoloByTitolo(articoloTitolo);
        if(listaArticoli!=null){
            return listaArticoli;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deleteArticolo(@RequestBody Map<String, String> id) {
        return articoloService.deleteArticoloById(id.get("id"));
    }

    @PutMapping("update")
    public Articolo updateArticolo(@RequestBody Articolo articolo) {
        return articoloService.updateArticolo(articolo);
    }
}
