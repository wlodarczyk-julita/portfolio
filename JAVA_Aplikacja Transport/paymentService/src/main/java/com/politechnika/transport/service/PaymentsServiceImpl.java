package com.politechnika.transport.service;

import com.politechnika.transport.dto.PaymentCreateDto;
import com.politechnika.transport.dto.PaymentDto;
import com.politechnika.transport.model.Payments;
import com.politechnika.transport.repository.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PaymentsServiceImpl implements PaymentsService{
    @Autowired
    private PaymentsRepository paymentsRepository;

    @Override
    public List<Payments> paymentList() {
        return paymentsRepository.findAll();
    }

    @Override
    public String addPayment(PaymentCreateDto dto) {
        Payments payment = mapPaymentsCreateToPayments(dto);
        return paymentsRepository.save(payment).getId();
    }

    private Payments mapPaymentsCreateToPayments(PaymentCreateDto dto) {
        Payments payment = new Payments();
        payment.setUserId(dto.getUserId());
        payment.setTicketId(dto.getTicketId());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setAmount(dto.getAmount());
        payment.setStatus(dto.isStatus());
        return payment;
    }

    @Override
    public String deletePayment(String id) {
        paymentsRepository.deleteById(id);
        return "delete";
    }
}
