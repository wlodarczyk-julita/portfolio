package com.politechnika.transport.controller;

import com.politechnika.transport.model.Seats;
import com.politechnika.transport.services.SeatsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/seats")
public class SeatsController {
    private SeatsService seatsService;

    public SeatsController(SeatsService seatsService) {
        this.seatsService = seatsService;
    }
}
