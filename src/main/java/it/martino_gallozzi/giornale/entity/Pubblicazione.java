package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document

public class Pubblicazione {
    @Id
    private String id;
    private String titolo;
    private String prezzo;
    private List<Articolo> listaArticoli;

    public Pubblicazione(String id, String titolo, String prezzo,List<Articolo> listaAricoli) {
        this.id = id;
        this.titolo = titolo;
        this.prezzo = prezzo;
        this.listaArticoli = listaAricoli;
    }
}
