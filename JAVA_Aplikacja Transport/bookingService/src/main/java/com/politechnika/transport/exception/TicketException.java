package com.politechnika.transport.exception;

public class TicketException extends RuntimeException{
    private TicketError ticketError;
    public TicketException(TicketError ticketError){this.ticketError = ticketError;}
    public TicketError getTicketError(){return ticketError;}
}
