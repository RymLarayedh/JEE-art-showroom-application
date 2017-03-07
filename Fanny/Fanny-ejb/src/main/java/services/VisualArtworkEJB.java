package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
