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
	public Set<User> ConversationListFilter(User u, String s) {
		TypedQuery<Message> q = em.createQuery("SELECT u FROM Message u where ( u.receiver=:id and ( u.sender.firstName LIKE :m or u.sender.lastName LIKE :m )) or (u.sender=:id and ( u.receiver.firstName LIKE :m or u.receiver.lastName LIKE :m ))", Message.class);
		q.setParameter("id", u);
		q.setParameter("m", "%"+s+"%");
		
		List<Message> UserMessages = q.getResultList();
		Set<User> contacts= new HashSet<>();
		for( Message m: UserMessages)
		{
			if(m.getSender().getIdUser()==u.getIdUser())
				contacts.add(m.getReceiver());
			else
				contacts.add(m.getSender());
				
		
			
		}
		
		return contacts;
	}
	
	
	
	@Override
	public Set<User> ConversationList(User u) {
		TypedQuery<Message> q = em.createQuery("SELECT u FROM Message u where  u.receiver=:id  or u.sender=:id ", Message.class);
		q.setParameter("id", u);
		
		
		
		List<Message> UserMessages = q.getResultList();
		Set<User> contacts= new HashSet<>();
		for( Message m: UserMessages)
		{
			if(m.getSender().getIdUser()==u.getIdUser())
				contacts.add(m.getReceiver());
			else
				contacts.add(m.getSender());
				
		
			
		}
		
		return contacts;
	}

	@Override
	public List<Message> msgList(User u1, User u2) {
		
		TypedQuery<Message> q = em.createQuery("SELECT u FROM Message u where  ( u.receiver=:id1 and u.sender=:id2 ) or ( u.sender=:id1 and u.receiver=:id2 ) order by time asc", Message.class);
		q.setParameter("id1", u1);
		q.setParameter("id2", u2);
		List<Message> UserMessages = q.getResultList();
		return UserMessages;
	}
	
	
	

}
