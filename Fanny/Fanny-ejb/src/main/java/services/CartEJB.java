package services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Cart;
import entities.User;

/**
 * Session Bean implementation class CartEJB
 */
@Stateless
public class CartEJB implements CartEJBRemote {
	
	
	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;


    /**
     * Default constructor. 
     */
    public CartEJB() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCart(Cart cart) {
		em.persist(em.merge(cart));
		
	}

	@Override
	public List<Cart> getAllCarts() {
		TypedQuery<Cart> q = em.createQuery("SELECT c FROM Cart c", Cart.class);
		List<Cart> Lcarts = q.getResultList();
		return Lcarts;
	}

	@Override
	public void updateCart(Cart cart) {
		em.merge(cart);
		
	}

	@Override
	public List<Cart> getMyCarts(User u) {
		TypedQuery<Cart> q = em.createQuery("SELECT c FROM Cart c where c.idCart.buyerId=:id", Cart.class);
		q.setParameter("id", u.getIdUser());
		List<Cart> Lcarts = q.getResultList();
		return Lcarts;
	}

}
