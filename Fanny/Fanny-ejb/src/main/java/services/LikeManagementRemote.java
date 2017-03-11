package services;

import java.util.List;

import javax.ejb.Remote;


import entities.Like;
import entities.Rating;
import entities.VisualArt;

@Remote
public interface LikeManagementRemote {
	public void addLike(Like L);
	public void deleteLike(Like L);
	public Like findByUserartID(int id , int ida);
	public boolean checkexistance(int idu , int ida );
	public long nbrlike(int ida);
	public Rating findByUserartIDR(int id , int ida);
	public boolean checkexistanceR(int idu , int ida );
	public void addRating(Rating R);
	public void upadateRating(Rating R);

	

	
	
}
