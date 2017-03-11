package applicationManel;

import java.awt.Button;
import javafx.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Artwork;
import entities.User;
import entities.VisualArt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import services.VisualArtworkEJBRemote;

public class AdminUpdDetController  implements Initializable {
	@FXML
    private JFXComboBox<String> cat2;
    @FXML
    private JFXButton bntup;
    @FXML
    private JFXTextField name1;
    @FXML
    private JFXTextField price2;
    @FXML
    private ImageView pict;
    @FXML
    private JFXTextArea descr2;
    @FXML
    private JFXButton btnpict2;
    @FXML
    private JFXTextField len2;
    @FXML
    private JFXTextField wid2;
    @FXML
    private ImageView pict2;
    @FXML
    private JFXButton del1;
    InitialContext ctx;
	File picture ;
	static public Artwork ArtworkChoosen;
    /**
     * Initializes the controller class.
     */
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	  // TODO
        
        ObservableList<String> Types = FXCollections.observableArrayList();
        Types.add("Pottery");
        Types.add("Ceramique");
        Types.add("Mosaic");
        Types.add("Traditional Clothing");
        Types.add("Other");
    	cat2.setItems(Types);
        
        
		
	}

    @FXML
    private void upvisualAction(ActionEvent event) throws NamingException  {
    	
    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;	
	    VisualArt TunC = new VisualArt() ;

		TunC = new VisualArt();
		
		String selectedCategory = cat2.getValue();
		TunC.setCategory(selectedCategory);
		TunC.setName(name1.getText());
		int lo = Integer.parseInt(len2.getText());
		int la = Integer.parseInt(wid2.getText());
		TunC.setLength(lo);
		TunC.setWidth(la);
		TunC.setDescription(descr2.getText());
		int Q = Integer.parseInt(price2.getText());
		TunC.setPrice(Q);
		
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
		
		
		proxy.upadateVisualArt(TunC);
    }

    @FXML
    private void chgpictAction(ActionEvent event) {
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
    private void bntdelAction(ActionEvent event) {
    }
	
}
