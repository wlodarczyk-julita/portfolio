package com.politechnika.transport.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
public class TicketDto {

    private String userId;
    private String connectionId;
    private ArrayList<String> seatsBooked;
    private LocalDateTime bookingDate;
    private boolean status = false;
}
