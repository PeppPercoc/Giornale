package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Abbonamento {
    @Id
    private String id;
    private String scadenza;
    private String argomento;
    private String data;

    public Abbonamento(String id, String scadenza, String argomento, String data) {
        this.id = id;
        this.scadenza = scadenza;
        this.argomento = argomento;
        this.data = data;
    }
}
