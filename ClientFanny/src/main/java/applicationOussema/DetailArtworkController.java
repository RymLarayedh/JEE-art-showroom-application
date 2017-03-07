/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema;

import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author Oussamabh
 */
public class DetailArtworkController implements Initializable {

    @FXML
    private AnchorPane Trip;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextArea commenttxt;
    @FXML
    private TableView<?> commenttable;
    @FXML
    private TableColumn<?, ?> namecl;
    @FXML
    private TableColumn<?, ?> bodycl;
    @FXML
    private TableColumn<?, ?> datecl;
    @FXML
    private Button deletecomment;
    @FXML
    private Label nbrl;
    @FXML
    private ToggleButton tj;
    @FXML
    private Label desct;
    @FXML
    private Label datet;
    @FXML
    private Label pricet;
    @FXML
    private Label artnamet;
    @FXML
    private Label artistn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addcomment(ActionEvent event) {
    }

    @FXML
    private void Modifycomment(ActionEvent event) {
    }

    @FXML
    private void Deletecomment(ActionEvent event) {
    }

    @FXML
    private void liked(ActionEvent event) {
    }

    @FXML
    private void bookTrip(ActionEvent event) {
    }
    
}
