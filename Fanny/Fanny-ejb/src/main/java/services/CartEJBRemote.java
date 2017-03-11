package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Cart;

@Remote
public interface CartEJBRemote {
	
	public void addCart(Cart cart);
	public List<Cart> getAllCarts();
	public void updateCart (Cart cart);
	
	
}
