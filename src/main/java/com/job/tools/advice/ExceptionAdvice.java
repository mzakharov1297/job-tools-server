package com.job.tools.advice;

import com.job.tools.exception.MappingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(final EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body((new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                ex.getMessage())));
    }

    @ExceptionHandler(MappingException.class)
    public ResponseEntity<ErrorResponseDto> handleMappingException(final MappingException ex) {
        return ResponseEntity.badRequest().body((new ErrorResponseDto(HttpStatus.NOT_FOUND.value(),
                ex.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    final var fieldName = ((FieldError) error).getField();
                    final var errorMessage = ex.getMessage();
                    errors.put(fieldName, errorMessage);
                });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDto> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ResponseEntity.badRequest().body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    public record ErrorResponseDto(int statusCode, String message) {
    }
}
