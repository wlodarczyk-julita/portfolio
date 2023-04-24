package com.politechnika.transport.controller;

import com.politechnika.transport.dto.ConnectionDto;
import com.politechnika.transport.model.Connection;
import com.politechnika.transport.services.ConnectionService;
import com.politechnika.transport.services.SeatsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/connections")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ConnectionController {

    private ConnectionService connectionService;
    public ConnectionController(ConnectionService connectionService){
        this.connectionService = connectionService;
    }
    @GetMapping
    public List<Connection> getConnections(){
        return connectionService.getConnections();
    }
    @PostMapping
    public Connection addConnection(@Valid @RequestBody Connection connection){

        return connectionService.addConnection(connection);
    }
    @GetMapping("/{id}")
    public Connection getConnection(@PathVariable String id){
        return connectionService.getConnection(id);
    }
    @PutMapping("/{id}")
    public Connection putConnection(@PathVariable String id, @RequestBody ConnectionDto connection) {
        return connectionService.putConnection(id, connection);
    }

    @PatchMapping("/{id}")
    public Connection patchConnection(@PathVariable String id, @RequestBody ConnectionDto connection) {
        return connectionService.patchConnection(id, connection);
    }
    @GetMapping("/start")
    public List<Connection> getConnectionsByStartStation(@RequestParam String startStation){
        return connectionService.getFilteredConnectionsByStartStation(startStation);
    }
    @GetMapping("/start-and-final")
    public List<Connection> getConnectionsByStartAndFinalStation(@RequestParam String startStation, @RequestParam String finalStation){
        return connectionService.getFilteredConnectionsByStartAndFinalStation(startStation, finalStation);
    }

}
