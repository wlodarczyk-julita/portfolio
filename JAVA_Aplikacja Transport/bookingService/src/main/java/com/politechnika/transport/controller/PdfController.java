package com.politechnika.transport.controller;

import com.politechnika.transport.model.Ticket;
import com.politechnika.transport.service.PdfService;
import com.politechnika.transport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    private PdfService pdfService;
    private TicketService ticketService;

    @Autowired
    public PdfController(PdfService pdfService, TicketService ticketService) {
        this.pdfService = pdfService;
        this.ticketService = ticketService;
    }

    @GetMapping(value = "/{ticketId}")
    public String generatePdf(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        pdfService.generatePdf(ticket);
        return "Wygenerowano bilet.";
    }
}