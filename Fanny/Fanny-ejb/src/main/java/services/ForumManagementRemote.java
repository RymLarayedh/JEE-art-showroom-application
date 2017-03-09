package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Category;
import entities.Music;


@Remote
public interface ForumManagementRemote {
	public void addCategory( Category c);
	public void updateCategory ( Category c);
	public void deleteCategory ( Category c);
	public List<Category> findAllCategories ();
	
	
	public void addMusic(Music m );
	public void updateMusic(Music m );
	public void deleteMusic ( Music m);
	public List<Music> findAllMusic();

	
	
}
