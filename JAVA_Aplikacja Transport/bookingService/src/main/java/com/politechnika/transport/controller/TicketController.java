package com.politechnika.transport.controller;

import com.politechnika.transport.dto.TicketCreateDto;
import com.politechnika.transport.dto.TicketDto;
import com.politechnika.transport.dto.TicketMapper;
import com.politechnika.transport.model.Ticket;
import com.politechnika.transport.repository.TicketRepository;
import com.politechnika.transport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class TicketController {
    private TicketService ticketService;
    private TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Autowired
    public TicketController(TicketService ticketService,
                            TicketRepository ticketRepository,
                            TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.ticketMapper = ticketMapper;
    }

    @GetMapping
    public List<Ticket> getTickets() {
        return ticketService.ticketList();
    }

    @PostMapping
    public String addTicket(@RequestBody @Valid TicketCreateDto dto) {
        return ticketService.addTicket(dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String ticketID) {
        ticketService.deleteTicket(ticketID);
        return "Ticket is deleted successfully";
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String id, @RequestBody TicketDto ticketDto) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (!optionalTicket.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Ticket ticket = optionalTicket.get();
        if (ticketDto.getSeatsBooked() != null) {
            ticket.setSeatsBooked(ticketDto.getSeatsBooked());
        }
        if (ticketDto.getBookingDate() != null) {
            ticket.setBookingDate(ticketDto.getBookingDate());
        }
        if (ticketDto.isStatus()) {
            ticket.setStatus(ticketDto.isStatus());
        }
        ticketRepository.save(ticket);
        TicketDto updatedTicketDto = ticketMapper.toDto(ticket);
        return ResponseEntity.ok(updatedTicketDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> editTicket(@PathVariable("id") String id, @RequestBody TicketDto ticketDto) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setUserId(ticketDto.getUserId());
            ticket.setConnectionId(ticketDto.getConnectionId());
            ticket.setSeatsBooked(ticketDto.getSeatsBooked());
            ticket.setBookingDate(ticketDto.getBookingDate());
            ticket.setStatus(ticketDto.isStatus());
            ticketRepository.save(ticket);
            TicketDto updatedTicketDto = ticketMapper.toDto(ticket);
            return ResponseEntity.ok(updatedTicketDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}