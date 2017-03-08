package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
	@EmbeddedId
	private FeedbackId  feedbackId ;
	@ManyToOne
	@JoinColumn(name="userId",insertable=false,updatable=false)
	private User user ;
	@ManyToOne
	@JoinColumn(name="artworkId",insertable=false,updatable=false)
	private Artwork artwork;
	public FeedbackId getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(FeedbackId feedbackId) {
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
