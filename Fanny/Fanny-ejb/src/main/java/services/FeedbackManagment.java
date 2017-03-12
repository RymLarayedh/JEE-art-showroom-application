package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Artwork;
import entities.Event;
import entities.Feedback;
import entities.Reclamation;

/**
 * Session Bean implementation class FeedbackManagment
 */
@Stateless
@LocalBean
public class FeedbackManagment implements FeedbackManagmentRemote {

	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public FeedbackManagment() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Reclamation> getAllReclamation() {
		try {
			TypedQuery<Reclamation> q = em.createQuery("SELECT a FROM Reclamation a WHERE a.handle = 0", Reclamation.class);
			List<Reclamation> Lrec = q.getResultList();
			return Lrec;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public void addReclamation(Reclamation r) {
		em.persist(r);
		
	}

	@Override
	public void updateReclamation(Reclamation r) {
		em.merge(r);
		
	}

	

	

	@Override
	public List<Feedback> findFeedbackByIdArtwork(Artwork artwork) {
		// TODO Auto-generated method stub
		return em.createQuery("select f from Feedback f where  f.artwork=:artwork",Feedback.class).setParameter("idArtwork",artwork).getResultList();
	}


	
	

}
