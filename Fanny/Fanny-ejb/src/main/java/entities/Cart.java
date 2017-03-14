package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cart
 *
 */
@Entity

public class Cart implements Serializable {

	@EmbeddedId
	private CartId idCart;
	
	@ManyToOne
	@JoinColumn(name="buyerId",insertable=false,updatable=false)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="artworkId",insertable=false,updatable=false)
	private Artwork artwork;
	
	private String date;
	private int status;
	private double price;
	private int quantity;
	private String address; 
	
	
	
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
	}
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public CartId getIdCart() {
		return idCart;
	}

	public void setIdCart(CartId idCart) {
		this.idCart = idCart;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
   
}
