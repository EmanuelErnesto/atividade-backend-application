package com.project.Atividade.Backend.Framework.shared.interceptors;

import com.project.Atividade.Backend.Framework.shared.exceptions.ApplicationException;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpBadRequestException;
import com.project.Atividade.Backend.Framework.shared.exceptions.HttpNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(HttpBadRequestException.class)
    public ResponseEntity<ApplicationException> httpApplicationBadRequestException(HttpServletRequest request, RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ApplicationException(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(HttpNotFoundException.class)
    public ResponseEntity<ApplicationException> httpApplicationNotFoundException(HttpServletRequest request, RuntimeException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ApplicationException(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApplicationException> dataIntegrityViolationException(HttpServletRequest request, DataIntegrityViolationException ex) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        new ApplicationException(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApplicationException> methodArgumentNotValidException(HttpServletRequest request, BindingResult result) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        new ApplicationException(request, HttpStatus.BAD_REQUEST, "validation error", result)
                );
    }
}
