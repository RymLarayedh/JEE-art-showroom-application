package integration;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ArtworkManagemetRemote;
import services.FeedbackManagmentRemote;
import services.UserManagmentRemote;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Reclamation;
import javafx.event.ActionEvent;

public class ReclamationAddController implements Initializable {
	
	    @FXML
	    private AnchorPane step1;
	    @FXML
	    private AnchorPane step2;
	    @FXML
	    private JFXTextArea bodyTF;

	    @FXML
	    private AnchorPane step3;

	@FXML
    private RadioButton hap1;
    @FXML
    private RadioButton hap2;
    @FXML
    private RadioButton hap3;
    
    InitialContext ctxU;
    InitialContext ctx;
    InitialContext ctxA;
    
 // Event Listener on RadioButton.onAction
 	@Override
 	public void initialize(URL location, ResourceBundle resources) {
 		step1.setVisible(true);
		step2.setVisible(false);
		step3.setVisible(false);
 		final ToggleGroup group = new ToggleGroup();	
 		hap1.setToggleGroup(group);
 		hap2.setToggleGroup(group);
 		hap3.setToggleGroup(group);
 		
 	}
    
	// Event Listener on Button.onAction
	@FXML
	public void continue1Action(ActionEvent event) throws NamingException {
		step1.setVisible(false);
		step2.setVisible(true);
		step3.setVisible(false);
		
	}
	 @FXML
	    private void continue2Action(ActionEvent event) throws NamingException {
		 step1.setVisible(false);
			step2.setVisible(false);
			step3.setVisible(true);
		 ctx = new InitialContext();
			Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/FeedbackManagment!services.FeedbackManagmentRemote");
			FeedbackManagmentRemote proxy = (FeedbackManagmentRemote) object;
			Reclamation r= new Reclamation();
			if (hap1.isSelected())
			{
				r.setDegree(1);
			}
			if (hap2.isSelected())
			{
				r.setDegree(2);
			}
			if (hap3.isSelected())
			{
				r.setDegree(3);
			}
			r.setBody(bodyTF.getText());
			r.setHandle(0);
			//Convert Date Now
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			//r.setDate(date);
			
			//*************************change*****************************
			ctx = new InitialContext();
			Object objectPP2 = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxyPP2 = (ArtworkManagemetRemote) objectPP2;
			r.setArtwork(proxyPP2.findArtworkByID(1));
			ctx = new InitialContext();
			Object objectPP = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyPP = (UserManagmentRemote) objectPP;
			r.setUser(proxyPP.findById(LoginController.userLogedIn.getIdUser()));
			//*************************change*****************************
			
			proxy.addReclamation(r);
	    }

	    @FXML
	    private void FinishAction(ActionEvent event) {
	    	final Node source = (Node) event.getSource();
			final Stage stage = (Stage) source.getScene().getWindow();
			stage.close();
			
	    }
	
	
}
