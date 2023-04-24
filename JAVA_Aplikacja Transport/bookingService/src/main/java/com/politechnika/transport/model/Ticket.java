package com.politechnika.transport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Document(collection ="tickets")
@Getter @Setter @NoArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private String id;
//    @DBRef
    private String userId;
//    @DBRef
    private String connectionId;

    private ArrayList <String> seatsBooked;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime bookingDate = LocalDateTime.now();

    private boolean status = false;

}
