package com.politechnika.transport.controller;
import com.politechnika.transport.model.EmailTemplate;
import com.politechnika.transport.service.EmailService;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/email")
@Slf4j
public class TextEmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping(value="/textemail",consumes = "application/json", produces = "application/json")
    public String sendEmail(@RequestBody EmailTemplate emailTemplate) {
        try {
            emailService.sendTextEmail(emailTemplate);
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }


    @PostMapping(value="/attachemail",consumes = "multipart/form-data")
    public String sendEmailWithAttachment(@RequestPart(value = "file") MultipartFile file) {
        try {
            emailService.sendEmailWithAttachment(file);
            return "Email Sent!";
        } catch (Exception ex) {
            return "Error in sending email: " + ex;
        }
    }


}