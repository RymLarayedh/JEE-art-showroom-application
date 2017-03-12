package integrationManel;




import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
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
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import application.ProfileuserController;
import entities.Artwork;
import entities.Category;
import entities.Comment;
import entities.Feedback;
import entities.VisualArt;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.VisualArtworkEJBRemote;
import utils.ConfirmBox;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class AdminAllVisualArtController implements Initializable {
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
	    private Button Del;
	    @FXML
	    private Button up;
	    @FXML
	    private Button Order;
	    @FXML
	    private JFXTextField artistn;
	    @FXML
	    private JFXTextField artnamet;
	    @FXML
	    private JFXTextField datet;
	    @FXML
	    private JFXTextArea desct;
	    @FXML
	    private JFXTextField pricet;
	    @FXML
	    private JFXTextField le;
	    @FXML
	    private JFXTextField wi;

    InitialContext ctx;
    public static VisualArtworkEJBRemote proxy;
    public static Artwork a = new Artwork ();
    private Comment selectedComment;
    ObservableList<Feedback> MyCmtdata = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
   
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
			a= proxy.findVisualArtById(AdminController.chosenArtworkAdmin.getIdArtwork());
			System.out.println(a);
			VisualArt v=(VisualArt) a;
        artnamet.setText(a.getName());
        le.setText(String.valueOf(v.getLength()));
        wi.setText(String.valueOf(v.getWidth()));
        String P = String.valueOf(a.getPrice());
        pricet.setText(P);
      artistn.setText(a.getUser().getUsername());
        
       datet.setText(String.valueOf(a.getDateOfOublication()));
       desct.setText(a.getDescription());
       BufferedImage imgbf = null;
       byte[] b = a.getPicture();
       commenttable.setEditable(true);
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

       img.setImage(wr);

         
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
//////////////////Comment
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
		a= proxy.findVisualArtById(AdminController.chosenArtworkAdmin.getIdArtwork());
		Com.setArtwork(a);
	Com.setUser(LoginController.userLogedIn);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		Com.setDateComment(date);

		
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

		a= proxy.findVisualArtById(AdminController.chosenArtworkAdmin.getIdArtwork());
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
		//bodycl.setCellValueFactory(cellData -> new ReadOnlyStringWrapper("bodyComment"));
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

			boolean confirm = ConfirmBox.display("", "Update Category ?");

			if (confirm) {

				proxy.upadateVisualComment(selectedComment);
			}
		});
				//proxy.upadateVisualComment(selectedComment);
		//DisplayVisualComment();
	//	commenttable.getItems().clear();
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
    private void OrderAction(ActionEvent event) {
  
    }

    @FXML
    private void liked(ActionEvent event) {
    }

    @FXML
    private void DeleteArt(ActionEvent event) throws IOException, NamingException{
    	boolean confirm = ConfirmBox.display("", "Delete this visual Art?");
		if (confirm) {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
			a= proxy.findVisualArtById(AdminController.chosenArtworkAdmin.getIdArtwork());
			proxy.deleteVisualArt(a);
			Parent adminScene = FXMLLoader.load(getClass().getResource("AdminAll.fxml"));
			Scene scene = new Scene(adminScene);
			Stage Sc = new Stage();
			Sc.setScene(scene);
			Sc.setScene(scene);
			Sc.setTitle("FannyTUNISIA");
			Sc.show();	
			}
			
    }

    @FXML
    private void updateArt(ActionEvent event) throws NamingException {
    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;	
		a= proxy.findVisualArtById(AdminController.chosenArtworkAdmin.getIdArtwork());
	    VisualArt VisArt =(VisualArt) a ;

	   

	    VisArt.setName(artnamet.getText());
		int lo = Integer.parseInt(le.getText());
		int la = Integer.parseInt(wi.getText());
		VisArt.setLength(lo);
		VisArt.setWidth(la);
		VisArt.setDescription(desct.getText());
		float Q = Float.parseFloat(pricet.getText());
		VisArt.setPrice(Q);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		VisArt.setDateOfOublication(date);

		
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Fanny");
		alert.setHeaderText(null);
		alert.setContentText("Update succes");
		alert.showAndWait();
		
		
		proxy.upadateVisualArt(VisArt);
    }
    
  
	
}