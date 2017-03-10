package services;

import javax.ejb.Remote;

import entities.Feedback;
import entities.FeedbackId;
import entities.Like;

@Remote
public interface LikeManagementRemote {
	public void addLike(Like L);
	public void deleteLike(Like L);
    public Like findartuser( int ida);
	public boolean checkexistance(int idu , int ida );
	
}
