package jaas;

import java.io.Serializable;
import java.security.Principal;

import entities.User;

public class FannyPrincipal implements Principal,Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String name  ;
	private User user;
	public FannyPrincipal(String name,User user) {
		this.name = name;
		this.user = user;
	}
	@Override
	public String getName() {
		return name;
	}
	public User getUser() {
		return user;
	}
	

}
