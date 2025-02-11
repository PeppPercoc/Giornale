package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Articolo {
    @Id
    private String id;
    private String titolo;
    private String testo;

    public Articolo(String id, String titolo, String testo) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
    }
}
