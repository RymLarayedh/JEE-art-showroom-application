package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {

	   
	@Id
	private int idEvent;
	private String title;
	private String description;
	private Date dateBegin;
	private Date dateEnd;
	private static final long serialVersionUID = 1L;
	@OneToOne
	@JoinColumn(name="gallery_fk")
	private Gallery gallery;
	@OneToOne
	@JoinColumn(name="artist_fk")
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
	
   
}
