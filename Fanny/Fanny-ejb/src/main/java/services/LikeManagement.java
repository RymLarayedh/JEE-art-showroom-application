package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.registry.FederatedConnection;

import entities.Artwork;
import entities.EventUser;
import entities.Feedback;
import entities.Like;
import entities.Rating;
import entities.User;

/**
 * Session Bean implementation class LikeManagement
 */
@Stateless
@LocalBean



public class LikeManagement implements LikeManagementRemote {
	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public LikeManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addLike(Like L) {
		em.persist(L);
		
	}

	@Override
	public void deleteLike(Like L) {
		em.remove(em.merge(L));

	}


	@Override
	public boolean checkexistance(User idu, Artwork ida) {
		if (findByUserartID(idu, ida) == null) {
			return false;
		}

		return true;
		

		}

	@Override
	public Like findByUserartID(User user , Artwork artwork ) {
		try {
			TypedQuery<Like> q = em.createQuery("SELECT b from Like b where b.user =:user AND b.artwork =:ida )", Like.class);
			q.setParameter("user", user);
			q.setParameter( "ida", artwork);
			Like like =  q.getSingleResult();
			return like;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public long nbrlike(Artwork ida )
	{
		Query q = em.createQuery
			    ("Select count(*) from Like b  where b.artwork =:ida)");
			q.setParameter("ida", ida);
			long countResult = (long)q.getSingleResult();
			
			return countResult;
	}

	@Override
	public Rating findByUserartIDR(User user, Artwork artwork) {
		try {
			TypedQuery<Rating> q = em.createQuery("SELECT b from Rating b where b.user =:user AND b.artwork =:ida)", Rating.class);
			q.setParameter("user", user);
			q.setParameter( "ida", artwork);
			Rating rating = (Rating) q.getSingleResult();
			return rating;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public boolean checkexistanceR(User idu, Artwork ida) {
		if (findByUserartIDR(idu, ida) == null) {
			return true;
		}

		return false;
		

	}

	@Override
	public void addRating(Rating R) {
     em.persist(R);		
	}

	@Override
	public void upadateRating(Rating R) {
        em.merge(R);		
	}

	@Override
	public Feedback FindbyId(int id) {
		try {
			return em.find(Feedback.class, id);
		} catch (javax.persistence.NoResultException e) {
			return null;
		}	
	
	}


}

	
	

	
	

	
		
		
		



