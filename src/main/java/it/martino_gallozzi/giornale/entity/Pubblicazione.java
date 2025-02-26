package it.martino_gallozzi.giornale.entity;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
public class Pubblicazione {
    @Id
    private String id;
    private Float prezzo;
    private String abbonamentoArgomento;
    @Nullable
    private LocalDateTime date;
    public Pubblicazione(Float prezzo, String abbonamentoArgomento) {
        this.prezzo = prezzo;
        this.abbonamentoArgomento = abbonamentoArgomento;
        this.date = LocalDateTime.now();
    }
}
