package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

import entities.Topic;

/**
 * Entity implementation class for Entity: Category
 *
 */
@Entity

public class Category implements Serializable {

	//added
	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategory;
	private String name;
	@OneToMany(targetEntity=Topic.class,mappedBy="catgory",cascade={CascadeType.ALL},orphanRemoval=true)
	private List<Topic> listTopic;
	
	private static final long serialVersionUID = 1L;


	public Category() {
		super();
	}   
	public int getIdCategory() {
		return this.idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<Topic> getListTopic() {
		return listTopic;
	}
	public void setListTopic(List<Topic> listTopic) {
		this.listTopic = listTopic;
	}
	public Category(int idCategory, String name, List<Topic> listTopic) {
		super();
		this.idCategory = idCategory;
		this.name = name;
		this.listTopic = listTopic;
	}
	public Category(String name, List<Topic> listTopic) {
		super();
		this.name = name;
		this.listTopic = listTopic;
	}
	
   
}
