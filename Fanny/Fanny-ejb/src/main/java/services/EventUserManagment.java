package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Artist;
import entities.ArtistFields;
import entities.ArtistFieldsID;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.Fields;
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
	@Override
	public List<EventUser> findByUserId(int id) {
		try {
			TypedQuery<EventUser> q = em.createQuery("SELECT b from EventUser b JOIN b.eventUserID c where c.idUserPK = :id)", EventUser.class);
			q.setParameter("id", id);
			List<EventUser> eventUser = q.getResultList();
			return eventUser;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}
	@Override
	public EventUser findByUserEventId(int idU, int idE) {
		try {
			TypedQuery<EventUser> q = em.createQuery("SELECT b from EventUser b JOIN b.eventUserID c where c.idUserPK = :idU and c.idEventPK = :idE)", EventUser.class);
			q.setParameter("idU", idU);
			q.setParameter("idE", idE);
			EventUser eventUser = q.getSingleResult();
			return eventUser;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}
	@Override
	public void deleteEvent(EventUser e) {
		em.remove(em.merge(e));
		
	}
	@Override
	public void deleteEventUser(Event e, User u) {
		EventUser EU = new EventUser();
		EU.setEvent(e);
		EU.setUser(u);
		EU.setStatus(1);;
		EventUserID EUID = new EventUserID();
		EUID.setIdEventPK(e.getIdEvent());
		EUID.setIdUserPK(u.getIdUser());
		EU.setEtudiantCoursID(EUID);
		em.remove(em.merge(EU));
	}

	@Override
	public void updateEventUser(EventUser event) {
		em.merge(event);
		
	}
	

}
