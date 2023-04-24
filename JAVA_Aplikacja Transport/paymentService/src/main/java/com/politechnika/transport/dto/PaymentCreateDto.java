package com.politechnika.transport.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class PaymentCreateDto {
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
