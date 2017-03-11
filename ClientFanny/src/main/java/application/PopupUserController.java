/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.ArtistFields;
import entities.Gallery;
import entities.User;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ElarbiMohamedAymen
 */
public class PopupUserController implements Initializable {

	@FXML
	private AnchorPane popupAnchor;
	@FXML
	private ImageView UserSelectedPic;
	@FXML
	private JFXTextField firstName;
	@FXML
	private JFXTextField LastName;
	@FXML
	private JFXTextField mailTf;
	@FXML
	private JFXTextField usernameTF;
	@FXML
	private JFXButton FollowUnfollow;
	@FXML
	private Tab titleTab;
	@FXML
	private AnchorPane GalleryThing;
	@FXML
	private Label surfaceTF;
	@FXML
	private Label AddressTF;
	@FXML
	private Label DescriptionTF;
	@FXML
	private AnchorPane ArtistThing;
	@FXML
	private Label BioLabel;
	@FXML
	private JFXComboBox<String> FieldsCombo;

	static public User userChoosen;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
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
			for (ArtistFields af : ((Artist) userChoosen).getLfields()) {
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
				UserSelectedPic.setImage(image);
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
				UserSelectedPic.setImage(wr);
			} catch (IOException e) {
			}
		}
		if (LoginController.userManagment.getAllFollowed(LoginController.userLogedIn).contains(userChoosen)) {
			FollowUnfollow.setText("UnFollow");
		} else {
			FollowUnfollow.setText("Follow");
		}

	}

	@FXML
	private void FollowUnfollowUser(ActionEvent event) {
		if (FollowUnfollow.getText().equals("Follow")) {
			LoginController.userManagment.addFollower(LoginController.userLogedIn, userChoosen);
			FollowUnfollow.setText("UnFollow");

		} else {
			LoginController.userManagment.removeFollower(LoginController.userLogedIn, userChoosen);
			FollowUnfollow.setText("Follow");
		}
	}

}
