package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import business.UserManagmentDelegate;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ElarbiMohamedAymen
 */
public class ChangePasswordController implements Initializable {

	@FXML
	private JFXButton chgpwd;
	@FXML
	private AnchorPane pwdusrChange;
	@FXML
	private Button usernameBTN;
	@FXML
	private Button pwdBTN;
	@FXML
	private AnchorPane VerificationAnchor;
	@FXML
	private JFXPasswordField pwdVerification;
	@FXML
	private JFXTextField CurreUsername;
	@FXML
	private JFXPasswordField CurrePWD;
	@FXML
	private JFXButton saveBTN;
	int frm;
	static boolean isOpen = false;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		isOpen = true;
		pwdusrChange.setVisible(false);
		VerificationAnchor.setVisible(false);
		saveBTN.setVisible(false);
	}

	@FXML
	private void shChg(ActionEvent event) {
		pwdusrChange.setVisible(true);
		VerificationAnchor.setVisible(false);
		usernameBTN.setVisible(false);
		pwdBTN.setVisible(false);
		CurreUsername.setEditable(false);
		CurrePWD.setEditable(false);
		CurreUsername.setText(LoginController.userLogedIn.getUsername());
		CurrePWD.setText(LoginController.userLogedIn.getPassword());

	}

	@FXML
	private void pwdupd(MouseEvent event) {
		pwdBTN.setVisible(true);
		usernameBTN.setVisible(false);
	}

	@FXML
	private void usernameupd(MouseEvent event) {
		pwdBTN.setVisible(false);
		usernameBTN.setVisible(true);

	}

	@FXML
	private void usernameUpdPerform(ActionEvent event) {
		VerificationAnchor.setVisible(true);
		pwdBTN.setVisible(false);
		usernameBTN.setVisible(false);
		frm = 2;
	}

	@FXML
	private void pwdUpdPerform(ActionEvent event) {
		VerificationAnchor.setVisible(true);
		pwdBTN.setVisible(false);
		usernameBTN.setVisible(false);
		frm = 1;
	}

	@FXML
	private void pwdVerify(ActionEvent event) {
		if (pwdVerification.getText().equals(LoginController.userLogedIn.getPassword())) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Identity verified for " + LoginController.userLogedIn.getUsername());
			alert.showAndWait();
			pwdVerification.clear();
			VerificationAnchor.setVisible(false);
			if (frm == 1) {
				CurrePWD.setEditable(true);
				CurreUsername.setEditable(false);
				CurrePWD.requestFocus();
				saveBTN.setVisible(true);
				return;
			} else if (frm == 2) {
				CurrePWD.setEditable(false);
				CurreUsername.setEditable(true);
				CurreUsername.requestFocus();
				saveBTN.setVisible(true);
				return;
			}

		}
		else
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Can't verify identity try again");
			alert.showAndWait();
			pwdVerification.clear();
			return;
		}
	}

	@FXML
	private void savePerform(ActionEvent event) {
		if (CurreUsername.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Username Field Cannot be left blank no changes Saved");
			alert.showAndWait();
			return;
		}
		if (CurrePWD.getText().trim().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Password Field Cannot be left blank no changes Saved");
			alert.showAndWait();
			return;
		}
		if (!CurreUsername.getText().equals(LoginController.userLogedIn.getUsername())) {
			if (!UserManagmentDelegate.checkUsernameExistance(CurreUsername.getText())) {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Fanny");
				alert.setHeaderText(null);
				alert.setContentText("Username Belong to another user no changes Saved");
				alert.showAndWait();
				return;	
			}

		}
		LoginController.userLogedIn.setUsername(CurreUsername.getText());
		LoginController.userLogedIn.setPassword(CurrePWD.getText());
		UserManagmentDelegate.updateUser(LoginController.userLogedIn);
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Fanny");
		alert.setHeaderText(null);
		alert.setContentText("Changes saved successfully");
		alert.showAndWait();
		saveBTN.setVisible(false);
		usernameBTN.setVisible(false);
		pwdBTN.setVisible(false);
		CurreUsername.setEditable(false);
		CurrePWD.setEditable(false);
		
	}

}
