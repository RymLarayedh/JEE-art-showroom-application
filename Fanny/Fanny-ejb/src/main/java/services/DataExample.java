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
import entities.Fields;
import entities.Gallery;
import entities.User;

/**
 * Session Bean implementation class DataExample
 */
@Singleton
@Startup
public class DataExample {

	@EJB UserManagmentRemote usrManagment;
	
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
		u13.setUsername("g");
		u13.setPassword("g");
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


	}

    

}
