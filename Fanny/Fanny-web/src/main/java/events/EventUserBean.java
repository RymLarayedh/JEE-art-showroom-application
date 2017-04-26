package events;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
import services.UserManagmentRemote;

@ManagedBean
@SessionScoped
public class EventUserBean {
	
	
	@EJB
	private EventUserManagmentRemote eventUserManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	
	@EJB
	private UserManagmentRemote userManagmentRemote;//on a pas le droit d'instancier dans l ejb erreur null pinter exception
	
	
	
	private Event event = new Event();
	


	private int LoggedIn = 1;
	private List<Event> listMyEvents = new ArrayList<Event>();
	private Boolean displayJoin;
	private Boolean displayUnjoin;
	

	@PostConstruct
	public void initialization() {
		List<EventUser> L= eventUserManagmentRemote.findByUserId(LoggedIn);
		for (EventUser eventUser : L) {
			listMyEvents.add(eventUser.getEvent());
		}
		
		
	}
	public void testJoin(){
		System.out.println("****** bien sur");
		if((eventUserManagmentRemote.findByUserEventId(LoggedIn, event.getIdEvent()) == null)
				|| (eventUserManagmentRemote.findByUserEventId(LoggedIn, event.getIdEvent()).getStatus()==0))
		{
			
			displayJoin=true;
			displayUnjoin=false;
		}
		else {
			displayJoin=false;
			displayUnjoin=true;
		}
	}
	
	public void joined(){
		if(eventUserManagmentRemote.findByUserEventId(LoggedIn, event.getIdEvent())==null)
		{
			EventUser eu = new EventUser();
			EventUserID euID = new EventUserID();
			euID.setIdEventPK(event.getIdEvent());
			euID.setIdUserPK(LoggedIn);
			eu.setEtudiantCoursID(euID);
			eu.setStatus(1);
		  eventUserManagmentRemote.addEventUser(eu);
		}
		else{
			EventUser eu = eventUserManagmentRemote.findByUserEventId(LoggedIn, event.getIdEvent());
			eu.setStatus(1);
			eventUserManagmentRemote.updateEventUser(eu);
		}
		
	}
	public void unjoined(){
		EventUser eu = eventUserManagmentRemote.findByUserEventId(LoggedIn, event.getIdEvent());
		eu.setStatus(0);
		eventUserManagmentRemote.updateEventUser(eu);
	}

	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getListMyEvents() {
		return listMyEvents;
	}

	public void setListMyEvents(List<Event> listMyEvents) {
		this.listMyEvents = listMyEvents;
	}

	
	
	public int getLoggedIn() {
		return LoggedIn;
	}
	public void setLoggedIn(int loggedIn) {
		LoggedIn = loggedIn;
	}


	public Boolean getDisplayJoin() {
		return displayJoin;
	}


	public void setDisplayJoin(Boolean displayJoin) {
		this.displayJoin = displayJoin;
	}


	public Boolean getDisplayUnjoin() {
		return displayUnjoin;
	}


	public void setDisplayUnjoin(Boolean displayUnjoin) {
		this.displayUnjoin = displayUnjoin;
	}
	
	


}
