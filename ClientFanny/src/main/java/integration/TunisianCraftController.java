/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration ;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.Gallery;
import entities.TunisianCraft;
import entities.User;

import java.awt.Label;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.UserManagmentRemote;

/**
 * FXML Controller class
 *
 
 */
public class TunisianCraftController implements Initializable {

    @FXML
    private JFXComboBox<String> TypeT;
    @FXML
    private JFXTextArea DescT;
    @FXML
    private JFXTextField QuantityT;
    @FXML
    private ImageView ImageT;
    
    @FXML
    private JFXTextField  pricet;
    @FXML
    private JFXTextField namet;
    int idar = 1 ;  
	InitialContext ctx;

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


    File picture ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	
    	
    	ObservableList<String> Types = FXCollections.observableArrayList();
        Types.add("Pottery");
        Types.add("Ceramique");
        Types.add("Mosaic");
        Types.add("Traditional Clothing");
        Types.add("Other");
    	TypeT.setItems(Types);
        try {
			DisplayTunisianCraft();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        }    

    @FXML
    private void choosePic(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		picture = fileChooser.showOpenDialog(null);
		if (picture == null) {
			return;
		}
		try {
			BufferedImage bufferedImage = ImageIO.read(picture);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			ImageT.setImage(image);
		} catch (IOException ex) {
		}
    	
    }

    @FXML
    private void AddTunisianCraft(ActionEvent event) throws NamingException {
    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;	
	    TunisianCraft TunC = new TunisianCraft() ;
	    LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		System.out.println(date);
		TunC.setDateOfOublication(date);
	    TunC = new TunisianCraft();
		TunC.setType(TypeT.getValue());
		TunC.setDescription(DescT.getText());
		TunC.setName(namet.getText());
		
		float f = Float.parseFloat(pricet.getText());
		

		TunC.setPrice(f);
		int Q = Integer.parseInt(QuantityT.getText());
		TunC.setQuantity(Q);
		
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxyU = (UserManagmentRemote) object;
		User U =new Artist();
		U= proxyU.findById(idar);
		System.out.println(U);
		TunC.setUser(U);
		byte[] bFile = new byte[(int) picture.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(picture);
			fileInputStream.read(bFile);
			fileInputStream.close();
			TunC.setPicture(bFile);

		} catch (Exception e) {
		}
		proxy.addTunisianCraft(TunC);
		DisplayTunisianCraft();
    }
     
    public void DisplayTunisianCraft() throws NamingException 
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
		ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;	

		TunisianTable.getItems().clear();
		List<TunisianCraft> ListTc = proxy.findByArtist(idar);
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
	
}
