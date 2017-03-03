package entities;

import entities.User;
import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artist
 *
 */
@Entity
@DiscriminatorValue("ARTIST")
public class Artist extends User implements Serializable {

	private String bio;
	
	@OneToMany(mappedBy="fileds")
	private List<ArtistFields> Lfields;
	@OneToMany(mappedBy="user")
	private List<ArtistFollowers> Followers;
	private static final long serialVersionUID = 1L;
	@OneToOne(mappedBy="artist")
	private Event event;

	public Artist() {
		super();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	

	public List<ArtistFields> getLfields() {
		return Lfields;
	}

	public void setLfields(List<ArtistFields> lfields) {
		Lfields = lfields;
	}

	public List<ArtistFollowers> getFollowers() {
		return Followers;
	}

	public void setFollowers(List<ArtistFollowers> followers) {
		Followers = followers;
	}

	@Override
	public String toString() {
		return "Artist ["+super.toString()+", bio=" + bio + "]";
	}
	
   
}
