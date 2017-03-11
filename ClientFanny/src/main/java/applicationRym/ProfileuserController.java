/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationRym;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.sun.javafx.collections.MappingChange.Map;

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
import services.UserManagmentRemote;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
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
	//*****
	@FXML
    private TableView<EventUser> tableP;
    @FXML
    private TableColumn<EventUser, String> usernameP;
    @FXML
    private TableColumn<EventUser, String> firstNameP;
    @FXML
    private TableColumn<EventUser, String> lastNameP;
    @FXML
    private TableColumn<EventUser, String> emailP;
    ObservableList<EventUser> dataEventUser = FXCollections.observableArrayList();
    
    @FXML
    private ImageView yes;
    @FXML
    private Label yesLabel;
    @FXML
    private ImageView no;
    @FXML
    private Label noLabel;

	//*****
    @FXML
    private Label eventMax1;
    @FXML
    private Label eventMax2;
    @FXML
    private Label eventMax3;
    @FXML
    private Label nbrMax1;
    @FXML
    private Label nbrMax2;
    @FXML
    private Label nbrMax3;
    @FXML
    private ImageView imgMax1;
    @FXML
    private ImageView imgMax2;
    @FXML
    private ImageView imgMax3;
    
    //*****
    @FXML
    private AnchorPane EventsPane;
    private static Event GlobalPTP;
    
    //***
    @FXML
    private Label bodyLabel;
    @FXML
    private Label dateBeginLabel;
    @FXML
    private Label dateBeginLabel1;
	
    

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        magicbar.setVisible(false);
        EventsPane.setVisible(false);
        yes.setVisible(false);
        yesLabel.setVisible(false);
        no.setVisible(false);
        noLabel.setVisible(false);
       
    }    

  
    @FXML
    private void profil(ActionEvent event) {
    	EventsPane.setVisible(false);
    }

    @FXML
    private void message(ActionEvent event) {
    	EventsPane.setVisible(false);
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
    	EventsPane.setVisible(false);
    }

    @FXML
    private void visualart(ActionEvent event) {
    	EventsPane.setVisible(false);
    }

    @FXML
    private void music(ActionEvent event) {
    	EventsPane.setVisible(false);
    }

    @FXML
    private void Event(ActionEvent event) throws NamingException {
    	yes.setVisible(false);
        yesLabel.setVisible(false);
        no.setVisible(false);
        noLabel.setVisible(false);
    	EventsPane.setVisible(true);
    	remplirEvents();
    	remplirEvents1();
    	updateMaxEvents();
    	
    }
    public void updateMaxEvents() throws NamingException{
    	eventMax1.setText("");
    	eventMax2.setText("");
    	eventMax3.setText("");
    	nbrMax1.setText("");
    	nbrMax2.setText("");
    	nbrMax3.setText("");
    	
    	InitialContext ctx = new InitialContext();
    	Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
		EventManagmentRemote proxy = (EventManagmentRemote) objet;
		List<Event> au = proxy.getAllEvents();	
		InitialContext ctxEU = new InitialContext();
		Object objetEU = ctxEU.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxyEU = (EventUserManagmentRemote) objetEU;	
    	
    	//********************
    	List<Event> EventBySize =new ArrayList<Event>();
    	List<EventUser> Leu = new ArrayList<EventUser>();
    	List<Event> EventBySize2 = new ArrayList<Event>();
//        for (Event e : au ){
//        	Leu = proxyEU.findByEventId(e.getIdEvent());
//        	for (EventUser eventUser : Leu) {
//    			if(eventUser.getStatus()==1){
//    				EventBySize.add(e);
//    				//EventBySize.add(proxy.findEventById(eventUser.getEvent().getIdEvent()));
//    			}
//    		}  
//        }
//        
//        System.out.println(EventBySize.get(1).getIdEvent());
    	EventBySize2=proxy.getAllEvents();
    	
          for (Event event : EventBySize2) {
        	  int t=0;
			Leu=event.getListEventUser();
			for (EventUser eventUser : Leu) {
				if(eventUser.getStatus()==1){
					
					t=t+1;
				}
		}
			System.out.println(t);
			EventBySize.add(event);
			System.out.println();
          
		}
        //************************
       
        //*************************
        
		Collections.sort(EventBySize, new Comparator<Event>() {
	        public int compare(Event o1, Event o2) {
	        	List<EventUser> aaa = new ArrayList<EventUser>();
	        	for (EventUser eu : o2.getListEventUser()) {
					if(eu.getStatus()==1){
						aaa.add(eu);
					}
				}
	        	List<EventUser> aaa2 = new ArrayList<EventUser>();
	        	for (EventUser eu : o1.getListEventUser()) {
					if(eu.getStatus()==1){
						aaa2.add(eu);
					}
				}
	            return aaa.size() - aaa2.size();
	        }
	    });
		if(EventBySize.size()>=3)
		{
			eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
				      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(0).getGallery().getUsername());
			eventMax2.setText("Title = "+EventBySize.get(1).getTitle()+"\nArtist = "
				      +EventBySize.get(1).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(1).getGallery().getUsername());
			
			eventMax3.setText("Title = "+EventBySize.get(2).getTitle()+"\nArtist = "
				      +EventBySize.get(2).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(2).getGallery().getUsername());
			
			List<EventUser> aaab = new ArrayList<EventUser>();
        	for (EventUser eu : EventBySize.get(0).getListEventUser()) {
				if(eu.getStatus()==1){
					aaab.add(eu);
				}
			}
        	
        	List<EventUser> aaab2 = new ArrayList<EventUser>();
        	for (EventUser eu : EventBySize.get(1).getListEventUser()) {
				if(eu.getStatus()==1){
					aaab2.add(eu);
				}
			}
        	
        	List<EventUser> aaab3 = new ArrayList<EventUser>();
        	for (EventUser eu : EventBySize.get(2).getListEventUser()) {
				if(eu.getStatus()==1){
					aaab3.add(eu);
				}
			}
        	
			Integer i1=aaab.size();
			nbrMax1.setText(i1.toString());
			Integer i2=aaab2.size();
			nbrMax2.setText(i2.toString());
			Integer i3=aaab3.size();
			nbrMax3.setText(i3.toString());
		}
		if(EventBySize.size()==2)
		{
			eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
				      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(0).getGallery().getUsername());
			eventMax2.setText("Title = "+EventBySize.get(1).getTitle()+"\nArtist = "
				      +EventBySize.get(1).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(1).getGallery().getUsername());
			eventMax3.setText("");
			
			Integer i1=EventBySize.get(0).getListEventUser().size();
			nbrMax1.setText(i1.toString());
			Integer i2=EventBySize.get(1).getListEventUser().size();
			nbrMax2.setText(i2.toString());
		}
		if(EventBySize.size()==1)
		{
			eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
				      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
							+EventBySize.get(0).getGallery().getUsername());
			eventMax2.setText("");
			eventMax3.setText("");
			Integer i1=EventBySize.get(0).getListEventUser().size();
			nbrMax1.setText(i1.toString());
		}	
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
            //******************************change***************************
            List<EventUser> abc=new ArrayList<EventUser>();
            for (EventUser e : proxy.findByUserId(1) ){
                if(e.getStatus()==1)
                {
                	abc.add(e);
                }
            }
            for (EventUser e : abc ){
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
    private void detailsMyEvents(MouseEvent event) {
    	
    	EventUser PTP = eventsPane1.getSelectionModel().getSelectedItem();
        if (PTP != null) {
        	yes.setVisible(true);
        	yesLabel.setVisible(true);
        	no.setVisible(false);
        	noLabel.setVisible(false);
        	
            this.selected = 1;
            remplirTableParticipant(PTP.getEvent());
            usernameArtist.setText(PTP.getEvent().getArtist().getUsername());
            usernameGallery.setText(PTP.getEvent().getGallery().getUsername());
            //*****************NBR Participant********************
            List<EventUser> dFinal = new ArrayList<EventUser>();
            List<EventUser> d=PTP.getEvent().getListEventUser(); 
            for (EventUser eventUser : d) {
				if(eventUser.getStatus()==1){
					dFinal.add(eventUser);
				}
			}
            Integer iFinal= dFinal.size();
            nbrParticipant.setText(iFinal.toString()+" participants");
            //*****************************************************
        } else {
            this.selected = 0;
            yes.setVisible(false);
        	yesLabel.setVisible(false);
        	no.setVisible(false);
        	noLabel.setVisible(false);
        }
    }
    @FXML
    private void detailsEvents(MouseEvent event) {
		ObservableList<EventUser> eventUSelected, allEvent;
        allEvent = tableP.getItems();
        allEvent.clear();
    	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
        if (PTP != null) {
        	remplirTableParticipant(PTP);
        	Boolean test =false;
        	
        	
        	for (EventUser e : PTP.getListEventUser()) {
        		//*******************change***************
				if ((e.getUser().getIdUser()==1)&&(e.getStatus()==1)){
					test =true;	
				}
			}
        	if (test ==true){
        		yes.setVisible(true);
            	yesLabel.setVisible(true);
            	no.setVisible(false);
            	noLabel.setVisible(false);
        	}
        	else{
        		yes.setVisible(false);
            	yesLabel.setVisible(false);
            	no.setVisible(true);
            	noLabel.setVisible(true);
        	}
            this.selected = 1;
            
            usernameArtist.setText(PTP.getArtist().getUsername());
            usernameGallery.setText(PTP.getGallery().getUsername()+"\n"+PTP.getGallery().getAddress());
            
            dateBeginLabel.setText(PTP.getDateBegin().toString());
            dateBeginLabel1.setText(PTP.getDateEnd().toString());
            bodyLabel.setText(PTP.getDescription());
            
//            Integer d=PTP.getListEventUser().size(); 
//            nbrParticipant.setText(d.toString());
            
        	//********* NBR Participant ******************
            List<EventUser> dFinal = new ArrayList<EventUser>();
            List<EventUser> d=PTP.getListEventUser(); 
            for (EventUser eventUser : d) {
    			if(eventUser.getStatus()==1){
    				dFinal.add(eventUser);
    			}
    		}
            Integer iFinal= dFinal.size();
            nbrParticipant.setText(iFinal.toString()+" participants");
            GlobalPTP=PTP;
        } else {
            this.selected = 0;
        }
    }
    public void remplirTableParticipant(Event ptp){
    		Integer idEvent=ptp.getIdEvent();
    		try {
    		InitialContext ctx = new InitialContext();
    		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
        	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;	
    		ObservableList<EventUser> eventUSelected, allEvent;
            allEvent = tableP.getItems();
            allEvent.clear();
            List<EventUser> aEU = new ArrayList<EventUser>();
            for (EventUser eventUser : proxy.findByEventId(idEvent)) {
            	if(eventUser.getStatus()==1){
            		aEU.add(eventUser);
            	}	
			}
            for (EventUser e : aEU) {
                dataEventUser.add(e);
            }
            
            usernameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

    			@Override
    			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
    				// TODO Auto-generated method stub
    				return new SimpleStringProperty(param.getValue().getUser().getUsername());
    			}

    		});
            firstNameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

    			@Override
    			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
    				// TODO Auto-generated method stub
    				return new SimpleStringProperty(param.getValue().getUser().getFirstName());
    			}

    		});
            lastNameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

    			@Override
    			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
    				// TODO Auto-generated method stub
    				return new SimpleStringProperty(param.getValue().getUser().getLastName());
    			}

    		});
            emailP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

    			@Override
    			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
    				// TODO Auto-generated method stub
    				return new SimpleStringProperty(param.getValue().getUser().getEmail());
    			}

    		});

            tableP.setItems(dataEventUser);
    		
    		} catch (NamingException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}	
    	
    }
    public void updatenbrParticipant(){
    	//*******ryyym
    	
    	//********* NBR Participant ******************
        List<EventUser> dFinal = new ArrayList<EventUser>();
        System.out.println("********"+GlobalPTP.getIdEvent());
        List<EventUser> d=GlobalPTP.getListEventUser(); 
        for (EventUser eventUser : d) {
			if(eventUser.getStatus()==1){
				dFinal.add(eventUser);
			}
		}
        Integer iFinal= dFinal.size();
        nbrParticipant.setText(iFinal.toString()+" participants");
        //***********************************
    	
    }
    @FXML
    private void yesParticipate(MouseEvent event) throws NamingException {
    	
    	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
        if (PTP != null) {
            this.selected = 1;
            InitialContext ctx = new InitialContext();
    		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
        	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;
        	//*************************change****************
        	
        	if(proxy.findByUserEventId(1, PTP.getIdEvent())==null){
        		EventUser eu = new EventUser();
            	//eu.setEvent(PTP);
            	//*******************change*********
            	InitialContext ctxU = new InitialContext();	
        		Object object = ctxU.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
        		UserManagmentRemote proxyU = (UserManagmentRemote) object;
        		User u = proxyU.findById(1);
            	//eu.setUser(u);
            	
            	EventUserID euID = new EventUserID();
            	euID.setIdEventPK(PTP.getIdEvent());
            	euID.setIdUserPK(1);
            	eu.setEtudiantCoursID(euID);
            	eu.setStatus(1);
            	proxy.addEventUser(eu);
        	}
        	
        	else{
        		//************change********
        		EventUser eu = proxy.findByUserEventId(1, PTP.getIdEvent());
            	eu.setStatus(1);
            	proxy.updateEventUser(eu);
        			
        	}       	
        	//************************************
        	
        	yes.setVisible(true);
        	yesLabel.setVisible(true);
        	no.setVisible(false);
        	noLabel.setVisible(false);
        	remplirEvents();
        	remplirEvents1();
        	updateMaxEvents();
        	updatenbrParticipant();
        } else {
            this.selected = 0;
        }
        
    	
    }
    @FXML
    private void noParticipate(MouseEvent event) throws NamingException {
//    	eventMax1.setText("");
//    	eventMax2.setText("");
//    	eventMax3.setText("");
    	
    	yes.setVisible(false);
    	yesLabel.setVisible(false);
    	no.setVisible(true);
    	noLabel.setVisible(true);
    	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
    	 if (PTP != null) {
    	InitialContext ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;
    	//********change
    	EventUser eu = proxy.findByUserEventId(1, PTP.getIdEvent());
    	eu.setStatus(9);
    	proxy.updateEventUser(eu);
//    	ObservableList<Event> eventSelected, allEvent;
//        allEvent = eventsPane.getItems();
//        allEvent.clear();
    	remplirEvents();
//    	ObservableList<EventUser> eventSelected1, allEvent1;
//        allEvent1 = eventsPane1.getItems();
//        allEvent1.clear();
    	remplirEvents1();
    	updateMaxEvents();
    	updatenbrParticipant();
    	 } 
    	
    	 else {
    	 this.selected = 0;
     		}
    	 EventUser PTP2 = eventsPane1.getSelectionModel().getSelectedItem();
    	 if (PTP2 != null) {
    	InitialContext ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxy = (EventUserManagmentRemote) objet;
    	//********change
    	
    	PTP2.setStatus(9);
    	proxy.updateEventUser(PTP2);
//    	ObservableList<Event> eventSelected, allEvent;
//        allEvent = eventsPane.getItems();
//        allEvent.clear();
    	remplirEvents();
//    	ObservableList<EventUser> eventSelected1, allEvent1;
//        allEvent1 = eventsPane1.getItems();
//        allEvent1.clear();
    	remplirEvents1();
    	updateMaxEvents();
    	updatenbrParticipant();
    	 } 
    	
    	 else {
    	 this.selected = 0;
     		}
    }
}
