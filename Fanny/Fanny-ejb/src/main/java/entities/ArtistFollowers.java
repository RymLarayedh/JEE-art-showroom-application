package entities;

import java.io.Serializable;
import javax.persistence.*;




/**
 * Entity implementation class for Entity: ArtistFollowers
 *
 */
@Entity

public class ArtistFollowers implements Serializable {

	@EmbeddedId
	private ArtistFollowersID artistFollowersId;
	@ManyToOne
	@JoinColumn(name="idUserPK",insertable=false,updatable=false)
	private User user;
	@ManyToOne
	@JoinColumn(name="idArtistPK",insertable=false,updatable=false)
	private Artist artist;
	private static final long serialVersionUID = 1L;

	public ArtistFollowers() {
		super();
	}

	public ArtistFollowersID getArtistId() {
		return artistFollowersId;
	}

	public void setArtistId(ArtistFollowersID artistFollowersId) {
		this.artistFollowersId = artistFollowersId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	
	
   
}
