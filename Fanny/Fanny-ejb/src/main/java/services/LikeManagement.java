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
	public boolean checkexistance(int idu, int ida) {
		if (findByUserartID(idu, ida) == null) {
			return false;
		}

		return true;
		
	}

	@Override
	public Like findByUserartID(int id , int ida ) {
		try {
			TypedQuery<Like> q = em.createQuery("SELECT b from Like b JOIN b.feedbackId c where c.userId = :id and c.artworkId =:ida)", Like.class);
			q.setParameter("id", id);
			q.setParameter( "ida", ida);
			Like like = q.getSingleResult();
			return like;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public long nbrlike(int ida )
	{
		Query q = em.createQuery
			    ("Select count(*) from Like b JOIN b.feedbackId c where c.artworkId =:ida)");
			q.setParameter("ida", ida);
			long countResult = (long)q.getSingleResult();
			
			return countResult;
	}

	@Override
	public Rating findByUserartIDR(int id, int ida) {
		try {
			TypedQuery<Rating> q = em.createQuery("SELECT b from Rating b JOIN b.feedbackId c where c.userId = :id and c.artworkId =:ida)", Rating.class);
			q.setParameter("id", id);
			q.setParameter( "ida", ida);
			Rating rating = q.getSingleResult();
			return rating;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}

	@Override
	public boolean checkexistanceR(int idu, int ida) {
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


}

	
	

	
	

	
		
		
		



