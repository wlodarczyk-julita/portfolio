package com.politechnika.transport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.politechnika.transport.repository.SeatsRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Repository;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PostPersist;
import javax.validation.constraints.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection ="connection")
@Getter @Setter @NoArgsConstructor
public class Connection {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private String id;
    @NotEmpty
    @Size(min = 1, max = 100)
    private String trainName;
    private String startStation;
    private String finalStation;
    private int capacity;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime deparature;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime arrive;
    @NotNull
    private double price;
}
