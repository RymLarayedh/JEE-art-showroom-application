/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema ;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.TunisianCraft;
import entities.User;

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
	InitialContext ctx;

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

		TunC = new TunisianCraft();
		TunC.setType(TypeT.getValue());
		
		TunC.setDescription(DescT.getText());
		int Q = Integer.parseInt(QuantityT.getText());
		TunC.setQuantity(Q);
		
		byte[] bFile = new byte[(int) picture.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(picture);
			fileInputStream.read(bFile);
			fileInputStream.close();
			TunC.setPicture(bFile);

		} catch (Exception e) {
		}
		proxy.addTunisianCraft(TunC);
    }
    
    
}
