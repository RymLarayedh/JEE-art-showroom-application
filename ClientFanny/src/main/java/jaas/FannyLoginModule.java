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
	
    private Subject subject = null;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private FannyPrincipal fannyPrincipal = null;

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
			e.printStackTrace();
		}
		String name = ((NameCallback) callbacks[0]).getName();
		String password = new String(((PasswordCallback) callbacks[1]).getPassword());
		if(UserManagmentDelegate.loginUser(name, password))
		{
			fannyPrincipal = new FannyPrincipal(name);
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
		authentificationSuccessFlag = false ;
		if(subject!=null && !subject.getPrincipals().contains(fannyPrincipal))
		{
			subject.getPrincipals().add(fannyPrincipal); 
			authentificationSuccessFlag = true;
		}
		return authentificationSuccessFlag;
	}

	@Override
	public boolean abort() throws LoginException {
		if(subject != null && fannyPrincipal!=null && subject.getPrincipals().contains(fannyPrincipal))
		{
			subject.getPrincipals().remove(fannyPrincipal);
		}
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(fannyPrincipal);
		subject = null;
		return true; 
	}

}
