package com.spring.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.net.ConnectException;
import java.time.LocalDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {SoapFaultClientException.class})
    public ResponseEntity<APIException> handleSoapFaultClientException(SoapFaultClientException e) {
        APIException exception = new APIException(e.getMessage(),
                HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        APIException exception = new APIException("the input provided is invalid",
                HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<APIException> handleConnectException(ConnectException e) {
        APIException exception = new APIException(e.getMessage(),
                HttpStatus.SERVICE_UNAVAILABLE, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.SERVICE_UNAVAILABLE);
    }

}