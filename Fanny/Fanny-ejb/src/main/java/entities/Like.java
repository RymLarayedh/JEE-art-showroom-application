package entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Like")
public class Like  extends Feedback implements Serializable {

	public Like() {
	}
	@Override
	public String toString() {
		return "Like [nbr=" + nbr + "]";
	}
	public int getNbr() {
		return nbr;
	}
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	private int nbr ;
	private static final long serialVersionUID = 1L;


}
