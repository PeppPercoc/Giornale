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
    private Map<String, LocalDateTime> listaUtentiId;

    public Abbonamento(String argomento, String periodicita, Map listaUtentiId) {
        this.argomento = argomento;
        this.periodicita = periodicita;
        this.listaUtentiId = listaUtentiId;
    }
}
