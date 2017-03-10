package application;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.ArtistFields;
import entities.Gallery;
import entities.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import utils.ConfirmBox;

public class popupController implements Initializable {
	@FXML
	private ImageView profileAdmin;
	@FXML
	private JFXButton ChangePic;

	@FXML
	private JFXTextField firstName;

	@FXML
	private JFXTextField LastName;

	@FXML
	private JFXTextField mailTf;

	@FXML
	private JFXTextField usernameTF;

	@FXML
	private JFXButton disableButton;

	@FXML
	private AnchorPane popupAnchor;

	@FXML
	private Tab titleTab;

	@FXML
	private Label DescriptionTF;

	@FXML
	private Label AddressTF;

	@FXML
	private Label surfaceTF;

	@FXML
	private AnchorPane GalleryThing;
	@FXML
	private AnchorPane ArtistThing;

	@FXML
	private Label BioLabel;

	@FXML
	private JFXComboBox<String> FieldsCombo;
	static public User userChoosen;
	static public String fromWhere = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// the more about section
		if (userChoosen instanceof Gallery) {
			GalleryThing.setVisible(true);
			ArtistThing.setVisible(false);
			surfaceTF.setText(String.valueOf(((Gallery) userChoosen).getSurface()));
			AddressTF.setText(((Gallery) userChoosen).getAddress());
			DescriptionTF.setText(((Gallery) userChoosen).getDescription());
		}

		if (userChoosen instanceof Artist) {
			FieldsCombo.getItems().add("----------------------");
			GalleryThing.setVisible(false);
			ArtistThing.setVisible(true);
			BioLabel.setText(((Artist) userChoosen).getBio());
			for(ArtistFields af:((Artist) userChoosen).getLfields())
			{
				FieldsCombo.getItems().add(af.getField().getLibelle());
			}
		}

