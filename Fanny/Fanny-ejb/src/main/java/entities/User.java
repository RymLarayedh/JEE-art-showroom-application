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
	private byte[] picture;
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="user")
	private Set<ArtistFollowers> listFollow;
	@OneToMany(mappedBy="user")
	private List<EventUser>listEventUser;
	@OneToMany(mappedBy="user")
	private List <Feedback> listFeedback;
	/*@OneToOne(mappedBy="user")
	private Reclamation reclamation;*/
	/*manel*/
	@OneToMany(mappedBy="user"/*,fetch=FetchType.EAGER*/)
	private List<Artwork> listArtwork;
	@OneToMany(mappedBy="sender")
	private List<Message> listMessages;
	

	public List<Message> getListMessages() {
		return listMessages;
	}

	public void setListMessages(List<Message> listMessages) {
		this.listMessages = listMessages;
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
	}
	
	public User(User user)
	{
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.isActive = false;
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
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", username="
				+ username + ", password=" + password + ", email=" + email + ", isActive=" + isActive
				+ "]";
	}
	
	
   
	
}
