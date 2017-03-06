package services;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Category;
import entities.Gallery;
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

}
