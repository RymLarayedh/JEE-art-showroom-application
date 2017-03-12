package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//Ines

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Feedback implements Serializable{
	/**
	 * **
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private int  feedbackId ;
	@ManyToOne
	private User user ;
	@ManyToOne
	private Artwork artwork;
	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Artwork getArtwork() {
		return artwork;
	}
	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}
	
	
}
