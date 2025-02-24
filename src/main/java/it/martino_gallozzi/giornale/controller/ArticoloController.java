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

    @PostMapping("register-articolo")
    public GenericResponse<Articolo> insertArticolo(@RequestBody ArticoloRegistration registration) {
        return articoloService.insertArticolo(registration);
    }

    @GetMapping("get-by-id/{articoloId}")
    public GenericResponse<Articolo> getArticoloById(@PathVariable String articoloId)  {
        return articoloService.getArticoloById(articoloId);
    }

    @GetMapping("get-by-titolo/{articoloTitolo}")
    public GenericResponse<List<Articolo>> getArticoloByTitolo(@PathVariable String articoloTitolo)  {
        return articoloService.getArticoloByTitolo(articoloTitolo);
    }

    @DeleteMapping("delete")
    public GenericResponse<Articolo> deleteArticolo(@RequestBody Map<String, String> id)  {
        return articoloService.deleteArticoloById(id.get("articoloId"));
    }

    @PutMapping("update")
    public GenericResponse<Articolo> updateArticolo(@RequestBody Articolo articolo)  {
        return articoloService.updateArticolo(articolo);
    }

}
