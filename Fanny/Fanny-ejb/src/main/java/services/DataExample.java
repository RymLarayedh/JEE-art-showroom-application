package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Admin;
import entities.Artist;
import entities.Artwork;
import entities.Cart;
import entities.CartId;
import entities.Category;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
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
	@EJB CartEJBRemote cartManagement;
	
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
		
		
		Admin admin = new Admin();
		admin.setUsername("inesW");
		admin.setPassword("ines");
		admin.setActive(true);
		admin.setEmail("ines.elarbi@esprit.tn");
		admin.setFirstName("ines");
		admin.setLastName("ines");
		
		
		

		for (Fields f : Lf) {
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
		

		//************Ines
		
		Category cat1 = new Category();
		Date dc1= new Date(2017-1900, 04, 22);
		cat1.setAddedAt(dc1);
		cat1.setAddedBy(admin);
		cat1.setName("Music Lovers");
		
		Category cat2 = new Category();
		cat2.setAddedAt(dc1);
		cat2.setAddedBy(admin);
		cat2.setName("Tunisian Craft");
		
		Category cat3 = new Category();
		cat3.setAddedAt(dc1);
		cat3.setAddedBy(admin);
		cat3.setName("FeedBack Events");
		
		Set<Category> listCategories = new HashSet<Category>();
		listCategories.add(cat1);
		listCategories.add(cat2);
		listCategories.add(cat3);
		
		admin.setListCategories(listCategories);
		System.out.println("33333333333333333"+listCategories);
		System.out.println("admiiiiiiiiiiiiiiiiiiiin"+admin.getListCategories());
		
		usrManagment.addUser(admin);



//		
//		forumManagement.addCategory(cat1);
//		forumManagement.addCategory(cat2);
//		forumManagement.addCategory(cat3);


		
		
		
		Music m = new Music();
		m.setName("sia");
		forumManagement.addMusic(m);
		

		/****
		 * 
		 */
		
		Cart c1= new Cart();
		CartId cId= new CartId();
		cId.setArtworkId(1);
		cId.setBuyerId(2);
		c1.setIdCart(cId);
		c1.setDate("12/12/12");
		c1.setPrice(20014);
		c1.setQuantity(1);
		c1.setStatus(1);
		cartManagement.addCart(c1);

	}

    

}
