package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Set;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Fields
 *
 */
@Entity

public class Fields implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idField;
	private String Libelle;
	@OneToMany(mappedBy="field")
	private Set<ArtistFields> listArtist;
	private static final long serialVersionUID = 1L;

	public Fields() {
		super();
	}   
	
	
	public Fields(String libelle) {
		super();
		Libelle = libelle;
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


	public Set<ArtistFields> getListArtist() {
		return listArtist;
	}


	public void setListArtist(Set<ArtistFields> listArtist) {
		this.listArtist = listArtist;
	}


	@Override
	public String toString() {
		return "Fields [idField=" + idField + ", Libelle=" + Libelle + ", listArtist=" + listArtist + "]";
	}
	
	
	
	
   
}
