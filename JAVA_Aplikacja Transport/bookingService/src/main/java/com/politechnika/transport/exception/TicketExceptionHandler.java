package com.politechnika.transport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TicketExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorInfo> handleException(TicketException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(TicketError.TICKET_NOT_FOUND.equals(e.getTicketError())){
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity.status(httpStatus).body(new ErrorInfo(e.getTicketError().getMessage()));
    }
}
