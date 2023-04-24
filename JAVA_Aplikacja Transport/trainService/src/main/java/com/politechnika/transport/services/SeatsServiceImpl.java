package com.politechnika.transport.services;

import com.politechnika.transport.model.Connection;
import com.politechnika.transport.model.Seats;
import com.politechnika.transport.repository.ConnectionRepository;
import com.politechnika.transport.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@Service
public class SeatsServiceImpl implements SeatsService{
    @Autowired
    private SeatsRepository seatsRepository;
    public void addSeats(Connection connection){
        List<Seats> seats = new LinkedList();
        for (int i = 1; i <= connection.getCapacity(); i++) {
            Seats seat = new Seats();
            seat.setSeatNumber(i);
            seat.setConnectionId(connection.getId());
            seats.add(seat);
        }
        seatsRepository.saveAll(seats);
    }
}
