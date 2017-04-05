/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.security.auth.login.LoginException;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import business.UserManagmentDelegate;
import entities.Admin;
import entities.Artist;
import entities.ArtistFields;
import entities.ArtistFollowers;
import entities.Fields;
import entities.Gallery;
import entities.User;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import utils.ConfirmBox;

/**
 * FXML Controller class
 *
 * @author Oussamabhhh
 */
public class ProfileuserController implements Initializable {

	@FXML
	private AnchorPane magicbar;
	@FXML
	private ImageView myimage;
	@FXML
	private ImageView magic2;
	@FXML
	private Text myname;
	@FXML
	private Text mylastname;
	@FXML
	private Button profil;
	@FXML
	private Button message;
	@FXML
	private ImageView magic;
	@FXML
	private Button home;

	@FXML
	private Button ownspace;
	@FXML
	private Button visuala;
	@FXML
	private Button musicb;
	@FXML
	private Button eventb;
	@FXML
	private Button Logout;
	@FXML
	private Button tunisianc;
	@FXML
	private AnchorPane MusicPane;
	@FXML
	private TableView tableMusic;

	/* DEBUT AYMEN */
	@FXML
	private AnchorPane AymensPane;
	@FXML
	private TabPane OwnProfileArtist;
	MouseEvent mEvent;

	@FXML
	private Button mailEDIT;
	@FXML
	private Button bioEDIT;
	@FXML
	private ImageView imageViewProfile;
	@FXML
	private JFXTextArea BIOTF;
	@FXML
	private JFXTextField FirstNameTF;
	@FXML
	private JFXTextField LastNameTF;
	@FXML
	private JFXTextField mailTF;
	@FXML
	private JFXButton SaveButton;
	boolean isChanged = false;
	@FXML
	private JFXTextField searchTF;
	@FXML
	private TableView<User> searchTable;

	@FXML
	private TableColumn<User, byte[]> Picture;

	@FXML
	private TableColumn<User, String> FirstName;

	@FXML
	private TableColumn<User, String> LastName;

	@FXML
	private TableColumn<User, String> Action;
	@FXML
	private TabPane OwnProfileGallery;
	ObservableList<User> UsersData = FXCollections.observableArrayList();
	List<User> LrecherchFN = new ArrayList<>();
	List<User> Lremove = new ArrayList<>();
	@FXML
	private JFXTextField mailTFGallery;
	@FXML
	private Button mailEDITGallery;
	@FXML
	private Button descriptionEDIT;
	@FXML
	private JFXTextArea description;
	@FXML
	private Button addressEDITGallery;
	@FXML
	private JFXTextField AddressTFGallery;
	@FXML
	private ImageView imageViewProfileG;
	@FXML
	private JFXTextField FirstNameTFG;
	@FXML
	private JFXTextField LastNameTFG;
	@FXML
	private JFXButton SaveGalleryButton;

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
	ObservableList<User> UsersDataME = FXCollections.observableArrayList();
	List<User> LrecherchFNME = new ArrayList<>();
	List<Artist> LrecherchArt = new ArrayList<>();
	@FXML
	private JFXButton FollowersBTN;
	@FXML
	private JFXButton followedBTN;
	private JFXButton FollowersBTNG;
	@FXML
	private TableView<User> FollowG;
	@FXML
	private TableColumn<User, byte[]> PictureTVG;
	@FXML
	private TableColumn<User, String> FirstNameTVG;
	@FXML
	private TableColumn<User, String> LastNameTVG;
	@FXML
	private TableColumn<User, String> ActionTVG;

	@FXML
	private JFXButton AddFieldsBTN;

	@FXML
	private JFXButton RemoveFieldsBTN;

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
	private boolean isAdding = false;

