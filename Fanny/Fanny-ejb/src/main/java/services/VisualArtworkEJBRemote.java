package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Artwork;
import entities.Comment;
import entities.Feedback;
import entities.VisualArt;


@Remote
public interface VisualArtworkEJBRemote {
public void addVisualArt(VisualArt VA);
public void upadateVisualArt(VisualArt VA);
public void deleteVisualArt(Artwork VA);
public Artwork findVisualArtById(int IdVisualArt );
public List<Artwork> findAllVisualArt ();
public List<VisualArt> findMyVisualArt (int idUser);


public void addVisualComment(Comment VC);
public void upadateVisualComment(Comment VC);
public void deleteVisualComment(Comment VC);
public Comment findVisualCommentById(int IdVisualComment );
public List<Comment> findAllVisualComment ();
public List<Comment> findAllVisualArtComment (int idVisualart);
public List<Comment> findMyVisualComment (int idUser ,int idVisualart);
}
