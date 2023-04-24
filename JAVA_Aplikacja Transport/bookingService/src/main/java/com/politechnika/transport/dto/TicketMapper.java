package com.politechnika.transport.dto;

import com.politechnika.transport.model.Ticket;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto();
        dto.setUserId(ticket.getUserId());
        dto.setConnectionId(ticket.getConnectionId());
        dto.setSeatsBooked(ticket.getSeatsBooked());
        dto.setBookingDate(ticket.getBookingDate());
        dto.setStatus(ticket.isStatus());
        return dto;
    }

}