package entities;

import java.io.Serializable;
import java.util.Date;

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
	private Date AddedAt;
	
	@ManyToOne(targetEntity=Category.class)
	private Category catgory;
	@ManyToOne(targetEntity=User.class)
	private User AddedBy;
	private String Text;
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
	public Date getAddedAt() {
		return AddedAt;
	}
	public void setAddedAt(Date addedAt) {
		AddedAt = addedAt;
	}
	public User getAddedBy() {
		return AddedBy;
	}
	public void setAddedBy(User addedBy) {
		AddedBy = addedBy;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	
	
	
   
}
