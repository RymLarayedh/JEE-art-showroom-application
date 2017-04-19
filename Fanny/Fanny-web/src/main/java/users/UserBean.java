package users;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import entities.Admin;
import entities.User;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import services.UserManagment;

@ManagedBean
@SessionScoped
public class UserBean {

	@EJB
	private UserManagment userManagment;
	private User user;
	private boolean loggedIN = false;
	List<User> Lu;

	@PostConstruct
	public void remplirList() {
		user = new User();
		Lu = new ArrayList<User>();
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

	public String doLogin() {
		String navTo = "";
		if (userManagment.loginUser(user.getUsername(), user.getPassword())) {
			user = userManagment.findByUsername(user.getUsername());
			if (user instanceof Admin) {
				loggedIN = true;
				navTo = "admin/AdminDashboard?faces-redirect=true";
			} else {
				loggedIN = true;
				navTo = "users/userIndex?faces-redirect=true";
			}

		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Passowrd", "Please enter correct username and Password"));
			return "Index";
		}

		// status = "Login Failed please try again";
		return navTo;
	}

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

	public StreamedContent getImageFromDB(User u) throws IOException {
		if (u.getPicture() == null) {
			File file = new File("/img/PasDePhotoDeProfil.png");
			byte[] bFile = new byte[(int) file.length()];
			try {
				FileInputStream fileInputStream = new FileInputStream(file);
				fileInputStream.read(bFile);
				fileInputStream.close();
			} catch (Exception e) {
			}
			u.setPicture(bFile);
		}

		return new DefaultStreamedContent(new ByteArrayInputStream(u.getPicture()));

	}

}
