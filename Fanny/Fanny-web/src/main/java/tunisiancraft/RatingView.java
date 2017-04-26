package tunisiancraft;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.RateEvent;

import entities.Artwork;
import entities.Rating;
import entities.User;
import services.LikeManagementRemote;
 
@ManagedBean
@ViewScoped
public class RatingView {
	public Rating R =new Rating();
     public User U = new User();
	 public Artwork art =new Artwork();

	
	 @EJB
  public LikeManagementRemote LikeMan ;
    private Integer rating1;
    private Integer rating2;  
    
    private Integer rating3 ;    
    public RatingView() {
		super();
	}


	private Integer rating4 = 3;
     
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue());
        FacesContext.getCurrentInstance().addMessage(null, message);
        U.setIdUser(2);
        art.setIdArtwork(1);
        R.setUser(U);
        R.setArtwork(art);
        R.setNote((double)rating3);
        if(LikeMan.checkexistanceR(U, art)){
        	LikeMan.addRating(R);
        }
        else{ 
        	Rating R1 = LikeMan.findByUserartIDR(U, art);
        	R1.setNote((double)rating3);
	      LikeMan.upadateRating(R1);
        
    }
    }
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
        Rating R1 = LikeMan.findByUserartIDR(U, art);
    	R1.setNote(0.0);
        LikeMan.upadateRating(R1);
    
    }
 
    public Integer getRating1() {
        return rating1;
    }
 
    public void setRating1(Integer rating1) {
        this.rating1 = rating1;
    }
 
    public Integer getRating2() {
        return rating2;
    }
 
    public void setRating2(Integer rating2) {
        this.rating2 = rating2;
    }
 
    public Integer getRating3() {
        return rating3;
    }
 
    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
 
    public Integer getRating4() {
        return rating4;
    }
 
    public void setRating4(Integer rating4) {
        this.rating4 = rating4;
    }
   
   
    public Integer exist()
	   { 
		   U.setIdUser(2);
	       art.setIdArtwork(1);
	    		 return LikeMan.findByUserartIDR(U, art).getNote().intValue();  
	     
	      }
    
}