package by.bsuir.rest.document.exception;

import by.bsuir.document.exception.DocumentGenerationException;
import by.bsuir.document.exception.DocumentOpeningException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentExceptionHandlerAdvice {

    @ExceptionHandler(DocumentGenerationException.class)
    public ResponseEntity<String> handleDocGenException(DocumentGenerationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(DocumentOpeningException.class)
    public ResponseEntity<String> handleDocOpenException(DocumentOpeningException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
