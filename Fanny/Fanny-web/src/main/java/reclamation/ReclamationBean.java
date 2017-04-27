package reclamation;

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
import entities.Artwork;
import entities.Event;
import entities.Gallery;
import entities.Reclamation;
import entities.User;
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.FeedbackManagmentRemote;
import services.UserManagmentRemote;

@ManagedBean
@SessionScoped //application gourmond en ressource //utiliser une seule fois
public class ReclamationBean {
	
	@EJB
	private FeedbackManagmentRemote feedbackManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	@EJB
	private UserManagmentRemote userManagmentRemote;
	@EJB
	private ArtworkManagemetRemote artworkManagmentRemote;
	@EJB
	private EventManagmentRemote eventManagmentRemote;
	
	
	
	private Reclamation reclamation = new Reclamation();
	
	private List<Reclamation> listReclamations = new ArrayList<Reclamation>();
	private List<Event> listEvents = new ArrayList<Event>();
	
	
	
	public List<Event> getListEvents() {
		return listEvents;
	}

	public void setListEvents(List<Event> listEvents) {
		this.listEvents = listEvents;
	}

	@PostConstruct
	public void initialization() {
		listReclamations = feedbackManagmentRemote.getAllReclamation();
		
		listEvents = eventManagmentRemote.getAllEvents();
		
	}
	
	public String addReclamation(){
		String navTo="";
		
		
	    User uA = userManagmentRemote.findById(1);
		reclamation.setUser(uA);
		
	    Artwork uG = artworkManagmentRemote.findById(1);
		reclamation.setArtwork(uG);
		reclamation.setHandle(0);
		Date date = new Date();
		reclamation.setDate(date);
		
		feedbackManagmentRemote.addReclamation(reclamation);
		listReclamations = feedbackManagmentRemote.getAllReclamation();
		reclamation= new Reclamation();
		return navTo;
	}
	
	public String doHandle(Reclamation reclamation){
		String navTo="";
		reclamation.setHandle(1);
		feedbackManagmentRemote.updateReclamation(reclamation);
		listReclamations = feedbackManagmentRemote.getAllReclamation();
		return navTo;
	}
	
	public String directionDisplay(){
		
		String navTo="";
		navTo="displayreclamation?faces-redirect=true";
		return navTo;
	}



	public Reclamation getReclamation() {
		return reclamation;
	}



	public void setReclamation(Reclamation reclamation) {
		this.reclamation = reclamation;
	}



	public List<Reclamation> getListReclamations() {
		return listReclamations;
	}



	public void setListReclamations(List<Reclamation> listReclamations) {
		this.listReclamations = listReclamations;
	}

	
	
	
	

}
