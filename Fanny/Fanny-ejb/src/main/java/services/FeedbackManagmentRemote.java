package services;

import java.util.List;

import javax.ejb.Remote;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import entities.Admin;
import entities.Artist;
import entities.Fields;
import entities.Gallery;
import entities.Reclamation;
import entities.User;
/**
 * 
 * @author rymlarayedh
 *
 */

@Remote
public interface FeedbackManagmentRemote {

	
	
	/**
	 * this method returns a list of all the galleries
	 * @return
	 */
	public List<Reclamation> getAllReclamation();
	
	
	
	
	
	
	
	
	

}
