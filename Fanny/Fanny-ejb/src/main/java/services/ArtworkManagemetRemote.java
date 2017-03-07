package services;

import javax.ejb.Remote;

import entities.TunisianCraft;

@Remote
public interface ArtworkManagemetRemote {

	public void addTunisianCraft(TunisianCraft Tun);

}
