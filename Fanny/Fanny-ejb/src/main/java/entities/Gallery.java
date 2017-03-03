package entities;

import entities.User;
import java.io.Serializable;
import java.lang.String;
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
	//private List<>
	private static final long serialVersionUID = 1L;
	@OneToOne(mappedBy="gallery")
	private Event event;

	public Gallery() {
		super();
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
