package events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import entities.Artist;
import entities.Event;
import entities.Gallery;
import entities.User;
import services.EventManagmentRemote;
import services.UserManagmentRemote;

@ManagedBean
@SessionScoped //application gourmond en ressource //utiliser une seule fois
public class EventsBean {
	
	@EJB
	private EventManagmentRemote eventManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	
	@EJB
	private UserManagmentRemote userManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	
	
	
	private Event event = new Event();
	private int LoggedIn = 1;
	private String artistString;
	private String galleryString;
	private List<Event> listEvents = new ArrayList<Event>();
	private Boolean formDisplayed = false;
	private Boolean formDetails = false;
	private Date test;
	
	
	
	

	public Date getTest() {
		return test;
	}

	public void setTest(Date test) {
		this.test = test;
	}

	public String addEvent(){
		String navTo="";
		
		
		Integer idA = Integer.valueOf(artistString);
	    User uA = userManagmentRemote.findById(idA);
		event.setArtist((Artist)uA);
		
		Integer idG = Integer.valueOf(galleryString);
	    User uG = userManagmentRemote.findById(idG);
		event.setGallery((Gallery)uG);
		
		eventManagmentRemote.addEvent(event);
		listEvents = eventManagmentRemote.getAllEvents();
		navTo="displayevents?faces-redirect=true";
		System.out.println("iciiii"+test);
		return navTo;
	}
	
	public List<Artist> ListArtist()
	{
		List<Artist> listArtist = new ArrayList<Artist>();
		listArtist = userManagmentRemote.getAllArtists();
		return listArtist;
		
		
		
	}
	
	public List<Gallery> ListGallery()
	{
		List<Gallery> listGallery = new ArrayList<Gallery>();
		listGallery = userManagmentRemote.getAllGalleries();
		return listGallery;
		
	}
public String doCancel(){
		
		String navTo="";
		event = new Event();
		formDisplayed=false;
		return navTo;
	}

public String doUpdate(){
	
	String navTo="";
	eventManagmentRemote.updateEvent(event);
	formDisplayed=false;
	initialization();
	return navTo;
}

public String doDelete(Event event){
	
	String navTo="";
	eventManagmentRemote.deleteEvent(event);
	formDisplayed=false;
	initialization();
	return navTo;
}
	
	
	@PostConstruct
	public void initialization() {
		listEvents = eventManagmentRemote.getAllEvents();
	}

	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getListEvents() {
		return listEvents;
	}

	public void setListEvents(List<Event> listEvents) {
		this.listEvents = listEvents;
	}

	public String getArtistString() {
		return artistString;
	}

	public void setArtistString(String artistString) {
		this.artistString = artistString;
	}

	public String getGalleryString() {
		return galleryString;
	}

	public void setGalleryString(String galleryString) {
		this.galleryString = galleryString;
	}
	public int getLoggedIn() {
		return LoggedIn;
	}
	public void setLoggedIn(int loggedIn) {
		LoggedIn = loggedIn;
	}
	
	public Boolean getFormDisplayed() {
		return formDisplayed;
	}
	public void setFormDisplayed(Boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}

	public Boolean getFormDetails() {
		return formDetails;
	}

	public void setFormDetails(Boolean formDetails) {
		this.formDetails = formDetails;
	}
	
	

}
