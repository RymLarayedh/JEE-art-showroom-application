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
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.Gallery;
import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RegistrationController implements Initializable {
	User newUser;
	File file;
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
	@FXML
	private AnchorPane firstFrame;
	@FXML
	private AnchorPane secondFrame;
	@FXML
	private AnchorPane registrationFrame;
	@FXML
	private AnchorPane thirdFrameArtist;
	@FXML
	private AnchorPane thirdFrameGallery;
	@FXML
	private Button Generalinformation;
	@FXML
	private Button youare;
	@FXML
	private Button lastStep;
	@FXML
	private Button GalleryRegistration;
	@FXML
	private Button ArtistRegistration;
	@FXML
	private JFXButton stepsButtonRegistration;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		mailRegistrationError.setVisible(false);
		usernameRegistrationError.setVisible(false);
		secondFrame.setVisible(false);
		thirdFrameArtist.setVisible(false);
		thirdFrameGallery.setVisible(false);
		youare.setDisable(true);
		lastStep.setDisable(true);

	}

	// Event Listener on JFXButton.onAction
	@FXML
	public void nextStepRegistration(ActionEvent event) throws IOException {
		newUser = new User();
		newUser.setFirstName(firstNameRegistrationTF.getText());
		newUser.setLastName(lastNameRegistrationTF.getText());
		newUser.setEmail(mailRegistrationTF.getText());
		newUser.setPassword(PasswordRegistrationTF.getText());
		if (file == null) {
			file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			myImageView.setImage(image);
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}
		newUser.setPicture(bFile);
		firstFrame.setVisible(false);
		secondFrame.setVisible(true);
		youare.setDisable(false);
		stepsButtonRegistration.setVisible(false);

		GalleryRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Gallery gallery = new Gallery(newUser);
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameGallery.setVisible(true);
				stepsButtonRegistration.setText("Finish");

			}
		});

		ArtistRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Artist artist = new Artist(newUser);
				System.out.println(artist.getLastName());
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameArtist.setVisible(true);
				stepsButtonRegistration.setText("Finish");

			}
		});

	}

	@FXML
	public void verifyRegistrationMail(KeyEvent event) {
		Runnable mailVerification = new Runnable() {

			@Override
			public void run() {
				if (!LoginController.userManagment.checkMailExistance(mailRegistrationTF.getText())) {
					mailRegistrationError.setVisible(true);
				} else {
					mailRegistrationError.setVisible(false);
				}

			}
		};
		new Thread(mailVerification).run();
	}

	@FXML
	public void verifyRegistrationUsername(KeyEvent event) {
		Runnable usernameVerification = new Runnable() {

			@Override
			public void run() {
				if (!LoginController.userManagment.checkUsernameExistance(usernameRegistrationTF.getText())) {
					usernameRegistrationError.setVisible(true);
				} else {
					usernameRegistrationError.setVisible(false);
				}

			}
		};
		new Thread(usernameVerification).run();
	}

	// Event Listener on JFXButton.onAction
	@FXML
	public void pickPicture(ActionEvent event) throws IOException {
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			myImageView.setImage(image);
		} catch (IOException ex) {
		}
	}

	@FXML
	public void ChooseWhoYouAre(ActionEvent event) {
		thirdFrameArtist.setVisible(false);
		thirdFrameGallery.setVisible(false);
		secondFrame.setVisible(true);
		lastStep.setDisable(true);
		stepsButtonRegistration.setVisible(false);

		GalleryRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Gallery gallery = new Gallery(newUser);
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameGallery.setVisible(true);
				stepsButtonRegistration.setText("Finish");

			}
		});

		ArtistRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Artist artist = new Artist(newUser);
				System.out.println(artist.getLastName());
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameArtist.setVisible(true);
				stepsButtonRegistration.setText("Finish");

			}
		});

	}

	@FXML
	public void reTypeGeneralInformation(ActionEvent event) {
		thirdFrameArtist.setVisible(false);
		thirdFrameGallery.setVisible(false);
		secondFrame.setVisible(false);
		firstFrame.setVisible(true);
		lastStep.setDisable(true);
		youare.setDisable(true);
		stepsButtonRegistration.setVisible(true);
		stepsButtonRegistration.setText("Next");

	}

	@FXML
	public void goToGeneral(MouseEvent event) {
		if (!Generalinformation.isDisable()) {
			ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
			reTypeGeneralInformation(ae);
		}
	}

	@FXML
	public void goToWhoYouAre(MouseEvent event) {
		if (!youare.isDisable()) {
			ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
			ChooseWhoYouAre(ae);
		}
	}

}
