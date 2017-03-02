package entities;

import java.io.Serializable;
import javax.persistence.*;

import entities.Category;

/**
 * Entity implementation class for Entity: Topic
 *
 */
@Entity

public class Topic implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTopic;
	private String Title;
	@ManyToOne(targetEntity=Category.class)
	private Category catgory;
	private static final long serialVersionUID = 1L;

	public Topic() {
		super();
	}   
	public int getIdTopic() {
		return this.idTopic;
	}

	public void setIdTopic(int idTopic) {
		this.idTopic = idTopic;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Category getCatgory() {
		return catgory;
	}
	public void setCatgory(Category catgory) {
		this.catgory = catgory;
	}
	public Topic(int idTopic, String title, Category catgory) {
		super();
		this.idTopic = idTopic;
		Title = title;
		this.catgory = catgory;
	}
	
   
}
