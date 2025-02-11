package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Articolo {
    @Id
    private String titolo;
    private String testo;
    private Giornalista giornalista;

    public Articolo(String titolo, String testo, Giornalista giornalista) {
        this.titolo = titolo;
        this.testo = testo;
        this.giornalista = giornalista;
    }
}
