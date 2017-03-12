package entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
	@Basic(fetch=FetchType.LAZY)
	@Lob @Column(name="PIC")
	private byte[] picture;
	
	private static final long serialVersionUID = 1L;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Feedback> listFeedback;
//	@OneToOne
//	private Picture pictArt;
	
	@ManyToOne
	private User user;
	@OneToMany(mappedBy="artwork")
	private Set<Cart> listCart;

	@Override
	public String toString() {
		return "Artwork [idArtwork=" + idArtwork + ", Description=" + Description + ", name=" + name + ", price="
				+ price + ", dateOfOublication=" + dateOfOublication + ", state=" + state + ", picture="
				+ Arrays.toString(picture) + ", listFeedback=" + listFeedback + ", user=" + user + "]";
	}
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
	
public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	//	public Picture getPictArt() {
//		return pictArt;
//	}
//	public void setPictArt(Picture pictArt) {
//		this.pictArt = pictArt;
//	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
   
}
