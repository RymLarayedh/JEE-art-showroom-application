package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ArtistFields
 *
 */
@Entity

public class ArtistFields implements Serializable {

	@EmbeddedId
	private ArtistFieldsID artistFieldId;
	@ManyToOne
	@JoinColumn(name="idFieldsPK",insertable=false,updatable=false)
	private Fields field;
	@ManyToOne
	@JoinColumn(name="idArtistPK",insertable=false,updatable=false)
	private Artist artist;
	
	private static final long serialVersionUID = 1L;

	public ArtistFields() {
		super();
	}


	public Fields getField() {
		return field;
	}


	public void setField(Fields field) {
		this.field = field;
	}


	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public ArtistFieldsID getArtistFieldId() {
		return artistFieldId;
	}

	public void setArtistFieldId(ArtistFieldsID artistFieldId) {
		this.artistFieldId = artistFieldId;
	}
	
	
   
}
