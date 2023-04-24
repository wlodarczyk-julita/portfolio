package com.politechnika.transport.services;

import com.politechnika.transport.dto.ConnectionDto;
import com.politechnika.transport.exception.ConnectionError;
import com.politechnika.transport.exception.ConnectionException;
import com.politechnika.transport.model.Connection;
import com.politechnika.transport.model.Seats;
import com.politechnika.transport.repository.ConnectionRepository;
import com.politechnika.transport.repository.SeatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

//import javax.management.Query;

@Service
public class ConnectionServiceImpl implements ConnectionService{
    @Autowired
    private ConnectionRepository connectionRepository;
    @Autowired
    private SeatsService seatsService;
    public ConnectionServiceImpl(ConnectionRepository connectionRepository) {
        this.connectionRepository = connectionRepository;
    }

    public List<Connection> getConnections(){
        return connectionRepository.findAll();
    }
    public Connection getConnection(String connectionId){
        return connectionRepository.findById(connectionId)
                .orElseThrow(() ->new ConnectionException(ConnectionError.CONNECTION_NOT_FOUND));
    }

    public Connection addConnection(Connection connection){
        Connection savedConnection = connectionRepository.save(connection);
        seatsService.addSeats(savedConnection);
        return savedConnection;
    }


    public Connection putConnection(String id, ConnectionDto connection) {
        return connectionRepository.findById(id)
                .map(connectionFromDb -> {
                    if (!connectionFromDb.getTrainName().equals(connection.getTrainName()) &&
                            connectionRepository.existsById(connection.getTrainName())) {
                        throw new ConnectionException(ConnectionError.CONNECTION_ALREADY_EXISTS);
                    }
                    connectionFromDb.setTrainName(connection.getTrainName());
                    connectionFromDb.setCapacity(connection.getCapacity());
                    connectionFromDb.setDeparature(connection.getDeparature());
                    connectionFromDb.setArrive(connection.getArrive());
                    return connectionRepository.save(connectionFromDb);
                }).orElseThrow(() -> new ConnectionException(ConnectionError.CONNECTION_NOT_FOUND));
    }

    private void validateConnectionExists(Connection connection) {
        if (connectionRepository.existsByTrainName(connection.getTrainName())) {
            throw new ConnectionException(ConnectionError.CONNECTION_ALREADY_EXISTS);
        }
    }

    public Connection patchConnection(String id, ConnectionDto connection) {
        return connectionRepository.findById(id)
                .map(connectionFromDb -> {
                    if (!StringUtils.isEmpty(connection.getTrainName())) {
                        connectionFromDb.setTrainName(connection.getTrainName());
                    }
                    if (!StringUtils.isEmpty(connection.getCapacity())) {
                        connectionFromDb.setCapacity(connection.getCapacity());
                    }
                    if (!StringUtils.isEmpty(connection.getDeparature())) {
                        connectionFromDb.setDeparature(connection.getDeparature());
                    }
                    if (!StringUtils.isEmpty(connection.getArrive())) {
                        connectionFromDb.setArrive(connection.getArrive());
                    }
                    return connectionRepository.save(connectionFromDb);
                }).orElseThrow(() -> new ConnectionException(ConnectionError.CONNECTION_NOT_FOUND));
    }
    public List<Connection> getFilteredConnectionsByStartStation(String startStation) {
        List<Connection> connections = connectionRepository.findAll();
        List<Connection> filteredConnections = new ArrayList<>();

        for (Connection connection : connections) {
            if (connection.getStartStation().equals(startStation)) {
                filteredConnections.add(connection);
            }
        }
        return filteredConnections;
    }
    public List<Connection> getFilteredConnectionsByStartAndFinalStation(String startStation, String finalStation) {
        List<Connection> connections = connectionRepository.findAll();
        List<Connection> filteredConnections = new ArrayList<>();

        for (Connection connection : connections) {
            if ((connection.getStartStation().equals(startStation)) && (connection.getFinalStation().equals(finalStation))) {
                filteredConnections.add(connection);
            }
        }
        return filteredConnections;
    }
}
