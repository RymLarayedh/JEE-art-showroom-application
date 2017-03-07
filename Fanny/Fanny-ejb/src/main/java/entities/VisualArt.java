package entities;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;



public class VisualArt extends Artwork implements Serializable {
	//
	
	private int length;
	private int width;
	
	private static final long serialVersionUID = 1L;
	@OneToOne()
	@JoinColumn(name="category_art")
	private Category category;
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	


}
