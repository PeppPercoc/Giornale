package it.martino_gallozzi.giornale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Abbonamento {
    @Id
    private String argomento;
    private String periodicita;
    private List<String> listaUtentiId = new ArrayList<>();

    public Abbonamento(String argomento, String periodicita) {
        this.argomento = argomento;
        this.periodicita = periodicita;
    }
}
