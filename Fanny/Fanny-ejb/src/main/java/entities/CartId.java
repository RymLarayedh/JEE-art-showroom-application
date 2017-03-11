package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: CartId
 *
 */
@Embeddable

public class CartId implements Serializable {

	
	private int buyerId;
	private int artworkId;
	private static final long serialVersionUID = 1L;

	public CartId() {
		super();
	}   
	public int getBuyerId() {
		return this.buyerId;
	}

	public void setBuyerId(int userId) {
		this.buyerId = userId;
	}   
	public int getArtworkId() {
		return this.artworkId;
	}

	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artworkId;
		result = prime * result + buyerId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartId other = (CartId) obj;
		if (artworkId != other.artworkId)
			return false;
		if (buyerId != other.buyerId)
			return false;
		return true;
	}
   
}
