package it.martino_gallozzi.giornale.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PubblicazioneRegistration {
    private Float prezzo;
    private String abbonamentoArgomento;
    private List<String> listaArticoliId = new ArrayList<>();
}
