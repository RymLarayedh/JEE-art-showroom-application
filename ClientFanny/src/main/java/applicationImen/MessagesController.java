/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationImen;

import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.xml.soap.Text;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Artist;
import entities.Message;
import entities.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import services.MessagesEJB;
import services.MessagesEJBRemote;
import services.UserManagmentRemote;

/**
 * FXML Controller class
 *
 * @author PC-ASUS
 */
enum SpeechDirection {
	LEFT, RIGHT
}

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
	private JFXTextField searchBar;

	@FXML
	private JFXListView<JFXButton> ConvoList;
	int k = 0;
	InitialContext ctx;
	public static UserManagmentRemote userManagment;
	public static MessagesEJBRemote messageManagement;;

	// MessagesEJB proxyM;
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

		// TODO
		
		//User sender = new User();
		//User receiver = new User();
		//sender = LoginController.userLogedIn;
		//receiver = userManagment.findById(3);
		Set<User> listConv = messageManagement.ConversationList(LoginController.userLogedIn);
		for (User user : listConv) {
			if(user.getIdUser()==LoginController.userLogedIn.getIdUser())
			{
				
			}
			else
			
			{
				JFXButton txt = new JFXButton(user.toString());
			
			txt.setTooltip(new Tooltip(String.valueOf(user.getIdUser())));
			txt.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					int idSelected = Integer.valueOf(txt.getTooltip().getText());
					User u = LoginController.userManagment.findById(idSelected);
					List<Message> listmsg = messageManagement.msgList(userManagment.findById(idSelected),LoginController.userLogedIn);
					System.out.println(messageManagement.msgList(userManagment.findById(3),LoginController.userLogedIn));
					vbox.getChildren().clear();
					System.out.println(listmsg);
					for (Message m : listmsg) {
						javafx.scene.text.Text t = new javafx.scene.text.Text(m.getContent() + "\n" + m.getTime());
						StackPane p = new StackPane();
						if (m.getSender().getIdUser() == LoginController.userLogedIn.getIdUser()) {
							p.setStyle("-fx-background-color: LightBlue;");
							p.setPadding(new Insets(10, 10, 10, 10));
						} else {
							p.setStyle("-fx-background-color: LightGreen;");
							p.setPadding(new Insets(10, 10, 10, 10));
						}
						p.setPrefWidth(t.getWrappingWidth());
						p.getChildren().add(t);
						p.setPadding(new Insets(10, 10, 10, 10));
						StackPane.setAlignment(t, Pos.CENTER_LEFT);
						vbox.getChildren().add(p);
						System.out.println(m);

					}
				}
			});
			
			ConvoList.getItems().add(txt);
		}}
		// Set<User> contacts= messageManagement.ConversationList(sender);
		/*
		 * for(User u:contacts) { String s=u.getFirstName()+" "+u.getLastName();
		 * ConvoList.getItems().add(s); }
		 */
	}

	@FXML
	private void SendMessage(ActionEvent event) throws NamingException {

		String content;
		content = MsgTextArea.getText();
		MsgTextArea.clear();
		User receiver = new User();
		receiver = userManagment.findById(2);
		Message message = new Message();
		message.setContent(content);
		message.setReceiver(receiver);
		message.setSender(LoginController.userLogedIn);
		messageManagement.addMessage(message);
		
		
		List<Message> listmsg = messageManagement.msgList(message.getReceiver(), LoginController.userLogedIn);
		vbox.getChildren().clear();
		System.out.println(listmsg);

		for (Message m : listmsg) {
			javafx.scene.text.Text t = new javafx.scene.text.Text();
			t.setText(m.getContent() + "\n" + m.getTime());
			StackPane p = new StackPane();
			if (m.getSender().getIdUser() == LoginController.userLogedIn.getIdUser()) {
				p.setStyle("-fx-background-color: LightBlue;");
				p.setPadding(new Insets(10, 10, 10, 10));
			} else
			{
				p.setStyle("-fx-background-color: LightGreen;");
				p.setPadding(new Insets(10, 10, 10, 10));
			}
			p.setPrefWidth(t.getWrappingWidth());
			p.getChildren().add(t);
			StackPane.setAlignment(t, Pos.CENTER_LEFT);
			vbox.getChildren().add(p);
			
		}

		/*TextField t = new TextField();
		t.setText(content);
		t.setEditable(false);
		vbox.setAlignment(Pos.TOP_CENTER);
		vbox.setSpacing(10);
		t.setStyle("-fx-background-color: LightBlue");
		Label lab = new Label();
		lab.setText(content);
		// lab.setMinWidth(Region.USE_PREF_SIZE);
		lab.setMaxWidth(180);
		lab.setWrapText(true);
		javafx.scene.text.Text hi = new javafx.scene.text.Text();
		hi.setText(content);
		// hi.setStyle("-fx-background-color: LightBlue");
		// hi.setStyle("-fx-padding: 50;");
		// hi.getS
		// hi.setFill(Color.LIGHTBLUE);
		StackPane p = new StackPane();
		// p.setPrefSize(
		p.setStyle("-fx-background-color: LightBlue;");

		// p.setStyle("-fx-padding: 50;");
		p.setPrefWidth(hi.getWrappingWidth());
		p.getChildren().add(hi);
		StackPane.setAlignment(hi, Pos.CENTER_LEFT);
		p.setPadding(new Insets(20, 20, 20, 10));
		// vbox.autosize();

		vbox.getChildren().add(p);*/
		ConvoList.getItems().clear();
		Set<User> listConv = messageManagement.ConversationList(LoginController.userLogedIn);
		for (User user : listConv) {
			if(user.getIdUser()==LoginController.userLogedIn.getIdUser())
			{
				
			}
			else
			
			{
			// ConvoList.getItems().add(u);
						JFXButton txt = new JFXButton(user.toString());
						txt.setTooltip(new Tooltip(String.valueOf(user.getIdUser())));
						txt.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								int idSelected = Integer.valueOf(txt.getTooltip().getText());
								User u = LoginController.userManagment.findById(idSelected);
								List<Message> listmsg = messageManagement.msgList(u, LoginController.userLogedIn);
								vbox.getChildren().clear();
								System.out.println(listmsg);

								for (Message m : listmsg) {
									javafx.scene.text.Text t = new javafx.scene.text.Text();
									t.setText(m.getContent() + "\n" + m.getTime());
									StackPane p = new StackPane();
									if (m.getSender().getIdUser() == LoginController.userLogedIn.getIdUser()) {
										p.setStyle("-fx-background-color: LightBlue;");
										p.setPadding(new Insets(10, 10, 10, 10));
									} else {
										p.setStyle("-fx-background-color: LightGreen;");
										p.setPadding(new Insets(10, 10, 10, 10));
									}
									p.setPrefWidth(t.getWrappingWidth());
									p.getChildren().add(t);
									StackPane.setAlignment(t, Pos.CENTER_LEFT);
									vbox.getChildren().add(p);
									
								}
							}
						});

						ConvoList.getItems().add(txt);
					}}

		}
		

	

	@FXML
	private void filterConversation(KeyEvent event) {
		ConvoList.getItems().clear();
		Set<User> listConv = messageManagement.ConversationListFilter(LoginController.userLogedIn, searchBar.getText());
		for (User user : listConv) {
			// ConvoList.getItems().add(u);
			if(user.getIdUser()==LoginController.userLogedIn.getIdUser())
			{
				
			}
			else
			
			{
			// ConvoList.getItems().add(u);
						JFXButton txt = new JFXButton(user.toString());
						txt.setTooltip(new Tooltip(String.valueOf(user.getIdUser())));
						txt.setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent event) {
								int idSelected = Integer.valueOf(txt.getTooltip().getText());
								User u = LoginController.userManagment.findById(idSelected);
								List<Message> listmsg = messageManagement.msgList(u, LoginController.userLogedIn);
								vbox.getChildren().clear();
								System.out.println(listmsg);

								for (Message m : listmsg) {
									javafx.scene.text.Text t = new javafx.scene.text.Text();
									t.setText(m.getContent() + "\n" + m.getTime());
									StackPane p = new StackPane();
									if (m.getSender() == LoginController.userLogedIn) {
										p.setStyle("-fx-background-color: LightGreen;");
										p.setPadding(new Insets(10, 10, 10, 10));
									} else {
										p.setStyle("-fx-background-color: LightBlue;");
										p.setPadding(new Insets(10, 10, 10, 10));
									}
									p.setPrefWidth(t.getWrappingWidth());
									p.getChildren().add(t);
									StackPane.setAlignment(t, Pos.CENTER_LEFT);
									vbox.getChildren().add(p);
									
								}
							}
						});

						ConvoList.getItems().add(txt);
					}}

		

	}

	@FXML
	public void ShowConvo(MouseEvent event) {

		/*
		 * vbox.getChildren().clear(); User
		 * u=ConvoList.getSelectionModel().getSelectedItem(); List<Message>
		 * listmsg= messageManagement.msgList(u, LoginController.userLogedIn);
		 * for(Message m:listmsg) { javafx.scene.text.Text t= new
		 * javafx.scene.text.Text(m.getContent()+"\n" +m.getTime()); StackPane
		 * p=new StackPane(); if(m.getSender()==LoginController.userLogedIn) {
		 * p.setStyle("-fx-background-color: LightGreen;"); p.setPadding(new
		 * Insets(10,10,10,10)); } else { p.setStyle(
		 * "-fx-background-color: LightBlue;"); p.setPadding(new
		 * Insets(10,10,10,10)); } p.setPrefWidth(t.getWrappingWidth());
		 * p.getChildren().add(t); StackPane.setAlignment(t,Pos.CENTER_LEFT);
		 * vbox.getChildren().add(p); System.out.println(m);
		 * 
		 * }
		 */

	}

}
