package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import mondapiBD.model.enums.Perfil;

@Data
@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id; // El ID siempre es String en MongoDB [2]
    private String username; // Único según requisitos [7]
    private String password; // Almacenada en HASH MD5 [6]
    private Perfil perfil;
    private String idAsociado; // ID del Alumno o del Tutor según el perfil [3]
    private Boolean activo;

}