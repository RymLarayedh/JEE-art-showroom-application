package users.validators;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import entities.User;
import services.UserManagmentRemote;
import users.UserBean;

public class UserValidator implements Validator {

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		// TODO Auto-generated method stub
		String username = (String) value;
		if (userBean.getUserManagment().findByUsername(username) != null) {

			throw new ValidatorException(
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Entrée non valide", "username already exists"));
		}
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
