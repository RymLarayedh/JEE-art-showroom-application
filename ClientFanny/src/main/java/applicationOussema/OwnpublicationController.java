/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Artwork;
import entities.TunisianCraft;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.ArtworkManagemetRemote;

/**
 * FXML Controller class
 *
 * @author Oussamabh
 */
public class OwnpublicationController implements Initializable {

    @FXML
    private Pane affichepane;
    @FXML
    private ImageView imgall;
    @FXML
    private Label artnamet;
    @FXML
    private Label artwnamet1;
    @FXML
    private Label date1;
    @FXML
    private Label type1;
	@FXML
	private VBox vbox;

	InitialContext ctx;
int i =0 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;
			List <TunisianCraft> lst= new ArrayList<>();
			lst= proxy.getAllTunisianCraft();
			
		
				
			
			for (  i =0 ; i< lst.size();i++);
			{
			 //ooo
		        artnamet.setText(lst.get(i).getUser().getUsername());
		        type1.setText(lst.get(i).getType());
		       artwnamet1.setText(lst.get(i).getName());
		       BufferedImage imgbf = null;
		       byte[] b = lst.get(i).getPicture();

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
		       imgall.setImage(wr);

		         
			}}
			catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
		    	
				
			
			

    	
    }    
    
    
}
