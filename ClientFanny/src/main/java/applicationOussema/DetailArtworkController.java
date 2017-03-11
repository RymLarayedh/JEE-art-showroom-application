/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema;

import com.jfoenix.controls.JFXTextArea;
import javafx.scene.input.MouseEvent;

import entities.Artist;
import entities.Artwork;
import entities.Feedback;
import entities.FeedbackId;
import entities.Gallery;
import entities.Like;
import entities.TunisianCraft;
import entities.User;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import services.LikeManagementRemote;
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
    @FXML
    private  Rating ratingg;
	InitialContext ctx;
      int idartwork = 2 ;
      int userconect = 1 ;
      long nbr ;
      Double note ;
      String str ;
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
       /******************************lIKE******************************/ 
       Object objet2 = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxy2 = (LikeManagementRemote) objet2;
		nbr = proxy2.nbrlike(2);
        str = "" + nbr;
		nbrl.setText(str);
		if (proxy2.checkexistance(1,2) )
		 {tj.setText("Unlike");
		 tj.setRotate(-180);
		
		
	 }

else {
   tj.setText("Like");
   tj.setRotate(360);
}
/********************************Rating*****************************************/
		Object objet3 = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxy3 = (LikeManagementRemote) objet3;
		//note= proxy3.findByUserartIDR(1, 2).getNote() ;	
		//ratingg.setRating(note);
		
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
    private void liked(ActionEvent event) throws NamingException {
    	
       
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxy = (LikeManagementRemote) objet;
	 Like L = new Like ();
	 Artwork A = new Artwork() ;
	 A.setIdArtwork(2);
	 User U = new User();
	 U.setIdUser(1);
	 FeedbackId feedbackId =new FeedbackId();
		feedbackId.setArtworkId(2);
		feedbackId.setUserId(1);
		L.setFeedbackId(feedbackId);

       
	 if (proxy.checkexistance(1,2) )
			 {tj.setText("Unlike");
			 tj.setRotate(-180);
             
			
			
		 }

     else {
        tj.setText("Like");
        tj.setRotate(360);
    }
	 if (tj.getText().equals("Like")) {
         tj.setText("Unlike");
         tj.setRotate(-180);
		

         proxy.addLike(L);

        nbr = proxy.nbrlike(2);
         str = "" + nbr;

         nbrl.setText(str);
     } 
	 else {

        
         proxy.deleteLike(L);
         nbr = proxy.nbrlike(2);

         str = "" + nbr;

         nbrl.setText(str);
         tj.setText("Like");
         tj.setRotate(360);


     }

 }
	 
	 

    
        
		
		
      

    @FXML
    private void bookTrip(ActionEvent event) {
    }
    @FXML
	public void Rate(MouseEvent event) throws NamingException {
    	Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxy = (LikeManagementRemote) objet;  
		 FeedbackId feedbackId =new FeedbackId();
		entities.Rating R = new entities.Rating();	
		 feedbackId.setArtworkId(2);
			feedbackId.setUserId(1);
			R.setFeedbackId(feedbackId);
			R.setNote(ratingg.getRating());
			if (proxy.checkexistanceR(1,2) )
			 {
				proxy.addRating(R);
			 }
            
			

    else {
    	R.setNote(ratingg.getRating());
    	proxy.upadateRating(R);
    }
    }
    
}
