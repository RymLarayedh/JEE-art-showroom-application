/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package applicationRym;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entities.Event;
import services.EventManagmentRemote;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author rymlarayedh
 */
public class pdf {

	
    public static void getReglement(String str) throws Exception {

        Document document = new Document();

        PdfWriter.getInstance(document, new FileOutputStream(new File(str).getAbsoluteFile()));
        document.open();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

        //WeenerAdd p = new WeenerAdd();

        String s = "                      ************      Fanny Events Summary for " + dateFormat.format(date)+"       *********\n\n" ;
        document.add(new Paragraph(s));
        document.add(createFirstTable());
        //document.add(createSecendTable());
        //document.add(createThirdTable());

        document.close();
    }

    public static PdfPTable createFirstTable() throws NamingException {
        PdfPTable table = new PdfPTable(5);
        PdfPCell cell;
        Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        cell = new PdfPCell(new Phrase("1/ List of Events :",boldFont));
        cell.setColspan(5);
        table.addCell(cell);
        Event p = new Event();
        int i = 0;
        table.addCell("");
        table.addCell("Last Name");
        table.addCell("Name");
        table.addCell("Phone");
        table.addCell("E-mail");
        
        InitialContext ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxy = (EventManagmentRemote) objet;
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        for (Event u : proxy.getAllEvents()) {
            i = i + 1;
            table.addCell("Event" + i);
            table.addCell(u.getTitle());
            table.addCell(u.getDescription());
            Date a = u.getDateBegin();
            String b = "";
            b = df.format(a);
            table.addCell(b);
            Date a2 = u.getDateEnd();
            String b2 = "";
            b = df.format(a2);
            table.addCell(b2);
            table.addCell(u.getGallery().getUsername());
            table.addCell(u.getArtist().getUsername());

        }

        return table;
    }
    
   
}
