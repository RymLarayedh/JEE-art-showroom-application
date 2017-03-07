package entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


//
@Entity
@DiscriminatorValue("VisualArt")
public class VisualArt extends Artwork implements Serializable {
	//
	
	
	private int length;
	private int width;
	private String category;
	private static final long serialVersionUID = 1L;
	
//	@ManyToOne()
//	private Category category;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
//	public Category getCategory() {
//		return category;
//	}
//	public void setCategory(Category category) {
//		this.category = category;
//	}
	
	
	public VisualArt() {
		super();
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "VisualArt [length=" + length + ", width=" + width + ", category=" + category + "]";
	}
	

}
