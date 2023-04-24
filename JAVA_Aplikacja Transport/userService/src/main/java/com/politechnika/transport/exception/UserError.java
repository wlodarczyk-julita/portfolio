package com.politechnika.transport.exception;

public enum UserError {
    USER_NOT_FOUND("User not found."),
    USER_ALREADY_EXISTS("User already exists.");
    private String message;
    UserError(String message){this.message = message;}
    public String getMessage(){ return message;}
}
