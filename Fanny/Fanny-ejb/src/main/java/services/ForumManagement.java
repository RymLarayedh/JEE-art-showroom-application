package services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Gallery;
import entities.Music;
import entities.Topic;


/**
 * Session Bean implementation class ForumManagement
 */
@Stateful
public class ForumManagement implements ForumManagementRemote {
	@PersistenceContext
	EntityManager em ;

    /**
     * Default constructor. 
     */
	
    public ForumManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCategory(Category c) {
		// TODO Auto-generated method stub
		em.persist(em.merge(c));

		
	}

	@Override
	public void updateCategory(Category c) {
		// TODO Auto-generated method stub
		em.merge(c);

		
	}

	@Override
	public void deleteCategory(Category c) {
		// TODO Auto-generated method stub
		em.remove(em.merge(c));

		
	}

	@Override
	public List<Category> findAllCategories() {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Category> c = em.createQuery("SELECT c FROM Category c", Category.class);
			List<Category> Listcategories = c.getResultList();
			return Listcategories;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}

	}
	
	@Override
	public Category findCategoryByName(String name) {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Category> c = em.createQuery("SELECT c FROM Category c where c.name =:name", Category.class);
			c.setParameter("name", name);
			Category category = c.getSingleResult();
			return category;
		} catch (javax.persistence.NoResultException E) {
			return null;
		}

	}
	
	/********* Topic
	 * 
	 */
	
	@Override
	public void addTopic(Topic T) {
		// TODO Auto-generated method stub
		em.persist(em.merge(T));
		
	}

	@Override
	public void updateTopoc(Topic T) {
		// TODO Auto-generated method stub
		em.merge(T);
		
	}

	@Override
	public void deleteTopic(Topic T) {
		// TODO Auto-generated method stub
		em.remove(em.merge(T));

		
	}

	@Override
	public List<Topic> findAllTopics() {
		// TODO Auto-generated method stub
		try {
			TypedQuery<Topic> T = em.createQuery("SELECT t FROM Topic t", Topic.class);
			List<Topic> ListTopics = T.getResultList();
			return ListTopics;
		} catch (javax.persistence.NoResultException e) {
			return null;
		}
		
	}

	@Override
	public Topic findById(int idTopic) {
		// TODO Auto-generated method stub
		return null;
	}

	


/**** Music
 * 
 */
	
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
