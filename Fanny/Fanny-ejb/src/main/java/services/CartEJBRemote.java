package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Cart;
import entities.User;

@Remote
public interface CartEJBRemote {
	
	public void addCart(Cart cart);
	public List<Cart> getAllCarts();
	public void updateCart (Cart cart);
	public List<Cart> getMyCarts(User u);
	
	
	
}
