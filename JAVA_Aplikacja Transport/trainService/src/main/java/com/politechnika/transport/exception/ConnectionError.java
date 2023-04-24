package com.politechnika.transport.exception;

public enum ConnectionError {
    CONNECTION_NOT_FOUND("Connection not found."),
    CONNECTION_ALREADY_EXISTS("Connection already exists.");
    private String message;
    ConnectionError(String message){this.message = message;}
    public String getMessage(){ return message;}
}
