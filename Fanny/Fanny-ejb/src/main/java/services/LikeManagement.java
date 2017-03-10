package services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.xml.registry.FederatedConnection;

import entities.Artwork;
import entities.Feedback;
import entities.FeedbackId;
import entities.Like;
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
	public Like findartuser( int ida) {
		
			try {
				TypedQuery<Like> q = em.createQuery("SELECT f FROM Like f where f.user =:user ", Like.class);
				q.setParameter("user", ida);
			Like Like= q.getSingleResult();
				return Like ;
			} catch (javax.persistence.NoResultException e) {
				return null;
			}
	}

	@Override
	public boolean checkexistance(int idu, int ida) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	
	}

	
		
		
		



