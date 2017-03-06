package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class FeedbackId implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int artworkId ;
	private int userId;
	public FeedbackId() {
		super();
	}
	public int getArtworkId() {
		return artworkId;
	}
	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + artworkId;
		result = prime * result + userId;
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
		FeedbackId other = (FeedbackId) obj;
		if (artworkId != other.artworkId)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	
	

}
