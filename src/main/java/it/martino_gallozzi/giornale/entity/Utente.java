package it.martino_gallozzi.giornale.entity;

import it.martino_gallozzi.giornale.dto.UtenteRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
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

    public Utente(UtenteRegistration registration) {
        this.nome = registration.getNome();
        this.cognome = registration.getCognome();
        this.indirizzo = registration.getIndirizzo();
        this.citta = registration.getCitta();
        this.cap = registration.getCap();
        this.email = registration.getEmail();
    }
}
