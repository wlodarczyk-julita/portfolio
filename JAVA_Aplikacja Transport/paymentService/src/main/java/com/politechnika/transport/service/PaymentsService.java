package com.politechnika.transport.service;

import com.politechnika.transport.dto.PaymentCreateDto;
import com.politechnika.transport.model.Payments;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

public interface PaymentsService {
    List<Payments> paymentList();
    String addPayment(@Valid PaymentCreateDto dto);
    String deletePayment(String id);
}
