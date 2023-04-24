package com.politechnika.transport.exception;

public class noAvaiableSeats extends RuntimeException  {
    public noAvaiableSeats(int capacity) {
        super("Seats available: " + capacity);
    }}
