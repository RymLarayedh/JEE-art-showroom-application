package business;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import entities.Admin;
import entities.Artist;
import entities.Fields;
import entities.Gallery;
import entities.User;
import services.UserManagmentRemote;

public class UserManagmentDelegate {
	private static final String JNDI = "/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote";

	private static UserManagmentRemote getProxy() {
		return (UserManagmentRemote) ServiceLocator.getInstance().getProxy(JNDI);
	}

	public static boolean loginUser(String username, String password) {
		return getProxy().loginUser(username, password);
	}

	public static int RedirectUser(User user) {
		return getProxy().RedirectUser(user);
	}

	public static void addUser(User user) {
		getProxy().addUser(user);

	}

	public static void updateUser(User user) {
		getProxy().updateUser(user);

	}

	public static void enableUser(User user) {
		getProxy().enableUser(user);

	}

	public static void disableUser(User user) {
		getProxy().disableUser(user);

	}

	public static List<User> getAllUsers() {
		return getProxy().getAllUsers();
	}

	public static User findById(int id) {
		return getProxy().findById(id);
	}

	public static User findByUsername(String username) {
		return getProxy().findByUsername(username);
	}

	public static User findByEmail(String email) {
		return getProxy().findByEmail(email);
	}

	public static void blockUser(User user) {
		getProxy().blockUser(user);
	}

	public static void unblockUser(User user) {
		getProxy().unblockUser(user);
	}

	public static List<User> filterFirstName(String name) {
		return getProxy().filterFirstName(name);
	}

	public static List<User> filterLastName(String name) {
		return getProxy().filterLastName(name);
	}

	public static List<User> filterLastNameAndLastName(String name) {
		return getProxy().filterLastNameAndLastName(name);
	}

	public static List<User> filterBlockedUser() {
		return getProxy().filterBlockedUser();

	}

	public static List<User> filterActiveUser() {
		return getProxy().filterActiveUser();

	}

	public static void sendMail(String Recipient, String text, String subject) throws AddressException, MessagingException {
		getProxy().sendMail(Recipient, text, subject);

	}

	public static String codeGeneration() {
		return getProxy().codeGeneration();

	}

	public static List<Artist> getAllArtists() {
		return getProxy().getAllArtists();

	}

	public static List<Admin> getAllAdmins() {
		return getProxy().getAllAdmins();

	}

	public static List<Gallery> getAllGalleries() {
		return getProxy().getAllGalleries();

	}

	public static void addFields(Fields field, User user) {
		getProxy().addFields(field, user);

	}

	public static void removeFields(Fields field, User user) {
		getProxy().removeFields(field, user);

	}

	public static void addFollower(User follower, User user) {
		getProxy().addFollower(follower,user);

	}

	public static void removeFollower(User follower, User user) {
		getProxy().removeFollower(follower, user);

	}

	public static boolean verifyMail(String mail) {
		return getProxy().verifyMail(mail);

	}

	public static boolean checkMailExistance(String mail) {
		return getProxy().checkMailExistance(mail);

	}

	public static boolean checkUsernameExistance(String username) {
		return getProxy().checkUsernameExistance(username);

	}

	public static List<Artist> getAllFollowed(User user) {
		return getProxy().getAllFollowed(user);

	}

	public static Fields findFieldsByName(String name) {
		return getProxy().findFieldsByName(name);

	}

}
