package entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
/**
 * Entity implementation class for Entity: Reclamation
 *
 */
@Entity
@DiscriminatorValue("RECLAMATION")
public class Reclamation extends Feedback implements Serializable {
	
	
	private String body;
	private Time time;
	private Integer degree;
	private static final long serialVersionUID = 1L;
	
	
	public Reclamation() {
		super();
	}
	
	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "Reclamation [body=" + body + ", time=" + time + "]";
	}
	
	
	
	

}
