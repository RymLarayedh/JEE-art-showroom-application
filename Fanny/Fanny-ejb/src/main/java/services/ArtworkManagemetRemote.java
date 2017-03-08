package services;

import javax.ejb.Remote;

import entities.Artwork;
import entities.TunisianCraft;

@Remote
public interface ArtworkManagemetRemote {

	public void addTunisianCraft(TunisianCraft Tun);
	public TunisianCraft findById(int id);
	public Artwork findArtworkByID(int id);
}
