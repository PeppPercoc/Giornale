package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document

public class Articolo {
    @Id
    private String titolo;
    private String testo;
    private List<Giornalista> listaGiornalisti;

    public Articolo(String titolo, String testo, List<Giornalista> listaGiornalisti) {
        this.titolo = titolo;
        this.testo = testo;
        this.listaGiornalisti = listaGiornalisti;
    }
}
