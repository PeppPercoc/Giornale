package it.martino_gallozzi.giornale.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtentePubblicazioneRelation {
    private String utenteId;
    private String pubblicazioneId;
}
