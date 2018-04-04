package nl.marcovp.avans.cavanz.Util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
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
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import nl.marcovp.avans.cavanz.Domain.Ticket;

/**
 * Created by marco on 4/3/18.
 */

public class PDFGenerator {

    public void createDocument(ArrayList<Ticket> tickets, Context context) {
        try {

            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "CavansTickets");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
            }


            // Omitting content protocols
            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                    m.invoke(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(date);

            File myFile = new File(pdfFolder + "/" + timeStamp + ".pdf");
            OutputStream output = new FileOutputStream(myFile);

            Document document = new Document();
            PdfWriter.getInstance(document, output);

            document.open();

            for (Ticket ticket : tickets) {
                document.newPage();
                document.add(new Paragraph("Film: " + ticket.getShowing().getMovie().getTitle()));
                document.add(new Paragraph("Datum: " + ticket.getShowing().getDate()));
                document.add(new Paragraph("Zaal: " + ticket.getShowing().getHall().getHallNumber()));
                document.add(new Paragraph("Speeltijd: " + ticket.getShowing().getStartingTime() + " - " + ticket.getShowing().getEndingTime()));
                document.add(new Paragraph("Stoel: " + ticket.getSeat()));
                document.add(new Paragraph(" "));
                document.add(new Paragraph("Naam: " + ticket.getName() + " " + ticket.getSurname()));
                document.add(new Paragraph("Email: " + ticket.getEmail()));
                document.add(new Paragraph("Prijs: " + ticket.getTotalPrice()));
            }

            document.close();

            Uri path = Uri.fromFile(myFile);

            Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
            pdfOpenintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            pdfOpenintent.setDataAndType(path, "application/pdf");
            try {
                context.startActivity(pdfOpenintent);
            } catch (ActivityNotFoundException e) {

            }

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }
    }
}
