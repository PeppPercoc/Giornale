package it.martino_gallozzi.giornale.entity;

import it.martino_gallozzi.giornale.dto.PubblicazioneRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Pubblicazione {
    @Id
    private String id;
    private Float prezzo;
    private String abbonamentoId;

    public Pubblicazione(Float prezzo, String abbonamentoId) {
        this.prezzo = prezzo;
        this.abbonamentoId = abbonamentoId;
    }
}
