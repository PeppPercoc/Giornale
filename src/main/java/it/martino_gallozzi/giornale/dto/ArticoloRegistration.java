package it.martino_gallozzi.giornale.dto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArticoloRegistration {

    private String titolo;
    private String testo;
    private List<String> listaGiornalistiId = new ArrayList<>();
}
