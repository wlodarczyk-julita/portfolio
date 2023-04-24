package com.politechnika.transport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConnectionExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(ConnectionException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(ConnectionError.CONNECTION_NOT_FOUND.equals(e.getConnectionError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getConnectionError().getMessage()));
    }
}
