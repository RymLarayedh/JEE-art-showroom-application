package services;

import javax.ejb.Stateful;

import entities.Category;

/**
 * Session Bean implementation class ForumManagement
 */
@Stateful
public class ForumManagement implements ForumManagementRemote {

    /**
     * Default constructor. 
     */
    public ForumManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCategory(Category c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCategory(Category c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCategory(Category c) {
		// TODO Auto-generated method stub
		
	}

}
