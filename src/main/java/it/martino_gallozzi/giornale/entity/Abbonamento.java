package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Document

public class Abbonamento {
    @Id
    private String argomento;
    private String periodicita;
    private Map<Utente, LocalDateTime> listaUtenti;

    public Abbonamento( String scadenza, String argomento, String periodicita, Map listaUtenti) {
        this.argomento = argomento;
        this.periodicita = periodicita;
        this.listaUtenti = listaUtenti;
    }
}