	static public User userChoosen = LoginController.userLogedIn;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		PaintingsField.setVisible(false);
		TcraftField.setVisible(false);
		photographyField.setVisible(false);
		scultureField.setVisible(false);
		musicField.setVisible(false);
		FollowG.setVisible(false);
		OwnProfileArtist.setVisible(false);
		OwnProfileGallery.setVisible(false);
		searchTable.setVisible(false);
		AymensPane.setVisible(true);
		magicbar.setVisible(false);
		userChoosen = LoginController.userLogedIn;

		// aymen//
		if (LoginController.userLogedIn.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				myimage.setImage(image);
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
				myimage.setImage(wr);
			} catch (IOException e) {
			}
		}
		// endAymen//

	}

	@FXML
	private void message(ActionEvent event) {
	}

	@FXML
	private void home(ActionEvent event) {
	}

	public void affichermagicbar() {
		Polyline polyline = new Polyline();
		polyline.getPoints()
				.addAll(new Double[] { -85.0, 320.0, 85.0, 320.0, 75.0, 320.0, 85.0, 320.0, 80.0, 320.0, 85.0, 320.0 });
		PathTransition transation = new PathTransition();
		transation.setNode(magicbar);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();
	}

	public void cachermagicbar() {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 85.0, 320.0, -85.0, 320.0, });
		PathTransition transation = new PathTransition();
		transation.setNode(magicbar);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();
	}

	public void afficherbouton(Button x, Button y, Button z) {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 52.0 });

		PathTransition transation = new PathTransition();
		transation.setNode(x);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();

		Polyline polyline2 = new Polyline();
		polyline2.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 92.0, });
		PathTransition transation2 = new PathTransition();
		transation2.setNode(y);
		transation2.setDuration(Duration.seconds(2));
		transation2.setPath(polyline2);
		transation2.play();

		Polyline polyline3 = new Polyline();
		polyline3.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 132.0, });
		PathTransition transation3 = new PathTransition();
		transation3.setNode(z);
		transation3.setDuration(Duration.seconds(2));
		transation3.setPath(polyline3);
		transation3.play();

	}

	public void cacherbouton(Button x, Button y, Button z) {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 100.0, 52.0, 100.0, 20.0 });

		PathTransition transation = new PathTransition();
		transation.setNode(x);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();

		Polyline polyline2 = new Polyline();
		polyline2.getPoints().addAll(new Double[] { 100.0, 92.0, 100.0, 20.0, });
		PathTransition transation2 = new PathTransition();
		transation2.setNode(y);
		transation2.setDuration(Duration.seconds(2));
		transation2.setPath(polyline2);
		transation2.play();

		Polyline polyline3 = new Polyline();
		polyline3.getPoints().addAll(new Double[] { 100.0, 132.0, 100.0, 20.0, });
		PathTransition transation3 = new PathTransition();
		transation3.setNode(z);
		transation3.setDuration(Duration.seconds(2));
		transation3.setPath(polyline3);
		transation3.play();
	}

	@FXML
	private void magic(MouseEvent event) {
		magicbar.setVisible(true);
		affichermagicbar();
		magic.setVisible(false);
	}

	@FXML
	private void magic2(MouseEvent event) {
		mEvent = event;
		magic.setVisible(false);
		cachermagicbar();
		magic.setVisible(true);
	}

	@FXML
	private void Myownspace(ActionEvent event) {
	}

	@FXML
	private void visualart(ActionEvent event) {
	}

	@FXML
	private void Event(ActionEvent event) {
	}

	@FXML
	private void Tunisiancraft(ActionEvent event) {
	}

	/*******************************************************************************************************************/
	/********************************************************** Ines ***************************************************/
	@FXML
	private void music(ActionEvent event) {
	}

	/*******************************************************************************************/
	/**************************************************** AYMENE *******************************/
	/*******************************************************************************************/

	@FXML
	public void showProfile(MouseEvent event) {
		ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
		showProfileBTN(ae);
	}

	@FXML
	public void showProfileBTN(ActionEvent event) {
		searchTable.setVisible(false);
		AymensPane.setVisible(true);
		if (LoginController.userLogedIn instanceof Artist) {
			OwnProfileArtist.setVisible(true);
			OwnProfileGallery.setVisible(false);
			bioEDIT.setVisible(false);
			mailEDIT.setVisible(false);
			mailEDITGallery.setVisible(false);
			addressEDITGallery.setVisible(false);
			descriptionEDIT.setVisible(false);
			mailTF.setEditable(false);
			BIOTF.setEditable(false);
			FirstNameTF.setEditable(false);
			LastNameTF.setEditable(false);
			SaveButton.setVisible(false);
			FirstNameTFG.setEditable(false);
			LastNameTFG.setEditable(false);
			mailTFGallery.setEditable(false);
			description.setEditable(false);
			AddressTFGallery.setEditable(false);
			FirstNameTF.setTooltip(new Tooltip("Click To Edit"));
			LastNameTF.setTooltip(new Tooltip("Click To Edit"));
			FirstNameTF.setText(LoginController.userLogedIn.getFirstName());
			LastNameTF.setText(LoginController.userLogedIn.getLastName());
			mailTF.setText(LoginController.userLogedIn.getEmail());
			BIOTF.setText(((Artist) LoginController.userLogedIn).getBio());
			if (LoginController.userLogedIn.getPicture() == null) {
				File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageViewProfile.setImage(image);
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
					imageViewProfile.setImage(wr);
				} catch (IOException e) {
				}

			}
		} else {
			OwnProfileArtist.setVisible(false);
			OwnProfileGallery.setVisible(true);
			bioEDIT.setVisible(false);
			mailEDIT.setVisible(false);
			mailEDITGallery.setVisible(false);
			addressEDITGallery.setVisible(false);
			descriptionEDIT.setVisible(false);
			mailTF.setEditable(false);
			BIOTF.setEditable(false);
			FirstNameTF.setEditable(false);
			LastNameTF.setEditable(false);
			SaveButton.setVisible(false);
			FirstNameTFG.setEditable(false);
			LastNameTFG.setEditable(false);
			mailTFGallery.setEditable(false);
			description.setEditable(false);
			AddressTFGallery.setEditable(false);
			FirstNameTFG.setTooltip(new Tooltip("Click To Edit"));
			LastNameTFG.setTooltip(new Tooltip("Click To Edit"));
			FirstNameTFG.setText(LoginController.userLogedIn.getFirstName());
			LastNameTFG.setText(LoginController.userLogedIn.getLastName());
			mailTFGallery.setText(LoginController.userLogedIn.getEmail());
			description.setText(((Gallery) LoginController.userLogedIn).getDescription());
			AddressTFGallery.setText(((Gallery) LoginController.userLogedIn).getAddress());
			if (LoginController.userLogedIn.getPicture() == null) {
				File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageViewProfileG.setImage(image);
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
					imageViewProfileG.setImage(wr);
				} catch (IOException e) {
				}

			}

		}

		magic2(mEvent);

	}

	@FXML
	private void mailEDITPERFORM(ActionEvent event) {
		mailTF.setEditable(true);
		mailTF.requestFocus();
		BIOTF.setEditable(false);
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void bioEDITPERFORM(ActionEvent event) {
		BIOTF.setEditable(true);
		BIOTF.requestFocus();
		mailTF.setEditable(false);
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void ChangeProfilePIC(MouseEvent event) {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this change ?");

		if (confirm) {
			imageViewProfile.setImage(image);
			LoginController.userLogedIn.setPicture(bFile);
			isChanged = true;
			SaveButton.setVisible(true);
		}

	}

	@FXML
	private void EmailEDIT(MouseEvent event) {
		mailEDIT.setVisible(true);
		bioEDIT.setVisible(false);
	}

	@FXML
	private void BIOEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(true);
	}

	@FXML
	private void FirstNameEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(false);
		mailTF.setEditable(false);
		BIOTF.setEditable(false);
		FirstNameTF.setEditable(true);
		LastNameTF.setEditable(false);
		FirstNameTF.requestFocus();
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void LastNameEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(false);
		mailTF.setEditable(false);
		BIOTF.setEditable(false);
		LastNameTF.setEditable(true);
		FirstNameTF.setEditable(false);
		LastNameTF.requestFocus();
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void SaveChanges(ActionEvent event) {
		if (isChanged) {
			LoginController.userLogedIn.setFirstName(FirstNameTF.getText());
			LoginController.userLogedIn.setLastName(LastNameTF.getText());
			if (!LoginController.userLogedIn.getEmail().equals(mailTF.getText())) {
				if (!UserManagmentDelegate.checkMailExistance(mailTF.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("Mail entered exist in our database please try another one");
					alert.showAndWait();
					mailTF.requestFocus();
					return;
				}
			}

			LoginController.userLogedIn.setEmail(mailTF.getText());
			((Artist) LoginController.userLogedIn).setBio(BIOTF.getText());
			UserManagmentDelegate.updateUser((Artist) LoginController.userLogedIn);
		}
	}

	@FXML
	public void findUsers(KeyEvent event) throws IOException {
		LrecherchFN.clear();
		Lremove.clear();
		UsersData.clear();
		searchTable.setVisible(true);
		OwnProfileArtist.setVisible(false);
		OwnProfileGallery.setVisible(false);
		if (searchTF.getText().trim().isEmpty()) {
			searchTable.setVisible(false);
			return;
		}
		if (searchTF.getText().trim().length() < 1) {
			searchTable.setVisible(false);
			return;
		}
		LrecherchFN = UserManagmentDelegate.filterLastNameAndLastName(searchTF.getText());
		if (LrecherchFN == null || LrecherchFN.equals(UsersData)) {
			return;
		}

		for (User user : LrecherchFN) {
			if (user.isBlocked() || (!user.isActive()) || (user.equals(LoginController.userLogedIn))
					|| (user instanceof Admin)) {
				Lremove.add(user);
			}
		}
		LrecherchFN.removeAll(Lremove);
		Picture.setResizable(false);
		Picture.setSortable(false);
		Picture.setCellValueFactory(new PropertyValueFactory<User, byte[]>("Picture"));
		Picture.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
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
		Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
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
									if (getTableView().getItems().get(getIndex()) instanceof Gallery) {
										btn.setText("More Information");
										btn.setOnAction((ActionEvent event) -> {
										});
									} else {
										if (UserManagmentDelegate.getAllFollowed(LoginController.userLogedIn)
												.contains(getTableView().getItems().get(getIndex()))) {
											btn.setText("UnFollow");
											btn.setOnAction((ActionEvent event) -> {
												User person = getTableView().getItems().get(getIndex());
												UserManagmentDelegate.removeFollower(LoginController.userLogedIn,
														person);
												searchTable.refresh();
											});
										} else {
											btn.setText("Follow");
											btn.setOnAction((ActionEvent event) -> {
												User person = getTableView().getItems().get(getIndex());
												UserManagmentDelegate.addFollower(LoginController.userLogedIn, person);
												searchTable.refresh();
											});
										}
									}
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		Action.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		searchTable.setItems(UsersData);
		searchTable.setVisible(true);

	}

	@FXML
	public void OpenSearchedProfile(MouseEvent event) throws IOException {
		if (PopupUserController.userChoosen == searchTable.getSelectionModel().getSelectedItem()) {
			return;
		}
		PopupUserController.userChoosen = searchTable.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.setOnCloseRequest(e -> {
			e.consume();
			Sc.close();
			PopupUserController.userChoosen = null;
		});
		Sc.showAndWait();

	}

	@FXML
	private void EmailEDITGallery(MouseEvent event) {
		mailEDITGallery.setVisible(true);
		descriptionEDIT.setVisible(false);
		addressEDITGallery.setVisible(false);
	}

	@FXML
	private void gallerymailEDITPERFORM(ActionEvent event) {
		mailTFGallery.setEditable(true);
		mailTFGallery.requestFocus();
		AddressTFGallery.setEditable(false);
		description.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void descriptionEDITPERFORM(ActionEvent event) {
		description.setEditable(true);
		description.requestFocus();
		AddressTFGallery.setEditable(false);
		mailTFGallery.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void DescriptionEDIT(MouseEvent event) {
		mailEDITGallery.setVisible(false);
		descriptionEDIT.setVisible(true);
		addressEDITGallery.setVisible(false);
	}

	@FXML
	private void galleryaddressEDITPERFORM(ActionEvent event) {
		AddressTFGallery.setEditable(true);
		AddressTFGallery.requestFocus();
		description.setEditable(false);
		mailTFGallery.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void AddressEDITGallery(MouseEvent event) {
		mailEDITGallery.setVisible(false);
		descriptionEDIT.setVisible(false);
		addressEDITGallery.setVisible(true);
	}

	@FXML
	private void SaveChangesGallery(ActionEvent event) {
		if (isChanged) {
			LoginController.userLogedIn.setFirstName(FirstNameTFG.getText());
			LoginController.userLogedIn.setLastName(LastNameTFG.getText());
			if (!LoginController.userLogedIn.getEmail().equals(mailTFGallery.getText())) {
				if (!UserManagmentDelegate.checkMailExistance(mailTFGallery.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("Mail entered exist in our database please try another one");
					alert.showAndWait();
					mailTFGallery.requestFocus();
					return;
				}
			}
			LoginController.userLogedIn.setEmail(mailTFGallery.getText());
			((Gallery) LoginController.userLogedIn).setDescription(description.getText());
			((Gallery) LoginController.userLogedIn).setAddress(AddressTFGallery.getText());
			UserManagmentDelegate.updateUser((Gallery) LoginController.userLogedIn);
		}
	}

	@FXML
	private void ChangeProfilePICG(MouseEvent event) {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this change ?");

		if (confirm) {
			imageViewProfile.setImage(image);
			LoginController.userLogedIn.setPicture(bFile);
			isChanged = true;
			SaveGalleryButton.setVisible(true);
		}

	}

	@FXML
	public void Logout(ActionEvent event) throws IOException {
		try {
			LoginController.loginContext.logout();
			//LoginController.userLogedIn = null;
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
			final Stage stage = (Stage) AymensPane.getScene().getWindow();
			stage.close();
		} catch (LoginException e1) {
			// TODO Auto-generated catch block
		}
	}

	@FXML
	public void showSettingsScreen(ActionEvent event) throws IOException {
		System.out.println("hi");
		if (!ChangePasswordController.isOpen) {
			Parent adminScene = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
			Scene scene = new Scene(adminScene);
			Stage Sc = new Stage();
			Sc.setScene(scene);
			Sc.setTitle("FannyTUNISIA");
			Sc.setOnCloseRequest(e -> {
				e.consume();
				ChangePasswordController.isOpen = false;
				Main.closeProgram(Sc);
			});

			Sc.show();
		} else {
			return;
		}

	}

	@FXML
	private void ShowFollowers(ActionEvent event) {
		// eli houwa itaba3 fihom
		Follow.setVisible(true);
		// GalleryThing.setVisible(false);
		// ArtistThing.setVisible(false);
		LrecherchArt.clear();
		UsersDataME.clear();
		LrecherchArt = UserManagmentDelegate.getAllFollowed(userChoosen);
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
									if (UserManagmentDelegate.getAllFollowed(userChoosen)
											.contains(getTableView().getItems().get(getIndex()))) {
										btn.setText("UnFollow");
										btn.setOnAction((ActionEvent event) -> {
											User person = getTableView().getItems().get(getIndex());
											UserManagmentDelegate.removeFollower(userChoosen, person);
											Follow.refresh();
										});
									} else {
										btn.setText("More Information");
									}

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
		UsersDataME.addAll(LrecherchArt);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		Follow.setItems(UsersDataME);

	}

	@FXML
	private void showFollowed(ActionEvent event) {
		// eli houma itab3ou fih
		// followUnfollow
		LrecherchFNME.clear();
		UsersDataME.clear();
		Follow.setVisible(true);
		// GalleryThing.setVisible(false);
		// ArtistThing.setVisible(false);

		for (ArtistFollowers u : ((Artist) userChoosen).getFollowers()) {
			LrecherchFNME.add(u.getUser());
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
		UsersDataME.addAll(LrecherchFNME);
		FirstNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastNameTV.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		Follow.setItems(UsersDataME);

	}

	@FXML
	public void MoreInformation(MouseEvent event) throws IOException {
		if (LoginController.userLogedIn instanceof Artist) {
			if (PopupUserController.userChoosen == Follow.getSelectionModel().getSelectedItem()) {
				return;
			}
			PopupUserController.userChoosen = Follow.getSelectionModel().getSelectedItem();
			Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
			Scene scene = new Scene(adminScene);
			Stage Sc = new Stage();
			Sc.setScene(scene);
			Sc.setScene(scene);
			Sc.setOnCloseRequest(e -> {
				e.consume();
				Sc.close();
				userChoosen = null;
			});
			Sc.setTitle("FannyTUNISIA");
			Sc.showAndWait();

		}

		else {
			if (PopupUserController.userChoosen == FollowG.getSelectionModel().getSelectedItem()) {
				return;
			}
			PopupUserController.userChoosen = FollowG.getSelectionModel().getSelectedItem();
			Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
			Scene scene = new Scene(adminScene);
			Stage Sc = new Stage();
			Sc.setScene(scene);
			Sc.setScene(scene);
			Sc.setOnCloseRequest(e -> {
				e.consume();
				Sc.close();
				userChoosen = null;
			});
			Sc.setTitle("FannyTUNISIA");
			Sc.showAndWait();

		}
	}

	@FXML
	private void ShowFollowersG(ActionEvent event) {
		//
		FollowG.setVisible(true);
		LrecherchArt.clear();
		UsersDataME.clear();
		LrecherchArt = UserManagmentDelegate.getAllFollowed(userChoosen);
		PictureTVG.setCellValueFactory(new PropertyValueFactory<User, byte[]>("Picture"));
		PictureTVG.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
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
		ActionTVG.setCellValueFactory(new PropertyValueFactory<>("Action"));
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
									if (UserManagmentDelegate.getAllFollowed(userChoosen)
											.contains(getTableView().getItems().get(getIndex()))) {
										btn.setText("UnFollow");
										btn.setOnAction((ActionEvent event) -> {
											User person = getTableView().getItems().get(getIndex());
											UserManagmentDelegate.removeFollower(userChoosen, person);
											Follow.refresh();
										});
									} else {
										btn.setText("More Information");
									}

									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		ActionTVG.setCellFactory(cellFactory);

		/**************************************************************/
		UsersDataME.addAll(LrecherchArt);
		FirstNameTVG.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastNameTVG.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		FollowG.setItems(UsersDataME);

	}

	@FXML
	public void DisableAccount(ActionEvent actionEvent) {
		UserManagmentDelegate.disableUser(LoginController.userLogedIn);
		final Stage stage = (Stage) AymensPane.getScene().getWindow();
		stage.close();
	}

	@FXML
	void addMusic(ActionEvent event) {
		musicField.setSelected(false);
		if (isAdding) {
			UserManagmentDelegate.addFields(UserManagmentDelegate.findFieldsByName(musicField.getText()),
					LoginController.userLogedIn);
			musicField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		} else {
			UserManagmentDelegate.removeFields(UserManagmentDelegate.findFieldsByName(musicField.getText()),
					LoginController.userLogedIn);
			musicField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		}

	}

	@FXML
	void addPHT(ActionEvent event) {
		photographyField.setSelected(false);
		if (isAdding) {
			UserManagmentDelegate.addFields(UserManagmentDelegate.findFieldsByName(photographyField.getText()),
					LoginController.userLogedIn);
			photographyField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		} else {
			UserManagmentDelegate.removeFields(UserManagmentDelegate.findFieldsByName(photographyField.getText()),
					LoginController.userLogedIn);
			photographyField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		}

	}

	@FXML
	void addPaints(ActionEvent event) {
		PaintingsField.setSelected(false);
		if (isAdding) {
			UserManagmentDelegate.addFields(UserManagmentDelegate.findFieldsByName(PaintingsField.getText()),
					LoginController.userLogedIn);
			PaintingsField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		} else {
			UserManagmentDelegate.removeFields(UserManagmentDelegate.findFieldsByName(PaintingsField.getText()),
					LoginController.userLogedIn);
			PaintingsField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		}

	}

	@FXML
	void addScu(ActionEvent event) {
		scultureField.setSelected(false);
		if (isAdding) {
			UserManagmentDelegate.addFields(UserManagmentDelegate.findFieldsByName(scultureField.getText()),
					LoginController.userLogedIn);
			scultureField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		} else {
			UserManagmentDelegate.removeFields(UserManagmentDelegate.findFieldsByName(scultureField.getText()),
					LoginController.userLogedIn);
			scultureField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		}

	}

	@FXML
	void addTC(ActionEvent event) {
		TcraftField.setSelected(false);
		if (isAdding) {
			UserManagmentDelegate.addFields(UserManagmentDelegate.findFieldsByName(TcraftField.getText()),
					LoginController.userLogedIn);
			TcraftField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		} else {
			UserManagmentDelegate.removeFields(UserManagmentDelegate.findFieldsByName(TcraftField.getText()),
					LoginController.userLogedIn);
			TcraftField.setVisible(false);
			LoginController.userLogedIn = UserManagmentDelegate.findById(LoginController.userLogedIn.getIdUser());
			return;
		}

	}

	@FXML
	void RemoveField(ActionEvent event) {
		isAdding = false;
		PaintingsField.setVisible(true);
		TcraftField.setVisible(true);
		photographyField.setVisible(true);
		scultureField.setVisible(true);
		musicField.setVisible(true);
		List<ArtistFields> Lf = new ArrayList<ArtistFields>();
		Lf.addAll(((Artist) LoginController.userLogedIn).getLfields());
		List<String> Lfs = new ArrayList<String>();
		for (ArtistFields artistFields : Lf) {
			Lfs.add(artistFields.getField().getLibelle());
		}
		if (!Lfs.contains(musicField.getText())) {
			musicField.setVisible(false);
		}
		if (!Lfs.contains(PaintingsField.getText())) {
			PaintingsField.setVisible(false);
		}
		if (!Lfs.contains(TcraftField.getText())) {
			TcraftField.setVisible(false);
		}
		if (!Lfs.contains(scultureField.getText())) {
			scultureField.setVisible(false);
		}
		if (!Lfs.contains(photographyField.getText())) {
			photographyField.setVisible(false);
		}

	}

	@FXML
	void AddField(ActionEvent event) {
		isAdding = true;
		PaintingsField.setVisible(false);
		TcraftField.setVisible(false);
		photographyField.setVisible(false);
		scultureField.setVisible(false);
		musicField.setVisible(false);
		List<ArtistFields> Lf = new ArrayList<ArtistFields>();
		Lf.addAll(((Artist) LoginController.userLogedIn).getLfields());
		List<String> Lfs = new ArrayList<String>();
		for (ArtistFields artistFields : Lf) {
			Lfs.add(artistFields.getField().getLibelle());
		}
		if (!Lfs.contains(musicField.getText())) {
			musicField.setVisible(true);
		}
		if (!Lfs.contains(PaintingsField.getText())) {
			PaintingsField.setVisible(true);
		}
		if (!Lfs.contains(TcraftField.getText())) {
			TcraftField.setVisible(true);
		}
		if (!Lfs.contains(scultureField.getText())) {
			scultureField.setVisible(true);
		}
		if (!Lfs.contains(photographyField.getText())) {
			photographyField.setVisible(true);
		}

	}

}
