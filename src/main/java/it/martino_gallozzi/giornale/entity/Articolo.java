package it.martino_gallozzi.giornale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
@AllArgsConstructor

public class Articolo {
    @Id
    private String id;
    private String titolo;
    private String testo;
    private List<String> listaGiornalistiId = new ArrayList<>();
}
