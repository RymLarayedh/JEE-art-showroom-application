package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Admin;
import entities.Artist;
import entities.Artwork;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.FeedbackId;
import entities.Fields;
import entities.Gallery;
import entities.Music;
import entities.Reclamation;
import entities.TunisianCraft;
import entities.User;
import entities.VisualArt;

/**
 * Session Bean implementation class DataExample
 */
@Singleton
@Startup
public class DataExample {

	@EJB FeedbackManagmentRemote feedbackManagment;
	@EJB UserManagmentRemote usrManagment;
	@EJB ArtworkManagemetRemote artworkManagment;
	@EJB EventManagmentRemote eventManagment;
	@EJB EventUserManagmentRemote eventUserManagment;
	@EJB ForumManagementRemote forumManagement;
	
	@PersistenceContext(unitName = "Fanny-ejb")
	EntityManager em;
    /**
     * Default constructor. 
     */
    public DataExample() {
        // TODO Auto-generated constructor stub
    }
    
    @PostConstruct
	public void addExamples() {
    	//******************* Aymen

		Admin u1 = new Admin();
		u1.setUsername("aymen");
		u1.setPassword("aymen");
		u1.setActive(true);
		u1.setEmail("mohamedaymen.elarbi@esprit.tn");
		u1.setFirstName("Aymen");
		u1.setLastName("Elarbi");
		
		Artist u12 = new Artist();
		u12.setUsername("ines");
		u12.setPassword("ines");
		u12.setActive(true);
		u12.setEmail("ines.wannen@esprit.tn");
		u12.setFirstName("ines");
		u12.setLastName("Wannen");
		
		Gallery u13 = new Gallery();
		u13.setUsername("gallery");
		u13.setPassword("gallery");
		u13.setActive(true);
		u13.setEmail("zimouarbi@gmail.com");
		u13.setFirstName("zimouarbi");
		u13.setLastName("EUREKA");
		
		List<Fields> Lf = new ArrayList();
		Lf.add(new Fields("Music"));
		Lf.add(new Fields("Paintings"));
		Lf.add(new Fields("Photography"));
		Lf.add(new Fields("Tunisian Craft"));
		Lf.add(new Fields("Sculpture"));
		
		for(Fields f:Lf)
		{
			em.persist(f);
		}
		

		usrManagment.addUser(u1);
		usrManagment.addUser(u12);
		usrManagment.addUser(u13);

		//************* Rym
		Event e= new Event();
		Artist aa =new Artist();
		aa=(Artist) usrManagment.findById(2);
		Gallery g =new Gallery();
		g=(Gallery) usrManagment.findById(3);
		e.setGallery(g);
		e.setArtist(aa);
		Date d= new Date(2017-1900, 06, 16);
		e.setDateBegin(d);
		Date d2= new Date(2017-1900, 06, 17);
		e.setDateEnd(d2);
		e.setDescription("Amazing");
		e.setTitle("ART SHOWROOM");
		eventManagment.addEvent(e);
		
		
		EventUser EU = new EventUser();
		EventUserID ee =new EventUserID();
		ee.setIdEventPK(1);
		ee.setIdUserPK(1);		
		EU.setEtudiantCoursID(ee);
		EU.setStatus(1);
		eventUserManagment.addEventUser(EU);
		
		
		
		TunisianCraft a = new TunisianCraft();
		a.setPrice(20);
		boolean test=true;
		a.setState(test);
		a.setDescription("Chachia Rouge");
		a.setName("Chachia");
		a.setType("Tissu");
		a.setQuantity(2);
		artworkManagment.addTunisianCraft(a);
		
		
		Reclamation r = new Reclamation();
		FeedbackId feedbackId =new FeedbackId();
		feedbackId.setArtworkId(1);
		feedbackId.setUserId(1);
		r.setFeedbackId(feedbackId);
		r.setBody("I don't like this");
		r.setDegree(1);
		r.setDate(d);
		r.setHandle(0);
		feedbackManagment.addReclamation(r);
		
		/********Ines
		 * 
		 */
		Music m = new Music();
		m.setName("sia");
		forumManagement.addMusic(m);

	}

    

}
