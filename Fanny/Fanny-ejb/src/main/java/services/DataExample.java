package services;

import java.util.ArrayList;
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
import entities.FeedbackId;
import entities.Fields;
import entities.Gallery;
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
		
		Artist u12 = new Artist();
		u12.setUsername("ines");
		u12.setPassword("ines");
		u12.setActive(true);
		u12.setEmail("ines.wannen@esprit.tn");
		u12.setFirstName("ines");
		
		Gallery u13 = new Gallery();
		u13.setUsername("gallery");
		u13.setPassword("gallery");
		u13.setActive(true);
		u13.setEmail("zimouarbi@gmail.com");
		u13.setFirstName("zimouarbi");
		
		List<Fields> Lf = new ArrayList();
		Lf.add(new Fields("Music"));
		Lf.add(new Fields("Arts"));
		Lf.add(new Fields("Visual Arts"));
		Lf.add(new Fields("Tunisian Craft"));
		
		for(Fields f:Lf)
		{
			em.persist(f);
		}
		

		usrManagment.addUser(u1);
		usrManagment.addUser(u12);
		usrManagment.addUser(u13);

		//************* Rym
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
		//r.setUser((User)u1);
		//r.setArtwork((Artwork)a);
		FeedbackId feedbackId =new FeedbackId();
		feedbackId.setArtworkId(1);
		feedbackId.setArtworkId(1);
		r.setFeedbackId(feedbackId);
		r.setBody("a");
		r.setDegree(1);
		//feedbackManagment.addReclamation(r);

	}

    

}
