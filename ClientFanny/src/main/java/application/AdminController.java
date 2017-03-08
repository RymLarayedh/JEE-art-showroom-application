package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.StyledEditorKit.BoldAction;

import entities.User;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AdminController implements Initializable {
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label userLoggedInName;
	@FXML
	private TableView<User> AllUsersTable;

	@FXML
	private TableColumn<User, byte[]> picture;

	@FXML
	private TableColumn<User, String> firstName;

	@FXML
	private TableColumn<User, String> LastName;

	@FXML
	private TableColumn<User, String> mail;

	@FXML
	private TableColumn<User, String> username;

	@FXML
	private TableColumn<User, String> status;
	ObservableList<User> UsersData = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		AllUsersTable.setVisible(false);
		userLoggedInName
				.setText(LoginController.userLogedIn.getFirstName() + " " + LoginController.userLogedIn.getLastName());
		if (LoginController.userLogedIn.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profilePicture.setImage(image);
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
				profilePicture.setImage(wr);
			} catch (IOException e) {
			}

		}

	}

	// Event Listener on ImageView[#profilePicture].onMouseClicked
	@FXML
	public void showProfile(MouseEvent event) {
		// TODO Autogenerated
		System.out.println("showing admin profil");
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void closeFanny(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void showSettingsScreen(ActionEvent event) {
		// TODO Autogenerated
	}

	// Event Listener on MenuItem.onAction
	@FXML
	public void showAboutFanny(ActionEvent event) {
		// TODO Autogenerated
	}

	@FXML
	public void ShowAllUsers(ActionEvent event) {

		DisplayUsers();
	}

	@FXML
	public void showPopup(MouseEvent event) throws IOException {
		popupController.userChoosen = AllUsersTable.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popup.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		popupController.userChoosen = AllUsersTable.getSelectionModel().getSelectedItem();

	}

	public void DisplayUsers()
	{
		AllUsersTable.getItems().clear();
		picture.setResizable(false);
		picture.setSortable(false);
		picture.setCellValueFactory(new PropertyValueFactory<User, byte[]>("picture"));
		picture.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
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

		/**************************************************************/
		AllUsersTable.getItems().clear();
		List<User> ListUsers = LoginController.userManagment.getAllUsers();
		for (User u : ListUsers) {
			UsersData.add(u);
		}
		firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		mail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		status.setCellValueFactory(User -> {
			boolean isActive = User.getValue().isActive();
			String isActiveAsString;
			if (isActive) {
				isActiveAsString = "Active";
			} else {
				isActiveAsString = "Not Active";
			}
			return new ReadOnlyStringWrapper(isActiveAsString);
		});
		AllUsersTable.setItems(UsersData);
		AllUsersTable.setVisible(true);

	}
}
