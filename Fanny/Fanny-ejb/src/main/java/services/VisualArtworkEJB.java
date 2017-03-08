package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.VisualArt;


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
	public void deleteVisualArt(VisualArt VA) {
		em.remove(em.merge(VA));
		
	}

	@Override
	public VisualArt findVisualArtById(int IdVisualArt) {
		// TODO Auto-generated method stub
		return  em.find(VisualArt.class,IdVisualArt );
	}

	@Override
	public List<VisualArt> findAllVisualArt() {
		// TODO Auto-generated method stub
				try {
					TypedQuery<VisualArt> c = em.createQuery("SELECT c FROM VisualArt c", VisualArt.class);
					List<VisualArt> ListAllVisualArt = c.getResultList();
					return ListAllVisualArt;
				} catch (javax.persistence.NoResultException e) {
					return null;
				}

			}
	

}
