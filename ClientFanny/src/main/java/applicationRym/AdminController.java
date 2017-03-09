/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationRym;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.Gallery;
import entities.Reclamation;
import entities.User;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
import services.FeedbackManagmentRemote;
import services.UserManagmentRemote;

/**
 * FXML Controller class
 *
 * @author rymlarayedh
 */

public class AdminController implements Initializable {
	static int idGa;
	static int idAr;
	InitialContext ctx;
	private int selected;
	//rrt
	@FXML
    private AnchorPane RymsPane;
    @FXML
    private AnchorPane addEventPane;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXDatePicker dateBegin;
    @FXML
    private JFXDatePicker dateEnd;
    @FXML
    private ComboBox<String> galleryList;
    @FXML
    private ComboBox<String> artistList;
    @FXML
    private Label details;
    @FXML
    private ImageView profilePicture;
    @FXML
    private Label userLoggedInName;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private AnchorPane displayReclamation;
    @FXML
	private TableView<Reclamation> tableReclamation;
	@FXML
	private TableColumn<Reclamation,String> artwork;
	@FXML
	private TableColumn<Reclamation,String> user;
	@FXML
	private TableColumn<Reclamation,String> body;
	@FXML
	private TableColumn<Reclamation,Date> date;
	@FXML
	private TableColumn<Reclamation,Integer> degree;
	
	ObservableList<Reclamation> dataReclamation = FXCollections.observableArrayList();
    
    //******
    @FXML
    private AnchorPane displayEventPane;
    @FXML
	private TableView<Event> tableEvent;
	@FXML
	private TableColumn<Event,String> title1;
	@FXML
	private TableColumn<Event,String> description1;
	@FXML
	private TableColumn<Event, Date> dateBegin1;
	@FXML
	private TableColumn<Event, Date> dateEnd1;
	@FXML
	private TableColumn<Event, String> gallery;
	@FXML
	private TableColumn<Event, String> artist;
    
	ObservableList<Event> dataEvent = FXCollections.observableArrayList();
	private Object id;
	
