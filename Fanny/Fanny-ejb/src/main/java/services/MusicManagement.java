package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Music;

public class MusicManagement implements MusicManagementRemote{
	@PersistenceContext
	EntityManager em ;
	
	

	@Override
	public void addMusic(Music m) {
		// TODO Auto-generated method stub
		em.persist(em.merge(m));

		
	}

	@Override
	public void updateMusic(Music m) {
		// TODO Auto-generated method stub
		em.merge(m);

		
	}

	@Override
	public void deleteMusic(Music m) {
		// TODO Auto-generated method stub
		em.remove(m);
	}

	@Override
	public List<Music> findAllMusic() {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Music> m = em.createQuery("SELECT m FROM Music m", Music.class);
			List<Music> ListMusic = m.getResultList();
			return ListMusic ;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}
	

}
