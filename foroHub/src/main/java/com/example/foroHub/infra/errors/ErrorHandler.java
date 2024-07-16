package com.example.foroHub.infra.errors;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;

    @RestControllerAdvice
    public class ErrorHandler {

        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity handleEntityNotFound() {
            return ResponseEntity.notFound().build();
        }

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity handleValidationErrors(MethodArgumentNotValidException e) {
            var errors = e.getFieldErrors().stream()
                    .map(ErrorDetails::new)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        private record ErrorDetails(String field, String message) {
            public ErrorDetails(FieldError error) {
                this(error.getField(), error.getDefaultMessage());
            }
        }

        @ExceptionHandler(IntegrityValidation.class)
        public ResponseEntity handleIntegrityValidation(IntegrityValidation e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity handleEmptyJSON() {
            return ResponseEntity.badRequest().body("Por favor proporcione datos en formato JSON.");
        }

    }
