package com.politechnika.transport.service;

import com.politechnika.transport.dto.TicketCreateDto;
import com.politechnika.transport.model.Ticket;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    List<Ticket> ticketList();
    String addTicket(@Valid TicketCreateDto dto);
    String deleteTicket(String id);
    Ticket getTicket(String ticketId);
}
