package org.maksymov.demo;

import org.maksymov.demo.dto.ErrorDTO;
import org.maksymov.demo.exception.AlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

import static org.maksymov.demo.ExceptionCode.INVALID_REQUEST_DATA;

@ControllerAdvice
public class SpringExceptionHandler {

    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ErrorDTO> handleAlreadyExistException(final AlreadyExistException e) {
        final ErrorDTO errorDTO = ErrorDTO.builder().code(e.getCode()).description(e.getDescription()).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDTO> handleConstraintViolationExeption(final ConstraintViolationException e) {
        final ErrorDTO errorDTO = ErrorDTO.builder().code(INVALID_REQUEST_DATA).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final ErrorDTO errorDTO = ErrorDTO.builder().code(INVALID_REQUEST_DATA).build();
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity handleThrowable(final Throwable throwable) {
        return new ResponseEntity<>(throwable.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
