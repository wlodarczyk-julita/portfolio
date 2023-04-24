package com.politechnika.transport.service;



import com.politechnika.transport.model.Ticket;

import javax.servlet.http.HttpServletResponse;

public interface PdfService {
    public void generatePdf(Ticket ticket);
}
