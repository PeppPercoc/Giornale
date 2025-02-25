package it.martino_gallozzi.giornale.entity;

import com.mongodb.lang.Nullable;
import it.martino_gallozzi.giornale.dto.GiornalistaRegistration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor

public class Giornalista {
    @Id
    private String id;
    private String nome;
    private String annoIscrizioneAlbo;

    public Giornalista(GiornalistaRegistration registration) {
        this.nome = registration.getNome();
        this.annoIscrizioneAlbo = registration.getAnnoIscrizioneAlbo();
    }
}
