package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.dto.ArticoloRegistration;
import it.martino_gallozzi.giornale.entity.Articolo;
import it.martino_gallozzi.giornale.response.GenericResponse;
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
    public GenericResponse<Articolo> insertArticolo(@RequestBody ArticoloRegistration registration) throws Exception {
        return articoloService.insertArticolo(registration);
    }

    @GetMapping("get-by-titolo/{articoloTitolo}")
    public GenericResponse<List<Articolo>> getArticoloByTitolo(@PathVariable String articoloTitolo) throws Exception {
        return articoloService.getArticoloByTitolo(articoloTitolo);
    }

    @DeleteMapping("delete")
    public GenericResponse<Articolo> deleteArticolo(@RequestBody Map<String, String> id) throws Exception {
        return articoloService.deleteArticoloById(id.get("id"));
    }

    @PutMapping("update")
    public GenericResponse<Articolo> updateArticolo(@RequestBody Articolo articolo) throws Exception {
        return articoloService.updateArticolo(articolo);
    }

}
