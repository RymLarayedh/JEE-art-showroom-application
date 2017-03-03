package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArtistFieldsID
 *
 */
@Embeddable

public class ArtistFieldsID implements Serializable {

	private int idFieldsPK;
	private int idArtistPK;
	private static final long serialVersionUID = 1L;

	public ArtistFieldsID() {
		super();
	}

	public int getIdFieldsPK() {
		return idFieldsPK;
	}

	public void setIdFieldsPK(int idFieldsPK) {
		this.idFieldsPK = idFieldsPK;
	}

	public int getIdArtistPK() {
		return idArtistPK;
	}

	public void setIdArtistPK(int idArtistPK) {
		this.idArtistPK = idArtistPK;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idArtistPK;
		result = prime * result + idFieldsPK;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtistFieldsID other = (ArtistFieldsID) obj;
		if (idArtistPK != other.idArtistPK)
			return false;
		if (idFieldsPK != other.idFieldsPK)
			return false;
		return true;
	}
	
	
	
   
}
