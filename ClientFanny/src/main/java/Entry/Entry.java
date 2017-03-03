package Entry;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Category;
import entities.User;
import services.ForumManagementRemote;
import services.UserManagmentRemote;

public class Entry {

	public static void main(String[] args) throws NamingException, AddressException, MessagingException {
		InitialContext ctx = new InitialContext();
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		UserManagmentRemote userManagment = (UserManagmentRemote) object;
		
		object = ctx.lookup("/Fanny-ear/Fanny-ejb/ForumManagement!services.ForumManagementRemote");
		ForumManagementRemote frmManagment = (ForumManagementRemote) object;
		
		//Category ctg = new Category(1,"aa");
		
		//frmManagment.addCategory(ctg);
		
		System.out.println(userManagment.getAllArtists());
		
	}

}
