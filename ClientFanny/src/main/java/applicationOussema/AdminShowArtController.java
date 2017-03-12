package applicationOussema;

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
import entities.TunisianCraft;
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
import services.ArtworkManagemetRemote;
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
	private TableView<Artwork> AllArtworkTable1;
	@FXML
	private TableColumn<Artwork, byte[]> picture1;
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
	/******************************************oussama********************/
	@FXML
	private AnchorPane OussamsPane;

    @FXML
    private TableColumn< TunisianCraft,byte[] > pictureo;

    @FXML
    private TableColumn<TunisianCraft,String > nameo;

    @FXML
    private TableColumn<TunisianCraft,String> priceo;

    @FXML
    private TableColumn<TunisianCraft,String> Typeo;

    @FXML
    private TableColumn<TunisianCraft,String> stateo;

    @FXML
    private TableColumn<TunisianCraft,String> dateo;

    @FXML
    private TableColumn<TunisianCraft,String> desco;
    
    @FXML
    private TableView<TunisianCraft> TunisianTable;
	ObservableList<TunisianCraft> TCdata = FXCollections.observableArrayList();

/*********************************/
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label userLoggedInName;


	ObservableList<Artwork> artdata = FXCollections.observableArrayList();

	InitialContext ctx;
	public static VisualArtworkEJBRemote proxy;
	public static ArtworkManagemetRemote proxyo;

	
	 @FXML
	    private void showtable1(ActionEvent event) {
		 AllArtworkTable1.setVisible(true);
			TunisianTable.setVisible(false);

	    }
	 @FXML
	    private void ShowTunisianCraft(ActionEvent event) {
		TunisianTable.setVisible(true);
		 AllArtworkTable1.setVisible(false);

	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			Artwork aa = new Artwork();
			
			/********ouss**************/
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			 proxyo = (ArtworkManagemetRemote) objet;	
		    TunisianCraft TunC = new TunisianCraft() ;
			DisplayTunisianCraft();
			TunisianTable.setVisible(false);
		    /********************/

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		DisplayVisualArts();
		
	AllArtworkTable1.setVisible(false);


//				
		
	}
	public void DisplayVisualArts() 
	{
		AllArtworkTable1.getItems().clear();
		picture1.setResizable(false);
		picture1.setSortable(false);
		picture1.setCellValueFactory(new PropertyValueFactory<Artwork, byte[]>("picture"));
		picture1.setCellFactory(new Callback<TableColumn<Artwork, byte[]>, TableCell<Artwork, byte[]>>() {
			@Override
			public TableCell<Artwork, byte[]> call(TableColumn<Artwork, byte[]> param) {
				TableCell<Artwork, byte[]> cell = new TableCell<Artwork, byte[]>() {
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

		AllArtworkTable1.getItems().clear();
		List<Artwork> ListArtworks = proxy.findAllVisualArt();
		for (Artwork a : ListArtworks) {
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
		
		AllArtworkTable1.setItems(artdata);
		AllArtworkTable1.setVisible(true);
		
		
		
		

	}
	
	public void DisplayTunisianCraft() 
	{
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
		for (TunisianCraft T : ListTc ) {
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
	
	// Event Listener on JFXButton.onAction
	@FXML
	public void ShowAllUsers(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TableView[#AllVisualArtTable].onMouseClicked
	@FXML
	public void showPopupp(MouseEvent event) throws IOException {
		///

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
		
//		
//		////
//		AdminUpdDetController.ArtworkChoosen = AllArtworkTable1.getSelectionModel().getSelectedItem();
//		Parent root=FXMLLoader.load(getClass().getResource("AdminUpdDel.fxml"));
//        Scene scene=new Scene(root);
//        Stage Sc=new Stage();
//        Sc.setScene(scene);
//        Sc.show();
//        final Node source =(Node) event.getSource();
//        final Stage stage=(Stage) source.getScene().getWindow();
//        stage.close();
	}
	
	// Event Listener on TableView[#AllArtworkTable1].onMouseClicked
	@FXML
	public void showPopuppp(MouseEvent event) throws IOException {
		AdminUpdDetController.ArtworkChoosen = AllArtworkTable1.getSelectionModel().getSelectedItem();
		Parent root=FXMLLoader.load(getClass().getResource("AdminUpdDel.fxml"));
      Scene scene=new Scene(root);
      Stage Sc=new Stage();
      Sc.setScene(scene);
      Sc.show();
      final Node source =(Node) event.getSource();
      final Stage stage=(Stage) source.getScene().getWindow();
      stage.close();
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
