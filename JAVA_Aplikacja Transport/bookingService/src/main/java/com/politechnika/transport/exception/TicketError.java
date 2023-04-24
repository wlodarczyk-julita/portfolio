package com.politechnika.transport.exception;

public enum TicketError {
    TICKET_NOT_FOUND("Ticket not found."),
    TICKET_ALREADY_EXISTS("Ticket already exists.");
    private String message;
    TicketError(String message){this.message = message;}
    public String getMessage(){ return message;}
}
