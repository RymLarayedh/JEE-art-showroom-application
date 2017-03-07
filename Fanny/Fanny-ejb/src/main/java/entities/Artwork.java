package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artwork
 *
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public class Artwork implements Serializable {



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idArtwork;
	private String Description;
	private String name;
	private float price;
	private Date dateOfOublication;
	private boolean state;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="artwork")
	private List<Feedback> listFeedback;
	@OneToMany
	private List<Picture> listPictures;
	@ManyToOne
	private User user;

	public Artwork() {
		super();
	}   
	public int getIdArtwork() {
		return this.idArtwork;
	}

	public void setIdArtwork(int idArtwork) {
		this.idArtwork = idArtwork;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Date getDateOfOublication() {
		return dateOfOublication;
	}
	public void setDateOfOublication(Date dateOfOublication) {
		this.dateOfOublication = dateOfOublication;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	public List<Feedback> getListFeedback() {
		return listFeedback;
	}
	public void setListFeedback(List<Feedback> listFeedback) {
		this.listFeedback = listFeedback;
	}
	public List<Picture> getListPictures() {
		return listPictures;
	}
	public void setListPictures(List<Picture> listPictures) {
		this.listPictures = listPictures;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
   
}
