package com.politechnika.transport.dto;

import com.politechnika.transport.model.Payments;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentDto toDto (Payments payment) {
        PaymentDto dto = new PaymentDto();
        dto.setUserId(payment.getUserId());
        dto.setTicketId(payment.getTicketId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.isStatus());
        return dto;
    }
}
