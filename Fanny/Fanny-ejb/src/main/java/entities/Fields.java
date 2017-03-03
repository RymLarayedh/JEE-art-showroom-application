package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Fields
 *
 */
@Entity

public class Fields implements Serializable {

	   
	@Id
	private int idField;
	private String Libelle;
	private static final long serialVersionUID = 1L;

	public Fields() {
		super();
	}   
	public int getIdField() {
		return this.idField;
	}

	public void setIdField(int idField) {
		this.idField = idField;
	}   
	public String getLibelle() {
		return this.Libelle;
	}

	public void setLibelle(String Libelle) {
		this.Libelle = Libelle;
	}
   
}
