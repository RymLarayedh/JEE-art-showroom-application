package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.SwingWorker;

import entities.User;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserManagmentRemote;

public class LoginController implements Initializable {
	InitialContext ctx;
	public static UserManagmentRemote userManagment;
	public static User userLogedIn;
	@FXML
	private TextField usernameTF;
	@FXML
	private PasswordField passwordTF;
	@FXML
	private AnchorPane loginAnchor;
	@FXML
	private StackPane resetPasswordPane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ctx = new InitialContext();
			Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			userManagment = (UserManagmentRemote) object;
		} catch (NamingException e) {
		}
	}

	@FXML
	public void LogIn(ActionEvent event) throws IOException {
		String username = usernameTF.getText();
		String password = passwordTF.getText();
		if (userManagment.loginUser(username, password)) {
			userLogedIn = userManagment.findByUsername(username);
			int redirect = userManagment.RedirectUser(userLogedIn);
			if (redirect == 1) {
				// Artist
				gotoArtist(event);
			} else if (redirect == 2) {
				// Gallery
				gotoGallery(event);
			} else {
				// Admin
				gotoAdmin(event);
			}

		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fanny");
			alert.setHeaderText(null);
			alert.setContentText("Wrong Password or username");

			alert.showAndWait();
			usernameTF.clear();
			passwordTF.clear();
		}

	}

	// Event Listener on Label.onMouseClicked
	@FXML
	public void registerNewUser(MouseEvent event) {
		gotoRegistration();
	}

	// Event Listener on Label.onMouseClicked
	@FXML
	public void resetPassword(MouseEvent event) {
		loginAnchor.setVisible(false);
		Label inform = new Label("To reset your password please type in your email");
		inform.setMinWidth(300);
		inform.setTranslateX(100);
		inform.setTranslateY(100);
		TextField mailTextField = new TextField();
		mailTextField.setMaxWidth(240);
		mailTextField.setTranslateX(10);
		mailTextField.setTranslateY(146);
		Button reset = new Button("Send");
		reset.setTranslateX(190);
		reset.setTranslateY(146);
		reset.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (mailTextField.getText().trim().isEmpty()) {
					// please type in a valid mail address
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("Please insert an email address");
					alert.showAndWait();

					return;
				}

				if (userManagment.findByEmail(mailTextField.getText().trim()) == null) {
					// this user doesnt exist
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("This address does not exist in our database");
					alert.showAndWait();
					Label createNewAccount = new Label("Create a new account from here");
					createNewAccount.setOnMouseClicked(new EventHandler<MouseEvent>() {
						public void handle(MouseEvent event) {
							gotoRegistration();
							System.out.println("hi");
						};
					});
					resetPasswordPane.getChildren().add(createNewAccount);
					System.out.println("utilisateur existe pas");
					return;
				}
				String verificationCode = userManagment.codeGeneration();
				Runnable mailing = new Runnable() {

					@Override
					public void run() {
						System.out.println("fi west el run " + verificationCode);
						try {

							userManagment.sendMail(mailTextField.getText(),
									"to reset your password use this code : " + verificationCode, "Resetting password");
						} catch (AddressException e) {
							// TODO Auto-generated catch block
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
						}

					}
				};
				new Thread(mailing).start();
				// resetPasswordPane.getChildren().clear();
				Label informVerification = new Label("Please type in your verification code");
				informVerification.setMinWidth(300);
				informVerification.setTranslateX(100);
				informVerification.setTranslateY(180);
				TextField verificationCodeTyped = new TextField();
				verificationCodeTyped.setMaxWidth(100);
				verificationCodeTyped.setTranslateX(10);
				verificationCodeTyped.setTranslateY(220);
				Button verify = new Button("Verify");
				verify.setTranslateX(190);
				verify.setTranslateY(220);
				verify.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						if (verificationCodeTyped.getText().equals(verificationCode)) {
							resetPasswordPane.getChildren().clear();
							Label informNewPassword = new Label("Please type in your new password");
							informNewPassword.setMinWidth(300);
							informNewPassword.setTranslateX(100);
							informNewPassword.setTranslateY(70);
							TextField newPassword = new TextField();
							newPassword.setMaxWidth(100);
							newPassword.setTranslateX(10);
							newPassword.setTranslateY(120);
							TextField reNewPassword = new TextField();
							reNewPassword.setMaxWidth(100);
							reNewPassword.setTranslateX(10);
							reNewPassword.setTranslateY(160);
							Button saveNewPassword = new Button("Save");
							saveNewPassword.setTranslateX(20);
							saveNewPassword.setTranslateY(190);

							saveNewPassword.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent event) {
									Alert alert = new Alert(Alert.AlertType.INFORMATION);
									alert.setTitle("Fanny");
									alert.setHeaderText(null);
									alert.setContentText("Password changed you can log in with it now");
									alert.showAndWait();
								}
							});

							resetPasswordPane.getChildren().add(informNewPassword);
							resetPasswordPane.getChildren().add(newPassword);
							resetPasswordPane.getChildren().add(reNewPassword);
							resetPasswordPane.getChildren().add(saveNewPassword);

						} else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setTitle("Fanny");
							alert.setHeaderText(null);
							alert.setContentText("Wrong verification Code");
							alert.showAndWait();
						}

					}
				});
				resetPasswordPane.getChildren().add(informVerification);
				resetPasswordPane.getChildren().add(verificationCodeTyped);
				resetPasswordPane.getChildren().add(verify);
			}
		});
		resetPasswordPane.getChildren().add(inform);
		resetPasswordPane.getChildren().add(mailTextField);
		resetPasswordPane.getChildren().add(reset);
		resetPasswordPane.setVisible(true);
	}

	
	void gotoRegistration() {

	}

	void gotoAdmin(ActionEvent event) throws IOException {
		Parent adminScene = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));
		Scene scene = new Scene(adminScene);
		scene.getStylesheets().add(getClass().getResource("Admin.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	void gotoArtist(ActionEvent event) throws IOException {

		Parent artistScene = FXMLLoader.load(getClass().getResource("Artist.fxml"));
		Scene scene = new Scene(artistScene);
		scene.getStylesheets().add(getClass().getResource("Artist.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

	void gotoGallery(ActionEvent event) throws IOException {
		Parent galleryScene = FXMLLoader.load(getClass().getResource("Gallery.fxml"));
		Scene scene = new Scene(galleryScene);
		scene.getStylesheets().add(getClass().getResource("Gallery.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();

	}

}
