package com.politechnika.transport.service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.politechnika.transport.dto.ConnectionDto;
import com.politechnika.transport.dto.UserDto;
import com.politechnika.transport.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PdfServiceImpl implements PdfService {
    @Autowired
    UserServiceClient userServiceClient;
    @Autowired
    ConnectionServiceClient connectionServiceClient;
    public PdfServiceImpl(UserServiceClient userServiceClient, ConnectionServiceClient connectionServiceClient) {
        this.userServiceClient = userServiceClient;
        this.connectionServiceClient = connectionServiceClient;
    }


    public void generatePdf(Ticket ticket) {
        try {
            UserDto user = userServiceClient.getUserById(ticket.getUserId());
            ConnectionDto connection = connectionServiceClient.getConnectionById(ticket.getConnectionId());
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("ticket_" + ticket.getId() + ".pdf"));
            document.open();
            document.add(new Paragraph("TRAIN TICKET"));
            document.add(new Paragraph("Username: " + user.getUsername()));
            document.add(new Paragraph("Email: " + user.getEmail()));
            document.add(new Paragraph("StartStation: " + connection.getStartStation()));
            document.add(new Paragraph("FinalStation: " + connection.getFinalStation()));
            document.add(new Paragraph("TrainName: " + connection.getTrainName()));
            document.add(new Paragraph("Departure date and time: " + connection.getDeparature()));
            document.add(new Paragraph("Arrive date and time: " + connection.getArrive()));
            document.add(new Paragraph("Price(PLN): " + connection.getPrice()));
            document.close();




//            Document pdf = new Document();
//            PdfWriter.getInstance(pdf, o);
//            pdf.open();
//            pdf.add(new Paragraph("BILET"));
//            pdf.add(new Paragraph(Chunk.NEWLINE));
//            PdfPTable table = new PdfPTable(2);
//            table.addCell("Username");
//            table.addCell(user.getUsername());
//            table.addCell("Email");
//            table.addCell(user.getEmail());
//            table.addCell("StartStation");
//            table.addCell(connection.getStartStation());
//            table.addCell("FinalStation");
//            table.addCell(connection.getFinalStation());
//            table.addCell("Departure");
//            table.addCell(connection.getDeparature().toString());
//            table.addCell("Arrive");
//            table.addCell(connection.getArrive().toString());
//            table.addCell("Price (PLN)");
//            table.addCell(String.valueOf(connection.getPrice()));
//            pdf.add(table);
//            pdf.close();
//            o.close();
        }catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
