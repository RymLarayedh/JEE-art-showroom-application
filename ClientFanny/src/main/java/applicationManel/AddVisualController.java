package applicationManel;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.TunisianCraft;
import entities.User;
import entities.VisualArt;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
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
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.VisualArtworkEJBRemote;

public class AddVisualController implements Initializable{

	//
	 @FXML
	    private JFXComboBox<String> cat;
	    @FXML
	    private JFXTextField name;
	    @FXML
	    private JFXTextField price;
	    @FXML
	    private ImageView pict;
	    @FXML
	    private JFXTextArea descr;
	    @FXML
	    private JFXButton btnpic;

	    @FXML
	    private JFXButton addVisual;
	    @FXML
	    private JFXTextField len;
	    @FXML
	    private JFXTextField wid;
	    @FXML
	    private ImageView pict1;
	    InitialContext ctx;
		File picture ;
	 
	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	        // TODO
	        
	        ObservableList<String> Types = FXCollections.observableArrayList();
	        Types.add("Pottery");
	        Types.add("Ceramique");
	        Types.add("Mosaic");
	        Types.add("Traditional Clothing");
	        Types.add("Other");
	    	cat.setItems(Types);
	        
	        
	        
	       
	        
	        
	        }    

	    @FXML
	    private void btnpicAction(ActionEvent event) {
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
				pict.setImage(image);
			} catch (IOException ex) {
			}
			
				
	        
	    }

	    @FXML
	    private void addVisualAction(ActionEvent event) throws NamingException {
	    	ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;	
		    VisualArt TunC = new VisualArt() ;

			TunC = new VisualArt();
			
			String selectedCategory = cat.getValue();
			TunC.setCategory(selectedCategory);
			TunC.setName(name.getText());
			int lo = Integer.parseInt(len.getText());
			int la = Integer.parseInt(wid.getText());
			TunC.setLength(lo);
			TunC.setWidth(la);
			TunC.setDescription(descr.getText());
			int Q = Integer.parseInt(price.getText());
			TunC.setPrice(Q);
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
			TunC.setDateOfOublication(date);
			byte[] bFile = new byte[(int) picture.length()];
			try {
				FileInputStream fileInputStream = new FileInputStream(picture);
				fileInputStream.read(bFile);
				fileInputStream.close();
				TunC.setPicture(bFile);

			} catch (Exception e) {
			}
			
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Add succes");
			alert.showAndWait();
			
			
			proxy.addVisualArt(TunC);
			
	    }
	    
	    
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}

}
