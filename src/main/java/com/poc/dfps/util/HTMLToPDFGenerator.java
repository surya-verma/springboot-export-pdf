package com.poc.dfps.util;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.*;

public class HTMLToPDFGenerator {
    public static ByteArrayInputStream generatePDFFromHTML(String html) throws IOException, DocumentException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();
        InputStream is = new ByteArrayInputStream(html.getBytes());
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
}
