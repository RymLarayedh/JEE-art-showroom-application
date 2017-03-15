/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationAymenTest;

import com.jfoenix.controls.JFXTextArea;

import entities.Artist;
import entities.Artwork;
import entities.Comment;
import entities.Feedback;
import entities.Gallery;
import entities.Like;
import entities.User;
import entities.VisualArt;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ArtworkManagemetRemote;
import services.LikeManagementRemote;
import services.UserManagmentRemote;
import services.VisualArtworkEJBRemote;
import utils.ConfirmBox;

import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author
 */
public class DetailArtworkController implements Initializable {

	@FXML
	private AnchorPane Trip;
	@FXML
	private ImageView img;
	@FXML
	private JFXTextArea commenttxt;
	@FXML
	private TableView<Feedback> commenttable;
	@FXML
	private TableColumn namecl;
	@FXML
	private TableColumn<Comment, String> bodycl;
	@FXML
	private TableColumn datecl;
	@FXML
	private Button deletecomment;
	@FXML
	private Label nbrl;
	@FXML
	private ToggleButton tj;
	@FXML
	private Label desct;
	@FXML
	private Label datet;
	@FXML
	private Label pricet;
	@FXML
	private Label artnamet;
	@FXML
	private Label artistn;
	InitialContext ctx;
	@FXML
	private Rating ratingg;
	int idartwork = 3;
	int userconect = LoginController.userLogedIn.getIdUser();
	long nbr;
	Double note;
	String str;

	/**
	 * Initializes the controller class.
	 */

