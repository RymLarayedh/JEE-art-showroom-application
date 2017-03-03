package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArtistFollowersID
 *
 */
@Embeddable

public class ArtistFollowersID implements Serializable {

	private int idUserPK;
	private int idArtistPK;
	private static final long serialVersionUID = 1L;

	public ArtistFollowersID() {
		super();
	}
	
	
	public int getIdUserPK() {
		return idUserPK;
	}


	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
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
		result = prime * result + idUserPK;
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
		ArtistFollowersID other = (ArtistFollowersID) obj;
		if (idArtistPK != other.idArtistPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}
	
	
   
}
