package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.service.GiornalistaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/giornalista")
@AllArgsConstructor
public class GiornalistaController {
    private final GiornalistaService giornalistaService;

    @PostMapping("insert-giornalista")
    public Giornalista insertGiornalista(@RequestBody Giornalista giornalista) {
        return giornalistaService.insertGiornalista(giornalista);
    }

    @GetMapping("get-by-id/{giornalistaId}")
    public Giornalista getGiornalistaById(@PathVariable String giornalistaId) {

        Giornalista giornalista = giornalistaService.getGiornalistaById(giornalistaId);
        if(giornalista!=null){
            return giornalista;
        }else return null;
    }

    @GetMapping("get-by-id/{giornalistaName}")
    public List<Giornalista> getGiornalistaByEmail(@PathVariable String giornalistaName) {

        List<Giornalista> listaGiornalisti = giornalistaService.getGiornalistaByName(giornalistaName);
        if(listaGiornalisti!=null){
            return listaGiornalisti;
        }else return null;
    }

    @DeleteMapping("delete")
    public String deleteGiornalista(@RequestBody Map<String, String> id) {
        return giornalistaService.deleteGiornalistaById(id.get("id"));
    }
}
