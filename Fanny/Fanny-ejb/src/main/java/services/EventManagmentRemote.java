package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Artist;
import entities.Event;
import entities.Gallery;
import entities.User;


@Remote
public interface EventManagmentRemote {
	
	/**
	 * Add an event
	 * @param event
	 */
	public void addEvent(Event event);
	
	/**
	 * Update Event
	 * @param event
	 */
	public void updateEvent(Event event);
	
	/**
	 * Join an event
	 * @param event
	 */
	public void joinEvent(Event event);
	
	/**
	 * Unjoin event
	 * @param event
	 */
	public void unjoinEvent(Event event);
	
	/**
	 * All Events
	 * @return
	 */
	public List<Event> getAllEvents();
	
	/**
	 * All Coming Events
	 * @return
	 */
	public List<Event> getAllComingEvents();
	
	/**
	 * All Past Events
	 * @return
	 */
	public List<Event> getAllPastEvents();
	
	/**
	 * find event by Id
	 * @param id
	 * @return
	 */
	public Event findEventById(int id);
	
	/**
	 * find event by title
	 * @param title
	 * @return
	 */
	public Event findByTitle(String title);
	
	/**
	 * find Event by Date
	 * @param date
	 * @return
	 */
	public Event findByDate(Date date);
	
	/**
	 * find Event by Date
	 * @param date
	 * @return
	 */
	public Event findByUser(User user);
	
	/**
	 * find Event by gallery
	 * @param date
	 * @return
	 */
	public Event findByGallery(Gallery gallery);
	
	/**
	 * find Event by artist
	 * @param date
	 * @return
	 */
	public Event findByDate(Artist artist);

	
		
	

}
