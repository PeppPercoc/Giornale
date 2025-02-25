package it.martino_gallozzi.giornale.entity;

import com.mongodb.lang.Nullable;
import it.martino_gallozzi.giornale.dto.ArticoloRegistration;
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

public class Articolo {
    @Id
    private String id;
    private String titolo;
    private String testo;
    @Nullable
    private String pubblicazioneID = null;

    public Articolo(String titolo, String testo) {
        this.titolo = titolo;
        this.testo = testo;
    }
}
