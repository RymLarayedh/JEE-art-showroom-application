package services;

import java.sql.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.Artist;
import entities.Event;
import entities.EventUser;
import entities.Gallery;
import entities.User;


@Remote
public interface EventUserManagmentRemote {
	
	/**
	 * Add an event
	 * @param event
	 */
	public void addEventUser(EventUser event);
	
	
	

}
