package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artwork
 *
 */
@Entity

public class Artwork implements Serializable {

	  //this line is added by manel 
	@Id
	private int idArtwork;
	private static final long serialVersionUID = 1L;
	@OneToOne(mappedBy="user")
	private Reclamation reclamation;

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