	//*********
	@FXML
    private AnchorPane participantPane;
    @FXML
    private TableView<EventUser> tableParticipant;
    @FXML
    private TableColumn<EventUser, String> usernameParticipant;
    @FXML
    private TableColumn<EventUser, String> emailParticipant;
    ObservableList<EventUser> dataEventUser = FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	addEventPane.setVisible(false);
    	displayReclamation.setVisible(false);
    	displayEventPane.setVisible(false);
    	participantPane.setVisible(false);
    	
    	
    }   
    @FXML
    private void ShowAllUsers(ActionEvent event) {
    }

    @FXML
    private void displayEvents(ActionEvent event) {
    	displayEventPane.setVisible(true);
    	addEventPane.setVisible(false);
    	displayReclamation.setVisible(false);
    	remplirTableEvent();
    	
    }

    @FXML
    private void addEvents(ActionEvent event) {
    	addEventPane.setVisible(true);
    	try {
			ctx = new InitialContext();
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxy = (UserManagmentRemote) object;
		
		//Remplir Combobox Gallery
    	ObservableList<String> LocG = FXCollections.observableArrayList();
    	List<Gallery> Lg = proxy.getAllGalleries();  
        for (Gallery X : Lg) {
        	String s=X.getUsername();
            LocG.add(s);
        }
        galleryList.setItems(LocG);
        
        //Remplir Combobox Artist
        ObservableList<String> LocA = FXCollections.observableArrayList();
    	List<Artist> La = proxy.getAllArtists();
        for (Artist X : La) {
        	String s=X.getUsername();
            LocA.add(s);
        }
        artistList.setItems(LocA);

       /* ObservableList<User> data = FXCollections.observableArrayList();
       // CityService CIS = new CityService();
        List<Artist> Lt = proxy.getAllArtists();
        for (Artist X : Lt) {
            data.add(X);
        }*/
       // Citycl.setCellValueFactory(new PropertyValueFactory<CityT, String>("Countrycl"));
       // Countrycl.setCellValueFactory(new PropertyValueFactory<CityT, String>("Citycl"));
       // tableid.setItems(data);

       /* tableid.setRowFactory(tv -> {
            TableRow<CityT> row = new TableRow<>();
            row.setOnMouseClicked(event1 -> {
                if (event1.getClickCount() == 1 && (!row.isEmpty())) {
                    CityT rowData = row.getItem();
                    currentID = rowData.getId();
                    Citytf.setText(rowData.getCitycl());

                }
            });
            return row;
        });*/
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void addEvent(ActionEvent event) throws NamingException {
    	addEventPane.setVisible(true);
    	ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxy = (EventManagmentRemote) objet;	
		Event e= new Event();
		e.setTitle(title.getText());
		e.setDescription(description.getText());
		
		//insert gallery and Artist
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxyU = (UserManagmentRemote) object;
		Gallery g =new Gallery();
		g=(Gallery) proxyU.findById(idGa);
		e.setGallery(g);
		Artist a =new Artist();
		a=(Artist) proxyU.findById(idAr);
		e.setArtist(a);
		
	  //Convert Local Date to Date Begin
		LocalDate localDateBegin =dateBegin.getValue();
		Instant instant1 = Instant.from(localDateBegin.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateBegin = Date.from(instant1);
	    e.setDateBegin(dateDateBegin);
	  //Convert Local Date to Date End
	    LocalDate localDateEnd =dateEnd.getValue();
		Instant instant2 = Instant.from(localDateEnd.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateDateEnd = Date.from(instant2);
	    e.setDateEnd(dateDateEnd);
	  //Convert Date Now
	    LocalDate localNow = LocalDate.now();
	    Instant instantb = Instant.from(localNow.atStartOfDay(ZoneId.systemDefault()));
		java.util.Date dateNow = Date.from(instantb);
		
	  //Condition chosen Date
		if((dateNow.before(dateDateBegin))&&(dateDateBegin.before(dateDateEnd))){
			proxy.addEvent(e);
		}
		else{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Please check the Begining Date and the End Date");
			alert.showAndWait();	
		}    
    }

    @FXML
    private void chooseGallery(ActionEvent event) {
    	try {
			ctx = new InitialContext();
		
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxy = (UserManagmentRemote) object;
    	String chosenGallery = galleryList.getValue();
        
        Gallery Ga = new Gallery();
        Ga = (Gallery) proxy.findByUsername(chosenGallery);
        idGa= Ga.getIdUser();

        details.setText("First Name = "+Ga.getFirstName()+"\n"+
        		"Last Name = "+Ga.getLastName()+"\n"+
        		"Surface = "+Ga.getSurface()+"m2\n"+
        		"Email = "+Ga.getEmail());
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
      
    }

    @FXML
    private void chooseArtist(ActionEvent event) {
    	try {
			ctx = new InitialContext();
		
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote proxy = (UserManagmentRemote) object;
    	String chosenArtist = artistList.getValue();
        
        Artist Ar = new Artist();
        Ar = (Artist) proxy.findByUsername(chosenArtist);
        idAr= Ar.getIdUser();
        details.setText("First Name = "+Ar.getFirstName()+"\n"+
        		"Last Name = "+Ar.getLastName()+"\n"+
        		"Surface = "+Ar.getBio()+"\n"+
        		"Email = "+Ar.getEmail());

    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @FXML
    private void showProfile(MouseEvent event) {
    }

    @FXML
    private void closeFanny(ActionEvent event) {
    }

    @FXML
    private void showSettingsScreen(ActionEvent event) {
    }

    @FXML
    private void showAboutFanny(ActionEvent event) {
    }
    
   
    @FXML
    private void allEvents(Event event) {
    }

    @FXML
    private void comingEvents(Event event) {
    }

    @FXML
    private void pastEvents(Event event) {
    }
    

    

   

    @FXML
    private void displayReclamation(ActionEvent event) {
    	
    	displayReclamation.setVisible(true);
    	remplirTableReclamation();
    }
    
    private void remplirTableReclamation(){
		try {
			ctx = new InitialContext();
		
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/FeedbackManagment!services.FeedbackManagmentRemote");
		FeedbackManagmentRemote proxy = (FeedbackManagmentRemote) objet;	
		ObservableList<Reclamation> eventSelected, allRec;
        allRec = tableReclamation.getItems();
       // allRec.clear();
        for (Reclamation e : proxy.getAllReclamation()) {
            dataReclamation.add(e);
           // System.out.println(data);
        }
        artwork.setCellValueFactory(new Callback<CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Reclamation, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getArtwork().getName());
			}

		});  
        user.setCellValueFactory(new Callback<CellDataFeatures<Reclamation, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Reclamation, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		}); 
        body.setCellValueFactory(new PropertyValueFactory<Reclamation, String>("body"));
        date.setCellValueFactory(new PropertyValueFactory<Reclamation, Date>("date"));
        degree.setCellValueFactory(new PropertyValueFactory<Reclamation, Integer>("degree"));
        tableReclamation.setItems(dataReclamation);
        System.out.println(proxy.getAllReclamation());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}


    @FXML
    private void selectEvent(MouseEvent event) throws NamingException {
    	participantPane.setVisible(true);
        Event PTP = tableEvent.getSelectionModel().getSelectedItem();
        if (PTP != null) {
            this.id = PTP.getIdEvent();
            this.selected = 1;
            remplirTableEventUser(PTP);
        } else {
            this.selected = 0;
        }
    }
    
    	
	public void remplirTableEventUser(Event ptp){
		Integer idEvent=ptp.getIdEvent();
		try {
		ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;	
		ObservableList<EventUser> eventUSelected, allEvent;
        allEvent = tableParticipant.getItems();
        allEvent.clear();
        System.out.println(idEvent);
        for (EventUser e : proxy.findByEventId(idEvent)) {
            dataEventUser.add(e);
        }
        
        usernameParticipant.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
        emailParticipant.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getEmail());
			}

		});

        tableParticipant.setItems(dataEventUser);
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
    
    
    private void remplirTableEvent(){
		try {
			ctx = new InitialContext();
		
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxy = (EventManagmentRemote) objet;	
		ObservableList<Event> eventSelected, allEvent;
        allEvent = tableEvent.getItems();
        allEvent.clear();
        for (Event e : proxy.getAllEvents()) {
            dataEvent.add(e);
        }
        title1.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
        description1.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
        dateBegin1.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateBegin"));
        dateEnd1.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateEnd"));
        gallery.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getGallery().getUsername());
			}

		});
        artist.setCellValueFactory(new Callback<CellDataFeatures<Event, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Event, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getArtist().getUsername());
			}

		});

        tableEvent.setItems(dataEvent);
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

    @FXML
    private void selectReclamation(MouseEvent event) {
    }
    @FXML
    private void deleteEvent(ActionEvent event) throws NamingException {
    	participantPane.setVisible(false);
        if (this.selected == 1) {
            ObservableList<Event> userSelected, allUser;
            allUser = tableEvent.getItems();
            userSelected = tableEvent.getSelectionModel().getSelectedItems();
            Event ptp = tableEvent.getSelectionModel().getSelectedItem();
            userSelected.forEach(allUser::remove);

            ctx = new InitialContext();
    		
    		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
    		EventManagmentRemote proxy = (EventManagmentRemote) objet;
            proxy.deleteEvent(ptp);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Event details has been deleted.");
            alert.showAndWait();
            remplirTableEvent();
           
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No Event selected");
            alert.showAndWait();

        }
    }

    @FXML
    private void selectParticipant(MouseEvent event) {
    	
    }

    
   
   
    
    
}
