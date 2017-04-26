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
import entities.Event;
import entities.Gallery;
import entities.Reclamation;
import entities.User;
import services.EventManagmentRemote;
import services.FeedbackManagmentRemote;
import services.UserManagmentRemote;

@ManagedBean
@SessionScoped //application gourmond en ressource //utiliser une seule fois
public class ReclamationBean {
	
	@EJB
	private FeedbackManagmentRemote feedbackManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	
	
	
	
	private Reclamation reclamation = new Reclamation();
	
	private List<Reclamation> listReclamations = new ArrayList<Reclamation>();
	
	
	
	@PostConstruct
	public void initialization() {
		listReclamations = feedbackManagmentRemote.getAllReclamation();
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
