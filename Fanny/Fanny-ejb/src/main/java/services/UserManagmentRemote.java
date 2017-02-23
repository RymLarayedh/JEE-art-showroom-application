package services;

import java.util.List;

import javax.ejb.Remote;

import entities.User;

@Remote
public interface UserManagmentRemote {
	
	/**
	 * this method add a user to the database
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * this method allowed to Update User's information
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * this method allowed to enable a user isEnable will be = true
	 * @param user
	 */
	public void enableUser(User user);
	
	/**
	 * this method allowed to disable a user isEnable will be = false
	 * @param user
	 */
	public void disableUser(User user);
	
	/**
	 * this method allowed to get all users 
	 * @return
	 */
	public List<User> getAllUsers();
	
	/**
	 * this method will check if the username and the password entred bellow are avaible
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean loginUser(String username , String password);
	
	/**
	 * this method will redirect user to admin dashboard , artist dashboard or Gallery dashboard
	 * @param user
	 * @return 
	 * if returned : 0 -> Admin , 1 -> artist , 2 -> Gallery
	 */
	public int RedirectUser(User user);
	
	/**
	 * this method will return the user with the same id given
	 * @param id
	 * @return
	 */
	public User findById(int id);
	
	/**
	 * this method will return the user with the same username given
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * this method will return the user with the same email given
	 * @param email
	 * @return
	 */
	public User findByEmail(String email);
	
	
	

}
