package tunisiancraft;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import entities.Artwork;
import entities.Like;
import entities.User;
import services.LikeManagementRemote;


@ManagedBean
@ViewScoped
public class LikeBean {
public Like L =new Like();
 public User U = new User();
 public Artwork art =new Artwork();
public boolean isLiked ;
	
 @EJB
 public LikeManagementRemote LikeMan ;
 public long nbr ; 
  public Like getL() {
	return L;
}

public void setL(Like l) {
	L = l;
}

public User getU() {
	return U;
}

public void setU(User u) {
	U = u;
}

public Artwork getArt() {
	return art;
}

public void setArt(Artwork art) {
	this.art = art;
}

private boolean enabled;

    public void toggle() {
        enabled = !enabled;
    }
    
    public void Like ()
   { 
        U.setIdUser(2);
        art.setIdArtwork(1);
        L.setUser(U);
        L.setArtwork(art);
	if (L instanceof Like)
	LikeMan.addLike(L);
    enabled = !enabled;
     isLiked=!isLiked;
	
	}
    public void Unlike ()
    {
     enabled = !enabled;
    LikeMan.findByUserartID(U, art);
 	LikeMan.deleteLike(LikeMan.findByUserartID(U, art));
 	}

public long nbrlike()
{
	
 	art.setIdArtwork(1);
 
 nbr = LikeMan.nbrlike(art);
 
 return nbr;
}
public boolean enable (){
	return enabled;
}
public boolean disable (){
	return !enabled;
}
public boolean isEnabled()
{
	 U.setIdUser(2);
     art.setIdArtwork(1);
     L.setUser(U);
     L.setArtwork(art);
if ( !LikeMan.checkexistance(U, art)){	     
   return isLiked;
	}
else
   return !isLiked;
}
public boolean isnotEnabled() {
	 U.setIdUser(2);
     art.setIdArtwork(1);
     L.setUser(U);
     L.setArtwork(art);
if ( LikeMan.checkexistance(U, art)){	     
   return isLiked;
	}
else
   return !isLiked;
}

public boolean isLiked() {
	return isLiked;
}

public void setLiked(boolean isLiked) {
	this.isLiked = isLiked;
}

}
