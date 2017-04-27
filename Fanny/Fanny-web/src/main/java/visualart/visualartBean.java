package visualart;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import entities.TunisianCraft;
import entities.VisualArt;
import services.ArtworkManagemetRemote;
import services.VisualArtworkEJBRemote;

@ManagedBean
@ViewScoped
public class visualartBean {

	@EJB
	private VisualArtworkEJBRemote visualArtworkEJBRemote ;
	private VisualArt visualArt =new VisualArt();
    private Part picture ;
    private List<VisualArt> listvisualArt = new ArrayList<VisualArt>() ;
 

	@PostConstruct
    public void initialization (){
    	listvisualArt = visualArtworkEJBRemote.findAllVisualArt2();
    }

	

	public Part getPicture() {
		return picture;
	}
	public void setPicture(Part picture) {
		this.picture = picture;
	}
	
	public String doSave(){
		
		String navTo = " ";
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		visualArt.setDateOfOublication(date);

		byte[] bFile = new byte[(int) picture.getSize()];
		try {
         picture.getInputStream().read(bFile);
			
		} catch (Exception e) {
		}
		visualArt.setPicture(bFile ); 
		visualArtworkEJBRemote.addVisualArt(visualArt);
		
		return navTo;
	}



	public VisualArt getVisualArt() {
		return visualArt;
	}



	public void setVisualArt(VisualArt visualArt) {
		this.visualArt = visualArt;
	}



	public List<VisualArt> getListvisualArt() {
		return listvisualArt;
	}



	public void setListvisualArt(List<VisualArt> listvisualArt) {
		this.listvisualArt = listvisualArt;
	}

//	public String doRemove(){
//		String navTo = " ";
//		TunMan.deleteTunisianCraft(TunC);
//		initialization();
//		formDisplay=false;
//		return navTo;
//	}
//
//	public String doNew(){
//		String navTo = " ";
//  TunC= new TunisianCraft() ;
//		formDisplay=true;
//		return navTo;
//	}
}
