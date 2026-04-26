package dev.vinicius.restaurant_management_api.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

@ExceptionHandler(EntityNotFoundException.class)
private ResponseEntity<ProblemDetail> userNotFound(EntityNotFoundException e){
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
}

}


