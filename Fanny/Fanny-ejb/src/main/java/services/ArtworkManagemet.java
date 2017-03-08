package services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Artwork;
import entities.TunisianCraft;
import entities.User;

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
    }
	@Override
	public void addTunisianCraft(TunisianCraft Tun) {
      em.persist(Tun);		
	}
	@Override
	public TunisianCraft findById(int id) {
		try {
			return em.find(TunisianCraft.class, id);
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
	}

	


