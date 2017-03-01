package services;

import javax.ejb.Remote;

import entities.Category;

@Remote
public interface ForumManagementRemote {
	public void addCategory( Category c);
	public void updateCategory ( Category c);
	public void deleteCategory ( Category c);

}
