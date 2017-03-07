package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Artwork;
import entities.TunisianCraft;

/**
 * Session Bean implementation class ArtworkManagemet
 */
@Stateless
@LocalBean

public class ArtworkManagemet implements ArtworkManagemetRemote {
	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public ArtworkManagemet() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void addTunisianCraft(TunisianCraft Tun) {
      em.persist(Tun);		
	}

	

}
