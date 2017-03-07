package entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//Ines

@Entity
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
}
