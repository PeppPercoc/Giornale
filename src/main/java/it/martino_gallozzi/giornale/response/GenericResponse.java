package it.martino_gallozzi.giornale.response;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericResponse <T>{
    @Nullable
    private T data = null;
    @Nullable
    private String error;
    private Integer status;
}
