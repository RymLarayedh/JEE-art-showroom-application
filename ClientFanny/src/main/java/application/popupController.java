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

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entities.User;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
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

	static public User userChoosen;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		firstName.setDisable(true);
		LastName.setDisable(true);
		mailTf.setDisable(true);
		usernameTF.setDisable(true);
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
		if (userChoosen.isActive()) {
			disableButton.setText("Disable this user");
		} else {
			disableButton.setText("Enable this user");
		}

	}

	@FXML
	void DisableUsersAccount(ActionEvent event) {
		if (disableButton.getText().equals("Disable this user")) {
			LoginController.userManagment.blockUser(userChoosen);
			disableButton.setText("Enable this user");
		} else {
			LoginController.userManagment.unblockUser(userChoosen);
			disableButton.setText("Disable this user");
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

}
