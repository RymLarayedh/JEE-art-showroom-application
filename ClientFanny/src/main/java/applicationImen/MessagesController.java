/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationImen;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;

import entities.Artist;
import entities.Message;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.MessagesEJB;
import services.MessagesEJBRemote;
import services.UserManagmentRemote;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
public class MessagesController implements Initializable {

    @FXML
    private Button SendButton;
    @FXML
    private VBox vbox;
    
    @FXML
    private JFXTextArea MsgTextArea;
    @FXML
    private Pane Discussion = new AnchorPane();
    @FXML
    private JFXListView<String> ConvoList;
    int k=0;
    InitialContext ctx;
	public static UserManagmentRemote userManagment;
	public static MessagesEJBRemote messageManagement;;
	
	
    //MessagesEJB proxyM;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			ctx = new InitialContext();
		

		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
		userManagment = (UserManagmentRemote) object;
		Object object2 = ctx.lookup("/Fanny-ear/Fanny-ejb/MessagesEJB!services.MessagesEJBRemote");
		messageManagement = (MessagesEJBRemote) object2;
    	} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//ConvoList.getItems().addAll("hi", "wassup", "hola");
    	
        // TODO
    	Label lab=new Label();
    	Label lab1=new Label();
    	Label lab2=new Label();
    	User sender =new User();
		User receiver= new User();
		sender= userManagment.findById(1);
		receiver= userManagment.findById(2);
    	/*lab2.setStyle("-fx-shape: 'M 45.673,0 C 67.781,0 85.703,12.475 85.703,"
    			+ "27.862 C 85.703,43.249 67.781,55.724 45.673,55.724 C 38.742,55."
    			+ "724 32.224,54.497 26.539,52.34 C 15.319,56.564 0,64.542 0,64.542"
    			+ " C 0,64.542 9.989,58.887 14.107,52.021 C 15.159,50.266 15.775"
    			+ ",48.426 16.128,46.659 C 9.618,41.704 5.643,35.106 5.643,27"
    			+ ".862 C 5.643,12.475 23.565,0 45.673,0 M 45."
    			+ "673,2.22 C 24.824,2.22 7.862,13.723 7.862,27.863 C 7"
    			+ ".862,34.129 11.275,40.177 17.472,44.893 L 18.576,45.734"
    			+ " L 18.305,47.094 C 17.86,49.324 17.088,51.366 16.011,53.163 C 15.6"
    			+ "7,53.73 15.294,54.29 14.891,54.837 C 18.516,53.191 22.312,51"
    			+ ".561 25.757,50.264 L 26.542,49.968 L 27.327,50.266 C 32.911,52.385 39.255,53.505 "
    			+ "45.673,53.505 C 66.522,53.505 83.484,42.002 83.484,27.862 C 83.484,13.722 66.522,2.22 45.673,2.22 L 45.673,2.22 z ';"
    			+ "-fx-background-color: black, white;"
    			+ "-fx-background-insets: 0,1;"
    			+ "-fx-padding: 50;");
    	lab1.getStyleClass().add("label");*/
    	lab.setText("hel");
    	lab1.setText("huhu");
    	lab2.setText("hhihi");
    	Set<User> listConv = messageManagement.ConversationList(sender);
    	for(User u:listConv)
    	{
    		ConvoList.getItems().add(u.getFirstName()+" "+u.getLastName());
    	}
    	//Set<User> contacts= messageManagement.ConversationList(sender);
    	/*for(User u:contacts)
    	{
    		String s=u.getFirstName()+" "+u.getLastName();
    		ConvoList.getItems().add(s);
    	}*/
    }   

    @FXML
    private void SendMessage(ActionEvent event) throws NamingException {
    	
		
		String content;
    	content=MsgTextArea.getText();
    	MsgTextArea.clear();
    	User sender =new User();
		User receiver= new User();
		sender= userManagment.findById(1);
		receiver= userManagment.findById(2);
		Message message=new Message();
		message.setContent(content);
		message.setReceiver(receiver);
		message.setSender(sender);
		messageManagement.addMessage(message);
		
    	
    	Label lab=new Label();
    	lab.setText(content);
    	//Discussion.setTopAnchor(lab, (double) 10);
    	lab.setTranslateY(14);
    	lab.setTranslateX(21);
    	Label lib=new Label();
    	lib.setText(content);
    	lib.setStyle("-fx-border-color:red;-fx-margin:20; -fx-background-color: blue;");
    	//lib.setPadding(new Insets(100));
    	Discussion.getChildren().add(lab);
    	Discussion.getChildren().add(lib);
    	Set<User> listConv = messageManagement.ConversationList(sender);
    	for(User u:listConv)
    	{
    		ConvoList.getItems().add(u.getFirstName()+" "+u.getLastName());
    	}
    	
    	
    }
    
}
