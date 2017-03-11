package applicationManel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import com.jfoenix.controls.JFXButton;


import entities.Artwork;
import entities.VisualArt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import services.VisualArtworkEJBRemote;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
public class AdminShowArtController implements Initializable{
	@FXML
	private AnchorPane AymensPane;
	@FXML
	private TableView AllVisualArtTable;
	@FXML
	private TableColumn picture;
	@FXML
	private TableColumn firstName;
	@FXML
	private TableColumn LastName;
	@FXML
	private TableColumn mail;
	@FXML
	private TableColumn username;
	@FXML
	private TableColumn status;
	@FXML
	private AnchorPane RymsPane;
	@FXML
	private AnchorPane InessPane;
	
	
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
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label userLoggedInName;

	public static VisualArt chosenArtworkAdmin;
	ObservableList<VisualArt> artdata = FXCollections.observableArrayList();

	InitialContext ctx;
	public static VisualArtworkEJBRemote proxy;
	
	 @FXML
	    private void showtable1(ActionEvent event) {
		 AllArtworkTable1.setVisible(true);
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {

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
//				userLoggedInName
//						.setText(LoginController.userLogedIn.getFirstName() + " " + LoginController.userLogedIn.getLastName());
//				if (LoginController.userLogedIn.getPicture() == null) {
//					File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
//					BufferedImage bufferedImage;
//					try {
//						bufferedImage = ImageIO.read(file);
//						Image image = SwingFXUtils.toFXImage(bufferedImage, null);
//						profilePicture.setImage(image);
//					} catch (IOException e) {
//					}
//
//				} else {
//					try {
//						byte[] b = LoginController.userLogedIn.getPicture();
//						BufferedImage imgbf = null;
//
//						imgbf = ImageIO.read(new ByteArrayInputStream(b));
//						WritableImage wr = null;
//						if (imgbf != null) {
//							wr = new WritableImage(imgbf.getWidth(), imgbf.getHeight());
//							PixelWriter pw = wr.getPixelWriter();
//							for (int x = 0; x < imgbf.getWidth(); x++) {
//								for (int y = 0; y < imgbf.getHeight(); y++) {
//									pw.setArgb(x, y, imgbf.getRGB(x, y));
//								}
//							}
//						}
//						profilePicture.setImage(wr);
//					} catch (IOException e) {
//					}
//
//				}
	

		
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
	// Event Listener on JFXButton.onAction
	@FXML
	public void ShowAllUsers(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TableView[#AllVisualArtTable].onMouseClicked
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
	// Event Listener on ImageView[#profilePicture].onMouseClicked
	@FXML
	public void showProfile(MouseEvent event) {
		// TODO Autogenerated
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
	
}
