package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Entity implementation class for Entity: EventUserID
 *
 */
@Embeddable

public class EventUserID implements Serializable {
	
	private int idEventPK;
	private int idUserPK;
	private static final long serialVersionUID = 1L;
	
	
	public EventUserID() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEventPK;
		result = prime * result + idUserPK;
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
		EventUserID other = (EventUserID) obj;
		if (idEventPK != other.idEventPK)
			return false;
		if (idUserPK != other.idUserPK)
			return false;
		return true;
	}
	public int getIdEventPK() {
		return idEventPK;
	}
	public void setIdEventPK(int idEventPK) {
		this.idEventPK = idEventPK;
	}
	public int getIdUserPK() {
		return idUserPK;
	}
	public void setIdUserPK(int idUserPK) {
		this.idUserPK = idUserPK;
	}
	
	

}
