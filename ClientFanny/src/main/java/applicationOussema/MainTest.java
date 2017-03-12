package applicationOussema;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import entities.Artwork;
import entities.Feedback;
import entities.Like;
import entities.User;
import services.LikeManagementRemote;

public class MainTest {

	public MainTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws NamingException {
		InitialContext ctx = new InitialContext();
		// TODO Auto-generated method stub
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxy = (LikeManagementRemote) objet;
		Artwork aa = new Artwork();
		aa.setIdArtwork(3);
		User user = new User();
		user.setIdUser(1);
		 Like L = new Like ();
		 L.setArtwork(aa);
			Like J=(Like) proxy.FindbyId(1);

		 L.setUser(user);
		 proxy.deleteLike(J);
		 System.out.println(J.getFeedbackId());
		 
		 
		
		//System.out.println(		proxy.findByUserartIDR(user, aa));
		
	}

}
