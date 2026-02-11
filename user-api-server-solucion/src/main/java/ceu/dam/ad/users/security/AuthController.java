package ceu.dam.ad.users.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.users.dto.request.LoginRequestDto;
import ceu.dam.ad.users.dto.response.LoginResponseDto;
import ceu.dam.ad.users.exception.UserException;
import ceu.dam.ad.users.exception.UserNotFoundException;
import ceu.dam.ad.users.exception.UserUnauthorizedException;
import ceu.dam.ad.users.model.User;
import ceu.dam.ad.users.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service; // Tu servicio que valida credenciales

    @Autowired
    private JwtUtil jwtUtil; // Ponemos JwtUtil aquí

    @PostMapping("/login")
    @Operation(summary = "Permite hacer login a un usuario utilizando su username o su email")
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto request) 
            throws UserNotFoundException, UserUnauthorizedException, UserException {
        
        // 1. Validar credenciales (esto ya lo tienes)
        User user = service.login(request.getLogin(), request.getPassword());
        
        // 2. Mapear usuario a DTO
        LoginResponseDto response = new ModelMapper().map(user, LoginResponseDto.class);
        
        // 3. Generar token usando JwtUtil
        String token = jwtUtil.generateToken(user.getUsername());
        
        // 4. Añadir token al response
        response.setToken(token);
        
        return response;
   }
}

