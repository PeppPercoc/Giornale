package it.martino_gallozzi.giornale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor

public class Utente {
    @Id
    private String id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String citta;
    private String cap;
    @Indexed(unique = true)
    private String email;
}
