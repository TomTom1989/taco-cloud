package tacos.web.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpSessionRequiredException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpSessionRequiredException.class)
    public ResponseEntity<?> handleSessionRequiredException(HttpSessionRequiredException ex) {
        // Log the error for more details
        log.error("Session attribute missing: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Session attribute missing");
    }
}