package com.politechnika.transport.services;

import com.politechnika.transport.dto.ConnectionDto;
import com.politechnika.transport.model.Connection;

import java.util.List;

public interface ConnectionService {
    public List<Connection> getConnections();
    public Connection getConnection(String connectionId);
    public Connection addConnection(Connection connection);
    public Connection putConnection(String id, ConnectionDto connection);
    public Connection patchConnection(String id, ConnectionDto connection);
    public List<Connection> getFilteredConnectionsByStartStation(String startStation);
    public List<Connection> getFilteredConnectionsByStartAndFinalStation(String startStation, String finalStation);
}
