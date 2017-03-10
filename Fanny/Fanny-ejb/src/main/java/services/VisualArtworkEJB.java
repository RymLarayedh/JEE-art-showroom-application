package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Artwork;
import entities.Category;
import entities.Comment;
import entities.User;
import entities.VisualArt;
import relation.Client;


/**
 * Session Bean implementation class VisualArtworkEJB
 */
@Stateless
@LocalBean
public class VisualArtworkEJB implements VisualArtworkEJBRemote {
	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public VisualArtworkEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addVisualArt(VisualArt VA) {
		em.persist(VA);
		
	}

	@Override
	public void upadateVisualArt(VisualArt VA) {
		em.merge(VA)	;
		
	}

	@Override
	public void deleteVisualArt(Artwork VA) {
		em.remove(em.merge(VA));
		
	}

	@Override
	public Artwork findVisualArtById(int IdVisualArt) {
		// TODO Auto-generated method stub
		return  em.find(Artwork.class,IdVisualArt );
	}

	@Override
	public List<Artwork> findAllVisualArt() {
		TypedQuery<Artwork> q = em.createQuery("SELECT a FROM Artwork a", Artwork.class);
		List<Artwork> Lart = q.getResultList();
		return Lart;

	}

	@Override
	public void addVisualComment(Comment VC) {
		em.persist(VC);
		
	}

	@Override
	public void upadateVisualComment(Comment VC) {
		// TODO Auto-generated method stub
		em.merge(VC);	
	}

	@Override
	public void deleteVisualComment(Comment VC) {
		// TODO Auto-generated method stub
		em.remove(em.merge(VC));
	}

	@Override
	public Comment findVisualCommentById(int IdVisualComment) {
		// TODO Auto-generated method stub
		return  em.find(Comment.class,IdVisualComment );
	}

	@Override
	public List<Comment> findAllVisualComment() {
		TypedQuery<Comment> qc = em.createQuery("SELECT c FROM Artwork c", Comment.class);
		List<Comment> Lcomment = qc.getResultList();
		return Lcomment;
	}

	@Override
	public List<VisualArt> findMyVisualArt(int idUser) {
		// TODO Auto-generated method stub
		return (List<VisualArt>) em.createQuery("select c from VisualArt c where c.user_idUser=:pid",VisualArt.class)
				.setParameter("pid", idUser).getSingleResult();
	}

	@Override
	public List<Comment> findMyVisualComment(int idUser, int idVisualart) {
		// TODO Auto-generated method stub
		return null;
	}

}
