package jaas;

import java.io.Serializable;
import java.security.Principal;

import entities.User;

public class FannyPrincipal implements Principal,Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String name  ;
	public FannyPrincipal(String name) {
		this.name = name;
	}
	@Override
	public String getName() {
		return name;
	}

	

}
