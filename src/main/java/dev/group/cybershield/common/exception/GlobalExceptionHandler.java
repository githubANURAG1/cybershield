package dev.group.cybershield.common.exception;

import dev.group.cybershield.common.global.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorDTO> handleBadRequestException(BadRequestException ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("BAD_REQUEST");
        errorDTO.setErrorMessage(ex.getMessage());
        errorDTO.setLandingTime(Timestamp.from(Instant.now()));
        errorDTO.setResponseTime(Timestamp.from(Instant.now()));
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .findFirst()
                .orElse(ex.getMessage());

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("VALIDATION_ERROR");
        errorDTO.setErrorMessage(errorMessage);
        errorDTO.setLandingTime(Timestamp.from(Instant.now()));
        errorDTO.setResponseTime(Timestamp.from(Instant.now()));
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGeneralException(Exception ex) {
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("INTERNAL_SERVER_ERROR");
        errorDTO.setErrorMessage(ex.getMessage());
        errorDTO.setLandingTime(Timestamp.from(Instant.now()));
        errorDTO.setResponseTime(Timestamp.from(Instant.now()));
        errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(errorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
