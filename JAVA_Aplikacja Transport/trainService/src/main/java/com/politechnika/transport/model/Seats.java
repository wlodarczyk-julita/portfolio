package com.politechnika.transport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Document (collection ="seats")
@Getter
@Setter
@NoArgsConstructor
public class Seats {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private String id;
//    @DBRef
    private String connectionId;
    @NotNull
    private int seatNumber;
    private boolean isAvaiable = true;
}
