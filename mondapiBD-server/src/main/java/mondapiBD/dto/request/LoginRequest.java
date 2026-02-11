package mondapiBD.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String username;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String password; // Viajará cifrada en SHA-2/MD5 [8]

}
