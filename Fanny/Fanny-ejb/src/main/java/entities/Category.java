package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
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
	  // 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCategory;
	private String name;
	private Date addedAt ;
	@ManyToOne(targetEntity=Admin.class)
	private Admin addedBy ;
//	@OneToMany(targetEntity=Topic.class,mappedBy="catgory",cascade={CascadeType.ALL},orphanRemoval=true)
	//private List<Topic> listTopic;
	
	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy="category")
	private Artwork visualArt ;
	
	public Category() {
		super();
	}   
	
	public Category(int idCategory, String name) {
		super();
		this.idCategory = idCategory;
		this.name = name;
	}
	
	

	public Category(String name, Admin addedBy) {
		super();
		this.name = name;
		this.addedBy = addedBy;
	}

	public Category(String name, Date addedAt, Admin addedBy, List<Topic> listTopic) {
		super();
		this.name = name;
		this.addedAt = addedAt;
		this.addedBy = addedBy;
		//this.listTopic = listTopic;
	}

	public Date getAddedAt() {
		return addedAt;
	}

	public void setAddedAt(Date addedAt) {
		this.addedAt = addedAt;
	}

	public Admin getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(Admin addedBy) {
		this.addedBy = addedBy;
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
//	public List<Topic> getListTopic() {
//		return listTopic;
//	}
//	public void setListTopic(List<Topic> listTopic) {
//		this.listTopic = listTopic;
//	}
	public Category(int idCategory, String name, List<Topic> listTopic) {
		super();
		this.idCategory = idCategory;
		this.name = name;
		//this.listTopic = listTopic;
	}
	public Category(String name, List<Topic> listTopic) {
		super();
		this.name = name;
		//this.listTopic = listTopic;
	}

	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", name=" + name + ", addedAt=" + addedAt + ", addedBy=" + addedBy
				+ ", listTopic=" +  "]";
	}
	
	
   
}
