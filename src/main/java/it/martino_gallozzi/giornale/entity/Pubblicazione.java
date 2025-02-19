package it.martino_gallozzi.giornale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Document
@AllArgsConstructor

public class Pubblicazione {
    @Id
    private String id;
    private String titolo;
    private String prezzo;
    private String argomento;
    private List<String> listaArticoliId = new ArrayList<>();
    private List<String> listaUtentiId = new ArrayList<>();
}
