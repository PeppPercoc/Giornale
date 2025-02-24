package it.martino_gallozzi.giornale.controller;

import it.martino_gallozzi.giornale.dto.GiornalistaRegistration;
import it.martino_gallozzi.giornale.entity.Giornalista;
import it.martino_gallozzi.giornale.response.GenericResponse;
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

    @PostMapping("register-giornalista")
    public GenericResponse<Giornalista> registerGiornalista(@RequestBody GiornalistaRegistration giornalista) {
        return giornalistaService.registerGiornalista(giornalista);
    }

    @GetMapping("get-by-id/{giornalistaId}")
    public GenericResponse<Giornalista> getGiornalistaById(@PathVariable String giornalistaId) throws Exception {
        return giornalistaService.getGiornalistaById(giornalistaId);
    }

    @GetMapping("get-by-name/{giornalistaName}")
    public GenericResponse<List<Giornalista>> getGiornalistaByName(@PathVariable String giornalistaName) throws Exception {
        return giornalistaService.getGiornalistaByName(giornalistaName);
    }

    @DeleteMapping("delete")
    public GenericResponse<Giornalista> deleteGiornalista(@RequestBody Map<String, String> id) throws Exception {
        return giornalistaService.deleteGiornalistaById(id.get("id"));
    }

    @PutMapping("update")
    public GenericResponse<Giornalista> updateGiornalista(@RequestBody Giornalista giornalista) throws Exception {
        return giornalistaService.updateGiornalista(giornalista);
    }
}
