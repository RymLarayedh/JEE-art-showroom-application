package entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username","email"})})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;
	private String firstName;
	private String lastName;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String email;
	private boolean isActive;
	private boolean isBlocked;
	@Lob
	private byte[] picture;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="user")
	private Set<ArtistFollowers> listFollow;
	@OneToMany(mappedBy="user")
	private List<EventUser>listEventUser;
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	private List <Feedback> listFeedback;
	/*@OneToOne(mappedBy="user")
	private Reclamation reclamation;*/
	/*manel*/
	@OneToMany(mappedBy="user"/*,fetch=FetchType.EAGER*/)
	private List<Artwork> listArtwork;
	@OneToMany(mappedBy="sender")
	private Set<Message> listMessages;
	@OneToMany(mappedBy="receiver")
	private Set<Message> listMes;
	
	@OneToMany(mappedBy="user")
	private Set<Cart> listCart;
	
	/**Ines **/
	@OneToMany(mappedBy="addedBy",cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	private Set<Category> listCategories;
	
	@OneToMany(mappedBy="AddedBy")
	private Set<Topic> listTopics;
	
	@OneToMany(mappedBy="AddedBy")
	private Set<Post> listPosts;
	
	@OneToMany(mappedBy="AddedBy")
	private Set<Reply> listReply;
	
	

	public Set<Cart> getListCart() {
		return listCart;
	}

	public Set<Message> getListMessages() {
		return listMessages;
	}

	public void setListMessages(Set<Message> listMessages) {
		this.listMessages = listMessages;
	}

	public Set<Message> getListMes() {
		return listMes;
	}

	public void setListMes(Set<Message> listMes) {
		this.listMes = listMes;
	}

	public void setListCart(Set<Cart> listCart) {
		this.listCart = listCart;
	}

	public List<Artwork> getListArtwork() {
		return listArtwork;
	}

	public void setListArtwork(List<Artwork> listArtwork) {
		this.listArtwork = listArtwork;
	}

	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String username, String password, String email, boolean isActive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.isActive = false;
		this.isBlocked = false;
	}
	
	public User(User user)
	{
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.picture = user.getPicture();
		this.isActive = false;
		this.isBlocked = false;
	}



	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Set<ArtistFollowers> getListFollow() {
		return listFollow;
	}

	public void setListFollow(Set<ArtistFollowers> listFollow) {
		this.listFollow = listFollow;
	}

	public boolean isBlocked() {
		return isBlocked;
	}

	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return  firstName + " " + lastName ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUser;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (idUser != other.idUser)
			return false;
		return true;
	}

	public List<EventUser> getListEventUser() {
		return listEventUser;
	}

	public void setListEventUser(List<EventUser> listEventUser) {
		this.listEventUser = listEventUser;
	}

	public List<Feedback> getListFeedback() {
		return listFeedback;
	}

	public void setListFeedback(List<Feedback> listFeedback) {
		this.listFeedback = listFeedback;
	}

	public Set<Category> getListCategories() {
		return listCategories;
	}

	public void setListCategories(Set<Category> listCategories) {
		this.listCategories = listCategories;
	}

	public Set<Topic> getListTopics() {
		return listTopics;
	}

	public void setListTopics(Set<Topic> listTopics) {
		this.listTopics = listTopics;
	}

	public Set<Post> getListPosts() {
		return listPosts;
	}

	public void setListPosts(Set<Post> listPosts) {
		this.listPosts = listPosts;
	}

	public Set<Reply> getListReply() {
		return listReply;
	}

	public void setListReply(Set<Reply> listReply) {
		this.listReply = listReply;
	}
	
	

	
	
	
	
	
   
	
}
