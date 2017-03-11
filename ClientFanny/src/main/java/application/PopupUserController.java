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
import entities.ArtistFollowers;
import entities.Gallery;
import entities.User;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	@FXML
	private TableView<User> Follow;

	@FXML
	private TableColumn<User, byte[]> PictureTV;

	@FXML
	private TableColumn<User, String> FirstNameTV;

	@FXML
	private TableColumn<User, String> LastNameTV;

	@FXML
	private TableColumn<User, String> ActionTV;
	ObservableList<User> UsersData = FXCollections.observableArrayList();
	List<User>LrecherchFN = new ArrayList<>();
	List<Artist>LrecherchArt = new ArrayList<>();
	@FXML
	private JFXButton FollowersBTN;
	@FXML
	private JFXButton followedBTN;

	static public User userChoosen;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Follow.setVisible(false);
		if (userChoosen instanceof Gallery) {
			GalleryThing.setVisible(true);
			ArtistThing.setVisible(false);
			surfaceTF.setText(String.valueOf(((Gallery) userChoosen).getSurface()));
			AddressTF.setText(((Gallery) userChoosen).getAddress());
			DescriptionTF.setText(((Gallery) userChoosen).getDescription());
			followedBTN.setVisible(false);
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
	
	@FXML
	private void ShowFollowers(ActionEvent event)
	{		
		//eli houwa itaba3 fihom
		Follow.setVisible(true);
		GalleryThing.setVisible(false);
		ArtistThing.setVisible(false);
		LrecherchArt.clear();
		UsersData.clear();
		LrecherchArt = LoginController.userManagment.getAllFollowed(userChoosen);
		PictureTV.setCellValueFactory(new PropertyValueFactory<User, byte[]>("Picture"));
		PictureTV.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
			@Override
			public TableCell<User, byte[]> call(TableColumn<User, byte[]> param) {
				TableCell<User, byte[]> cell = new TableCell<User, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});
		ActionTV.setCellValueFactory(new PropertyValueFactory<>("Action"));
		Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = //
				new Callback<TableColumn<User, String>, TableCell<User, String>>() {
					@Override
					public TableCell call(final TableColumn<User, String> param) {
						final TableCell<User, String> cell = new TableCell<User, String>() {

							final JFXButton btn = new JFXButton();

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setText("More Information");
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		ActionTV.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		Follow.setItems(UsersData);
		
	}
	
	@FXML
	private void showFollowed(ActionEvent event)
	{
		//eli houma itab3ou fih
		//followUnfollow
		LrecherchFN.clear();
		UsersData.clear();
		Follow.setVisible(true);
		GalleryThing.setVisible(false);
		ArtistThing.setVisible(false);

		for(ArtistFollowers u :((Artist)userChoosen).getFollowers())
		{
			LrecherchFN.add(u.getUser());
		}
		PictureTV.setCellValueFactory(new PropertyValueFactory<User, byte[]>("Picture"));
		PictureTV.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
			@Override
			public TableCell<User, byte[]> call(TableColumn<User, byte[]> param) {
				TableCell<User, byte[]> cell = new TableCell<User, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});
		ActionTV.setCellValueFactory(new PropertyValueFactory<>("Action"));
		Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = //
				new Callback<TableColumn<User, String>, TableCell<User, String>>() {
					@Override
					public TableCell call(final TableColumn<User, String> param) {
						final TableCell<User, String> cell = new TableCell<User, String>() {

							final JFXButton btn = new JFXButton();

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									btn.setText("More Information");
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		ActionTV.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		Follow.setItems(UsersData);
		
	}
	
	@FXML
	public void MoreInformation(MouseEvent event) throws IOException {
		if (PopupUserController.userChoosen == Follow.getSelectionModel().getSelectedItem()) {
			return;
		}
		PopupUserController.userChoosen = Follow.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.showAndWait();

	}

}
