package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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
	@Override
	public Artwork findArtworkByID(int id) {
		try {
			return em.find(Artwork.class, id);
		} catch (javax.persistence.NoResultException e) {
			return null;
		}	
	}
	@Override
	public TunisianCraft findByArtist(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<TunisianCraft> getAllTunisianCraft() {
		TypedQuery<TunisianCraft> T = em.createQuery("SELECT t FROM Artwok t", TunisianCraft.class);
		List<TunisianCraft> Tcraft = T.getResultList();
		return Tcraft;	}
	@Override
	public List<TunisianCraft> findByType(String type) {
		try {

			TypedQuery<TunisianCraft> T = em.createQuery("SELECT t FROM Artwork t where t.type =:type ", TunisianCraft.class);
			T.setParameter("type", type);
			List<TunisianCraft> TcraftType = T.getResultList();
			return TcraftType;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}
	}
	}

	


