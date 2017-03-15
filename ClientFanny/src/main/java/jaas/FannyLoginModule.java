package jaas;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import business.UserManagmentDelegate;
import entities.User;

public class FannyLoginModule implements LoginModule {
	private boolean authentificationSuccessFlag = false;
	
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;

	}

	@Override
	public boolean login() throws LoginException {
        if (callbackHandler == null)
            throw new LoginException("Error: no CallbackHandler available " +
                        "to garner authentication information from the user");

		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("username");
		callbacks[1] = new PasswordCallback("Password", false);
		try {
			callbackHandler.handle(callbacks);
		} catch (IOException | UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String name = ((NameCallback) callbacks[0]).getName();
		String password = new String(((PasswordCallback) callbacks[1]).getPassword());
		if(UserManagmentDelegate.loginUser(name, password))
		{
			System.out.println("authentification succss ...");
			authentificationSuccessFlag = true;
		}
		else
		{
			authentificationSuccessFlag = false ;
			throw new FailedLoginException("authentification failure"); 
		}
		return authentificationSuccessFlag;
	}

	@Override
	public boolean commit() throws LoginException {
	
		return authentificationSuccessFlag;
	}

	@Override
	public boolean abort() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		// TODO Auto-generated method stub
		return false;
	}

}
