package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Artist;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.Gallery;
import entities.User;


@Remote
public interface EventUserManagmentRemote {
	
	/**
	 * Add an event
	 * @param event
	 */
	public void addEventUser(EventUser event);
	/**
	 * 
	 * @param id
	 * @return
	 */
	public List<EventUser> findByEventId(int id) ;
	/**
	 * 
	 * @param e
	 */
	public void deleteEvent(EventUser e);
	
	
	

}
