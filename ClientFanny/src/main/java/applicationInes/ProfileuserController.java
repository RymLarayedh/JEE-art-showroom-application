/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationInes;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Category;
import entities.Music;
import entities.User;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import services.ForumManagementRemote;
import services.UserManagmentRemote;

/**
 * FXML Controller class
 *
 * @author Oussamabhhh
 */
public class ProfileuserController implements Initializable {

    @FXML
    private AnchorPane magicbar;
    @FXML
    private ImageView myimage;
    @FXML
    private ImageView magic2;
    @FXML
    private Text myname;
    @FXML
    private Text mylastname;
    @FXML
    private Button profil;
    @FXML
    private Button message;
    @FXML
    private ImageView magic;
    @FXML
    private Button home;
   
    @FXML
    private Button ownspace;
    @FXML
    private Button visuala;
    @FXML
    private Button musicb;
    @FXML
    private Button eventb;
    @FXML
    private Button tunisianc;
	@FXML
	private AnchorPane MusicPane;
	@FXML
	private TableView<Music> tableMusic;

	@FXML
	private TableColumn<Music, byte[]> picture;
	@FXML
	private TableColumn<Music,String> user;
	@FXML
	private TableColumn<Music,String> name;
	
	ObservableList<Music> dataMusic = FXCollections.observableArrayList();

	

	InitialContext ctx;
	public static ForumManagementRemote forumManagement;
	public static UserManagmentRemote userManagement;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        magicbar.setVisible(false);
        MusicPane.setVisible(false);
        
    	try {
			ctx = new InitialContext();
			forumManagement = (ForumManagementRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/ForumManagement!services.ForumManagementRemote");
			userManagement = (UserManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			Category p = new Category();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }    

  
    @FXML
    private void profil(ActionEvent event) {
    }

    @FXML
    private void message(ActionEvent event) {
    }


    @FXML
    private void home(ActionEvent event) {
    }

   

    
     public void affichermagicbar() {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            -85.0, 320.0,
            85.0, 320.0,
            75.0, 320.0,
            85.0, 320.0,
            80.0, 320.0,
            85.0, 320.0
        });
        PathTransition transation = new PathTransition();
        transation.setNode(magicbar);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();
    }
      public void cachermagicbar() {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            85.0, 320.0,
            -85.0, 320.0,});
        PathTransition transation = new PathTransition();
        transation.setNode(magicbar);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();
    }
        public void afficherbouton(Button x, Button y, Button z) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 52.0
        });

        PathTransition transation = new PathTransition();
        transation.setNode(x);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();

        Polyline polyline2 = new Polyline();
        polyline2.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 92.0,});
        PathTransition transation2 = new PathTransition();
        transation2.setNode(y);
        transation2.setDuration(Duration.seconds(2));
        transation2.setPath(polyline2);
        transation2.play();

        Polyline polyline3 = new Polyline();
        polyline3.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 132.0,});
        PathTransition transation3 = new PathTransition();
        transation3.setNode(z);
        transation3.setDuration(Duration.seconds(2));
        transation3.setPath(polyline3);
        transation3.play();

    }

    public void cacherbouton(Button x, Button y, Button z) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            100.0, 52.0,
            100.0, 20.0
        });

        PathTransition transation = new PathTransition();
        transation.setNode(x);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();

        Polyline polyline2 = new Polyline();
        polyline2.getPoints().addAll(new Double[]{
            100.0, 92.0,
            100.0, 20.0,});
        PathTransition transation2 = new PathTransition();
        transation2.setNode(y);
        transation2.setDuration(Duration.seconds(2));
        transation2.setPath(polyline2);
        transation2.play();

        Polyline polyline3 = new Polyline();
        polyline3.getPoints().addAll(new Double[]{
            100.0, 132.0,
            100.0, 20.0,});
        PathTransition transation3 = new PathTransition();
        transation3.setNode(z);
        transation3.setDuration(Duration.seconds(2));
        transation3.setPath(polyline3);
        transation3.play();
    }

    @FXML
    private void magic(MouseEvent event) {
        magicbar.setVisible(true);
        affichermagicbar();
        magic.setVisible(false);
    }

    @FXML
    private void magic2(MouseEvent event) {
        magic.setVisible(false);
        cachermagicbar();
        magic.setVisible(true);
    }

    @FXML
    private void Myownspace(ActionEvent event) {
    }

    @FXML
    private void visualart(ActionEvent event) {
    }

   

    @FXML
    private void Event(ActionEvent event) {
    }

    @FXML
    private void Tunisiancraft(ActionEvent event) {
    }
    
    /*******************************************************************************************************************/
    /**********************************************************Ines ***************************************************/
    @FXML
    private void music(ActionEvent event) {
        MusicPane.setVisible(true);
        displayMusic();

    }
    
    public void displayMusic()
    {
    	tableMusic.getItems().clear();
		List<Music> listMusic = forumManagement.findAllMusic();
		for (Music m : listMusic) {
			dataMusic.add(m);
		}
		//System.out.println(dataMusic);
		//picture.setCellValueFactory(new PropertyValueFactory<User, byte[]>("picutre"));
		
//		picture.setCellValueFactory(new Callback<CellDataFeatures<Music, byte[]>, ObservableValue<String>>() {
//
//			@Override
//			public ObservableValue<String> call(CellDataFeatures<Music, byte[]> param) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//			
//		});
		
		
//		  picture.setCellValueFactory(new PropertyValueFactory("album"));
//	        picture.setPrefWidth(200); 
//	        picture.setCellFactory(new Callback<TableColumn<Music,byte[]>,TableCell<Music,byte[]>>(){        
//	            @Override
//	            public TableCell<Music, byte[]> call(TableColumn<Music, byte[]> param) {                
//	                TableCell<Music, byte[]> cell = new TableCell<Music, byte[]>(){
//	                    @Override
//	                    public void updateItem(byte[] item, boolean empty) {                        
//	                        if(item!=null){                            
//	                            HBox box= new HBox();
//	                            box.setSpacing(10) ;
//	                            VBox vbox = new VBox();
//	                            vbox.getChildren().add(new Label(item.getArtist()));
//	                            vbox.getChildren().add(new Label(item.getAlbum())); 
//
//	                            ImageView imageview = new ImageView();
//	                            imageview.setFitHeight(50);
//	                            imageview.setFitWidth(50);
//	                            imageview.setImage(new Image(MusicTable.class.getResource("img").toString()+"/"+item.getFilename())); 
//
//	                            box.getChildren().addAll(imageview,vbox); 
//	                            //SETTING ALL THE GRAPHICS COMPONENT FOR CELL
//	                            setGraphic(box);
//	                        }
//	                    }
//	                };
//	                System.out.println(cell.getIndex());               
//	                return cell;
//	            }
//	            
//	        }); 
	        
		
		name.setCellValueFactory(new PropertyValueFactory<Music, String>("name"));
		user.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Music, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
		tableMusic.setItems(dataMusic);
    	
    }
}
