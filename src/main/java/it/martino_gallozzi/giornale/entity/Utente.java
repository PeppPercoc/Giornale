package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

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
    private String password;

    public Utente(String id, String nome, String cognome, String indirizzo,
                  String citta, String cap, String email, String password) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.cap = cap;
        this.email = email;
        this.password = password;
    }
}
