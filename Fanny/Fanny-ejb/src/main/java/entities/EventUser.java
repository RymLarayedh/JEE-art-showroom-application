package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class EventUser implements Serializable {

	@EmbeddedId
	private EventUserID eventUserID;
	private int status;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="idUserPK",insertable=false, updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idEventPK",insertable=false, updatable=false)
	private Event event;
	
	
	public EventUserID getEtudiantCoursID() {
		return eventUserID;
	}
	public void setEtudiantCoursID(EventUserID etudiantCoursID) {
		this.eventUserID = etudiantCoursID;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	
	
}
