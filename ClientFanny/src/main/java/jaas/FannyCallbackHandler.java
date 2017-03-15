package jaas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class FannyCallbackHandler implements CallbackHandler {
	private String username;
	private String password;

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {

		int counter = 0;
		while (counter < callbacks.length)
		{
			if(callbacks[counter] instanceof NameCallback){
				NameCallback nameCallback = (NameCallback) callbacks[counter];
				nameCallback.setName(username);
			}
			else if (callbacks[counter] instanceof PasswordCallback)
			{
				PasswordCallback passwordCallback = (PasswordCallback) callbacks[counter];
				passwordCallback.setPassword(password.toCharArray());
			}
			counter++;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
