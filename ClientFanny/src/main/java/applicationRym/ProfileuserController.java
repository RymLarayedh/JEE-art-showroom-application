/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationRym;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.animation.PathTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
import entities.Event;
import entities.EventUser;
import entities.User;

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
    
    //***
    @FXML
    private TableView<Event> eventsPane;
    @FXML
    private TableColumn<Event, Date> DateBeginEvents;
    @FXML
    private TableColumn<Event, String> TitleEvents;
	ObservableList<Event> dataEvent = FXCollections.observableArrayList();

    //***
	public static User userLogedIn;
	@FXML
    private TableView<EventUser> eventsPane1;
    @FXML
    private TableColumn<EventUser, Date> DateBeginEvents1;
    @FXML
    private TableColumn<EventUser, String> TitleEvents1;
    ObservableList<EventUser> dataEvent1 = FXCollections.observableArrayList();
	//***
    @FXML
    private Label nbrParticipant;
    @FXML
    private Label usernameArtist;
    @FXML
    private Label usernameGallery;
    //***
	private int selected;
	
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        magicbar.setVisible(false);
       
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
    private void music(ActionEvent event) {
    }

    @FXML
    private void Event(ActionEvent event) throws NamingException {
    	remplirEvents();
    	//remplirEvents1();
    }
    public void remplirEvents() throws NamingException{
    	InitialContext ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxy = (EventManagmentRemote) objet;
		ObservableList<Event> eventSelected, allEvent;
        allEvent = eventsPane.getItems();
        allEvent.clear();
        for (Event e : proxy.getAllEvents()) {
            dataEvent.add(e);
        }
        DateBeginEvents.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateBegin"));
        TitleEvents.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
       
        	eventsPane.setItems(dataEvent);
    	
    }
    public void remplirEvents1() throws NamingException{
    		
    		try {
    		InitialContext ctx = new InitialContext();
    		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
        	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;	
    		ObservableList<EventUser> eventUSelected, allEvent;
            allEvent = eventsPane1.getItems();
            allEvent.clear();
            for (EventUser e : proxy.findByUserId(userLogedIn.getIdUser())) {
                dataEvent1.add(e);
            }
            
            DateBeginEvents1.setCellValueFactory(new Callback<CellDataFeatures<EventUser, Date>, ObservableValue<Date>>() {

    			@Override
    			public ObservableValue<Date> call(CellDataFeatures<EventUser, Date> param) {
    				// TODO Auto-generated method stub
    				return new SimpleObjectProperty<Date>(param.getValue().getEvent().getDateBegin());
    			}

    		});
            TitleEvents1.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

    			@Override
    			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
    				// TODO Auto-generated method stub
    				return new SimpleStringProperty(param.getValue().getEvent().getTitle());
    			}

    		});

            eventsPane1.setItems(dataEvent1);
    		
    		} catch (NamingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		
    	}
    	
    

    @FXML
    private void Tunisiancraft(ActionEvent event) {
    }
    @FXML
    private void complain(ActionEvent event) throws IOException {
    	Parent ReclamationScene = FXMLLoader.load(getClass().getResource("ReclamationAdd.fxml"));
		Scene scene = new Scene(ReclamationScene);
		//scene.getStylesheets().add(getClass().getResource("Gallery.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
    }
    @FXML
    private void detailsEvents(MouseEvent event) {
    	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
        if (PTP != null) {
            this.selected = 1;
            remplirTableParticipant();
            usernameArtist.setText(PTP.getArtist().getUsername());
            usernameGallery.setText(PTP.getGallery().getUsername());
            Integer d=PTP.getListEventUser().size(); 
            nbrParticipant.setText(d.toString());
        } else {
            this.selected = 0;
        }
    }
    public void remplirTableParticipant(){
    	
    }

}
