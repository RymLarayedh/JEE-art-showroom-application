package Entry;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Artist;
import entities.Category;
import entities.Fields;
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
		Fields music = new Fields();
		music.setIdField(1);
		music.setLibelle("Music");
		userManagment.addFields(music, userManagment.findById(2));
		List<Artist> LA = userManagment.getAllArtists();
		for(Artist i : LA)
		{
			System.out.println("Inside");
			
		}
		
	}

}
