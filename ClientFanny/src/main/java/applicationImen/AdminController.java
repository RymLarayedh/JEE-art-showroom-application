package applicationImen;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CartEJBRemote;
import services.UserManagmentRemote;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;

import javafx.event.Event;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import application.LoginController;
import entities.Cart;
import entities.Category;
import entities.Gallery;
import entities.User;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;

public class AdminController implements Initializable {
	@FXML
	private AnchorPane AymensPane;
	@FXML
	private TableView AllUsersTable;
	@FXML
	private TableColumn picture;
	@FXML
	private TableColumn firstName;
	@FXML
	private TableColumn LastName;
	@FXML
	private TableColumn mail;
	@FXML
	private TableColumn username;
	@FXML
	private TableColumn status;
	@FXML
	private Label sortLabel;
	@FXML
	private JFXComboBox nature;
	@FXML
	private AnchorPane ImensPane;
	@FXML
	private TableView<Cart> OrdersTable;
	@FXML
	private TableColumn<Cart , String> ProductPicture;
	@FXML
	private TableColumn<Cart , String> Client;
	@FXML
	private TableColumn<Cart , Double> ProductPrice;
	@FXML
	private TableColumn<Cart , String> ProductQuantity;
	@FXML
	private TableColumn<Cart , String> PurchaseDate;
	@FXML
	private TableColumn<Cart , String> PurchaseStatus;
	@FXML
	private TableColumn<Cart , String> Action;
	@FXML
	private ImageView profilePicture;
	@FXML
	private Label userLoggedInName;
	@FXML
	private JFXButton msg;
	InitialContext ctx;
	public static CartEJBRemote cartManagement;

    @FXML
    private TitledPane ManageOrders;
    ObservableList<Cart> CartsData = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			ctx = new InitialContext();
		
		Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/CartEJB!services.CartEJBRemote");
		cartManagement= (CartEJBRemote) object;
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		nature.setVisible(false);
		// byFollowers.setVisible(false);
		sortLabel.setVisible(false);
		// byFollowers.getItems().addAll("LESS FOLLOWED", "MOST FOLLOWED",
		// "NEITHER");
		nature.getItems().addAll("ARTISTS", "GALLERIES", nature.getPromptText());
		AllUsersTable.setEditable(true);
		AllUsersTable.setVisible(false);
		OrdersTable.setVisible(false);
		userLoggedInName
				.setText(applicationImen.LoginController.userLogedIn.getFirstName() + " " + applicationImen.LoginController.userLogedIn.getLastName());
		if (applicationImen.LoginController.userLogedIn.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				profilePicture.setImage(image);
			} catch (IOException e) {
			}

		} else {
			try {
				byte[] b = applicationImen.LoginController.userLogedIn.getPicture();
				BufferedImage imgbf = null;

				imgbf = ImageIO.read(new ByteArrayInputStream(b));
				WritableImage wr = null;
				if (imgbf != null) {
					wr = new WritableImage(imgbf.getWidth(), imgbf.getHeight());
					PixelWriter pw = wr.getPixelWriter();
					for (int x = 0; x < imgbf.getWidth(); x++) {
						for (int y = 0; y < imgbf.getHeight(); y++) {
							pw.setArgb(x, y, imgbf.getRGB(x, y));
						}
					}
				}
				profilePicture.setImage(wr);
			} catch (IOException e) {
			}

		}

	}

	// Event Listener on JFXButton.onAction
	@FXML
	public void ShowBlockedUsers(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton.onAction
	@FXML
	public void ShowAllUsers(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXButton.onAction
	@FXML
	public void ShowWaitList(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on TableView[#AllUsersTable].onMouseClicked
	@FXML
	public void showPopup(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on JFXComboBox[#nature].onHiding
	@FXML
	public void filterType(Event event) {
		// TODO Autogenerated
	}
	// Event Listener on ImageView[#profilePicture].onMouseClicked
	@FXML
	public void showProfile(MouseEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void Disconnect(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void closeFanny(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void showSettingsScreen(ActionEvent event) {
		// TODO Autogenerated
	}
	// Event Listener on MenuItem.onAction
	@FXML
	public void showAboutFanny(ActionEvent event) {
		// TODO Autogenerated
	}
	
    @FXML
    void ShowOrdersTable(MouseEvent event) {
    	OrdersTable.setVisible(true);
    	List<Cart> liste= cartManagement.getAllCarts();
    	for (Cart l:liste)
    	{
    		CartsData.add(l);
    	}
    	
		ProductPrice.setCellValueFactory(new PropertyValueFactory<Cart, Double>("price"));
		PurchaseDate.setCellValueFactory(new PropertyValueFactory<Cart, String>("date"));
		PurchaseStatus.setCellValueFactory(new PropertyValueFactory<Cart, String>("status"));
		
		ProductQuantity.setCellValueFactory(new PropertyValueFactory<Cart, String>("quantity"));
		ProductPrice.setCellValueFactory(new PropertyValueFactory<Cart, Double>("price"));
		Client.setCellValueFactory(new Callback<CellDataFeatures<Cart, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Cart, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
		ProductPicture.setCellValueFactory(new Callback<CellDataFeatures<Cart, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Cart, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getArtwork().getName());
			}

		});
		
		
		Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
		Callback<TableColumn<Cart, String>, TableCell<Cart, String>> cellFactory = //
				new Callback<TableColumn<Cart, String>, TableCell<Cart, String>>() {
					@Override
					public TableCell call(final TableColumn<Cart, String> param) {
						final TableCell<Cart, String> cell = new TableCell<Cart, String>() {

							final JFXButton btn = new JFXButton();

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else 
									if (getTableView().getItems().get(getIndex()) instanceof Cart) {
										btn.setText("More Information"); 
										
										btn.setOnAction((ActionEvent event) -> {
										});
									} /*else {
										if (LoginController.userManagment.getAllFollowed(LoginController.userLogedIn)
												.contains(getTableView().getItems().get(getIndex()))) {
											btn.setText("UnFollow");
											btn.setOnAction((ActionEvent event) -> {
												User person = getTableView().getItems().get(getIndex());
												LoginController.userManagment
														.removeFollower(LoginController.userLogedIn, person);
												searchTable.refresh();
											});
										} else {
											btn.setText("Follow");
											btn.setOnAction((ActionEvent event) -> {
												User person = getTableView().getItems().get(getIndex());
												LoginController.userManagment.addFollower(LoginController.userLogedIn,
														person);
												searchTable.refresh();
											});
										}
									}*/
									
									setGraphic(btn);
									//setText(null);
								//}
							}
						};
						return cell;
					}
				};

		Action.setCellFactory(cellFactory);
    	//Client.setCellValueFactory(new PropertyValueFactory<Cart, String>("i"));
    		System.out.println(CartsData);
    	OrdersTable.setItems(CartsData);
    	//OrdersTable.getColumns().addAll(Action);

    }
    
    @FXML
    void OpenInbox(ActionEvent event) throws IOException {
    	Parent msgScene = FXMLLoader.load(getClass().getResource("Messages.fxml"));
		Scene scene = new Scene(msgScene);
		//scene.getStylesheets().add(getClass().getResource("Artist.css").toExternalForm());
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.show();
		/*final Node source = (Node) event.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();*/
    	

    }
}
