package applicationImen;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.IntStream;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import entities.Artwork;
import entities.Cart;
import entities.CartId;
import entities.TunisianCraft;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import services.ArtworkManagemetRemote;
import services.CartEJBRemote;
import services.UserManagmentRemote;

public class CartController implements Initializable{
	@FXML
	private VBox shVbox;
	InitialContext ctx;
	public static CartEJBRemote cartManagement;
	ArtworkManagemetRemote artManagement;
	UserManagmentRemote userManagment;
	static Cart cart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("hahah");
		// TODO Auto-generated method stub
		Object object3;
		Object obj;
		Object object2;
		try {
			ctx = new InitialContext();
			object3 = ctx.lookup("/Fanny-ear/Fanny-ejb/CartEJB!services.CartEJBRemote");
			cartManagement= (CartEJBRemote) object3;
			object2 = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			userManagment= (UserManagmentRemote) object2;
			obj= ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			artManagement= (ArtworkManagemetRemote) obj;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		List<Cart> cartlist =cartManagement.getMyCarts(userManagment.findById(2));
		System.out.println(cartlist);
		for(Cart c:cartlist)
			
		{
			System.out.println(c);
			HBox hbox=new HBox();
			Label artName=new Label(c.getArtwork().getName());
			artName.setPrefWidth(186);
			artName.setPrefHeight(132);
			hbox.getChildren().add(artName);
			Label price= new Label(String.valueOf(c.getPrice()));
			price.setPrefWidth(186);
			price.setPrefHeight(132);
			hbox.getChildren().add(price);
			Label status= new Label();
			if(c.getStatus()==0)
				status.setText("Status:  On hold");
			if(c.getStatus()==1)
				status.setText("Status:  On it's way");
			
			
			//status.setStyle("-fx-background-color:Black; -fx-text-fill:White; -fx-font-style:bold; -fx-padding:5px; -fx-font-size:18px;");
			status.setPrefWidth(186);
			status.setPrefHeight(40);
			status.setAlignment(Pos.CENTER);
			hbox.getChildren().add(status);
			
			if(c.getArtwork() instanceof TunisianCraft)
			{
			TunisianCraft tc=(TunisianCraft) c.getArtwork();
			//List<Integer> l= new ArrayList<>();
			ComboBox<Integer> combo= new ComboBox<>();
			for (int i=1; i<= tc.getQuantity();i++)
			{
				combo.getItems().add(i);
			}
			combo.getSelectionModel().select(0);
			//combo.setStyle("-fx-background-color:black");
			
			combo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {

				@Override
				public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
					//System.out.println(oldValue);
					c.setPrice(combo.getSelectionModel().getSelectedItem()*c.getArtwork().getPrice());
					price.setText(String.valueOf(c.getPrice()));
					
				}
			});
		
			hbox.getChildren().add(combo);
			}
			 
			
			//ComboBox combo= new ComboBox<>();
			//combo.setModel(new DefaultComboBoxModel(l.toArray()));
			//combo.getItems().add(l);
			
			
			//}
			
			//ComboBox<T> quantity= new JFXTextField(String.valueOf(c.getQuantity()));
			//quantity.setPrefWidth(186);
			//quantity.setPrefHeight(132);
			//hbox.getChildren().add(quantity);
			Button buy= new Button("hol");
			buy.setStyle("-fx-background-image: url('./src/main/java/buttons/43936.png')");
			//buy.setPrefHeight(100);
			buy.setStyle("-fx-pref-width: 200px;-fx-pref-height: 200px;");
			buy.setText("Buy");
			buy.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event ) {
					Parent msgScene;
					try {
						cart=c;
						msgScene = FXMLLoader.load(getClass().getResource("popupCart.fxml"));
					
					Scene scene = new Scene(msgScene);
					//scene.getStylesheets().add(getClass().getResource("Artist.css").toExternalForm());
					Stage Sc = new Stage();
					Sc.setScene(scene);
					Sc.show();
					final Node source = (Node) event.getSource();
					final Stage stage = (Stage) source.getScene().getWindow();
					stage.close();
					buy.setText("bought");
					//buy.setDisable(true);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			});
			hbox.setAlignment(Pos.CENTER);
			hbox.getChildren().add(buy);
			
			hbox.setPrefWidth(0);
			hbox.setSpacing(50);
			hbox.setStyle("-fx-padding: 10;" + 
                    "-fx-border-style: solid inside;" + 
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" + 
                    "-fx-border-radius: 5;" + 
                    "-fx-border-color: black; -fx-background-color;white;");
			
			shVbox.getChildren().add(hbox);
			shVbox.setVisible(true);
			Label a=new Label("test");
			a.setPrefWidth(186);
			a.setPrefHeight(132);
			hbox.getChildren().add(a);
			//hbox.setStyle("-fx-margin:20px");
		}
		
		Label aa=new Label("test");
		aa.setPrefWidth(186);
		aa.setPrefHeight(132);
		shVbox.getChildren().add(aa);
		Button bu= new Button("hol");
		bu.setStyle("-fx-background-image: url('./src/main/java/buttons/43936.png')");
		bu.setPrefHeight(100);
		bu.setPrefHeight(40);
		bu.setText("Buy");
		shVbox.getChildren().add(bu);
		
	
	}
	
	
	public void addToCart (ActionEvent event, Artwork art) throws NamingException
	{
		Cart cart= new Cart();
		cart.setUser(LoginController.userLogedIn);
		cart.setArtwork(art);
		cart.setPrice(art.getPrice());
		cart.setAddress("No address added");
		cart.setQuantity(1);
		cart.setStatus(0);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cart.setDate(String.valueOf(cal));
		//System.out.println(cart.getDate());
		cartManagement.addCart(cart);
		
		
		
	}

}
