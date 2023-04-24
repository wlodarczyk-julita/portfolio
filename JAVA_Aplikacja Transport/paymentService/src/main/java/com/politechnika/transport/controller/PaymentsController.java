package com.politechnika.transport.controller;

import com.politechnika.transport.dto.PaymentCreateDto;
import com.politechnika.transport.dto.PaymentDto;
import com.politechnika.transport.dto.PaymentMapper;
import com.politechnika.transport.model.Payments;
import com.politechnika.transport.repository.PaymentsRepository;
import com.politechnika.transport.service.PaymentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PaymentsController {
    private PaymentsRepository paymentsRepository;
    private PaymentsService paymentsService;
    private PaymentMapper paymentMapper;
    public PaymentsController(PaymentsRepository paymentsRepository,
                              PaymentsService paymentsService,
                              PaymentMapper paymentMapper) {
        this.paymentsRepository = paymentsRepository;
        this.paymentsService = paymentsService;
        this.paymentMapper = paymentMapper;
    }
    @GetMapping("/test")
    public String hello() {
        return "hello";
    }

    @GetMapping
    public List<Payments> getPayment() {
        return paymentsService.paymentList();
    }

    @PostMapping
    public String addPayment(@RequestBody @Valid PaymentCreateDto dto) {
        return paymentsService.addPayment(dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String paymentId) {
        paymentsService.deletePayment(paymentId);
        return "Payment is deleted successfully";
    }


    @PatchMapping("/{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable String id, @RequestBody PaymentDto paymentDto) {
        Optional<Payments> optionalPayment = paymentsRepository.findById(id);
        if (!optionalPayment.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Payments payment = optionalPayment.get();
        if (paymentDto.getPaymentDate() != null) {
            payment.setPaymentDate(paymentDto.getPaymentDate());
        }
        if (paymentDto.getAmount() != 0) { //tu chyba powinno być inne porównanie
            payment.setAmount(paymentDto.getAmount());
        }
        if (paymentDto.isStatus()) {
            payment.setStatus(paymentDto.isStatus());
        }
        paymentsRepository.save(payment);
        PaymentDto updatedPaymentDto = paymentMapper.toDto(payment);
        return ResponseEntity.ok(updatedPaymentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentDto> editPayment(@PathVariable("id") String id, @RequestBody  PaymentDto dto) {
        Optional<Payments> optionalPayment = paymentsRepository.findById(id);
        if (optionalPayment.isPresent()) {
            Payments payment = new Payments();
            payment.setUserId(dto.getUserId());
            payment.setTicketId(dto.getTicketId());
            payment.setAmount(dto.getAmount());
            payment.setStatus(dto.isStatus());
            paymentsRepository.save(payment);
            PaymentDto updatedPaymentDto = paymentMapper.toDto(payment);
            return ResponseEntity.ok(updatedPaymentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
