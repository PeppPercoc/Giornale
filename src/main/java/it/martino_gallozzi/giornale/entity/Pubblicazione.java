package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Document

public class Pubblicazione {
    @Id
    private String id;
    private String titolo;
    private String prezzo;
    private String argomento;
    private List<String> listaArticoliId;
    private List<String> listaUtentiId;

    public Pubblicazione(String id, String titolo, String prezzo,List listaAricoliId, List listaUtentiId) {
        this.id = id;
        this.titolo = titolo;
        this.prezzo = prezzo;
        this.listaArticoliId = listaArticoliId;
        this.listaUtentiId = listaUtentiId;
    }
}