		//
		firstName.setEditable(false);
		LastName.setEditable(false);
		mailTf.setEditable(false);
		usernameTF.setEditable(false);
		titleTab.setText("More about " + userChoosen.getFirstName() + " " + userChoosen.getLastName());
		firstName.setText(userChoosen.getFirstName());
		LastName.setText(userChoosen.getLastName());
		mailTf.setText(userChoosen.getEmail());
		usernameTF.setText(userChoosen.getUsername());
		if (userChoosen.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profileAdmin.setImage(image);
			} catch (IOException e) {
			}

		} else {
			try {
				byte[] b = userChoosen.getPicture();
				BufferedImage imgbf = null;

				imgbf = ImageIO.read(new ByteArrayInputStream(b));
				WritableImage wr = null;
				if (imgbf != null) {
					wr = new WritableImage(imgbf.getWidth(), imgbf.getHeight());
					PixelWriter pw = wr.getPixelWriter();
					for (int x = 0; x < imgbf.getWidth(); x++) {
						for (int y = 0; y < imgbf.getHeight(); y++) {
							pw.setArgb(x, y, imgbf.getRGB(x, y));
						}
					}
				}
				profileAdmin.setImage(wr);
			} catch (IOException e) {
			}
		}
		if (fromWhere.equals("Active")) {
			disableButton.setText("Block this user");
		} else if (fromWhere.equals("Wating")) {
			disableButton.setText("Approve this user");
		} else {
			disableButton.setText("Unblock this user");
		}

	}

	@FXML
	void DisableUsersAccount(ActionEvent event) throws AddressException, MessagingException {
		if (disableButton.getText().equals("Block this user")) {
			LoginController.userManagment.blockUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						LoginController.userManagment.sendMail(userChoosen.getEmail(),
								"FannyTUNISIA is sorry to announce that we blocked your account for the moment",
								"Something went wrong");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("User Blocked");
			alert.showAndWait();
			disableButton.setDisable(true);
		} else if (disableButton.getText().equals("Approve this user")) {
			LoginController.userManagment.enableUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						LoginController.userManagment.sendMail(userChoosen.getEmail(),
								"FannyTUNISIA is so glad to have you again in our community welcome aboard again",
								"Welcome again");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("User Approved");
			alert.showAndWait();
			disableButton.setDisable(true);
		} else {
			LoginController.userManagment.unblockUser(userChoosen);
			Runnable mailing = new Runnable() {

				@Override
				public void run() {
					try {
						LoginController.userManagment.sendMail(userChoosen.getEmail(),
								"FannyTUNISIA is so glad to have you in our community so please behave and respect other members",
								"Welcome aboard");
					} catch (MessagingException e) {
					}

				}

			};
			new Thread(mailing).start();
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("User Unblocked");
			alert.showAndWait();
			disableButton.setDisable(true);
		}

	}

	@FXML
	void changeUsersPicture(ActionEvent event) throws IOException {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			image = SwingFXUtils.toFXImage(bufferedImage, null);
		} catch (IOException ex) {
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this changes ?");

		if (confirm) {
			profileAdmin.setImage(image);
			userChoosen.setPicture(bFile);
			LoginController.userManagment.updateUser(userChoosen);
			if (LoginController.userLogedIn.getIdUser() == userChoosen.getIdUser()) {
				LoginController.userLogedIn = userChoosen;
			}
		}
	}

	@FXML
	public void UpdateFirstName(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			firstName.setEditable(true);
			firstName.requestFocus();
			// saveFile
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setUsername(usernameTF.getText());
					userChoosen.setEmail(mailTf.getText());
					LoginController.userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Admin.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					AdminController controller = loader.getController();
					// controller.DisplayUsers();
				}
				stage.close();
			});

		} else {

			firstName.setEditable(false);
		}

	}

	@FXML
	public void UpdateLastName(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			LastName.setEditable(true);
			LastName.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setUsername(usernameTF.getText());
					userChoosen.setEmail(mailTf.getText());
					LoginController.userManagment.updateUser(userChoosen);
					stage.close();
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Admin.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					AdminController controller = loader.getController();
					controller.setTable(LoginController.userManagment.getAllUsers());

				}

			});
		} else {
			LastName.setEditable(false);
		}
	}

	@FXML
	public void UpdateMail(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			mailTf.setEditable(true);
			mailTf.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setUsername(usernameTF.getText());
					userChoosen.setEmail(mailTf.getText());
					LoginController.userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Admin.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					AdminController controller = loader.getController();
					// controller.DisplayUsers();

				}
				stage.close();
			});
		}

		else {
			mailTf.setEditable(false);
		}
	}

	@FXML
	public void UpdateUsername(MouseEvent event) {
		boolean confirm = ConfirmBox.display("", "Do you want the change this value ?");
		if (confirm) {
			usernameTF.setEditable(true);
			usernameTF.requestFocus();
			Stage stage = (Stage) popupAnchor.getScene().getWindow();
			stage.setOnCloseRequest(e -> {
				if (isChanged()) {
					userChoosen.setFirstName(firstName.getText());
					userChoosen.setLastName(LastName.getText());
					userChoosen.setUsername(usernameTF.getText());
					userChoosen.setEmail(mailTf.getText());
					LoginController.userManagment.updateUser(userChoosen);
					FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Admin.fxml"));
					try {
						Parent root = (Parent) loader.load();
					} catch (Exception e1) {
					}
					AdminController controller = loader.getController();
					// controller.DisplayUsers();
				}
				stage.close();
			});
		} else {
			usernameTF.setEditable(false);
		}

	}

	boolean isChanged() {
		if (usernameTF.getText().trim().isEmpty() || (mailTf.getText().trim().isEmpty())
				|| (firstName.getText().trim().isEmpty()) || (LastName.getText().trim().isEmpty())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Empty fields are not accepted chnages wont be saved");
			alert.showAndWait();
			return false;
		} else {
			if ((usernameTF.getText().equals(userChoosen.getUsername()))
					|| (mailTf.getText().equals(userChoosen.getEmail()))
					|| (LastName.getText().equals(userChoosen.getLastName()))
					|| (firstName.getText().equals(userChoosen.getFirstName()))) {
				return true;
			}
		}
		return false;
	}

}
