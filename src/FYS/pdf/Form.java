/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FYS.pdf;

import FYS.Database;
import FYS.Main;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Daan Dirker
 */
public class Form {

    private static final String IMG = "src/FYS/img/Corendon-Logo.jpg";
    private final Document document = new Document();
    private final Database database = Main.getDatabase();

    private void makeTable(PdfPTable tableName, String[] eigenschap,
            String[] resultaat) throws DocumentException {
        for (int i = 0; i < eigenschap.length; i++) {
            tableName.addCell(eigenschap[i]);
            tableName.addCell(resultaat[i]);
        }
        tableName.setSpacingAfter(20f);
        document.add(tableName);
    }

    public void createMissing(String dest, int id) throws IOException, DocumentException {
        try {
            ResultSet vermist = database.executeQuery("SELECT * FROM testDatabase.Vermist WHERE idVermist = " + id);
            //Gaat net zo lang door, tot er geen records meer zijn

            while (vermist.next()) {
                PdfWriter.getInstance(document, new FileOutputStream(dest));

                document.open();

                Image logo = Image.getInstance(IMG);
                logo.setIndentationLeft(50f);
                logo.setSpacingAfter(10f);
                logo.scalePercent(30f, 30f);
                document.add(logo);

                Paragraph titel = new Paragraph("Found baggage registration form");
                titel.setSpacingAfter(20f);
                titel.setIndentationLeft(70f);
                document.add(titel);

                String[] algemeen = {"Date", "Time", "Airport", "Lost-and-found ID"};
                String[] algemeenRes = {vermist.getString("Datum"),
                    vermist.getString("Tijd"), vermist.getString("Luchthaven"),
                    vermist.getString("idVermist")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), algemeen, algemeenRes);

                Paragraph paragraph1 = new Paragraph("Traveler information:");
                paragraph1.setSpacingAfter(10f);
                paragraph1.setIndentationLeft(50f);
                document.add(paragraph1);

                String[] reizigerInformatie = {"Name:", "Adress:", "Residence:", "Zip code:",
                    "Country:", "Phone number:", "E-mail:"};
                String[] reizigerInformatieRes = {vermist.getString("Naam"),
                    vermist.getString("Adres"), vermist.getString("Woonplaats"),
                    vermist.getString("Postcode"), vermist.getString("Land"),
                    vermist.getString("Telefoon"), vermist.getString("Email")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), reizigerInformatie, reizigerInformatieRes);

                Paragraph paragraph2 = new Paragraph("Baggage label information:");
                paragraph2.setSpacingAfter(10f);
                paragraph2.setIndentationLeft(50f);
                document.add(paragraph2);

                String[] bagageLabelInformatie = {"Label number:", "Flight number:",
                    "Destination:"};
                String[] bagageLabelInformatieRes = {vermist.getString("Labelnummer"),
                    vermist.getString("Vluchtnummer"), vermist.getString("Bestemming")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), bagageLabelInformatie, bagageLabelInformatieRes);

                Paragraph paragraph3 = new Paragraph("Baggage information:");
                paragraph3.setSpacingAfter(10f);
                paragraph3.setIndentationLeft(50f);
                document.add(paragraph3);

                String[] bagageInformatie = {"Type:", "Brand:", "Colour:",
                    "Special Characteristics:"};
                String[] bagageInformatieRes = {vermist.getString("BagageType"),
                    vermist.getString("Merk"), vermist.getString("Kleur"),
                    vermist.getString("BijzonderKenmerken")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), bagageInformatie, bagageInformatieRes);

                Paragraph createdBy = new Paragraph("Pdf document generated by: ");
                createdBy.setSpacingBefore(20f);
                createdBy.setIndentationLeft(50f);
                document.add(createdBy);

                document.close();

                System.out.println("Saved in: " + dest);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        }

    }

    public void createFound(String dest, int id) throws IOException, DocumentException {
        try {
            ResultSet gevonden = database.executeQuery("SELECT * FROM testDatabase.Gevonden WHERE idGevonden = " + id);

            while (gevonden.next()) {
                PdfWriter.getInstance(document, new FileOutputStream(dest));

                document.open();

                Image logo = Image.getInstance(IMG);
                logo.setIndentationLeft(50f);
                logo.setSpacingAfter(10f);
                logo.scalePercent(30f, 30f);
                document.add(logo);

                Paragraph titel = new Paragraph("Lost baggage registration form");
                titel.setSpacingAfter(20f);
                titel.setIndentationLeft(70f);
                document.add(titel);

                String[] algemeen = {"Date", "Time", "Airport", "Lost-and-found ID"};
                String[] algemeenRes = {gevonden.getString("Datum"),
                    gevonden.getString("Tijd"), gevonden.getString("Luchthaven"),
                    gevonden.getString("idGevonden")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), algemeen, algemeenRes);

                Paragraph paragraph1 = new Paragraph("Baggage label information:");
                paragraph1.setSpacingAfter(10f);
                paragraph1.setIndentationLeft(50f);
                document.add(paragraph1);

                String[] bagageLabelInformatie = {"Label number:", "Flight number:",
                    "Destination:", "Naam reiziger:"};
                String[] bagageLabelInformatieRes = {gevonden.getString("Labelnummer"),
                    gevonden.getString("Vluchtnummer"), gevonden.getString("Bestemming"),
                    "Dit bestaat niet!!!"};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), bagageLabelInformatie, bagageLabelInformatieRes);

                Paragraph paragraph3 = new Paragraph("Baggage information:");
                paragraph3.setSpacingAfter(10f);
                paragraph3.setIndentationLeft(50f);
                document.add(paragraph3);

                String[] bagageInformatie = {"Type:", "Brand:", "Colour:",
                    "Special Characteristics:"};
                String[] bagageInformatieRes = {gevonden.getString("BagageType"),
                    gevonden.getString("Merk"), gevonden.getString("Kleur"),
                    gevonden.getString("BijzonderKenmerken")};
                makeTable(new com.itextpdf.text.pdf.PdfPTable(2), bagageInformatie, bagageInformatieRes);

                Paragraph createdBy = new Paragraph("Pdf document generated by: ");
                createdBy.setSpacingBefore(20f);
                createdBy.setIndentationLeft(50f);
                document.add(createdBy);

                document.close();

                System.out.println("Saved in: " + dest);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        }

    }

}
