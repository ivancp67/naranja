package mondapiBD.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice // Indica que esta clase gestiona las excepciones de todos los controladores [1]
public class GlobalExceptionHandler {

    // 1. Manejo de ConflictException (Error 409 - Conflicto)
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> handle(ConflictException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    // 2. Manejo de InactiveUserException (Error 401 - No autorizado)
    @ExceptionHandler(InactiveUserException.class)
    public ResponseEntity<String> handle(InactiveUserException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    // 3. Manejo de IncorrectPasswordException (Error 401 - No autorizado)
    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handle(IncorrectPasswordException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    // 4. Manejo de NotFoundException (Error 404 - No encontrado) [1]
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handle(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    // 5. Manejo de NotValidException personalizada (Error 400 - Petición incorrecta)
    @ExceptionHandler(NotValidException.class)
    public ResponseEntity<String> handle(NotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    /**
     * Manejador específico para errores de validación automáticos de Spring (@Valid).
     * Devuelve el nombre del campo y el mensaje de error configurado [1].
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
            .body(e.getFieldError().getField() + ": " + e.getFieldError().getDefaultMessage());
    }

    /**
     * Manejador genérico para cualquier otra excepción no controlada.
     * Devuelve un error 500 e imprime información en el log [1, 2].
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        log.error("Error inesperado en la petición: ", e); // Importante registrar el error en el servidor [2]
        return ResponseEntity.internalServerError()
            .body("Error inesperado en el servidor. Consulte el log del servidor si tiene acceso");
    }
}
