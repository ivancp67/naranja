package mondapiBD.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class RegistroPracticaRequest {
    @NotBlank
    private String idFecha;
    
    @Positive(message = "Las horas deben ser mayores a 0")
    @Max(value = 8, message = "No se pueden registrar más de 8 horas diarias")
    private Double horas; // Debe validar saltos de 0.5 en el Service [7]
    
    @NotBlank
    private String descripcion;

}
