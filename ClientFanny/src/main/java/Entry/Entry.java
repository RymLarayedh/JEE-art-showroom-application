package Entry;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Artist;
import entities.ArtistFields;
import entities.ArtistFollowers;
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
		
		/*User u = userManagment.findById(3);
		userManagment.blockUser(u);
		userManagment.unblockUser(u);*/
		
		//Category ctg = new Category(1,"aa");
		
		//frmManagment.addCategory(ctg);
		
		//System.out.println(userManagment.getAllArtists());
		/*Fields music = new Fields();
		music.setIdField(2);
		Fields music1 = new Fields();
		music1.setIdField(1);
		userManagment.addFields(music, userManagment.findById(2));
		userManagment.addFields(music1, userManagment.findById(2));
		List<Artist> LA = userManagment.getAllArtists();
		for(Artist i : LA)
		{
			System.out.println("Inside");
			for(ArtistFields af:i.getLfields())
			{
				System.out.println(af.getField().getLibelle());
			}
			
		}
		
		userManagment.removeFields(music, userManagment.findById(2));
		LA = userManagment.getAllArtists();
		for(Artist i : LA)
		{
			System.out.println("Inside2");
			for(ArtistFields af:i.getLfields())
			{
				System.out.println(af.getField().getLibelle());
			}
			
		}*/

		//userManagment.addFollower(userManagment.findById(1), userManagment.findById(2));
		Artist x = (Artist) userManagment.findById(2);
		for(ArtistFollowers u :x.getFollowers())
		{
			System.out.println(u.getUser());
		}
		
		List<Artist> LA = userManagment.getAllFollowed(userManagment.findById(2));
		
		for(Artist u : LA)
		{
			System.out.println(u);
		}
		
	}

}
