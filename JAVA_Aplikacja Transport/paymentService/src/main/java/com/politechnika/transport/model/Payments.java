package com.politechnika.transport.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;


@Document(collection ="payments")
@Getter @Setter @NoArgsConstructor
public class Payments {

    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private String id;
    @DBRef
    private String ticketId;
    @DBRef
    private String userId;
    @NotNull
    private int amount;
    @NotNull
    private Date paymentDate;
    private boolean status = false;

}
