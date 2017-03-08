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
import entities.Gallery;
import entities.User;

import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.EventManagmentRemote;
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
    private AnchorPane displayEventPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	addEventPane.setVisible(false);
    	
    	
    }   
    @FXML
    private void ShowAllUsers(ActionEvent event) {
    }

    @FXML
    private void displayEvents(ActionEvent event) {
    	
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

    
   
   
    
    
}
