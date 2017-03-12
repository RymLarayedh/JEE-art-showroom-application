package integration;

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
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.print.DocFlavor.BYTE_ARRAY;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import entities.Artist;
import entities.Artwork;
import entities.Gallery;
import entities.User;
import entities.VisualArt;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.stage.Window;
import javafx.util.Callback;
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
	
///////////manel//////////////////////
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
@FXML
private AnchorPane OussamsPane;

public static VisualArt chosenArtworkAdmin;
ObservableList<VisualArt> artdata = FXCollections.observableArrayList();

InitialContext ctx;
public static VisualArtworkEJBRemote proxy;

//FIN MANEL



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		/*DEBUT AYMEN*/
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
		/*FIN AYMEN*/
		
		/*MANEL*/
		try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
//			VisualArtwork = (VisualArtworkEJBRemote) ctx
//					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			Artwork aa = new Artwork();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AllArtworkTable1.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label

                
            }});
		
		DisplayVisualArts();
		
		
	AllArtworkTable1.setVisible(false);
	/*FINMANEL*/

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
		if(nature.getValue() == null)
		{
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
		if(popupController.userChoosen == AllUsersTable.getSelectionModel().getSelectedItem())
		{
			return ;
		}
		popupController.userChoosen = AllUsersTable.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popup.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.setOnCloseRequest(e ->{
            e.consume();
            Main.closeProgram(Sc);
            popupController.userChoosen = null ;
		});
		Sc.showAndWait();


	}

	@FXML
	public void ShowWaitList(ActionEvent event) {
		nature.setVisible(true);
		// byFollowers.setVisible(true);
		sortLabel.setVisible(true);
		popupController.fromWhere = "Wating";
		if(nature.getValue() == null)
		{
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
		if(nature.getValue() == null)
		{
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
	/*FINAYMEN*/
	/*DEBUTMANEL*/
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
                Scene scene=new Scene(root);
                Stage Sc=new Stage();
                Sc.setScene(scene);
                Sc.show();
                final Node source =(Node) event.getSource();
                final Stage stage=(Stage) source.getScene().getWindow(); 
                stage.close();
				
			}});
		

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
		///////////////prec
//		AdminUpdDetController.ArtworkChoosen = AllArtworkTable1.getSelectionModel().getSelectedItem();
//		Parent root=FXMLLoader.load(getClass().getResource("AdminUpdDel.fxml"));
//      Scene scene=new Scene(root);
//      Stage Sc=new Stage();
//      Sc.setScene(scene);
//      Sc.show();
//      final Node source =(Node) event.getSource();
//      final Stage stage=(Stage) source.getScene().getWindow();
//      stage.close();
      ///////////////////
//		AllArtworkTable1.setRowFactory(tv -> {
//            TableRow<Artwork> row = new TableRow<>();
//            row.setOnMouseClicked(event1 -> {
//                if (event1.getClickCount() == 1 && (!row.isEmpty())) {
//                	Alert alert = new Alert(Alert.AlertType.ERROR);
//        			alert.setTitle("Fanny");
//        			alert.setHeaderText(null);
//        			alert.setContentText("yyyyy");
//        			alert.showAndWait();
//        			
        			
//                    Artwork rowData = row.getItem();
//                    Parent adminart = null;
//					try {
//						adminart = FXMLLoader.load(getClass().getResource("AdminDelUpd.fxml"));
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//            		Scene scene = new Scene(adminart);
//            		
//            		Stage Sc = new Stage();
//            		Sc.setScene(scene);
//            		Sc.show();
//            		final Node source = (Node) event.getSource();
//            		final Stage stage = (Stage) source.getScene().getWindow();
//            		//stage.close();

//                }
//            });
//            return row;
//        });
		
		
	}
	
	public void DisplayVisualArts() 
	{
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
//							if (item == null) {
//								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
//								BufferedImage bufferedImage;
//								try {
//									bufferedImage = ImageIO.read(file);
//									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//									imageview.setImage(image);
//								} catch (IOException e) {
//								}
//
//							} else {
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

//							}

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
		//status.setCellValueFactory(Artwork -> {
			//boolean isActive = Artwork.getValue().isActive();
//			String isActiveAsString;
//			if (isActive) {
//				isActiveAsString = "Active";
//			} else {
//				isActiveAsString = "Not Active";
//			}
//			return new ReadOnlyStringWrapper(isActiveAsString);
		//});
		AllArtworkTable1.setItems(artdata);
		AllArtworkTable1.setVisible(true);
		
		
		
		
		///////////////////////////////
//		AllArtworkTable1.setRowFactory(tv -> {
//            TableRow<Artwork> row = new TableRow<>();
//            row.setOnMouseClicked(event -> {
//                if (event.getClickCount() == 1 && (!row.isEmpty())) {
//                	Parent root=FXMLLoader.load(getClass().getResource("AdminDelUpd.fxml"));
//                    Scene scene=new Scene(root);
//                    Stage Sc=new Stage();
//                    Sc.setScene(scene);
//                    Sc.show();
//                    final Node source =(Node) event.getSource();
//                    final Stage stage=(Stage) source.getScene().getWindow(); 
//                    stage.close();
//                }
//            });
//            return row;
//        });
	}
	
	 @FXML
	    private void showtable1(ActionEvent event) {
		 AllArtworkTable1.setVisible(true);
	    }
/*FIN MANEL*/
}
