package entities;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

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
	
	//rymree
	//er
	//eeeee
	private String body;
	private Date date;
	private Integer degree;
	private Integer handle;
	private static final long serialVersionUID = 1L;
	
	//master
	public Reclamation() {
		super();
	}
	
	public Integer getHandle() {
		return handle;
	}

	public void setHandle(Integer handle) {
		this.handle = handle;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
		return "Reclamation [body=" + body + ", time=" + date + "]";
	}
	
	
	
	

}
