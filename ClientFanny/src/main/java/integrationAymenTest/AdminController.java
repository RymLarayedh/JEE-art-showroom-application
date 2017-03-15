package integrationAymenTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import applicationRym.PdfController;
import applicationRym.utilprojet;
import business.UserManagmentDelegate;
import entities.Artist;
import entities.Artwork;
import entities.Event;
import entities.EventUser;
import entities.Gallery;
import entities.Reclamation;
import entities.TunisianCraft;
import entities.User;
import entities.VisualArt;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
//import javafx.event.Event;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
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
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
import services.FeedbackManagmentRemote;
import services.LikeManagementRemote;
import services.UserManagmentRemote;
import services.VisualArtworkEJBRemote;
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

	/////////// manel//////////////////////
	@FXML
	private JFXButton vart;
	@FXML
	private AnchorPane ManelsPane;

	@FXML
	private TableView<VisualArt> AllArtworkTable1;
	@FXML
	private TableColumn<VisualArt, byte[]> picture1;
	@FXML
	private TableColumn name1;
	@FXML
	private TableColumn price1;
	@FXML
	private TableColumn category1;
	@FXML
	private TableColumn lenght1;
	@FXML
	private TableColumn width1;
	@FXML
	private TableColumn state1;
	@FXML
	private TableColumn date1;
	@FXML
	private TableColumn desc1;
	@FXML
	private AnchorPane ImensPane;

	public static VisualArt chosenArtworkAdmin;
	ObservableList<VisualArt> artdata = FXCollections.observableArrayList();

	InitialContext ctx;
	public static VisualArtworkEJBRemote proxy;

	// FIN MANEL

	/********** Ines ***********/

	public static EventManagmentRemote eventManagmentI;
	public static ArtworkManagemetRemote artworkManagementI;
	public static FeedbackManagmentRemote feedbackManagmentI;
	public static LikeManagementRemote likeManagmentI;

	@FXML
	private JFXButton updateStatistics;
	@FXML
	private AnchorPane statisticsPane;
	@FXML
	private ScatterChart eventPerMonthChart;
	@FXML
	private PieChart pieChart;
	@FXML
	private BarChart<String, Integer> barChart;

	/************** End Ines ***************/

	/* RYM */
	static int idGa1;
	static int idGa;
	static int idAr;
	static int idAr1;
	InitialContext ctxRym;
	private int selected;
	@FXML
	private AnchorPane RymsPane;
	@FXML
	private AnchorPane addEventPane;
	@FXML
	private JFXTextArea description;
	@FXML
	private JFXTextField title;
	@FXML
	private JFXDatePicker dateBegin;
	@FXML
	private JFXDatePicker dateEnd;
	@FXML
	private ComboBox<String> galleryList;
	@FXML
	private ComboBox<String> artistList;
	@FXML
	private Label details;
	@FXML
	private AnchorPane displayReclamation;
	@FXML
	private TableView<Reclamation> tableReclamation;
	@FXML
	private TableColumn<Reclamation, String> artwork;
	@FXML
	private TableColumn<Reclamation, String> user;
	@FXML
	private TableColumn<Reclamation, String> body;
	@FXML
	private TableColumn<Reclamation, Date> date;
	@FXML
	private TableColumn<Reclamation, Integer> degree;

	ObservableList<Reclamation> dataReclamation = FXCollections.observableArrayList();

	@FXML
	private AnchorPane displayEventPane;
	@FXML
	private TableView<Event> tableEvent;
	@FXML
	private TableColumn<Event, String> title1;
	@FXML
	private TableColumn<Event, String> description1;
	@FXML
	private TableColumn<Event, Date> dateBegin1;
	@FXML
	private TableColumn<Event, Date> dateEnd1;
	@FXML
	private TableColumn<Event, String> gallery;
	@FXML
	private TableColumn<Event, String> artist;

	ObservableList<Event> dataEvent = FXCollections.observableArrayList();
	private Object id;

	@FXML
	private AnchorPane participantPane;
	@FXML
	private TableView<EventUser> tableParticipant;
	@FXML
	private TableColumn<EventUser, String> usernameParticipant;
	@FXML
	private TableColumn<EventUser, String> emailParticipant;
	ObservableList<EventUser> dataEventUser = FXCollections.observableArrayList();

	@FXML
	private AnchorPane updateEventPane;
	@FXML
	private JFXTextArea description11;
	@FXML
	private JFXTextField title11;
	@FXML
	private JFXDatePicker dateBegin11;
	@FXML
	private JFXDatePicker dateEnd11;
	@FXML
	private ComboBox<String> galleryList1;
	@FXML
	private ComboBox<String> artistList1;
	@FXML
	private Label details1;
	/* FINRYM */

	/* Oussama */
	/****************************************** oussama ********************/
	@FXML
	private AnchorPane OussamsPane;

	@FXML
	private TableColumn<TunisianCraft, byte[]> pictureo;

	@FXML
	private TableColumn<TunisianCraft, String> nameo;

	@FXML
	private TableColumn<TunisianCraft, String> priceo;

	@FXML
	private TableColumn<TunisianCraft, String> Typeo;

	@FXML
	private TableColumn<TunisianCraft, String> stateo;

	@FXML
	private TableColumn<TunisianCraft, String> dateo;

	@FXML
	private TableColumn<TunisianCraft, String> desco;

	@FXML
	private TableView<TunisianCraft> TunisianTable;
	ObservableList<TunisianCraft> TCdata = FXCollections.observableArrayList();
	public static ArtworkManagemetRemote proxyo;

	/*********************************/

	/* FIN OUSSAMA */

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/***** Initialize Ines ************/

		try {
			ctx = new InitialContext();
			eventManagmentI = (EventManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
			artworkManagementI = (ArtworkManagemetRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			feedbackManagmentI = (FeedbackManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/FeedbackManagment!services.FeedbackManagmentRemote");
			likeManagmentI = (LikeManagementRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");

		}

		catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// scatteChart(); /** chart1 **/
		// pieChart(); /** chart2 **/
		// barChart(); /** chart3 **/

		/********** End Initialize Ines ***************/

		/* DEBUT AYMEN */
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
		/* FIN AYMEN */

		/* MANEL */
		try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			// VisualArtwork = (VisualArtworkEJBRemote) ctx
			// .lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			Artwork aa = new Artwork();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		AllArtworkTable1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label

			}
		});

		DisplayVisualArts();

		AllArtworkTable1.setVisible(false);
		/* FINMANEL */

		/* RYM */
		updateEventPane.setVisible(false);

		addEventPane.setVisible(false);
		displayReclamation.setVisible(false);
		displayEventPane.setVisible(false);
		participantPane.setVisible(false);
		/* FIN RYM */

		/******** ouss **************/
		try {
			InitialContext ctxo = new InitialContext();
			Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			proxyo = (ArtworkManagemetRemote) objeto;
			TunisianCraft TunC = new TunisianCraft();
			DisplayTunisianCraft();
			TunisianTable.setVisible(false);
		} catch (NamingException e) {
		}

		/********************/

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
		Sc.setOnCloseRequest(e -> {
			e.consume();
			Main.closeProgram(Sc);
			popupController.userChoosen = null;
		});
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
		List<User> ListUsers = UserManagmentDelegate.getAllUsers();
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
	public void filterType(javafx.event.Event event) {
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
		List<User> ListUsers = UserManagmentDelegate.getAllUsers();
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

	/* FINAYMEN */
	/* DEBUTMANEL */
	@FXML
	public void showPopupp(MouseEvent event) throws IOException {

		AllArtworkTable1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

			@Override
			public void changed(ObservableValue observable, Object oldValue, Object newValue) {
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("AdminUpdDel.fxml"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scene scene = new Scene(root);
				Stage Sc = new Stage();
				Sc.setScene(scene);
				Sc.show();
				final Node source = (Node) event.getSource();
				final Stage stage = (Stage) source.getScene().getWindow();
				stage.close();

			}
		});

	}

	// Event Listener on TableView[#AllArtworkTable1].onMouseClicked
	@FXML
	public void showPopuppp(MouseEvent event) throws IOException {

		chosenArtworkAdmin = AllArtworkTable1.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("MyArtworlDetail.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.show();
		/////////////// prec
		// AdminUpdDetController.ArtworkChoosen =
		// AllArtworkTable1.getSelectionModel().getSelectedItem();
		// Parent
		// root=FXMLLoader.load(getClass().getResource("AdminUpdDel.fxml"));
		// Scene scene=new Scene(root);
		// Stage Sc=new Stage();
		// Sc.setScene(scene);
		// Sc.show();
		// final Node source =(Node) event.getSource();
		// final Stage stage=(Stage) source.getScene().getWindow();
		// stage.close();
		///////////////////
		// AllArtworkTable1.setRowFactory(tv -> {
		// TableRow<Artwork> row = new TableRow<>();
		// row.setOnMouseClicked(event1 -> {
		// if (event1.getClickCount() == 1 && (!row.isEmpty())) {
		// Alert alert = new Alert(Alert.AlertType.ERROR);
		// alert.setTitle("Fanny");
		// alert.setHeaderText(null);
		// alert.setContentText("yyyyy");
		// alert.showAndWait();
		//

		// Artwork rowData = row.getItem();
		// Parent adminart = null;
		// try {
		// adminart =
		// FXMLLoader.load(getClass().getResource("AdminDelUpd.fxml"));
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Scene scene = new Scene(adminart);
		//
		// Stage Sc = new Stage();
		// Sc.setScene(scene);
		// Sc.show();
		// final Node source = (Node) event.getSource();
		// final Stage stage = (Stage) source.getScene().getWindow();
		// //stage.close();

		// }
		// });
		// return row;
		// });

	}

	public void DisplayVisualArts() {
		AllArtworkTable1.getItems().clear();
		picture1.setResizable(false);
		picture1.setSortable(false);
		picture1.setCellValueFactory(new PropertyValueFactory<VisualArt, byte[]>("picture"));
		picture1.setCellFactory(new Callback<TableColumn<VisualArt, byte[]>, TableCell<VisualArt, byte[]>>() {
			@Override
			public TableCell<VisualArt, byte[]> call(TableColumn<VisualArt, byte[]> param) {
				TableCell<VisualArt, byte[]> cell = new TableCell<VisualArt, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							// if (item == null) {
							// File file = new
							// File("./src/main/java/buttons/PasDePhotoDeProfil.png");
							// BufferedImage bufferedImage;
							// try {
							// bufferedImage = ImageIO.read(file);
							// Image image =
							// SwingFXUtils.toFXImage(bufferedImage, null);
							// imageview.setImage(image);
							// } catch (IOException e) {
							// }
							//
							// } else {
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

							// }

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
		AllArtworkTable1.getItems().clear();
		List<VisualArt> ListArtworks = proxy.findAllVisualArt2();
		for (VisualArt a : ListArtworks) {
			artdata.add(a);
		}

		name1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("name"));
		category1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("category"));
		lenght1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("length"));
		width1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("width"));
		desc1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("Description"));
		price1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("price"));
		date1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("dateOfOublication"));
		state1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("state"));
		// status.setCellValueFactory(Artwork -> {
		// boolean isActive = Artwork.getValue().isActive();
		// String isActiveAsString;
		// if (isActive) {
		// isActiveAsString = "Active";
		// } else {
		// isActiveAsString = "Not Active";
		// }
		// return new ReadOnlyStringWrapper(isActiveAsString);
		// });
		AllArtworkTable1.setItems(artdata);
		AllArtworkTable1.setVisible(true);

		///////////////////////////////
		// AllArtworkTable1.setRowFactory(tv -> {
		// TableRow<Artwork> row = new TableRow<>();
		// row.setOnMouseClicked(event -> {
		// if (event.getClickCount() == 1 && (!row.isEmpty())) {
		// Parent
		// root=FXMLLoader.load(getClass().getResource("AdminDelUpd.fxml"));
		// Scene scene=new Scene(root);
		// Stage Sc=new Stage();
		// Sc.setScene(scene);
		// Sc.show();
		// final Node source =(Node) event.getSource();
		// final Stage stage=(Stage) source.getScene().getWindow();
		// stage.close();
		// }
		// });
		// return row;
		// });
	}

	@FXML
	private void showtable1(ActionEvent event) {
		AllArtworkTable1.setVisible(true);
	}
	/* FIN MANEL */

	/* Debut Statistique Ines */

	@FXML
	void updateStatistics(ActionEvent event) {

	}

	public void scatteChart() {

		/** Scatter** Event Per month **/
		int January = 0;
		int February = 0;
		int March = 0;
		int April = 0;
		int May = 0;
		int June = 0;
		int July = 0;
		int August = 0;
		int September = 0;
		int October = 0;
		int November = 0;
		int December = 0;

		List<entities.Event> listEventsI = new ArrayList<entities.Event>();
		List<Date> DateEventsI = new ArrayList<Date>();
		listEventsI = eventManagmentI.getAllEvents();
		for (entities.Event event : listEventsI) {

			DateEventsI.add(event.getDateBegin());
			// System.out.println(DateEventsI);
		}
		for (Date date : DateEventsI) {
			if ((date.getYear()) == (LocalDateTime.now().getYear() - 1900)) {
				switch (date.getMonth()) {
				case 0:
					January++;
					break;
				case 1:
					February++;
					break;
				case 2:
					March++;
					break;
				case 3:
					April++;
					break;
				case 4:
					May++;
					break;
				case 5:
					June++;
					break;
				case 6:
					July++;
					break;
				case 7:
					August++;
					break;
				case 8:
					September++;
					break;
				case 9:
					October++;
					break;
				case 10:
					November++;
					break;
				case 11:
					December++;
					break;

				}
				XYChart.Series series = new XYChart.Series(); // Make a new
																// XYChart
																// object

				// Add Data
				series.getData().add(new XYChart.Data("January", January));
				series.getData().add(new XYChart.Data("February", February));
				series.getData().add(new XYChart.Data("March", March));
				series.getData().add(new XYChart.Data("April", April));
				series.getData().add(new XYChart.Data("May", May));
				series.getData().add(new XYChart.Data("June", June));
				series.getData().add(new XYChart.Data("July", July));
				series.getData().add(new XYChart.Data("August", August));
				series.getData().add(new XYChart.Data("September", September));
				series.getData().add(new XYChart.Data("October", October));
				series.getData().add(new XYChart.Data("November", November));
				series.getData().add(new XYChart.Data("December", December));
				eventPerMonthChart.getData().addAll(series);

			}

		}

	}

	/** Pie ** most liked artworks **/
	public void pieChart() {

		List<Artwork> listArtwork = new ArrayList<Artwork>();
		Map<Integer, String> map = new TreeMap<Integer, String>();
		Data pieChartData = null;

		listArtwork = artworkManagementI.findAllArtworks();
		for (Artwork A : listArtwork) {
			long nbrLike = likeManagmentI.nbrlike(A);
			System.out.println("nbrLike= " + nbrLike);
			if (nbrLike > 0) {
				map.put((int) nbrLike, A.getName());

				for (Map.Entry<Integer, String> e : map.entrySet()) {
					pieChartData = new PieChart.Data(e.getValue(), e.getKey());

				}
				;
			}

		}
		ObservableList<PieChart.Data> pieChartDataa = FXCollections.observableArrayList(pieChartData);

		pieChart.setData(pieChartDataa);

	}

	/** Bar ** publications perMonth **/
	public void barChart() {
		int January = 0;
		int February = 0;
		int March = 0;
		int April = 0;
		int May = 0;
		int June = 0;
		int July = 0;
		int August = 0;
		int September = 0;
		int October = 0;
		int November = 0;
		int December = 0;

		List<Artwork> listArtwork = new ArrayList<Artwork>();
		List<Date> Datepublication = new ArrayList<Date>();
		listArtwork = artworkManagementI.findAllArtworks();
		for (Artwork l : listArtwork) {

			Datepublication.add(l.getDateOfOublication());
			// System.out.println(DateEventsI);
		}
		for (Date date : Datepublication) {
			if ((date.getYear()) == (LocalDateTime.now().getYear() - 1900)) {
				switch (date.getMonth()) {
				case 0:
					January++;
					break;
				case 1:
					February++;
					break;
				case 2:
					March++;
					break;
				case 3:
					April++;
					break;
				case 4:
					May++;
					break;
				case 5:
					June++;
					break;
				case 6:
					July++;
					break;
				case 7:
					August++;
					break;
				case 8:
					September++;
					break;
				case 9:
					October++;
					break;
				case 10:
					November++;
					break;
				case 11:
					December++;
					break;

				}
				final CategoryAxis xAxis = new CategoryAxis();
				final NumberAxis yAxis = new NumberAxis();
				final BarChart<String, Number> bc = new BarChart<String, Number>(xAxis, yAxis);
				bc.setTitle("Country Summary");
				xAxis.setLabel("Country");
				yAxis.setLabel("Value");

				XYChart.Series series = new XYChart.Series();
				series.setName("2003");
				series.getData().add(new XYChart.Data("January", January));
				series.getData().add(new XYChart.Data("February", February));
				series.getData().add(new XYChart.Data("March", March));
				series.getData().add(new XYChart.Data("April", April));
				series.getData().add(new XYChart.Data("May", May));
				series.getData().add(new XYChart.Data("June", June));
				series.getData().add(new XYChart.Data("July", July));
				series.getData().add(new XYChart.Data("August", August));
				series.getData().add(new XYChart.Data("September", September));
				series.getData().add(new XYChart.Data("October", October));
				series.getData().add(new XYChart.Data("November", November));
				series.getData().add(new XYChart.Data("December", December));
				barChart.getData().addAll(series);

			}

		}

	}

	/* Fin Statistique Ines */

	/* DEBUT RYM */

	@FXML
	private void displayEvents(ActionEvent event) {
		displayEventPane.setVisible(true);
		addEventPane.setVisible(false);
		displayReclamation.setVisible(false);
		remplirTableEvent();

	}

	@FXML
	private void addEvents(ActionEvent event) {
		displayReclamation.setVisible(false);
		displayEventPane.setVisible(false);
		addEventPane.setVisible(true);
		try {
			ctxRym = new InitialContext();
			Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyRYM = (UserManagmentRemote) object;

			// Remplir Combobox Gallery
			ObservableList<String> LocG = FXCollections.observableArrayList();
			List<Gallery> Lg = proxyRYM.getAllGalleries();
			for (Gallery X : Lg) {
				String s = X.getUsername();
				LocG.add(s);
			}
			galleryList.setItems(LocG);

			// Remplir Combobox Artist
			ObservableList<String> LocA = FXCollections.observableArrayList();
			List<Artist> La = proxyRYM.getAllArtists();
			for (Artist X : La) {
				String s = X.getUsername();
				LocA.add(s);
			}
			artistList.setItems(LocA);

			/*
			 * ObservableList<User> data = FXCollections.observableArrayList();
			 * // CityService CIS = new CityService(); List<Artist> Lt =
			 * proxyRYM.getAllArtists(); for (Artist X : Lt) { data.add(X); }
			 */
			// Citycl.setCellValueFactory(new PropertyValueFactory<CityT,
			// String>("Countrycl"));
			// Countrycl.setCellValueFactory(new PropertyValueFactory<CityT,
			// String>("Citycl"));
			// tableid.setItems(data);

			/*
			 * tableid.setRowFactory(tv -> { TableRow<CityT> row = new
			 * TableRow<>(); row.setOnMouseClicked(event1 -> { if
			 * (event1.getClickCount() == 1 && (!row.isEmpty())) { CityT rowData
			 * = row.getItem(); currentID = rowData.getId();
			 * Citytf.setText(rowData.getCitycl());
			 * 
			 * } }); return row; });
			 */
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void addEvent(ActionEvent event) throws NamingException {
		displayReclamation.setVisible(false);

		ctxRym = new InitialContext();
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxyRYM = (EventManagmentRemote) objet;
		entities.Event e = new Event();
		e.setTitle(title.getText());
		e.setDescription(description.getText());

		// insert gallery and Artist
		Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxyU = (UserManagmentRemote) object;
		Gallery g = new Gallery();
		g = (Gallery) proxyU.findById(idGa);
		e.setGallery(g);
		Artist a = new Artist();
		a = (Artist) proxyU.findById(idAr);
		e.setArtist(a);

		// Convert Local Date to Date Begin
		LocalDate localDateBegin = dateBegin.getValue();
		Instant instant1 = Instant.from(localDateBegin.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateBegin = Date.from(instant1);
		e.setDateBegin(dateDateBegin);
		// Convert Local Date to Date End
		LocalDate localDateEnd = dateEnd.getValue();
		Instant instant2 = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateEnd = Date.from(instant2);
		e.setDateEnd(dateDateEnd);
		// Convert Date Now
		LocalDate localNow = LocalDate.now();
		Instant instantb = Instant.from(localNow.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateNow = Date.from(instantb);

		// Condition chosen Date
		if ((dateNow.before(dateDateBegin)) && (dateDateBegin.before(dateDateEnd))) {
			proxyRYM.addEvent(e);
			addEventPane.setVisible(false);
			displayEventPane.setVisible(true);
			remplirTableEvent();

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Please check the Begining Date and the End Date");
			alert.showAndWait();
		}
	}

	@FXML
	private void chooseGallery(ActionEvent event) {
		try {
			ctxRym = new InitialContext();

			Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyRYM = (UserManagmentRemote) object;
			String chosenGallery = galleryList.getValue();

			Gallery Ga = new Gallery();
			Ga = (Gallery) proxyRYM.findByUsername(chosenGallery);
			idGa = Ga.getIdUser();

			details.setText("First Name = " + Ga.getFirstName() + "\n" + "Last Name = " + Ga.getLastName() + "\n"
					+ "Surface = " + Ga.getSurface() + "m2\n" + "Email = " + Ga.getEmail());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void chooseArtist(ActionEvent event) {
		try {
			ctxRym = new InitialContext();

			Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyRYM = (UserManagmentRemote) object;
			String chosenArtist = artistList.getValue();

			Artist Ar = new Artist();
			Ar = (Artist) proxyRYM.findByUsername(chosenArtist);
			idAr = Ar.getIdUser();
			details.setText("First Name = " + Ar.getFirstName() + "\n" + "Last Name = " + Ar.getLastName() + "\n"
					+ "Surface = " + Ar.getBio() + "\n" + "Email = " + Ar.getEmail());

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void allEvents(Event event) {
	}

	@FXML
	private void comingEvents(Event event) {
	}

	@FXML
	private void pastEvents(Event event) {
	}

	@FXML
	private void displayReclamation(ActionEvent event) {
		displayEventPane.setVisible(false);
		updateEventPane.setVisible(false);

		displayReclamation.setVisible(true);
		remplirTableReclamation();
	}

	private void remplirTableReclamation() {
		try {
			ctxRym = new InitialContext();

			Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/FeedbackManagment!services.FeedbackManagmentRemote");
			FeedbackManagmentRemote proxyRYM = (FeedbackManagmentRemote) objet;
			ObservableList<Reclamation> eventSelected, allRec;
			allRec = tableReclamation.getItems();
			// allRec.clear();
			for (Reclamation e : proxyRYM.getAllReclamation()) {
				dataReclamation.add(e);
				// System.out.println(data);
			}
			artwork.setCellValueFactory(new Callback<CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<Reclamation, String> param) {
					// TODO Auto-generated method stub
					return new SimpleStringProperty(param.getValue().getArtwork().getName());
				}

			});
			user.setCellValueFactory(new Callback<CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<Reclamation, String> param) {
					// TODO Auto-generated method stub
					return new SimpleStringProperty(param.getValue().getUser().getUsername());
				}

			});
			body.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("body"));
			date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
			degree.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("degree"));
			tableReclamation.setItems(dataReclamation);
			System.out.println(proxyRYM.getAllReclamation());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	private LocalDate ConvertDateToLocal(Date date2) {
		LocalDate date = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return date;
	}

	@FXML
	private void selectEvent(MouseEvent event) throws NamingException {
		participantPane.setVisible(true);
		remplirComboboxUpdate();
		Event PTP = tableEvent.getSelectionModel().getSelectedItem();
		if (PTP != null) {
			this.id = PTP.getIdEvent();
			this.selected = 1;
			remplirTableEventUser(PTP);

			title11.setText(PTP.getTitle());
			description11.setText(PTP.getDescription());
			dateBegin11.setValue(ConvertDateToLocal(PTP.getDateBegin()));
			dateEnd11.setValue(ConvertDateToLocal(PTP.getDateEnd()));
			galleryList1.setValue(PTP.getGallery().getUsername());
			artistList1.setValue(PTP.getArtist().getUsername());
		} else {
			this.selected = 0;
		}
	}

	public void remplirTableEventUser(Event ptp) {
		Integer idEvent = ptp.getIdEvent();
		try {
			ctxRym = new InitialContext();
			Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
			EventUserManagmentRemote proxyRYM = (EventUserManagmentRemote) objet;
			ObservableList<EventUser> eventUSelected, allEvent;
			allEvent = tableParticipant.getItems();
			allEvent.clear();
			System.out.println(idEvent);
			for (EventUser e : proxyRYM.findByEventId(idEvent)) {
				dataEventUser.add(e);
			}

			usernameParticipant
					.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

						@Override
						public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
							// TODO Auto-generated method stub
							return new SimpleStringProperty(param.getValue().getUser().getUsername());
						}

					});
			emailParticipant
					.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

						@Override
						public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
							// TODO Auto-generated method stub
							return new SimpleStringProperty(param.getValue().getUser().getEmail());
						}

					});

			tableParticipant.setItems(dataEventUser);

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	private void remplirTableEvent() {
		try {
			ctxRym = new InitialContext();

			Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
			EventManagmentRemote proxyRYM = (EventManagmentRemote) objet;
			ObservableList<Event> eventSelected, allEvent;
			allEvent = tableEvent.getItems();
			allEvent.clear();
			for (Event e : proxyRYM.getAllEvents()) {
				dataEvent.add(e);
			}
			title1.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
			description1.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
			dateBegin1.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateBegin"));
			dateEnd1.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEnd"));
			gallery.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
					// TODO Auto-generated method stub
					return new SimpleStringProperty(param.getValue().getGallery().getUsername());
				}

			});
			artist.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
					// TODO Auto-generated method stub
					return new SimpleStringProperty(param.getValue().getArtist().getUsername());
				}

			});

			tableEvent.setItems(dataEvent);

		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@FXML
	private void selectReclamation(MouseEvent event) {
	}

	@FXML
	private void deleteEvent(ActionEvent event) throws NamingException {
		participantPane.setVisible(false);
		if (this.selected == 1) {
			ObservableList<Event> userSelected, allUser;
			allUser = tableEvent.getItems();
			userSelected = tableEvent.getSelectionModel().getSelectedItems();
			Event ptp = tableEvent.getSelectionModel().getSelectedItem();
			userSelected.forEach(allUser::remove);

			// **********
			ctxRym = new InitialContext();
			Object objetEU = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
			EventUserManagmentRemote proxyEU = (EventUserManagmentRemote) objetEU;
			List<EventUser> checkParticipant = proxyEU.findByEventId(ptp.getIdEvent());
			// *************

			if (checkParticipant == null) {
				System.out.println("Liste vide");
				ctxRym = new InitialContext();
				Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
				EventManagmentRemote proxyRYM = (EventManagmentRemote) objet;
				proxyRYM.deleteEvent(ptp);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Event details has been deleted.");
				alert.showAndWait();
				remplirTableEvent();
			} else {

				for (EventUser eventUser : checkParticipant) {
					proxyEU.deleteEvent(eventUser);
				}
				ctxRym = new InitialContext();
				Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
				EventManagmentRemote proxyRYM = (EventManagmentRemote) objet;
				proxyRYM.deleteEvent(ptp);
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText("Event with Participant");
				alert.setContentText("Event details has been deleted.");
				alert.showAndWait();
				remplirTableEvent();

			}
			// **********

		} else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("No Event selected");
			alert.showAndWait();

		}
	}

	@FXML
	private void selectParticipant(MouseEvent event) {

	}

	@FXML
	private void updateEvent(ActionEvent event) throws NamingException {
		updateEventPane.setVisible(true);
		participantPane.setVisible(false);
		tableEvent.setVisible(false);
		displayReclamation.setVisible(false);

	}

	@FXML
	private void updateEventDone(ActionEvent event) throws NamingException {
		updateEventPane.setVisible(false);
		tableEvent.setVisible(true);

		ctxRym = new InitialContext();
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxyRYM = (EventManagmentRemote) objet;
		ObservableList<Event> userSelected, allEvent;
		allEvent = tableEvent.getItems();
		userSelected = tableEvent.getSelectionModel().getSelectedItems();
		Event e = tableEvent.getSelectionModel().getSelectedItem();
		e.setTitle(title11.getText());
		e.setDescription(description11.getText());
		// insert gallery and Artist
		Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxyU = (UserManagmentRemote) object;
		Gallery g = new Gallery();
		g = (Gallery) proxyU.findById(idGa1);
		e.setGallery(g);
		Artist a = new Artist();
		a = (Artist) proxyU.findById(idAr1);
		e.setArtist(a);
		// Convert Local Date to Date Begin
		LocalDate localDateBegin = dateBegin11.getValue();
		Instant instant1 = Instant.from(localDateBegin.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateBegin = Date.from(instant1);
		e.setDateBegin(dateDateBegin);
		// Convert Local Date to Date End
		LocalDate localDateEnd = dateEnd11.getValue();
		Instant instant2 = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateEnd = Date.from(instant2);
		e.setDateEnd(dateDateEnd);
		// Convert Date Now
		LocalDate localNow = LocalDate.now();
		Instant instantb = Instant.from(localNow.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateNow = Date.from(instantb);
		// Condition chosen Date
		if ((dateNow.before(dateDateBegin)) && (dateDateBegin.before(dateDateEnd))) {
			proxyRYM.updateEvent(e);
			remplirTableEvent();
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Please check the Begining Date and the End Date");
			alert.showAndWait();
		}

	}

	@FXML
	private void chooseGalleryUpdate(ActionEvent event) {
		try {
			ctxRym = new InitialContext();

			Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyRYM = (UserManagmentRemote) object;
			String chosenGallery = galleryList1.getValue();

			Gallery Ga = new Gallery();
			Ga = (Gallery) proxyRYM.findByUsername(chosenGallery);
			idGa1 = Ga.getIdUser();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	private void chooseArtistUpdate(ActionEvent event) {
		try {
			ctxRym = new InitialContext();

			Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			UserManagmentRemote proxyRYM = (UserManagmentRemote) object;
			String chosenArtist = artistList1.getValue();

			Artist Ar = new Artist();
			Ar = (Artist) proxyRYM.findByUsername(chosenArtist);
			idAr1 = Ar.getIdUser();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void remplirComboboxUpdate() throws NamingException {
		ctxRym = new InitialContext();
		Object object = ctxRym.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxyRYM = (UserManagmentRemote) object;

		// Remplir Combobox Gallery
		ObservableList<String> LocG = FXCollections.observableArrayList();
		List<Gallery> Lg = proxyRYM.getAllGalleries();
		for (Gallery X : Lg) {
			String s = X.getUsername();
			LocG.add(s);
		}
		galleryList1.setItems(LocG);

		// Remplir Combobox Artist
		ObservableList<String> LocA = FXCollections.observableArrayList();
		List<Artist> La = proxyRYM.getAllArtists();
		for (Artist X : La) {
			String s = X.getUsername();
			LocA.add(s);
		}
		artistList1.setItems(LocA);
	}

	@FXML
	private void handle(ActionEvent event) throws NamingException {
		ObservableList<Reclamation> userSelected, allRec;
		allRec = tableReclamation.getItems();
		userSelected = tableReclamation.getSelectionModel().getSelectedItems();
		Reclamation ptp = tableReclamation.getSelectionModel().getSelectedItem();
		userSelected.forEach(allRec::remove);
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/FeedbackManagment!services.FeedbackManagmentRemote");
		FeedbackManagmentRemote proxyF = (FeedbackManagmentRemote) objet;
		ptp.setHandle(1);
		proxyF.updateReclamation(ptp);

	}

	@FXML
	private void imprimer(ActionEvent event) {
		try {
			utilprojet u = new utilprojet();
			u.DownloadDocuemntProjet();
		} catch (Exception ex) {
			Logger.getLogger(PdfController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/* FIN RYM */

	/* Oussama */
	@FXML
	private void ShowTunisianCraft(ActionEvent event) {
		TunisianTable.setVisible(true);
		AllArtworkTable1.setVisible(false);
		statisticsPane.setVisible(false);

	}

	public void DisplayTunisianCraft() {
		TunisianTable.getItems().clear();
		pictureo.setResizable(false);
		pictureo.setSortable(false);
		pictureo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, byte[]>("picture"));
		pictureo.setCellFactory(new Callback<TableColumn<TunisianCraft, byte[]>, TableCell<TunisianCraft, byte[]>>() {
			@Override
			public TableCell<TunisianCraft, byte[]> call(TableColumn<TunisianCraft, byte[]> param) {
				TableCell<TunisianCraft, byte[]> cell = new TableCell<TunisianCraft, byte[]>() {
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

		TunisianTable.getItems().clear();
		List<TunisianCraft> ListTc = proxyo.getAllTunisianCraft();
		for (TunisianCraft T : ListTc) {
			TCdata.add(T);
		}

		nameo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("name"));
		Typeo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("Type"));
		desco.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("Description"));
		priceo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("price"));
		dateo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("dateOfOublication"));
		stateo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("state"));

		TunisianTable.setItems(TCdata);
		TunisianTable.setVisible(true);
	}

	/* FIN OUSSAMA */
}