	public static VisualArtworkEJBRemote proxy;
	public static Artwork a = new Artwork();
	private Comment selectedComment;
	ObservableList<Feedback> MyCmtdata = FXCollections.observableArrayList();

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		try {
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
			Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
			ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
			Artwork a = new Artwork();
			a = proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
			System.out.println(a);
			artnamet.setText(a.getName());
			String P = String.valueOf(a.getPrice());
			pricet.setText(P);
			artistn.setText(a.getUser().getUsername());
			desct.setText(a.getDescription());
			BufferedImage imgbf = null;
			byte[] b = a.getPicture();

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

			// ImageView imView = new ImageView(wr);
			img.setImage(wr);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		commenttable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item
				// to Label

			}
		});

		try {
			DisplayVisualComment();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/* Oussama */
		/****************************** lIKE ******************************/
		Artwork A = new Artwork();
		A.setIdArtwork(3);
		User U = new User();
		U.setIdUser(1);
		Object objet2;
		try {
			objet2 = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");

			LikeManagementRemote proxy2 = (LikeManagementRemote) objet2;
			nbr = proxy2.nbrlike(A);
			System.out.println(nbr);
			str = "" + nbr;
			nbrl.setText(str);
			Like L = new Like();
			L.setArtwork(A);
			L.setUser(U);
			if (L instanceof Like)
				if (proxy2.checkexistance(U, A)) {
					tj.setText("Unlike");
					tj.setRotate(-180);

				}

				else {
					tj.setText("Like");
					tj.setRotate(360);
				}
			/******************************** Rating *****************************************/
			Object objet3 = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
			LikeManagementRemote proxy3 = (LikeManagementRemote) objet3;
			if (proxy3.checkexistanceR(U, A)) {
				note = (double) 0;
				ratingg.setRating(note);
			} else {
				entities.Rating ri = proxy3.findByUserartIDR(U, A);
				note = ri.getNote();
				ratingg.setRating(note);
			}
		} catch (NamingException e) {
		}
		/* END OUSSAMA */
	}

	@FXML
	private void addcomment(ActionEvent event) throws NamingException {
		ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
		Comment TunC = new Comment();

		TunC = new Comment();

		// String selectedCategory = cat.getValue();
		// TunC.setCategory(selectedCategory);
		TunC.setBodyComment(commenttxt.getText());
		a = proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
		TunC.setArtwork(a);
		// TunC.setFeedbackId();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);
		TunC.setDateComment(date);
		// byte[] bFile = new byte[(int) picture.length()];
		// try {
		// FileInputStream fileInputStream = new FileInputStream(picture);
		// fileInputStream.read(bFile);
		// fileInputStream.close();
		// TunC.setPicture(bFile);
		//
		// } catch (Exception e) {
		// }
		/*
		 * FeedbackId feedbackId =new FeedbackId();
		 * //*************************change*****************************
		 * feedbackId.setArtworkId(a.getIdArtwork()); feedbackId.setUserId(3);
		 * //*************************change*****************************
		 * TunC.setFeedbackId(feedbackId); Alert alert = new
		 * Alert(Alert.AlertType.ERROR); alert.setTitle("Fanny");
		 * alert.setHeaderText(null); alert.setContentText("Comment Add succes"
		 * ); alert.showAndWait();
		 * 
		 * 
		 * proxy.addVisualComment(TunC); DisplayVisualComment();
		 * commenttable.getItems().clear();
		 */
	}

	@FXML
	private void selectComment(MouseEvent event) {
		// bodycl.setEditable(true);
		// selectedComment = (Comment)
		// commenttable.getSelectionModel().getSelectedItem();
		// commenttable.setEditable(true);
		// bodycl.setCellFactory(TextFieldTableCell.<Comment> forTableColumn());
		//
		//
		// proxy.upadateVisualComment(selectedComment);

	}

	public void DisplayVisualComment() throws NamingException {
		commenttable.getItems().clear();

		ctx = new InitialContext();
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
		VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
		// Artwork a = new Artwork ();
		a = proxy.findVisualArtById(ProfileuserController.chosenArtwork.getIdArtwork());
		System.out.println(a);
		VisualArt v = (VisualArt) a;
		/**************************************************************/
		commenttable.getItems().clear();
		// List<Comment> ListComments = proxy.findAllVisualArtComment(2);
		// for (Comment compte : ListComments) {
		// System.out.println(compte.getBodyComment());
		// }
		List<Comment> ListComments = proxy.findAllVisualComment();
		for (Comment a : ListComments) {
			MyCmtdata.add(a);
		}

		// Comment c=new Comment();
		// c.getFeedbackId().getArtworkId()
		// name1.setCellValueFactory(new PropertyValueFactory<Artwork,
		// String>("name"));

		// namecl.setCellValueFactory(new PropertyValueFactory<Comment,
		// String>("name"));
		// bodycl.<Comment, String>forTableColumn("bodyComment");

		// namecl.setCellValueFactory(new PropertyValueFactory<Comment,
		// String>("name"));
		bodycl.setCellValueFactory(new PropertyValueFactory<Comment, String>("bodyComment"));
		datecl.setCellValueFactory(new PropertyValueFactory<Comment, String>("dateComment"));
		// bodycl.setCellValueFactory(cellData -> new
		// ReadOnlyStringWrapper("bodyComment"));
		commenttable.setItems(MyCmtdata);
		commenttable.setVisible(true);

	}

	@FXML
	private void Modifycomment(ActionEvent event) throws NamingException {
		bodycl.setEditable(true);
		selectedComment = (Comment) commenttable.getSelectionModel().getSelectedItem();
		commenttable.setEditable(true);
		bodycl.setCellFactory(TextFieldTableCell.<Comment> forTableColumn());

		bodycl.setOnEditCommit(t -> {
			t.getRowValue().setBodyComment(t.getNewValue());

			boolean confirm = ConfirmBox.display("", "Update Category ?");

			if (confirm) {

				proxy.upadateVisualComment(selectedComment);
			}
		});
		// proxy.upadateVisualComment(selectedComment);
		DisplayVisualComment();
		commenttable.getItems().clear();
	}

	@FXML
	private void Deletecomment(ActionEvent event) throws NamingException, IOException {
		boolean confirm = ConfirmBox.display("", "Delete Comment ?");
		if (confirm) {
			// proxy.deleteVisualArt(UserArtController.chosenArtwork);
			ctx = new InitialContext();
			Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			VisualArtworkEJBRemote proxy = (VisualArtworkEJBRemote) objet;
			// Artwork a = new Artwork ();
			// a=
			// proxy.findVisualArtById(UserArtController.chosenArtwork.getIdArtwork());
			selectedComment = (Comment) commenttable.getSelectionModel().getSelectedItem();
			proxy.deleteVisualComment(selectedComment);

		}
		DisplayVisualComment();
		commenttable.getItems().clear();
	}

	@FXML
	private void bookTrip(ActionEvent event) {
	}

	/* OUSSAMA */
	@FXML
	private void liked(ActionEvent event) throws NamingException {

		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxyo = (LikeManagementRemote) objet;
		Like L = new Like();
		Artwork A = new Artwork();
		A.setIdArtwork(3);
		User U = new User();
		U.setIdUser(1);

		L.setArtwork(A);
		L.setUser(U);

		if (L instanceof Like)
			if (proxyo.checkexistance(U, A)) {
				tj.setText("Unlike");
				tj.setRotate(-180);

			}

			else {
				tj.setText("Like");
				tj.setRotate(360);
			}
		if (tj.getText().equals("Like")) {
			tj.setText("Unlike");
			tj.setRotate(-180);

			proxyo.addLike(L);

			nbr = proxyo.nbrlike(A);
			str = "" + nbr;

			nbrl.setText(str);
		} else {

			System.out.println(L.getArtwork().getIdArtwork() + "******");
			Like li = proxyo.findByUserartID(U, A);
			proxyo.deleteLike(li);
			nbr = proxyo.nbrlike(A);

			str = "" + nbr;

			nbrl.setText(str);
			tj.setText("Like");
			tj.setRotate(360);

		}

	}

	@FXML
	public void Rate(MouseEvent event) throws NamingException {
		Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/LikeManagement!services.LikeManagementRemote");
		LikeManagementRemote proxyo = (LikeManagementRemote) objet;
		entities.Rating R = new entities.Rating();
		Artwork A = new Artwork();
		A.setIdArtwork(3);
		User U = new User();
		U.setIdUser(1);
		R.setUser(U);
		R.setArtwork(A);

		note = ratingg.getRating();
		R.setNote(note);

		if (proxyo.checkexistanceR(U, A)) {
			proxyo.addRating(R);

		}

		else {
			System.out.println("oussama");
			entities.Rating ri = proxyo.findByUserartIDR(U, A);
			ri.setNote(ratingg.getRating());

			proxyo.upadateRating(ri);

		}
	}

	/* END OUSSAMA */

}