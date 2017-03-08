package entities;

import entities.User;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artist
 *
 */
@Entity
@DiscriminatorValue("ARTIST")
public class Artist extends User implements Serializable {

	private String bio;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "artist")
	private Set<ArtistFields> Lfields;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "artist")
	private Set<ArtistFollowers> Followers;
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Event>listEvent;

	public Artist() {
		super();
	}

	public Artist(User user) {
		super(user);
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public Set<ArtistFields> getLfields() {
		return Lfields;
	}

	public void setLfields(Set<ArtistFields> lfields) {
		Lfields = lfields;
	}

	public Set<ArtistFollowers> getFollowers() {
		return Followers;
	}

	public void setFollowers(Set<ArtistFollowers> followers) {
		Followers = followers;
	}

	@Override
	public String toString() {
		return "Artist [" + super.toString() + ", bio=" + bio + "]";
	}

}
