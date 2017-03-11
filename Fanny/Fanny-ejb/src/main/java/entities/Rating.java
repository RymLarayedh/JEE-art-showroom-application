package entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("Rating")
public class Rating  extends Feedback implements Serializable {

	public Rating() {
		// TODO Auto-generated constructor stub
	}
	private static final long serialVersionUID = 1L;
  
	private Double note ;

	@Override
	public String toString() {
		return "Rating [note=" + note + "]";
	}

	public Double getNote() {
		return note;
	}

	public void setNote(Double note) {
		this.note = note;
	}
}
