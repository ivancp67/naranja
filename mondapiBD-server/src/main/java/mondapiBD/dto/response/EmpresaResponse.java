package mondapiBD.dto.response;

import lombok.Data;

@Data
public class EmpresaResponse {
    private String id;
    private String nombre;
    private String tutorLaboralNombre;
    private String tutorLaboralEmail; // Se puede ocultar según el perfil que consulte [14]
    private String tutorLaboralTelefono;
    private Boolean activo;
}
