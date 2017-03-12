/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationManel;

import com.jfoenix.controls.JFXTextArea;

import entities.Artist;
import entities.Artwork;
import entities.Comment;
import entities.Feedback;

import entities.Gallery;
import entities.VisualArt;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.ArtworkManagemetRemote;
import services.UserManagmentRemote;
import services.VisualArtworkEJBRemote;
import utils.ConfirmBox;

import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author 
 */
public class DetailArtworkController implements Initializable {

    @FXML
    private AnchorPane Trip;
    @FXML
    private ImageView img;
    @FXML
    private JFXTextArea commenttxt;
    @FXML
    private TableView<Feedback> commenttable;
    @FXML
    private TableColumn namecl;
    @FXML
    private TableColumn<Comment, String> bodycl;
    @FXML
    private TableColumn datecl;
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
      
    /**
     * Initializes the controller class.
     */
	
	    public static VisualArtworkEJBRemote proxy;
	  public static Artwork a = new Artwork ();
	    private Comment selectedComment;
	    ObservableList<Feedback> MyCmtdata = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
		 Artwork a = new Artwork ();
			a= proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
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
    	try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		commenttable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label

                
            }});
		
		try {
			DisplayVisualComment();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    

    @FXML
    private void addcomment(ActionEvent event) throws NamingException {
    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;	
	    Comment Com = new Comment() ;

	    Com = new Comment();
		

	    Com.setBodyComment(commenttxt.getText());
	    Com.setUser(LoginController.userLogedIn);
		a= proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
		Com.setArtwork(a);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		Com.setDateComment(date);

		
		
	
		Com.setUser(LoginController.userLogedIn);
		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Fanny");
		alert.setHeaderText(null);
		alert.setContentText("Comment Add succes");
		alert.showAndWait();
		
		
		proxy.addVisualComment(Com);
		DisplayVisualComment();
		commenttable.getItems().clear();
    }
    @FXML
    private void selectComment(MouseEvent event) {
	
    	
    }
    
    public void DisplayVisualComment() throws NamingException 
	{
    	commenttable.getItems().clear();

    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
		a= proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
		System.out.println(a);
		VisualArt v=(VisualArt) a;

		commenttable.getItems().clear();

		List<Comment> ListComments = proxy.findAllVisualArtComment(AdminController.chosenArtworkAdmin.getIdArtwork());
		for (Comment a : ListComments) {
			MyCmtdata.add(a);
		}

		namecl.setCellValueFactory(new Callback<CellDataFeatures<Comment, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Comment, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
		bodycl.setCellValueFactory(new PropertyValueFactory<Comment, String>("bodyComment"));
		datecl.setCellValueFactory(new PropertyValueFactory<Comment, String>("dateComment"));

		commenttable.setItems(MyCmtdata);
		commenttable.setVisible(true);
		
		
		
		

	}
    @FXML
    private void Modifycomment(ActionEvent event) throws NamingException {
    	bodycl.setEditable(true);
		selectedComment = (Comment) commenttable.getSelectionModel().getSelectedItem();
        commenttable.setEditable(true);
		bodycl.setCellFactory(TextFieldTableCell.<Comment> forTableColumn());

		bodycl.setOnEditCommit(t -> {
			t.getRowValue().setBodyComment(t.getNewValue());

			boolean confirm = ConfirmBox.display("", "Update Comment ?");

			if (confirm) {

				proxy.upadateVisualComment(selectedComment);
			}
		});
				
		DisplayVisualComment();
		commenttable.getItems().clear();	
    }

    @FXML
    private void Deletecomment(ActionEvent event) throws NamingException, IOException {
    	boolean confirm = ConfirmBox.display("", "Delete Comment ?");
		if (confirm) {

			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;

			selectedComment = (Comment) commenttable.getSelectionModel().getSelectedItem();
			proxy.deleteVisualComment(selectedComment);
			
			}
		DisplayVisualComment();
		commenttable.getItems().clear();
    }

    @FXML
    private void liked(ActionEvent event) {
    }

    @FXML
    private void bookTrip(ActionEvent event) {
    }
    
}
