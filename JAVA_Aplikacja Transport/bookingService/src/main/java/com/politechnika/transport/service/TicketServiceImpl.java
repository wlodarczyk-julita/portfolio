package com.politechnika.transport.service;

import com.politechnika.transport.dto.TicketCreateDto;
import com.politechnika.transport.exception.TicketError;
import com.politechnika.transport.exception.TicketException;
import com.politechnika.transport.model.Ticket;
import com.politechnika.transport.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TicketServiceImpl implements TicketService{
    @Autowired
    private TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> ticketList(){
        return ticketRepository.findAll();
    }

    public String addTicket(TicketCreateDto dto) {
        Ticket ticket = mapTicketCreateDtoToTicket(dto);
        return ticketRepository.save(ticket).getId();
    }
    private static Ticket mapTicketCreateDtoToTicket(TicketCreateDto dto) {
        Ticket ticket = new Ticket();
        ticket.setUserId(dto.getUserId());
        ticket.setConnectionId(dto.getConnectionId());
        ticket.setSeatsBooked(dto.getSeatsBooked());
        ticket.setBookingDate(dto.getBookingDate());
        ticket.setStatus(dto.isStatus());
        return ticket;
    }
    @Override
    public String deleteTicket(String id) {
        ticketRepository.deleteById(id);
        return "delete";
    }

    public Ticket getTicket(String ticketId){
        return ticketRepository.findById(ticketId)
                .orElseThrow(() ->new TicketException(TicketError.TICKET_NOT_FOUND));
    }


}
