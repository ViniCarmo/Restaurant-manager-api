package dev.vinicius.restaurant_management_api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

@ExceptionHandler(EntityNotFoundException.class)
private ResponseEntity<ProblemDetail> userNotFound(EntityNotFoundException e){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
}

@ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ProblemDetail> methodInvalid(MethodArgumentNotValidException e){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation Failed");

    Map<String, String> errors = e.getFieldErrors()
            .stream()
            .collect(Collectors.toMap(
                    FieldError::getField,
                    FieldError::getDefaultMessage
            ));
    problemDetail.setProperty("errors", errors);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
}

@ExceptionHandler(DataIntegrityViolationException.class)
        private ResponseEntity<ProblemDetail> dataIntegrityViolation(DataIntegrityViolationException e){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, "Email Already in use");
    return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
}

}


