package entities;

import entities.User;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Gallery
 *
 */
@Entity
@DiscriminatorValue("GALLERY")
public class Gallery extends User implements Serializable {

	private float Surface;
	private String Description;
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	private Set<Picture> PhotoAlbum;
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Event>listEvent;

	public Gallery() {
		super();
	}

	public Gallery(User user) {
		super(user);
	}

	public float getSurface() {
		return this.Surface;
	}

	public void setSurface(float Surface) {
		this.Surface = Surface;
	}

	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public Set<Picture> getPhotoAlbum() {
		return PhotoAlbum;
	}

	public void setPhotoAlbum(Set<Picture> photoAlbum) {
		PhotoAlbum = photoAlbum;
	}




}
