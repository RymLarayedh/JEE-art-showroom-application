package applicationInes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.StyledEditorKit.BoldAction;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import entities.Artist;
import entities.Gallery;
import entities.User;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
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
import javafx.stage.Window;
import javafx.util.Callback;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
import utils.ConfirmBox;

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

	@FXML
	private JFXComboBox<String> nature;

	/*
	 * @FXML private JFXComboBox<String> byFollowers;
	 */

	@FXML
	private Label sortLabel;

	@FXML
	private AnchorPane AymensPane;
	ObservableList<User> UsersData = FXCollections.observableArrayList();

	/********** Ines ***********/

	InitialContext ctx;
	public static EventManagmentRemote eventManagmentI;
	@FXML
	private JFXButton updateStatistics;
	@FXML
	private AnchorPane statisticsPane;
	@FXML
	private ScatterChart eventPerMonthChart;

	/************** End Ines ***************/

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		/***** Initialize Ines ************/

		try {
			ctx = new InitialContext();
			eventManagmentI = (EventManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		}

		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scatteChart(); /** chart1 **/

		/********** End Initialize Ines ***************/

		// TODO Auto-generated method stub
		nature.setVisible(false);
		// byFollowers.setVisible(false);
		sortLabel.setVisible(false);
		// byFollowers.getItems().addAll("LESS FOLLOWED", "MOST FOLLOWED",
		// "NEITHER");
		nature.getItems().addAll("ARTISTS", "GALLERIES", nature.getPromptText());
		AllUsersTable.setEditable(true);
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
		boolean answer = ConfirmBox.display("Exit", "Sure you want to exit ?");
		if (answer) {
			Platform.exit();
		}

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
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Active";
		if (nature.getValue() == null) {
			nature.setValue("Artist & Galleries");
		}

		if (nature.getValue().equals("Artist & Galleries")) {

			DisplayUsersByFilter(popupController.fromWhere, "Artist & Galleries");
		}

		if (nature.getValue().equals("GALLERIES")) {
			DisplayUsersByFilter(popupController.fromWhere, "GALLERIES");
		}
		if (nature.getValue().equals("ARTISTS")) {
			DisplayUsersByFilter(popupController.fromWhere, "ARTISTS");
		}
	}

	@FXML
	public void showPopup(MouseEvent event) throws IOException {
		if (popupController.userChoosen == AllUsersTable.getSelectionModel().getSelectedItem()) {
			return;
		}
		popupController.userChoosen = AllUsersTable.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popup.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.showAndWait();

	}

	@FXML
	public void ShowWaitList(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Wating";
		if (nature.getValue() == null) {
			nature.setValue("Artist & Galleries");
		}
		if (nature.getValue().equals("GALLERIES")) {
			DisplayUsersByFilter(popupController.fromWhere, "GALLERIES");
		}
		if (nature.getValue().equals("Artist & Galleries")) {

			DisplayUsersByFilter(popupController.fromWhere, "Artist & Galleries");
		}
		if (nature.getValue().equals("ARTISTS")) {
			DisplayUsersByFilter(popupController.fromWhere, "ARTISTS");
		}
	}

	@FXML
	public void ShowBlockedUsers(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Blocked";
		if (nature.getValue() == null) {
			nature.setValue("Artist & Galleries");
		}
		if (nature.getValue().equals("GALLERIES")) {
			DisplayUsersByFilter(popupController.fromWhere, "GALLERIES");
		}
		if (nature.getValue().equals("Artist & Galleries")) {

			DisplayUsersByFilter(popupController.fromWhere, "Artist & Galleries");
		}
		if (nature.getValue().equals("ARTISTS")) {
			DisplayUsersByFilter(popupController.fromWhere, "ARTISTS");
		}
	}

	public void DisplayUsers(String etat) {
		UsersData.removeAll(UsersData);
		AllUsersTable.refresh();
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
		List<User> ListUsers = LoginController.userManagment.getAllUsers();
		for (User u : ListUsers) {
			if (etat.equals("Active")) {
				if (u.isActive() && (!u.isBlocked())) {
					if (u.getIdUser() == LoginController.userLogedIn.getIdUser()) {

					} else {
						UsersData.add(u);
					}

				}

			}

			else if (etat.equals("Wating")) {
				if (!u.isActive()) {
					UsersData.add(u);
				}
			} else if (etat.equals("Blocked")) {
				if (u.isBlocked()) {
					UsersData.add(u);
				}
			}
		}
		firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		mail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		status.setCellValueFactory(User -> {
			boolean isActive = User.getValue().isActive();
			String isActiveAsString = "";
			if (isActive && !User.getValue().isBlocked()) {
				isActiveAsString = "Active";
			} else if (!isActive) {
				isActiveAsString = "Not Active";
			} else if (User.getValue().isBlocked()) {
				isActiveAsString = "Blocked";
			}
			return new ReadOnlyStringWrapper(isActiveAsString);
		});
		AllUsersTable.setItems(UsersData);
		AllUsersTable.setVisible(true);

	}

	public void setTable(List<User> Users) {
		AllUsersTable.getItems().setAll(Users);
	}

	@FXML
	public void Disconnect(ActionEvent event) throws IOException {
		LoginController.userLogedIn = null;
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
	}

	@FXML
	public void filterType(Event event) {
		String filterType = nature.getValue();
		// String filterType2 = byFollowers.getValue();
		if (filterType.equals("GALLERIES")) {
			DisplayUsersByFilter(popupController.fromWhere, "GALLERIES");
		}
		if (filterType.equals("Artist & Galleries")) {

			DisplayUsersByFilter(popupController.fromWhere, "Artist & Galleries");
		}
		if (filterType.equals("ARTISTS")) {
			DisplayUsersByFilter(popupController.fromWhere, "ARTISTS");
		}

	}

	public void DisplayUsersByFilter(String etat, String t) {
		UsersData.removeAll(UsersData);
		AllUsersTable.refresh();
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
		List<User> ListUsers = LoginController.userManagment.getAllUsers();
		for (User u : ListUsers) {
			if (etat.equals("Active")) {
				if (u.isActive() && (!u.isBlocked())) {
					if (u.getIdUser() == LoginController.userLogedIn.getIdUser()) {

					} else {
						if (t.equals("GALLERIES")) {
							if (u instanceof Gallery) {
								UsersData.add(u);
							} else {

							}
						}
						if (t.equals("ARTISTS")) {
							if (u instanceof Artist) {
								UsersData.add(u);
							} else {

							}
						}
						if (t.equals("Artist & Galleries")) {
							UsersData.add(u);
						}

					}

				}

			}

			else if (etat.equals("Wating")) {
				if (!u.isActive()) {
					if (t.equals("GALLERIES")) {
						if (u instanceof Gallery) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("ARTISTS")) {
						if (u instanceof Artist) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Artist & Galleries")) {
						UsersData.add(u);
					}

				}
			} else if (etat.equals("Blocked")) {
				if (u.isBlocked()) {
					if (t.equals("GALLERIES")) {
						if (u instanceof Gallery) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("ARTISTS")) {
						if (u instanceof Artist) {
							UsersData.add(u);
						} else {

						}
					}
					if (t.equals("Artist & Galleries")) {
						UsersData.add(u);
					}
				}
			}
		}
		firstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		mail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
		username.setCellValueFactory(new PropertyValueFactory<User, String>("username"));
		status.setCellValueFactory(User -> {
			boolean isActive = User.getValue().isActive();
			String isActiveAsString = "";
			if (isActive && !User.getValue().isBlocked()) {
				isActiveAsString = "Active";
			} else if (!isActive) {
				isActiveAsString = "Not Active";
			} else if (User.getValue().isBlocked()) {
				isActiveAsString = "Blocked";
			}
			return new ReadOnlyStringWrapper(isActiveAsString);
		});
		AllUsersTable.setItems(UsersData);
		AllUsersTable.setVisible(true);

	}

	/**** Statistic Ines ***/
	@FXML
	void updateStatistics(ActionEvent event) {

	}

	public void scatteChart() {

		/** Scatter** Event Per month **/
		int January=0;
		int February =0;
		int March=0;
		int April =0;
		int May=0;
		int June=0;
		int July=0;
		int August=0;
		int September=0;
		int October=0;
		int November=0;
		int December=0;

		List<entities.Event> listEventsI = new ArrayList<entities.Event>();
		List<Date> DateEventsI = new ArrayList<Date>();
		listEventsI = eventManagmentI.getAllEvents();
		for (entities.Event event : listEventsI) {
			
			DateEventsI.add(event.getDateBegin());
			//System.out.println(DateEventsI);
		}
		for (Date date : DateEventsI)
		{
			System.out.println(date.getYear());
			if((date.getYear()) == ( LocalDateTime.now().getYear()- 1900))
			{
				switch (date.getMonth())
				{
				case 0: January++;
				break;
				case 1: February++;
				break;
				case 2: March++;
				break;
				case 3: April ++;
				break;
				case 4:May ++;
				break;
				case 5: June ++;
				break;
				case 6: July++;
				break;
				case 7: August++;
				break;
				case 8: September++;
				break;
				case 9: October++;
				break;
				case 10: November++;
				break;
				case 11: December++;
				break;
					
				}
				XYChart.Series series = new XYChart.Series(); //Make a new XYChart object

				//Add Data
				series.getData().add(new XYChart.Data("January", January));
				series.getData().add(new XYChart.Data("February", February));
				series.getData().add(new XYChart.Data("March", March));
				series.getData().add(new XYChart.Data("April", April));
				series.getData().add(new XYChart.Data("May", May));
				series.getData().add(new XYChart.Data("June", June));
				series.getData().add(new XYChart.Data("July", July));
				series.getData().add(new XYChart.Data("August", August));
				series.getData().add(new XYChart.Data("September", September));
				series.getData().add(new XYChart.Data("October" ,October));
				series.getData().add(new XYChart.Data("November", November));
				series.getData().add(new XYChart.Data("December" ,December));
				eventPerMonthChart.getData().addAll(series);

			}
			
		}

	}

}
