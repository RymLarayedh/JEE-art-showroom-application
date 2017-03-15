package jaas;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {

	public static void main(String[] args) {
		System.setProperty("java.security.auth.login.config", "jaasConfig.config");
		LoginContext loginContext = null;
		try {
			loginContext = new LoginContext("jaasConfig", new FannyCallbackHandler());
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			System.exit(0);
		}

		int i;
		for (i = 0; i < 3; i++) {
			try {
				System.out.println("Welcome...");
				loginContext.login();
			} catch (LoginException e) {
				System.err.println("Authentication failed:");
				System.err.println("  " + e.getMessage());
				try {
					Thread.currentThread().sleep(3000);
				} catch (Exception eh) {
					// ignore
				}
			}
		}
	      if (i == 3) {
	          System.out.println("Sorry");
	          System.exit(-1);
	      }

	      System.out.println("Authentication succeeded!");

	}

}
