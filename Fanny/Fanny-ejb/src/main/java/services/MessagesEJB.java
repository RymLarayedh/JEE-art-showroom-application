package services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	public Set<User> ConversationList(User u) {
		TypedQuery<Message> q = em.createQuery("SELECT u FROM Message u where u.receiver.idUser=:id or u.sender.idUser=:idd", Message.class);
		q.setParameter("id", u.getIdUser());
		q.setParameter("idd", u.getIdUser());
		
		List<Message> UserMessages = q.getResultList();
		Set<User> contacts= new HashSet<>();
		for( Message m: UserMessages)
		{
			if(m.getSender()!=u)
				contacts.add(m.getReceiver());
			else
				contacts.add(m.getSender());
				
		
			
		}
		
		return contacts;
	}

}
