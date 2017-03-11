package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Artwork;
import entities.TunisianCraft;
import entities.User;

@Remote
public interface ArtworkManagemetRemote {

	public void addTunisianCraft(TunisianCraft Tun);
	public TunisianCraft findById(int id);
	public Artwork findArtworkByID(int id);
	public TunisianCraft findByArtist(String name);
	public List<TunisianCraft> findByType(String type);
	public List<TunisianCraft> getAllTunisianCraft();
	
	/**Ines**/
	public List<Artwork> findAllArtworks();
	
}