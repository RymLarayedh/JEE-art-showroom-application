package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * Entity implementation class for Entity: Album
 *
 */
@Entity

public class Picture implements Serializable {

	   
	@Id
	private int idAlbum;
	private String Description;
	private byte[] Photo;
	@ManyToOne
	private Gallery gallery;
	private static final long serialVersionUID = 1L;

	public Picture() {
		super();
	}   
	public int getIdAlbum() {
		return this.idAlbum;
	}

	public void setIdAlbum(int idAlbum) {
		this.idAlbum = idAlbum;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public byte[] getPhotos() {
		return Photo;
	}
	public void setPhotos(byte[] photos) {
		Photo = photos;
	}
	public Gallery getGallery() {
		return gallery;
	}
	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}
   
}
