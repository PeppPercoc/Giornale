package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Pubblicazione {
    @Id
    private String codice;
    private String titolo;
    private String periodicita;
    private String prezzo;
}
