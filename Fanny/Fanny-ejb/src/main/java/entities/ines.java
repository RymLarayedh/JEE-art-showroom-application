package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ines
 *
 */
@Entity

public class ines implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idEvent;

	
	private static final long serialVersionUID = 1L;

	public ines() {
		super();
	}
   
}
