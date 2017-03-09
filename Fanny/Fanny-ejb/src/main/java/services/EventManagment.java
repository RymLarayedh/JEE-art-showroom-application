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
import entities.Gallery;
import entities.User;

/**
 * Session Bean implementation class EventManagment
 */
@Stateless
@LocalBean
public class EventManagment implements EventManagmentRemote {

	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public EventManagment() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addEvent(Event event) {
		em.persist(event);
		
	}

	@Override
	public void updateEvent(Event event) {
		em.merge(event);
		
	}

	@Override
	public void joinEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unjoinEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Event> getAllEvents() {
		TypedQuery<Event> q = em.createQuery("SELECT e FROM Event e", Event.class);
		List<Event> Levent = q.getResultList();
		return Levent;
	}

	@Override
	public List<Event> getAllComingEvents() {
		TypedQuery<Event> q = em.createQuery("SELECT e FROM Event e", Event.class);
		List<Event> Levent = q.getResultList();
		return Levent;
	}

	@Override
	public List<Event> getAllPastEvents() {
		TypedQuery<Event> q = em.createQuery("SELECT e FROM Event e", Event.class);
		List<Event> Levent = q.getResultList();
		return Levent;
	}

	@Override
	public Event findEventById(int id) {
		return em.find(Event.class, id);
	}

	@Override
	public Event findByTitle(String title) {
		try {
			TypedQuery<Event> q = em.createQuery("SELECT e FROM Event e where e.title =:title", Event.class);
			q.setParameter("title", title);
			Event event = q.getSingleResult();
			return event;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public Event findByDate(Date date) {
		try {
			TypedQuery<Event> q = em.createQuery("SELECT e FROM Event e where e.dateBegin =:date", Event.class);
			q.setParameter("date", date);
			Event event = q.getSingleResult();
			return event;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public Event findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event findByGallery(Gallery gallery) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event findByDate(Artist artist) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteEvent(Event e) {
		em.remove(em.merge(e));
		
	}

	

}
