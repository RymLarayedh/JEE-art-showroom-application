package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

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
	@OneToMany(mappedBy="artist")
	private List<ArtistFields> listArtist;
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


	public List<ArtistFields> getListArtist() {
		return listArtist;
	}


	public void setListArtist(List<ArtistFields> listArtist) {
		this.listArtist = listArtist;
	}
	
	
   
}
