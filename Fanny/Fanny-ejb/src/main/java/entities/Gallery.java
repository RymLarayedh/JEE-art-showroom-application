package entities;

import entities.User;
import java.io.Serializable;
import java.lang.String;
import java.util.List;

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
	// private List<>
	private static final long serialVersionUID = 1L;
	@OneToMany
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

}
