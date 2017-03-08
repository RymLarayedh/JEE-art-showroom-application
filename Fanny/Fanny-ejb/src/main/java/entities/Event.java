package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {
//rym//Aymen
	   
	@Id
	private int idEvent;
	private String title;
	private String description;
	private Date dateBegin;
	private Date dateEnd;
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Gallery gallery;
	@ManyToOne
	private Artist artist;
	@OneToMany(mappedBy="event")
	private List<EventUser>listEventUser;

	public Event() {
		super();
	}   
	public int getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateBegin() {
		return dateBegin;
	}
	public void setDateBegin(Date dateBegin) {
		this.dateBegin = dateBegin;
	}
	public Date getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}
	public Gallery getGallery() {
		return gallery;
	}
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	public List<EventUser> getListEventUser() {
		return listEventUser;
	}
	public void setListEventUser(List<EventUser> listEventUser) {
		this.listEventUser = listEventUser;
	}
	
	
   
}
