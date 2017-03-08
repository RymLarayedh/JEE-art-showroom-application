package applicationRym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import services.ArtworkManagemetRemote;
import services.FeedbackManagmentRemote;
import services.UserManagmentRemote;

import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.FeedbackId;
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
	    private JFXTextField titleTF;
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
			
			ctxU = new InitialContext();
			Object objectU = ctxU.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyU = (UserManagmentRemote) objectU;
			//r.setUser(proxyU.findById(1));
			
			ctxA = new InitialContext();
			Object objectA = ctxU.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxyA = (ArtworkManagemetRemote) objectA;
			//r.setArtwork(proxyA.findArtworkByID(1));
			
			FeedbackId feedbackId =new FeedbackId();
			feedbackId.setArtworkId(1);
			feedbackId.setUserId(2);
			
			r.setFeedbackId(feedbackId);
			proxy.addReclamation(r);
	    }

	    @FXML
	    private void FinishAction(ActionEvent event) {
	    }
	
	
}
