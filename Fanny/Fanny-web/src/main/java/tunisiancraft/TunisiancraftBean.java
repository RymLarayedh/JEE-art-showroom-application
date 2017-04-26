package tunisiancraft;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.imageio.ImageIO;
import javax.persistence.NamedEntityGraph;
import javax.servlet.http.Part;


import entities.TunisianCraft;

import services.ArtworkManagemetRemote;

@ManagedBean
@ViewScoped

public class TunisiancraftBean {

	@EJB
	private ArtworkManagemetRemote TunMan  ;
	private TunisianCraft TunC = new TunisianCraft();
    private Part picture ;
    private List<TunisianCraft> listTunc = new ArrayList<TunisianCraft>() ;
    private boolean formDisplay =false ;

    public boolean isFormDisplay() {
		return formDisplay;
	}

	public void setFormDisplay(boolean formDisplay) {
		this.formDisplay = formDisplay;
	}

	@PostConstruct
    public void initialization (){
    	listTunc = TunMan.getAllTunisianCraft();
    }

	public List<TunisianCraft> getListTunc() {
		return listTunc;
	}

	public void setListTunc(List<TunisianCraft> listTunc) {
		this.listTunc = listTunc;
	}

	public Part getPicture() {
		return picture;
	}
	public void setPicture(Part picture) {
		this.picture = picture;
	}
	public TunisianCraft getTunC() {
		return TunC;
	}
	public void setTunC(TunisianCraft tunC) {
		TunC = tunC;
	}
	/*public byte[] uploadpic(){
		
		byte[] bFile = new byte[(int) pictureoo.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(pictureoo);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}
		return bFile ;
	}
	*/
	public String doSave(){
		
		String navTo = " ";
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		TunC.setDateOfOublication(date);

		byte[] bFile = new byte[(int) picture.getSize()];
		try {
         picture.getInputStream().read(bFile);
			
		} catch (Exception e) {
		}
	     TunC.setPicture(bFile ); 
		TunMan.addTunisianCraft(TunC);
		
		return navTo;
	}

	public String doRemove(){
		String navTo = " ";
		TunMan.deleteTunisianCraft(TunC);
		initialization();
		formDisplay=false;
		return navTo;
	}

	public String doNew(){
		String navTo = " ";
  TunC= new TunisianCraft() ;
		formDisplay=true;
		return navTo;
	}
}

