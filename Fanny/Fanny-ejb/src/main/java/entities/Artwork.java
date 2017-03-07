package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artwork
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public class Artwork implements Serializable {

	 
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public List<Feedback> getListFeedback() {
		return listFeedback;
	}
	public void setListFeedback(List<Feedback> listFeedback) {
		this.listFeedback = listFeedback;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idArtwork;
	private String Description;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="artwork")
	private List<Feedback> listFeedback;

	public Artwork() {
		super();
	}   
	public int getIdArtwork() {
		return this.idArtwork;
	}

	public void setIdArtwork(int idArtwork) {
		this.idArtwork = idArtwork;
	}
   
}
