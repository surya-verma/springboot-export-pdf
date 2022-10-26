package com.poc.dfps.controller;

import com.itextpdf.text.DocumentException;
import com.poc.dfps.model.WelcomeData;
import com.poc.dfps.repository.service.WelcomePdfService;
import com.poc.dfps.util.HTMLToPDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTMLToPDFController {

    @Autowired
    private WelcomePdfService welcomePdfService;
    public static final String HTML = "<h1>Hello</h1>"
            + "<p>This was created using iText</p>"
            + "<a href='abc.com'>abc.com</a>";

    @GetMapping(value = "/htmltopdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> employeeReport() throws IOException {


        ByteArrayInputStream bis = null;
        try {
            bis = HTMLToPDFGenerator.generatePDFFromHTML(HTML);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @GetMapping(value = "/htmltopdfTemplate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> welcomePdf() throws IOException {

        WelcomeData welcomeData = new WelcomeData();

        // Populate the template data
        Map<String, Object> templateData = new HashMap<>();
        templateData.put("name", "Atul Rai");
        // List of team members...
        List<String> teamMembers = Arrays.asList("Tendulkar", "Manish", "Dhirendra");
        templateData.put("teamMembers", teamMembers);
        templateData.put("location", "India");
        welcomeData.setDataMap(templateData);

        ByteArrayInputStream bis = welcomePdfService.sendWelcomeEmail(welcomeData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=employees.pdf");

        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
