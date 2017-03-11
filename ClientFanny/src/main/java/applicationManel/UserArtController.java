package applicationManel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;

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

import entities.Artwork;
import entities.VisualArt;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.VisualArtworkEJBRemote;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.control.TableColumn;

public class UserArtController  implements Initializable {
	@FXML
	private TableView<VisualArt> AllArtworkTable2;
	@FXML
	private TableColumn picture1;
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
	private Button artDet;
	
	 @FXML
	    private TableView<Artwork> allart;
	    @FXML
	    private TableColumn pitart;
	    @FXML
	    private TableColumn nameart;
	    
	InitialContext ctx;
	public static Artwork chosenArtwork;
	public static Artwork chosenArtworks;
	public static VisualArtworkEJBRemote proxy;
	ObservableList<VisualArt> Myartdata = FXCollections.observableArrayList();
	ObservableList<Artwork> artdata = FXCollections.observableArrayList();
	// Event Listener on TableView[#AllArtworkTable1].onMouseClicked
	@FXML
	public void showPopuppp(MouseEvent event) throws IOException {
		
		
	}
	// Event Listener on Button[#artDet].onAction
	@FXML
	public void showArtDetail(ActionEvent event) throws IOException {
		chosenArtwork = AllArtworkTable2.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("MyArtworlDetail.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.show();	
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
		
		AllArtworkTable2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label

                
            }});
		DisplayArts();
		DisplayVisualArts();
		
		
	//AllArtworkTable2.setVisible(false);

	
		
	}
	public void DisplayVisualArts() 
	{
		
		
		AllArtworkTable2.getItems().clear();
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
		AllArtworkTable2.getItems().clear();
		List<VisualArt> ListArtworks = proxy.findAllVisualArt2();
		for (VisualArt a : ListArtworks) {
			Myartdata.add(a);
		}
		
	
		
		name1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("name"));
		category1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("category"));
		lenght1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("length"));
		width1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("width"));
		desc1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("Description"));
		price1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("price"));
		date1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("dateOfOublication"));
		state1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("state"));

		AllArtworkTable2.setItems(Myartdata);
		AllArtworkTable2.setVisible(true);
		
		
		
		

	}
	
	//////////
	
	 @FXML
	    private void showArtsDetail(ActionEvent event) throws IOException {
			chosenArtwork = allart.getSelectionModel().getSelectedItem();
			Parent adminScene = FXMLLoader.load(getClass().getResource("DetailArtwork.fxml"));
			Scene scene = new Scene(adminScene);
			Stage Sc = new Stage();
			Sc.setScene(scene);
			Sc.setScene(scene);
			Sc.setTitle("FannyTUNISIA");
			Sc.show();	
		 
	    }
	public void DisplayArts() 
	{
		
		
		allart.getItems().clear();
		pitart.setResizable(false);
		pitart.setSortable(false);
		pitart.setCellValueFactory(new PropertyValueFactory<Artwork, byte[]>("picture"));
		pitart.setCellFactory(new Callback<TableColumn<Artwork, byte[]>, TableCell<Artwork, byte[]>>() {
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

		/**************************************************************/
		allart.getItems().clear();
		List<Artwork> Artworks = proxy.findAllVisualArt();
		for (Artwork a : Artworks) {
			artdata.add(a);
		}
		
	
		
		nameart.setCellValueFactory(new PropertyValueFactory<Artwork, String>("name"));
		

		allart.setItems(artdata);
		AllArtworkTable2.setVisible(true);
		
		
		
		

	}
}
