package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Music;

@Remote
public interface MusicManagementRemote {
	
	public void addMusic(Music m );
	public void updateMusic(Music m );
	public void deleteMusic ( Music m);
	public List<Music> findAllMusic();

}
