package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Artwork;
import entities.Feedback;
import entities.Like;
import entities.Rating;
import entities.User;
import entities.VisualArt;

@Remote
public interface LikeManagementRemote {
	public void addLike(Like L);
	public void deleteLike(Like L);
	public Like findByUserartID(User user , Artwork artwork);
	public boolean checkexistance(User idu , Artwork ida );
	public long nbrlike(Artwork ida);
	public Rating findByUserartIDR(User user , Artwork artwork);
	public boolean checkexistanceR(User idu , Artwork ida );
	public void addRating(Rating R);
	public void upadateRating(Rating R);
	public Feedback FindbyId (int id);

	

	
	
}
