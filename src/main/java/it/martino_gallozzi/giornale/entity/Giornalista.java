package it.martino_gallozzi.giornale.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor

public class Giornalista {
    @Id
    private String id;
    private String nome;
    private String annoIscrizioneAlbo;
}
