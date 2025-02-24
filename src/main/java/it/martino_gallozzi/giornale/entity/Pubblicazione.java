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
    private String argomento;
    private List<String> listaArticoliId = new ArrayList<>();
    private List<String> listaUtentiId = new ArrayList<>();

    public Pubblicazione(PubblicazioneRegistration registartion) {
        this.prezzo = registartion.getPrezzo();
        this.argomento = registartion.getArgomento();
        this.listaArticoliId = registartion.getListaArticoliId();
    }
}
