/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationImen;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class MessagesController implements Initializable {

    @FXML
    private Button SendButton;
    
    @FXML
    private JFXTextArea MsgTextArea;
    @FXML
    private Pane Discussion = new AnchorPane();
    @FXML
    private JFXListView<?> ConvoList;
    int k=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void SendMessage(ActionEvent event) {
    	String msg;
    	msg=MsgTextArea.getText();
    	MsgTextArea.clear();
    	Label lab=new Label();
    	lab.setText(msg);
    	//Discussion.setTopAnchor(lab, (double) 10);
    	lab.setTranslateY(14);
    	lab.setTranslateX(21);
    	Discussion.getChildren().add(lab);
    	//ConvoList.setItems(value);
    	k+=30;
    	
    }
    
}
