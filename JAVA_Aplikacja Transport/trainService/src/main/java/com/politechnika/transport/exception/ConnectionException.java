package com.politechnika.transport.exception;

public class ConnectionException extends RuntimeException{
    private ConnectionError connectionError;
    public ConnectionException(ConnectionError connectionError){this.connectionError = connectionError;}
    public ConnectionError getConnectionError(){return connectionError;}
}
