/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationRym;

import static com.itextpdf.text.pdf.XfaXpathConstructor.XdpPackage.Pdf;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Properties;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author rymlarayedh
 */
public class utilprojet {
    
     public void DownloadDocuemntProjet() throws Exception {
        Properties info = System.getProperties();
        String User = info.getProperty("user.name");
        String pathPc = "C:\\Users\\" + User + "\\Documents\\SummaryDuWeen";
        File folder = new File(pathPc);
       // System.out.println("Resultat de creation de fichier = " + folder.mkdirs());

        pdf.getReglement(pathPc + "\\WeenSummary.pdf");

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(pathPc + "\\WeenSummary.pdf");
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                // no application registered for PDFs
            }
        }

    }
    
}
