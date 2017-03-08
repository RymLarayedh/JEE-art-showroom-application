package services;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Message;
import entities.User;

/**
 * Session Bean implementation class MessagesEJB
 */
@Stateless
public class MessagesEJB implements MessagesEJBRemote {

	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public MessagesEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addMessage(Message message) {
		em.persist(message);
		
	}

	@Override
	public Set<User> ConversationList(Message message) {
		// TODO Auto-generated method stub
		return null;
	}

}
