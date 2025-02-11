package it.martino_gallozzi.giornale.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
/*
Entità creata per avere nel database gli argomenti,
la lista può essere modificata senza bloccare il programma
 */
public class Argomento {
    @Id
    private String nome;
}
