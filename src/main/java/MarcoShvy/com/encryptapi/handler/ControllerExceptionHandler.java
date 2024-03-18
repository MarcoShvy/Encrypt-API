package MarcoShvy.com.encryptapi.handler;

import MarcoShvy.com.encryptapi.domain.operation.exceptions.OperationNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(OperationNotFoundException.class)
    public ResponseEntity notFoundId(OperationNotFoundException exception) {
        return ResponseEntity.status(404).body(exception.getMessage());
    }
}
