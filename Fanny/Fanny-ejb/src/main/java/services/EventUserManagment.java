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

	

}
