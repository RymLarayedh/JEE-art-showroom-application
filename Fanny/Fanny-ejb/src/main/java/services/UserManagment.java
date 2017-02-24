package services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
		em.persist(user);

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
		if (user.getPassword().equals(password)) {
			if (user.isActive()) {
				return true;
			}
			return false;

		}
		return false;

	}

	@Override
	public int RedirectUser(User user) {
		if (user.getRole().equals("ARTIST")) {
			return 1;
		}
		if (user.getRole().equals("GALLERY")) {
			return 2;
		}

		return 0;

	}

	@Override
	public User findById(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User findByUsername(String username) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.username =:username", User.class);
		q.setParameter("username", username);
		User user = q.getSingleResult();
		return user;
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.email =:email", User.class);
		q.setParameter("email", email);
		User user = q.getSingleResult();
		return user;
	}

	@Override
	public void blockUser(User user) {

		enableUser(user);
	}

	@Override
	public void unblockUser(User user) {
		disableUser(user);
	}

	@Override
	public List<User> filterFirstName(String name) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.firstName LIKE :ln ", User.class);
		q.setParameter("ln",'%'+name+'%');
		List<User> Luser = q.getResultList();
		return Luser;
	}

	@Override
	public List<User> filterLastName(String name) {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.lastName LIKE :fn ", User.class);
		q.setParameter("fn",'%'+name+'%');
		List<User> Luser = q.getResultList();
		return Luser;
	}

	@Override
	public List<User> filterBlockedUser() {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.isActive =:status ", User.class);
		q.setParameter("status", false);
		List<User> LuserBlocked = q.getResultList();
		return LuserBlocked;
	}

	@Override
	public List<User> filterActiveUser() {
		TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.isActive =:status ", User.class);
		q.setParameter("status", true);
		List<User> LuserActive = q.getResultList();
		return LuserActive;
	}

}
