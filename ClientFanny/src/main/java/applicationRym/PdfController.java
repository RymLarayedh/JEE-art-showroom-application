/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationRym;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;


/**
 * FXML Controller class
 *
 * @author rymlarayedh
 */
public class PdfController implements Initializable {
    @FXML
    private CheckBox accepted;
    @FXML
    private Button telechargerDocReglementAction;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void telechargerDocReglementAction(ActionEvent event) {
        try {
            utilprojet u = new utilprojet();
            u.DownloadDocuemntProjet();
        } catch (Exception ex) {
            Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
