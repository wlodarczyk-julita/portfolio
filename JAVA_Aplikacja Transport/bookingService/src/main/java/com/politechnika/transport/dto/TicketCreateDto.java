package com.politechnika.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class TicketCreateDto {
    @DBRef
    private String userId;
    @DBRef
    private String connectionId;
    private ArrayList<String> seatsBooked;
    //@JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime bookingDate;
    private boolean status = false;
}
