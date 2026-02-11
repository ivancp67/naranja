package mondapiBD.dto.response;

import lombok.Data;

@Data
public class AlumnoResponse {
    private String nombreCompleto;
    private String ciclo;
    private String nombreEmpresa;
    private String nombreTutorDocente;
    
    // Resumen de horas [14]
    private Double horasTotales;
    private Double horasRealizadas;
    private Double porcentajeCompletado;
    private Double horasPendientes;
}
