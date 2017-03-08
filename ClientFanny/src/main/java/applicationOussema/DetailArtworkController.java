/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema;

import com.jfoenix.controls.JFXTextArea;

import entities.Artist;
import entities.Artwork;
import entities.Gallery;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import services.ArtworkManagemetRemote;
import services.UserManagmentRemote;

import org.controlsfx.control.Rating;


/**
 * FXML Controller class
 *
 * @author Oussamabh
 */
public class DetailArtworkController implements Initializable {

    @FXML
    private AnchorPane Trip;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextArea commenttxt;
    @FXML
    private TableView<?> commenttable;
    @FXML
    private TableColumn<?, ?> namecl;
    @FXML
    private TableColumn<?, ?> bodycl;
    @FXML
    private TableColumn<?, ?> datecl;
    @FXML
    private Button deletecomment;
    @FXML
    private Label nbrl;
    @FXML
    private ToggleButton tj;
    @FXML
    private Label desct;
    @FXML
    private Label datet;
    @FXML
    private Label pricet;
    @FXML
    private Label artnamet;
    @FXML
    private Label artistn;
	InitialContext ctx;
      int idartwork = 1 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;
		 Artwork a = new Artwork ();
			a= proxy.findById(idartwork);
			System.out.println(a);
        artnamet.setText(a.getName());
        String P = String.valueOf(a.getPrice());
        pricet.setText(P);
       artistn.setText(a.getUser().getUsername());
       desct.setText(a.getDescription());
       BufferedImage imgbf = null;
       byte[] b = a.getPicture();

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

       // ImageView imView = new ImageView(wr);
       img.setImage(wr);

         
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	
    }    

    @FXML
    private void addcomment(ActionEvent event) {
    }

    @FXML
    private void Modifycomment(ActionEvent event) {
    }

    @FXML
    private void Deletecomment(ActionEvent event) {
    }

    @FXML
    private void liked(ActionEvent event) {
    }

    @FXML
    private void bookTrip(ActionEvent event) {
    }
    
}
