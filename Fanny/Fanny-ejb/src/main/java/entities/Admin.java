package entities;

import entities.User;
import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Admin
 *
 */
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User implements Serializable {

	
	private String domain;
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}   
	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
   
}
