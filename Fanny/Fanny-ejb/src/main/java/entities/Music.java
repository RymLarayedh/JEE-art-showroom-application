package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Music
 *
 */
@Entity
@DiscriminatorValue("MUSIC")

public class Music extends Artwork implements Serializable {
	
	private float duration;

	
	private static final long serialVersionUID = 1L;

	public Music() {
		super();
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	
   
}
