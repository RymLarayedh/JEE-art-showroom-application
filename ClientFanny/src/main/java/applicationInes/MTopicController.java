package applicationInes;

import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import services.ForumManagementRemote;
import services.UserManagmentRemote;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Category;
import entities.Topic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
/**
 * 
 * @author Ines
 *
 */

public class MTopicController implements Initializable{

	@FXML
	private JFXButton addTopic;
	  @FXML
	    private JFXComboBox<String> CategoriesTopic;

	    @FXML
	    private JFXTextArea descriptionTopic;

	    @FXML
	    private JFXButton browse;
	    
	    @FXML
	    private JFXTextField titleTopic;
		InitialContext ctx;
		public static ForumManagementRemote forumManagement;
		public static UserManagmentRemote userManagement;
		ObservableList<String> categories = FXCollections.observableArrayList();
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			try {
				ctx = new InitialContext();
				forumManagement= (ForumManagementRemote) ctx
						.lookup("/Fanny-ear/Fanny-ejb/ForumManagement!services.ForumManagementRemote");
				userManagement = (UserManagmentRemote) ctx
						.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
				Category p = new Category();

			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Category> categoriesList = new ArrayList();
			categoriesList= forumManagement.findAllCategories();
			for (Category c : categoriesList)
			{
				categories.add(c.getName());
			}
			CategoriesTopic.setItems(categories);
			Category cat = new Category();
	    	cat=forumManagement.findCategoryByName("noussa");
	    	System.out.println(cat);

			
			
		}

	 

	    @FXML
	    void addTopicc(ActionEvent event) {
	    	
	    	Topic T = new Topic();
	    	Category c = new Category();
	    	System.out.println(CategoriesTopic.getValue());
	    	c=forumManagement.findCategoryByName(CategoriesTopic.getValue());
	    	LocalDateTime now = LocalDateTime.now();
			Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
			Date date = Date.from(instant);
	    	T.setTitle(titleTopic.getText());
	    	T.setAddedAt(date);
	    	T.setAddedBy(LoginController.userLogedIn);
	    	T.setText(descriptionTopic.getText());
	    	T.setCatgory(c);
	    	forumManagement.addTopic(T);;
	    	

	    }

	    @FXML
	    void browsee(ActionEvent event) {

	    }



	
}
