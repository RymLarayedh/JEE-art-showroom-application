package services;

import java.util.List;

import javax.ejb.Remote;

import entities.Category;
import entities.Music;
import entities.Topic;


@Remote
public interface ForumManagementRemote {
	public void addCategory( Category c);
	public void updateCategory ( Category c);
	public void deleteCategory ( Category c);
	public List<Category> findAllCategories ();
	public Category findCategoryByName(String name);

	
	
	/****** Topic ******/
	public void addTopic( Topic T );
	public void updateTopoc (Topic T);
	public void deleteTopic ( Topic T);
	public List<Topic> findAllTopics();
	public Topic findById(int idTopic);
	
	
	/*****Music*****/
	public void addMusic(Music m );
	public void updateMusic(Music m );
	public void deleteMusic ( Music m);
	public List<Music> findAllMusic();

	
	
}
