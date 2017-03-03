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
	private List<Fields> Lfields;
	//private List<User> Followers;
	private static final long serialVersionUID = 1L;

	public Artist() {
		super();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	
	
	public List<Fields> getLfields() {
		return Lfields;
	}

	public void setLfields(List<Fields> lfields) {
		Lfields = lfields;
	}

	@Override
	public String toString() {
		return "Artist ["+super.toString()+", bio=" + bio + "]";
	}


	/*
	public List<User> getFollowers() {
		return Followers;
	}

	public void setFollowers(List<User> followers) {
		Followers = followers;
	}*/
	
   
}
