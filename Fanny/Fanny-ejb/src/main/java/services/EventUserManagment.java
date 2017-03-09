package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Artist;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.Gallery;
import entities.User;

/**
 * Session Bean implementation class EventManagment
 */
@Stateless
@LocalBean
public class EventUserManagment implements EventUserManagmentRemote {

	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EventUserManagment() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addEventUser(EventUser event) {
		em.persist(event);
		
	}
	/*@Override
	public List<EventUser> findByEventId(EventUserID id) {
		try {
			TypedQuery<EventUser> q = em.createQuery("SELECT e FROM EventUser e WHERE e.eventUserID =:id", EventUser.class);
			q.setParameter("id", id);
			List<EventUser> eventUser = q.getResultList();
			return eventUser;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}*/
	@Override
	public List<EventUser> findByEventId(int id) {
		try {
			TypedQuery<EventUser> q = em.createQuery("SELECT b from EventUser b JOIN b.eventUserID c where c.idEventPK = :id)", EventUser.class);
			q.setParameter("id", id);
			List<EventUser> eventUser = q.getResultList();
			return eventUser;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	

}
