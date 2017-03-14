package applicationImen;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class popupCartController implements Initializable {
	  @FXML
	    private RadioButton fast;

	    @FXML
	    private ToggleGroup delivery;

	    @FXML
	    private RadioButton free;

	    @FXML
	    private ImageView buyImg;

	    @FXML
	    private Button buyBtn;

	    @FXML
	    private ImageView fastImg;

	    @FXML
	    private ImageView freeImg;

	    @FXML
	    private JFXTextField addressField;

	    @FXML
	    private Button nxtBtn;
	   

	    @FXML
	    private ImageView nextImg;

	    @FXML
	    private Label addressLabel;
	    
	    @FXML
	    private ImageView prevImg;

	    @FXML
	    private Button prevBtn;

	    
	    

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			fastImg.setVisible(false);
			freeImg.setVisible(false);
			buyBtn.setVisible(false);
			buyImg.setVisible(false);
			fast.setVisible(false);
			free.setVisible(false);
			nextImg.setVisible(true);
			nxtBtn.setVisible(true);
			addressField.setVisible(true);
			addressLabel.setVisible(true);
			nextImg.setVisible(true);
			System.out.println("hi"+CartController.cart);
			prevBtn.setVisible(false);
			prevImg.setVisible(false);
			
		}
		
		
		@FXML
	    void Next(ActionEvent event) {
			
			fastImg.setVisible(true);
			freeImg.setVisible(true);
			buyBtn.setVisible(true);
			buyImg.setVisible(true);
			fast.setVisible(true);
			free.setVisible(true);
			addressField.setVisible(false);
			addressLabel.setVisible(false);
			nextImg.setVisible(false);
			prevBtn.setVisible(true);
			prevImg.setVisible(true);
			addressField.getText();
			

	    }
		
		 @FXML
		 void Buy(ActionEvent event) {
			 //CartController.cart.
			 CartController.cart.setAddress(addressField.getText());
			 RadioButton chk = (RadioButton)delivery.getSelectedToggle();
			 if(chk.getId().equals("fast"))
			 {
				 CartController.cart.setPrice(CartController.cart.getPrice()+50);
			 }
			 CartController.cartManagement.updateCart(CartController.cart);
			 Parent msgScene;
				try {
					
					msgScene = FXMLLoader.load(getClass().getResource("Cart.fxml"));
				
				Scene scene = new Scene(msgScene);
				//scene.getStylesheets().add(getClass().getResource("Artist.css").toExternalForm());
				Stage Sc = new Stage();
				Sc.setScene(scene);
				Sc.show();
				final Node source = (Node) event.getSource();
				final Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
				//y.setText("bought");
				//buy.setDisable(true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }
		 
		 @FXML
		    void goBack(ActionEvent event) {
			 fastImg.setVisible(false);
				freeImg.setVisible(false);
				buyBtn.setVisible(false);
				buyImg.setVisible(false);
				fast.setVisible(false);
				free.setVisible(false);
				nextImg.setVisible(true);
				nxtBtn.setVisible(true);
				addressField.setVisible(true);
				addressLabel.setVisible(true);
				nextImg.setVisible(true);
				prevBtn.setVisible(false);
				prevImg.setVisible(false);

		    }

}
