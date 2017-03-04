package application;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXTextField;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

public class RegistrationController implements Initializable {
	@FXML
	private JFXTextField firstNameRegistrationTF;
	@FXML
	private JFXTextField lastNameRegistrationTF;
	@FXML
	private JFXTextField mailRegistrationTF;
	@FXML
	private JFXTextField usernameRegistrationTF;
	@FXML
	private JFXTextField PasswordRegistrationTF;
	@FXML
	private JFXTextField rePasswordRegistrationTF;
	@FXML
	private ImageView myImageView;
	@FXML
	private Button mailRegistrationError;
	@FXML
	private Button usernameRegistrationError;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mailRegistrationError.setVisible(false);
		usernameRegistrationError.setVisible(false);
		
	}
	// Event Listener on JFXButton.onAction
	@FXML
	public void nextStepRegistration(ActionEvent event) {
		
	}
	
	@FXML
	public void verifyRegistrationMail(KeyEvent event)
	{
		Runnable mailVerification = new Runnable() {
			
			@Override
			public void run() {
					if(!LoginController.userManagment.checkMailExistance(mailRegistrationTF.getText()))
					{
						mailRegistrationError.setVisible(true);
					}
					else
					{
						mailRegistrationError.setVisible(false);
					}
				
			}
		};
		new Thread(mailVerification).run();
	}
	
	@FXML
	public void verifyRegistrationUsername(KeyEvent event)
	{
		Runnable usernameVerification = new Runnable() {
			
			@Override
			public void run() {
					if(!LoginController.userManagment.checkUsernameExistance(usernameRegistrationTF.getText()))
					{
						usernameRegistrationError.setVisible(true);
					}
					else
					{
						usernameRegistrationError.setVisible(false);
					}
				
			}
		};
		new Thread(usernameVerification).run();
	}
	
	// Event Listener on JFXButton.onAction
	@FXML
	public void pickPicture(ActionEvent event) {
		 FileChooser fileChooser = new FileChooser();
         FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
         FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
         fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);     
         File file = fileChooser.showOpenDialog(null);               
         try {
             BufferedImage bufferedImage = ImageIO.read(file);
             Image image = SwingFXUtils.toFXImage(bufferedImage, null);
             myImageView.setImage(image);
         } catch (IOException ex) { 
         }
         catch (IllegalArgumentException e) {
			System.out.println("hi");
		}
	}

}