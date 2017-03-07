package application;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import entities.User;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

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
		firstName.setText(userChoosen.getFirstName());
		LastName.setText(userChoosen.getLastName());
		mailTf.setText(userChoosen.getEmail());
		usernameTF.setText(userChoosen.getUsername());
		if (LoginController.userLogedIn.getPicture() == null) {
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
				byte[] b = LoginController.userLogedIn.getPicture();
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
	void changeUsersPicture(ActionEvent event) {
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
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			profileAdmin.setImage(image);
		} catch (IOException ex) {
		}

	}

}
