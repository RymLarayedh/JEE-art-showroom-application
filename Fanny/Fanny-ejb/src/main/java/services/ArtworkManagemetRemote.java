package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Artwork;
import entities.TunisianCraft;
import entities.User;
import entities.VisualArt;

@Remote
public interface ArtworkManagemetRemote {

	public void addTunisianCraft(TunisianCraft Tun);
	public TunisianCraft findById(int id);
	public Artwork findArtworkByID(int id);
	public List<TunisianCraft> findByArtist(int id);
	public List<TunisianCraft> findByType(String type);
	public List<TunisianCraft> getAllTunisianCraft();
	public void upadateTunisianCraft(TunisianCraft TC);
	public void deleteTunisianCraft(TunisianCraft TC);
	byte[] findPictureByProductName(String name);

	/**Ines**/
	public List<Artwork> findAllArtworks();
}