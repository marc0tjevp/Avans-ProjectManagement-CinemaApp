package nl.marcovp.avans.cavanz.Util;

import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.marcovp.avans.cavanz.Domain.Ticket;

/**
 * Created by marco on 4/3/18.
 */

public class PDFGenerator {

    private static Font font = new Font(Font.FontFamily.COURIER);

    public void createDocument(Ticket ticket) {
        try {

            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "CavansTickets");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
            }

            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(date);

            File myFile = new File(pdfFolder + "/" + timeStamp + ".pdf");
            OutputStream output = new FileOutputStream(myFile);

            Document document = new Document();
            PdfWriter.getInstance(document, output);

            document.open();

            document.add(new Paragraph("Film: " + ticket.getShowing().getMovie().getTitle()));
            document.add(new Paragraph("Datum: " + ticket.getShowing().getDate()));
            document.add(new Paragraph("Speeltijd: " + ticket.getShowing().getStartingTime() + " - " + ticket.getShowing().getEndingTime()));
            document.add(new Paragraph("Rij: " + ticket.getRowNumber() + " Stoel: " + ticket.getSeatNumber()));
            document.add(new Paragraph("Naam: " + ticket.getName() + " " + ticket.getSurname()));
            document.add(new Paragraph("Email: " + ticket.getEmail()));
            document.add(new Paragraph("Prijs: " + ticket.getTotalPrice()));

            document.close();

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
