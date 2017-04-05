package services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Admin;
import entities.Artist;
import entities.ArtistFields;
import entities.ArtistFieldsID;
import entities.ArtistFollowers;
import entities.ArtistFollowersID;
import entities.Fields;
import entities.Gallery;
import entities.User;

/**
 * Session Bean implementation class UserManagment
 */
@Stateless
public class UserManagment implements UserManagmentRemote {

	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public UserManagment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addUser(User user) {
		em.persist(em.merge(user));

	}

	@Override
	public void updateUser(User user) {
		em.merge(user);

	}

	@Override
	public void enableUser(User user) {
		user.setActive(true);
		updateUser(user);

	}

	@Override
	public void disableUser(User user) {
		user.setActive(false);
		updateUser(user);
	}

	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u", User.class);
		List<User> Luser = q.getResultList();
		return Luser;

	}

	@Override
	public boolean loginUser(String username, String password) {
		User user = findByUsername(username);
		if (user == null) {
			return false;
		} else if (user.getPassword().equals(password)) {
			if (user.isActive() && (!user.isBlocked())) {
				return true;
			}
			return false;

		}
		return false;

	}

	@Override
	public int RedirectUser(User user) {

		if (user instanceof Artist) {
			return 1;
		}
		if (user instanceof Gallery) {
			return 2;
		}

		return 0;
	}

	@Override
	public User findById(int id) {
		try {
			return em.find(User.class, id);
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User with id = " + id + " not found");
			return null;
		}
	}

	@Override
	public User findByUsername(String username) {
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.username =:username", User.class);
			q.setParameter("username", username);
			User user = q.getSingleResult();
			return user;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"User with username = " + username + " not found");
			return null;
		}

	}

	@Override
	public User findByEmail(String email) {
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.email =:email", User.class);
			q.setParameter("email", email);
			User user = q.getSingleResult();
			return user;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User with email = " + email + " not found");
			return null;
		}
	}

	@Override
	public void blockUser(User user) {

		user.setBlocked(true);
		updateUser(user);
	}

	@Override
	public void unblockUser(User user) {
		user.setBlocked(false);
		updateUser(user);
	}

	@Override
	public List<User> filterFirstName(String name) {
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.firstName LIKE :ln ", User.class);
			q.setParameter("ln", '%' + name + '%');
			List<User> Luser = q.getResultList();
			return Luser;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User with FirstName = " + name + " not found");
			return null;

		}
	}

	@Override
	public List<User> filterLastName(String name) {
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.lastName LIKE :fn ", User.class);
			q.setParameter("fn", '%' + name + '%');
			List<User> Luser = q.getResultList();
			return Luser;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "User with LastName = " + name + " not found");
			return null;
		}
	}

	@Override
	public List<User> filterBlockedUser() {
		try {
			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.isActive =:status ", User.class);
			q.setParameter("status", false);
			List<User> LuserBlocked = q.getResultList();
			return LuserBlocked;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no blocked user found");
			return null;
		}
	}

	@Override
	public List<User> filterActiveUser() {
		try {

			TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.isActive =:status ", User.class);
			q.setParameter("status", true);
			List<User> LuserActive = q.getResultList();
			return LuserActive;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no active user found");
			return null;
		}
	}

	@Resource(name = "java:jboss/mail/gmail")
	private Session session;

	@Asynchronous
	public void sendMail(String Recipient, String text, String subject) throws AddressException, MessagingException {
		// Recipient's email ID needs to be mentioned.
		String to = Recipient;

		// Sender's email ID needs to be mentioned
		String from = "fannytunisia@gmail.com";

		// Get system properties
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		// Get the default Session object.

		// Session session = Session.getDefaultInstance(props);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("fannytunisia@gmail.com", "fanny2017");
			}
		});

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// Set Subject: header field
			message.setSubject(subject);

			// Now set the actual message
			message.setText(text);

			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	@Override
	public String codeGeneration() {

		List<Integer> Lascii = new ArrayList<>();
		for (int i = 48; i < 58; i++) {
			Lascii.add(i);
		}

		for (int i = 65; i < 91; i++) {
			Lascii.add(i);
		}

		for (int i = 97; i < 123; i++) {
			Lascii.add(i);
		}
		Random randomizer = new Random();
		String random = "";
		for (int i = 0; i < 8; i++) {
			int x;
			x = Lascii.get(randomizer.nextInt(Lascii.size()));
			random += Character.toString((char) ((char) x));
		}
		return random;
	}

	@Override
	public List<Artist> getAllArtists() {
		try {
			TypedQuery<Artist> q = em.createQuery("SELECT a FROM Artist a", Artist.class);
			List<Artist> Lartist = q.getResultList();
			return Lartist;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no artist found");
			return null;
		}
	}

	@Override
	public List<Admin> getAllAdmins() {
		try {
			TypedQuery<Admin> q = em.createQuery("SELECT a FROM Admin a", Admin.class);
			List<Admin> Ladmin = q.getResultList();
			return Ladmin;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no admins found");
			return null;
		}
	}

	@Override
	public List<Gallery> getAllGalleries() {
		try {
			TypedQuery<Gallery> q = em.createQuery("SELECT a FROM Gallery a", Gallery.class);
			List<Gallery> Lgallery = q.getResultList();
			return Lgallery;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no gallery found");
			return null;
		}
	}

	@Override
	public void addFields(Fields field, User user) {
		Artist artist = (Artist) user;
		ArtistFields Af = new ArtistFields();
		Af.setArtist(artist);
		Af.setField(field);
		Af.setArtistFieldId(new ArtistFieldsID(field.getIdField(), user.getIdUser()));
		artist.getLfields().add(Af);
		em.persist(Af);
		em.merge(artist);
	}

	@Override
	public List<Artist> findByField(List<Fields> lf) {
		// cette method permet d'avoir une liste d'artist d'où ces artist on
		// l'une des ces fields en commun
		return null;
	}

	@Override
	public void removeFields(Fields field, User user) {
		Artist artist = (Artist) user;
		ArtistFields Af = new ArtistFields();
		Af.setArtist(artist);
		Af.setField(field);
		Af.setArtistFieldId(new ArtistFieldsID(field.getIdField(), user.getIdUser()));
		em.remove(em.merge(Af));
	}

	@Override
	public void addFollower(User follower, User user) {
		Artist artist = (Artist) user;
		ArtistFollowers Af = new ArtistFollowers();
		Af.setArtist(artist);
		Af.setUser(follower);
		Af.setArtistId(new ArtistFollowersID(follower.getIdUser(), artist.getIdUser()));
		artist.getFollowers().add(Af);
		em.persist(Af);
		em.merge(artist);

	}

	@Override
	public void removeFollower(User follower, User user) {
		Artist artist = (Artist) user;
		ArtistFollowers Af = new ArtistFollowers();
		Af.setArtist(artist);
		Af.setUser(follower);
		Af.setArtistId(new ArtistFollowersID(follower.getIdUser(), artist.getIdUser()));
		em.remove(em.merge(Af));

	}

	@Override
	public boolean verifyMail(String mail) {
		String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
				+ "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
		Pattern pattern = Pattern.compile(masque);
		Matcher controler = pattern.matcher(mail);
		if (controler.matches()) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean checkMailExistance(String mail) {
		if (findByEmail(mail) == null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUsernameExistance(String username) {
		if (findByUsername(username) == null) {
			return true;
		}

		return false;
	}

	@Override
	public List<Artist> getAllFollowed(User user) {
		try {
			TypedQuery<Artist> q = em.createQuery("SELECT a FROM Artist a join a.Followers af where af.user=:User",
					Artist.class);
			q.setParameter("User", user);
			List<Artist> Lartist = q.getResultList();
			return Lartist;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no Followed found");
			return null;
		}
	}

	@Override
	public Fields findFieldsByName(String name) {
		try {
			TypedQuery<Fields> q = em.createQuery("SELECT f FROM Fields f where f.Libelle =:name", Fields.class);
			q.setParameter("name", name);
			Fields fields = q.getSingleResult();
			return fields;
		} catch (javax.persistence.NoResultException e) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO, "no fields with name = " + name + " found");
			return null;
		}
	}

	@Override
	public List<User> filterLastNameAndLastName(String name) {
		try {
			TypedQuery<User> q = em
					.createQuery("SELECT u FROM User u where u.firstName LIKE :ln or u.lastName LIKE :ln ", User.class);
			q.setParameter("ln", '%' + name + '%');
			List<User> Luser = q.getResultList();
			return Luser;
		} catch (javax.persistence.NoResultException E) {
			Logger.getLogger(this.getClass().getName()).log(Level.INFO,
					"no user found with firstName like = " + name + " or lastName like = " + name);
			return null;
		}
	}

}
