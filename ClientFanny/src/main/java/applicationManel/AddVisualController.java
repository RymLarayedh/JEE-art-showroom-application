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
import java.util.ArrayList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.VisualArtworkEJBRemote;

public class AddVisualController implements Initializable{

	
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
	    InitialContext ctx;
	File picture ;
	    @FXML
	    private JFXButton addVisual;
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
			//TunC.setCategory(cat.getValue());
			TunC.setName(name.getText());
			TunC.setDescription(descr.getText());
			int Q = Integer.parseInt(price.getText());
			TunC.setPrice(Q);
			
			byte[] bFile = new byte[(int) picture.length()];
			try {
				FileInputStream fileInputStream = new FileInputStream(picture);
				fileInputStream.read(bFile);
				fileInputStream.close();
				TunC.setPicture(bFile);

			} catch (Exception e) {
			}
			proxy.addVisualArt(TunC);
	    }
	    
//	@Override
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}

}
