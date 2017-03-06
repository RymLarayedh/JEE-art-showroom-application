package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artwork
 *
 */
@Entity

public class Artwork implements Serializable {

	 
	@Id
	private int idArtwork;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="artwork")
	private List<Feedback> listFeedback;

	public Artwork() {
		super();
	}   
	public int getIdArtwork() {
		return this.idArtwork;
	}

	public void setIdArtwork(int idArtwork) {
		this.idArtwork = idArtwork;
	}
   
}
