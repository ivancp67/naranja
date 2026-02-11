package mondapiBD.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "registros_practicas")
public class RegistroPractica {
    @Id
    private String id;
    private String idAlumno; // Referencia al alumno
    private String idFecha;  // Referencia a la fecha asociada
    private Double horas;   // Decimal: saltos de 0.5, entre 0 y 8 [11]
    private String descripcion; // Texto largo [4]

}
