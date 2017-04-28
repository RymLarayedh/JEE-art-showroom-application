package users;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.validator.ValidatorException;
import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entities.Admin;
import entities.Artist;
import entities.ArtistFollowers;
import entities.Gallery;
import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import services.UserManagment;

@ManagedBean(name="userBean")
@SessionScoped
public class UserBean implements Serializable {

	@EJB
	private UserManagment userManagment;
	private User user;
	private User userChoosen ;
	private boolean loggedIN = false;
	private boolean editIt = false;
	private Gallery gallery;
	private Artist artist;
	List<User> Lu;

	@PostConstruct
	public void remplirList() {
		user = new User();
		userChoosen = new User();
		Lu = new ArrayList<User>();
	}
	
	public boolean isEditIt() {
		return editIt;
	}

	public void setEditIt(boolean editIt) {
		this.editIt = editIt;
	}

	public boolean isLoggedIN() {
		return loggedIN;
	}



	public void setLoggedIN(boolean loggedIN) {
		this.loggedIN = loggedIN;
	}



	public UserManagment getUserManagment() {
		return userManagment;
	}

	public void setUserManagment(UserManagment userManagment) {
		this.userManagment = userManagment;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getLu() {
		return Lu;
	}

	public void setLu(List<User> lu) {
		Lu = lu;
	}

	public User getUserChoosen() {
		return userChoosen;
	}

	public void setUserChoosen(User userChoosen) {
		this.userChoosen = userChoosen;
	}
	
	
	public Gallery getGallery() {
		return gallery;
	}

	public void setGallery(Gallery gallery) {
		this.gallery = gallery;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String doSignUp() {
		String navTo = "";
		userManagment.addUser(user);
		return navTo;
	}
	
	public String doEdit() {
		String navTo = "userUpdate?faces-redirect=true";
		return navTo;
	}


	/**
	 * this method ensure the login and the redirection
	 * @return
	 */
	public String doLogin() {
		String navTo = "";
		if (userManagment.loginUser(user.getUsername(), user.getPassword())) {
			user = userManagment.findByUsername(user.getUsername());
			if (user instanceof Admin) {
				navTo = "pages/admin/AdminDashboard?faces-redirect=true";
			} else {
				navTo = "pages/users/userIndex?faces-redirect=true";
			}

		} else {
		}

		// status = "Login Failed please try again";
		return navTo;
	}

	/**
	 * this method will returns all the users in the database (in order to display them later)
	 * @return
	 */
	public String ShowAllUsers() {
		String navTo = "ManageUsers?faces-redirect=true";
		Lu = userManagment.getAllUsers();
		for (User user : Lu) {
			if (user.getPicture() == null) {

				File file = new File(
						"C:/Users/ElarbiMohamedAymen/git/Eureka/Fanny/Fanny-web/src/main/webapp/resources/img/PasDePhotoDeProfil.png");
				byte[] bFile = new byte[(int) file.length()];
				try {
					FileInputStream fileInputStream = new FileInputStream(file);
					fileInputStream.read(bFile);
					fileInputStream.close();
				} catch (Exception e) {
				}
				user.setPicture(bFile);
			}
		}
		return navTo;
	}

	
	public  void ShowUserProfile(User user)
	{
		userChoosen = user;
		if(user instanceof Gallery)
		{
			gallery = new Gallery();
			gallery = (Gallery) user;
		}
		
		if(userChoosen instanceof Artist)
		{
			artist = new Artist();
			artist = (Artist) user;
		}
	}
	
	/**
	 * this method will block the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void BlockUser() throws AddressException, MessagingException
	{
		userManagment.blockUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"FannyTUNISIA is sorry to announce that we blocked your account for the moment",
				"Something went wrong");
		Lu = userManagment.getAllUsers();
	}
	
	/**
	 * this method will unblock the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void unBlockUser() throws AddressException, MessagingException
	{
		userManagment.unblockUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"FannyTUNISIA is so glad to have you again in our community welcome aboard again",
				"Welcome again");
		Lu = userManagment.getAllUsers();
	}
	
	/**
	 * this method will approve the "userChoosen from the dataTable" and the user will receive an e-mail in purpose
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void ApproveUser() throws AddressException, MessagingException
	{
		userManagment.enableUser(userChoosen);
		userManagment.sendMail(userChoosen.getEmail(),
				"FannyTUNISIA is so glad to have you in our community so please behave and respect other members",
				"Welcome aboard");
		Lu = userManagment.getAllUsers();
	}
	
	public List<Artist> getAllFollowed()
	{
		return userManagment.getAllFollowed(userChoosen);
	}
	
	public int getAllFollow()
	{
		if(userChoosen instanceof Artist)
		{
			Artist artist = (Artist) userChoosen;
			return artist.getFollowers().size();
		}
		return 0;
	}
	
	public List<User> getAllFollowers()
	{
		if(userChoosen instanceof Artist)
		{
			List<User> Lues = new ArrayList<User>();
			Artist artist = (Artist) userChoosen;
			for(ArtistFollowers af:artist.getFollowers())
			{
				Lues.add(af.getUser());
			}
			return Lues;
		}
		return null;
	}
	
	

}
