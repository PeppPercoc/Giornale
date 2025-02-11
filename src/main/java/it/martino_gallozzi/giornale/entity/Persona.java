package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Persona {
    @Id
    private String id;
    private String nome;
    private String cognome;
    private String indirizzo;
    private String citta;
    private String cap;
    private String email;
    private String password;
}
