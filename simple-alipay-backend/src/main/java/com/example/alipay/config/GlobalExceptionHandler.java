package com.example.alipay.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private Map<String, Object> body(HttpStatus status, String error, String message, String exception, String path) {
        return Map.of(
            "status", status.value(),
            "error", error,
            "message", message,
            "exception", exception,
            "path", path,
            "timestamp", Instant.now().toString()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNotReadable(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("invalid_request: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(HttpStatus.BAD_REQUEST, "invalid_request", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotWritableException.class)
    public ResponseEntity<?> handleNotWritable(org.springframework.http.converter.HttpMessageNotWritableException e, HttpServletRequest request) {
        log.error("message_not_writable", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(HttpStatus.INTERNAL_SERVER_ERROR, "message_not_writable", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("validation_failed: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(HttpStatus.BAD_REQUEST, "validation_failed", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        log.warn("invalid_argument: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body(HttpStatus.BAD_REQUEST, "invalid_argument", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDenied(AccessDeniedException e, HttpServletRequest request) {
        log.warn("forbidden: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(body(HttpStatus.FORBIDDEN, "forbidden", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<?> handleAuth(Exception e, HttpServletRequest request) {
        log.warn("unauthorized: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(body(HttpStatus.UNAUTHORIZED, "unauthorized", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<?> handleDataAccess(DataAccessException e, HttpServletRequest request) {
        log.error("data_access_error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(HttpStatus.INTERNAL_SERVER_ERROR, "data_access_error", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNpe(NullPointerException e, HttpServletRequest request) {
        log.error("null_pointer", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(HttpStatus.INTERNAL_SERVER_ERROR, "null_pointer", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneric(Exception e, HttpServletRequest request) {
        log.error("internal_error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body(HttpStatus.INTERNAL_SERVER_ERROR, "internal_error", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<?> handleNoHandler(org.springframework.web.servlet.NoHandlerFoundException e, HttpServletRequest request) {
        log.warn("not_found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(HttpStatus.NOT_FOUND, "not_found", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }

    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResource(org.springframework.web.servlet.resource.NoResourceFoundException e, HttpServletRequest request) {
        log.warn("not_found: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body(HttpStatus.NOT_FOUND, "not_found", e.getMessage(), e.getClass().getSimpleName(), request.getRequestURI()));
    }
}
