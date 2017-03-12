package integration;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import application.LoginController;
import entities.Artwork;
import entities.TunisianCraft;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.ArtworkManagemetRemote;

public class DisplayTunisianCrController implements Initializable{
	@FXML
	private Label artist;
	@FXML
	private Label artwork;
	@FXML
	private Label price;
	@FXML
	private Label date;
	 @FXML
	    private ImageView img;
	InitialContext ctx;
 int pos = 0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
				AfficherTn();
    }    

	
	// Event Listener on Button.onAction
	@FXML
	public void First(ActionEvent event)  {
		
    pos =0 ;
    AfficherTn();
		
	}
	// Event Listener on Button.onAction
	@FXML
	public void Next(ActionEvent event) throws NamingException {
		ctx = new InitialContext();
		Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
		List <TunisianCraft> lst= new ArrayList<>();
		lst= proxyo.getAllTunisianCraft();
		int size = lst.size();

        if (pos < size-1) {
            pos++;
            AfficherTn(); 
        }	}
	// Event Listener on Button.onAction
	@FXML
	public void Prec(ActionEvent event) throws NamingException {
		ctx = new InitialContext();
		Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
		List <TunisianCraft> lst= new ArrayList<>();
		lst= proxyo.getAllTunisianCraft(); 
		int size = lst.size();

	        if (pos >= 0) {
	            pos--;
	            AfficherTn();
	            
	            
	        }
	}
	// Event Listener on Button.onAction
	@FXML
	public void Last(ActionEvent event) throws NamingException {
		ctx = new InitialContext();
		Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
		List <TunisianCraft> lst= new ArrayList<>();
		lst= proxyo.getAllTunisianCraft(); 
		int size = lst.size();

	       pos=size-1;
	            AfficherTn();
	        }
	
	public void AfficherTn()
	{
		try {
			ctx = new InitialContext();
			Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
			List <TunisianCraft> lst= new ArrayList<>();
			lst= proxyo.getAllTunisianCraft();
			
			
			System.out.println(lst);
        //artist.setText(lst.get(pos).getUser().getUsername());
        String P = String.valueOf(lst.get(pos).getPrice());
        price.setText(P);
       artwork.setText(lst.get(pos).getName());
       artist.setText(lst.get(pos).getUser().getUsername());
		if (lst.get(pos).getPicture()== null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				img.setImage(image);
			} catch (IOException e) {
			}}
			else
			{
       BufferedImage imgbf = null;
       byte[] b = lst.get(pos).getPicture();

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

         
			}} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
    	
		
	}
	
}
