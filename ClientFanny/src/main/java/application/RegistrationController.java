package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.Fields;
import entities.Gallery;
import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RegistrationController implements Initializable {
	User newUser = new User();
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
	private JFXPasswordField PasswordRegistrationTF;
	@FXML
	private JFXPasswordField rePasswordRegistrationTF;
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
	@FXML
	private JFXButton GalleryFinish;
	@FXML
	private JFXButton ArtistFinish;
	@FXML
	private ToggleButton musicField;
	@FXML
	private ToggleButton PaintingsField;
	@FXML
	private ToggleButton TcraftField;
	@FXML
	private ToggleButton photographyField;
	@FXML
	private ToggleButton scultureField;
	@FXML
	private JFXTextArea bioTextArea;
	@FXML
	private JFXTextField addressRegistrationTF;
	@FXML
	private JFXTextField surfaceRegistrationTF;
	@FXML
	private JFXTextArea DescriptionRegistrationTA;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		ArtistFinish.setVisible(false);
		GalleryFinish.setVisible(false);
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
		if ((firstNameRegistrationTF.getText().trim().isEmpty()) || (lastNameRegistrationTF.getText().trim().isEmpty())
				|| (mailRegistrationTF.getText().trim().isEmpty())
				|| (usernameRegistrationTF.getText().trim().isEmpty())
				|| (PasswordRegistrationTF.getText().trim().isEmpty())
				|| (rePasswordRegistrationTF.getText().trim().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Empty fields ARE NOT ACCEPTED please fulfill all fields");
			alert.showAndWait();
			return;
		}
		if (!PasswordRegistrationTF.getText().equals(rePasswordRegistrationTF.getText())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Passwords does not match");
			alert.showAndWait();
			return;
		}

		if (mailRegistrationError.isVisible()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("E-mail already exist");
			alert.showAndWait();
			return;
		}

		if (usernameRegistrationError.isVisible()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Username already exist");
			alert.showAndWait();
			return;
		}
        try {
            for (int i = 0; i < firstNameRegistrationTF.getText().trim().length(); i++) {
                if ((firstNameRegistrationTF.getText().trim().toUpperCase().charAt(i) > 90 )||(firstNameRegistrationTF.getText().trim().toUpperCase().charAt(i) < 65 )) {
                    throw new NumberFormatException();
                }
            }
            }catch (NumberFormatException E) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Fanny");
    			alert.setHeaderText(null);
    			alert.setContentText("First Name must Contains only characters");
    			alert.showAndWait();
    			return;
        }
        
        try {
            for (int i = 0; i < lastNameRegistrationTF.getText().trim().length(); i++) {
                if ((lastNameRegistrationTF.getText().trim().toUpperCase().charAt(i) > 90 )||(lastNameRegistrationTF.getText().trim().toUpperCase().charAt(i) < 65 )) {
                    throw new NumberFormatException();
                }
            }
            }catch (NumberFormatException E) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    			alert.setTitle("Fanny");
    			alert.setHeaderText(null);
    			alert.setContentText("Last Name must Contains only characters");
    			alert.showAndWait();
    			return;
        }
		newUser.setFirstName(firstNameRegistrationTF.getText());
		newUser.setLastName(lastNameRegistrationTF.getText());
		newUser.setEmail(mailRegistrationTF.getText());
		newUser.setUsername(usernameRegistrationTF.getText());
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
		ArtistFinish.setVisible(false);
		GalleryFinish.setVisible(false);

		GalleryRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Gallery gallery = new Gallery(newUser);
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameGallery.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				GalleryFinish.setVisible(true);
				ArtistFinish.setVisible(false);
				GalleryFinish.setText("Finish");
				GalleryFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(DescriptionRegistrationTA.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fanny");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Description must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						gallery.setDescription(DescriptionRegistrationTA.getText());
						try {
							gallery.setSurface(Float.valueOf(surfaceRegistrationTF.getText()));

						} catch (NumberFormatException E) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Surface is NUMERIC only ");
							alert.showAndWait();
							surfaceRegistrationTF.requestFocus();
							return;
						}
						gallery.setAddress(addressRegistrationTF.getText());
						LoginController.userManagment.addUser(gallery);
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
				});
	
			}
		});

		ArtistRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Artist artist = new Artist(newUser);
				List<Fields> Lf = new ArrayList<>();
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameArtist.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				GalleryFinish.setVisible(false);
				ArtistFinish.setVisible(true);
				ArtistFinish.setText("Finish");
				ArtistFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (musicField.isSelected()) {
							Fields music = LoginController.userManagment.findFieldsByName("Music");
							Lf.add(music);
						}
						if (PaintingsField.isSelected()) {
							Fields painting = LoginController.userManagment.findFieldsByName("Paintings");
							Lf.add(painting);
						}
						if (photographyField.isSelected()) {
							Fields photography = LoginController.userManagment.findFieldsByName("Photography");
							Lf.add(photography);
						}
						if (TcraftField.isSelected()) {
							Fields TC = LoginController.userManagment.findFieldsByName("Tunisian Craft");
							Lf.add(TC);
						}
						if (scultureField.isSelected()) {
							Fields sculpture = LoginController.userManagment.findFieldsByName("Sculpture");
							Lf.add(sculpture);
						}
						if(bioTextArea.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fanny");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Bio must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						artist.setBio(bioTextArea.getText());
						LoginController.userManagment.addUser(artist);
						for (Fields f : Lf) {
							LoginController.userManagment.addFields(f,
									LoginController.userManagment.findByUsername(artist.getUsername()));
						}
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}

					}
				});
			}
		});

	}

	@FXML
	public void verifyRegistrationMail(KeyEvent event) {
		Runnable mailVerification = new Runnable() {

			@Override
			public void run() {
				if ((!LoginController.userManagment.checkMailExistance(mailRegistrationTF.getText()))
						|| (!LoginController.userManagment.verifyMail(mailRegistrationTF.getText()))) {
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
		ArtistFinish.setVisible(false);
		GalleryFinish.setVisible(false);

		GalleryRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Gallery gallery = new Gallery(newUser);
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameGallery.setVisible(true);
				stepsButtonRegistration.setText("Finish");
				stepsButtonRegistration.setVisible(false);
				ArtistFinish.setVisible(false);
				GalleryFinish.setVisible(true);
				GalleryFinish.setText("Finish");
				GalleryFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						if(DescriptionRegistrationTA.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fanny");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Description must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						gallery.setDescription(DescriptionRegistrationTA.getText());
						try {
							gallery.setSurface(Float.valueOf(surfaceRegistrationTF.getText()));

						} catch (NumberFormatException E) {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Information Dialog");
							alert.setHeaderText(null);
							alert.setContentText("Surface is NUMERIC only ");
							alert.showAndWait();
							surfaceRegistrationTF.requestFocus();
							return;
						}
						gallery.setAddress(addressRegistrationTF.getText());
						LoginController.userManagment.addUser(gallery);
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}
					}
				});

			}
		});

		ArtistRegistration.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stepsButtonRegistration.setVisible(true);
				Artist artist = new Artist(newUser);
				List<Fields> Lf = new ArrayList<>();
				lastStep.setDisable(false);
				secondFrame.setVisible(false);
				thirdFrameArtist.setVisible(true);
				stepsButtonRegistration.setVisible(false);
				ArtistFinish.setVisible(true);
				GalleryFinish.setVisible(false);
				ArtistFinish.setText("Finish");
				ArtistFinish.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (musicField.isSelected()) {
							Fields music = LoginController.userManagment.findFieldsByName("Music");
							Lf.add(music);
						}
						if (PaintingsField.isSelected()) {
							Fields painting = LoginController.userManagment.findFieldsByName("Paintings");
							Lf.add(painting);
						}
						if (photographyField.isSelected()) {
							Fields photography = LoginController.userManagment.findFieldsByName("Photography");
							Lf.add(photography);
						}
						if (TcraftField.isSelected()) {
							Fields TC = LoginController.userManagment.findFieldsByName("Tunisian Craft");
							Lf.add(TC);
						}
						if (scultureField.isSelected()) {
							Fields sculpture = LoginController.userManagment.findFieldsByName("Sculpture");
							Lf.add(sculpture);
						}
						if(bioTextArea.getText().trim().length() < 15)
						{
			    			Alert alert = new Alert(Alert.AlertType.ERROR);
			    			alert.setTitle("Fanny");
			    			alert.setHeaderText(null);
			    			alert.setContentText("Bio must have at least 15 caracters");
			    			alert.showAndWait();
			    			return;
						}
						artist.setBio(bioTextArea.getText());
						LoginController.userManagment.addUser(artist);
						for (Fields f : Lf) {
							LoginController.userManagment.addFields(f,
									LoginController.userManagment.findByUsername(artist.getUsername()));
						}
						try {
							FinalAlert();
						} catch (IOException e) {
							// TODO Auto-generated catch block
						}

					}
				});

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
		ArtistFinish.setVisible(false);
		GalleryFinish.setVisible(false);
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

	@FXML
	public void goBack(ActionEvent event) throws IOException {
		Parent adminScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(adminScene, 600, 600);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.setOnCloseRequest(e -> {
			e.consume();
			Main.closeProgram(Sc);
		});

		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	void FinalAlert() throws IOException {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("FannyTUNISIA");
		alert.setHeaderText(null);
		alert.setContentText(
				"THANK you for choosing our application you will get an approval e-mail from our contact service announcing the activation of your account");
		alert.showAndWait();
		Parent adminScene = FXMLLoader.load(getClass().getResource("Login.fxml"));
		Scene scene = new Scene(adminScene, 600, 600);
		scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.setOnCloseRequest(e -> {
			e.consume();
			Main.closeProgram(Sc);
		});

		Sc.show();
		final Stage stage = (Stage) firstFrame.getScene().getWindow();
		stage.close();

	}

}
