package services;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import entities.Message;
import entities.User;


@Remote
public interface MessagesEJBRemote {
	
	/**
	 * this method add a message to the database
	 * @param message
	 */
	public void addMessage(Message message);
	
	/**
	 * this method return list of conversations
	 * @param message
	 */
	public Set <User> ConversationListFilter (User u, String m);
	public Set <User> ConversationList (User u);
	public List<Message> msgList(User u1, User u2);
	
	

}
