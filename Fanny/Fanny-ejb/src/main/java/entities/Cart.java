package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import entities.Topic;


/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity

public class Cart implements Serializable {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCart;
	
	//private List<Artwork> ArtworkList;
	
	private static final long serialVersionUID = 1L;
	

}
