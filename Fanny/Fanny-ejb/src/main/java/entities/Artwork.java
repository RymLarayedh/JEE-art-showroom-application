package entities;

import java.io.Serializable;
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
