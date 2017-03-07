package entities;
import entities.Artwork;
import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Entity
@DiscriminatorValue("TunisianCraft")
public class TunisianCraft extends Artwork implements Serializable {
    
	
	private String Type ;
	private int Quantity ;
	
	private static final long serialVersionUID = 1L; 
	
	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	

	@Override
	public String toString() {
		return "TunisianCraft [Type=" + Type + ", Quantity=" + Quantity + "]";
	}

	public TunisianCraft() {
		super();	
		}
	
}
