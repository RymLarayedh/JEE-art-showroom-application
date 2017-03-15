package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.ConfirmBox;

public class AboutController implements Initializable {

	@FXML
	private AnchorPane	AboutPane;
	static boolean isOpen = false;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		isOpen = true;	
	}
	
	@FXML
	private void close(ActionEvent event) {
		isOpen = false;
		final Stage stage = (Stage) AboutPane.getScene().getWindow();
		stage.close();

	}


}
