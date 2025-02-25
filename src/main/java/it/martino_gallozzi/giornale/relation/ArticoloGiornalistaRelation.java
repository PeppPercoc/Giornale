package it.martino_gallozzi.giornale.relation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticoloGiornalistaRelation {
    private String articoloId;
    private String giornalistaId;
}
