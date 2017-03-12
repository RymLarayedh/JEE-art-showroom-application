/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integrationInes;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Admin;
import entities.Artist;
import entities.Artwork;
import entities.Gallery;
import entities.Music;
import entities.User;
import entities.VisualArt;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import services.ForumManagementRemote;
import services.UserManagmentRemote;
import services.VisualArtworkEJBRemote;
import utils.ConfirmBox;

/**
 * FXML Controller class
 *
 * @author Oussamabhhh
 */
public class ProfileuserController implements Initializable {

	@FXML
	private AnchorPane magicbar;
	@FXML
	private ImageView myimage;
	@FXML
	private ImageView magic2;
	@FXML
	private Text myname;
	@FXML
	private Text mylastname;
	@FXML
	private Button profil;
	@FXML
	private Button message;
	@FXML
	private ImageView magic;
	@FXML
	private Button home;

	@FXML
	private Button ownspace;
	@FXML
	private Button visuala;
	@FXML
	private Button musicb;
	@FXML
	private Button eventb;
	@FXML
	private Button tunisianc;

	/* DEBUT AYMEN */
	@FXML
	private AnchorPane AymensPane;
	@FXML
	private TabPane OwnProfileArtist;
	MouseEvent mEvent;

	@FXML
	private Button mailEDIT;
	@FXML
	private Button bioEDIT;
	@FXML
	private ImageView imageViewProfile;
	@FXML
	private JFXTextArea BIOTF;
	@FXML
	private JFXTextField FirstNameTF;
	@FXML
	private JFXTextField LastNameTF;
	@FXML
	private JFXTextField mailTF;
	@FXML
	private JFXButton SaveButton;
	boolean isChanged = false;
	@FXML
	private JFXTextField searchTF;
	@FXML
	private TableView<User> searchTable;

	@FXML
	private TableColumn<User, byte[]> Picture;

	@FXML
	private TableColumn<User, String> FirstName;

	@FXML
	private TableColumn<User, String> LastName;

	@FXML
	private TableColumn<User, String> Action;
	@FXML
	private TabPane OwnProfileGallery;
	ObservableList<User> UsersData = FXCollections.observableArrayList();
	List<User> LrecherchFN = new ArrayList<>();
	List<User> Lremove = new ArrayList<>();
	@FXML
	private JFXTextField mailTFGallery;
	@FXML
	private Button mailEDITGallery;
	@FXML
	private Button descriptionEDIT;
	@FXML
	private JFXTextArea description;
	@FXML
	private Button addressEDITGallery;
	@FXML
	private JFXTextField AddressTFGallery;
	@FXML
	private ImageView imageViewProfileG;
	@FXML
	private JFXTextField FirstNameTFG;
	@FXML
	private JFXTextField LastNameTFG;
	@FXML
	private JFXButton SaveGalleryButton;
	/*FINAYMEN*/
	
	/*DEBUT MANEL*/
	@FXML
	private AnchorPane ManelsPane ;
	@FXML
	private TableView<VisualArt> AllArtworkTable2;
	@FXML
	private TableColumn picture1;
	@FXML
	private TableColumn name1;
	@FXML
	private TableColumn price1;
	@FXML
	private TableColumn category1;
	@FXML
	private TableColumn lenght1;
	@FXML
	private TableColumn width1;
	@FXML
	private TableColumn state1;
	@FXML
	private TableColumn date1;
	@FXML
	private TableColumn desc1;
	@FXML
	private Button artDet;
	
	InitialContext ctx;
	public static Artwork chosenArtwork;
	public static Artwork chosenArtworks;
	public static VisualArtworkEJBRemote proxy;
	ObservableList<VisualArt> Myartdata = FXCollections.observableArrayList();
	ObservableList<Artwork> artdata = FXCollections.observableArrayList();
	
	/*FINMANEL*/
	/*************************** Ines ************************/
	@FXML
	private AnchorPane MusicPane;
	@FXML
	private AnchorPane musicBarAnchor;
	@FXML
	private TableView<Music> tableMusic;

	@FXML
	private TableColumn<Music, byte[]> picture;
	@FXML
	private TableColumn<Music, String> user;
	@FXML
	private TableColumn<Music, String> name;
	@FXML
	private Button playSong;

	@FXML
	private Button stopSong;

	@FXML
	private Button fastSpeedSong;

	@FXML
	private Button slowSpeedSong;

	@FXML
	private Button repeatSong;

	@FXML
	private Button volume;

	@FXML
	private Button restartSong;

	@FXML
	private Button pause;

	@FXML
	private Button muteVolume;

	@FXML
	private Rectangle musicBar;
	  @FXML
	private JFXSlider volumeSlider;


	ObservableList<Music> dataMusic = FXCollections.observableArrayList();

	public static ForumManagementRemote forumManagement;
	public static UserManagmentRemote userManagement;

	public String selectedSong;
	Media hit;
	// = new Media(new File(selectedSong).toURI().toString());
	MediaPlayer mediaPlayer;
	// = new MediaPlayer(hit);

	/****************************** End Ines ************************/

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		OwnProfileArtist.setVisible(false);
		OwnProfileGallery.setVisible(false);
		searchTable.setVisible(false);
		AymensPane.setVisible(true);
		magicbar.setVisible(false);
		ManelsPane.setVisible(false);

		// aymen//
		if (LoginController.userLogedIn.getPicture() == null) {
			File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
			BufferedImage bufferedImage;
			try {
				bufferedImage = ImageIO.read(file);
				Image image = SwingFXUtils.toFXImage(bufferedImage, null);
				myimage.setImage(image);
			} catch (IOException e) {
			}

		} else {
			try {
				byte[] b = LoginController.userLogedIn.getPicture();
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
				myimage.setImage(wr);
			} catch (IOException e) {
			}
		}
		// endAymen//
		
		/*MANEL*/
		try {
			ctx = new InitialContext();
			proxy = (VisualArtworkEJBRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
//			VisualArtwork = (VisualArtworkEJBRemote) ctx
//					.lookup("/Fanny-ear/Fanny-ejb/VisualArtworkEJB!services.VisualArtworkEJBRemote");
			forumManagement = (ForumManagementRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/ForumManagement!services.ForumManagementRemote");
			userManagement = (UserManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			Artwork aa = new Artwork();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			/**Ines**/
		MusicPane.setVisible(false);
		musicBarAnchor.setDisable(true);
		magicbar.setVisible(false);
		
		AllArtworkTable2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
        //Check whether item is selected and set value of selected item to Label

                
            }});
		DisplayArts();
		/*ENDMANEL*/

	}

	@FXML
	private void message(ActionEvent event) {
	}

	@FXML
	private void home(ActionEvent event) {
	}

	public void affichermagicbar() {
		Polyline polyline = new Polyline();
		polyline.getPoints()
				.addAll(new Double[] { -85.0, 320.0, 85.0, 320.0, 75.0, 320.0, 85.0, 320.0, 80.0, 320.0, 85.0, 320.0 });
		PathTransition transation = new PathTransition();
		transation.setNode(magicbar);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();
	}

	public void cachermagicbar() {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 85.0, 320.0, -85.0, 320.0, });
		PathTransition transation = new PathTransition();
		transation.setNode(magicbar);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();
	}

	public void afficherbouton(Button x, Button y, Button z) {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 52.0 });

		PathTransition transation = new PathTransition();
		transation.setNode(x);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();

		Polyline polyline2 = new Polyline();
		polyline2.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 92.0, });
		PathTransition transation2 = new PathTransition();
		transation2.setNode(y);
		transation2.setDuration(Duration.seconds(2));
		transation2.setPath(polyline2);
		transation2.play();

		Polyline polyline3 = new Polyline();
		polyline3.getPoints().addAll(new Double[] { 100.0, 30.0, 100.0, 132.0, });
		PathTransition transation3 = new PathTransition();
		transation3.setNode(z);
		transation3.setDuration(Duration.seconds(2));
		transation3.setPath(polyline3);
		transation3.play();

	}

	public void cacherbouton(Button x, Button y, Button z) {
		Polyline polyline = new Polyline();
		polyline.getPoints().addAll(new Double[] { 100.0, 52.0, 100.0, 20.0 });

		PathTransition transation = new PathTransition();
		transation.setNode(x);
		transation.setDuration(Duration.seconds(2));
		transation.setPath(polyline);
		transation.play();

		Polyline polyline2 = new Polyline();
		polyline2.getPoints().addAll(new Double[] { 100.0, 92.0, 100.0, 20.0, });
		PathTransition transation2 = new PathTransition();
		transation2.setNode(y);
		transation2.setDuration(Duration.seconds(2));
		transation2.setPath(polyline2);
		transation2.play();

		Polyline polyline3 = new Polyline();
		polyline3.getPoints().addAll(new Double[] { 100.0, 132.0, 100.0, 20.0, });
		PathTransition transation3 = new PathTransition();
		transation3.setNode(z);
		transation3.setDuration(Duration.seconds(2));
		transation3.setPath(polyline3);
		transation3.play();
	}

	@FXML
	private void magic(MouseEvent event) {
		magicbar.setVisible(true);
		affichermagicbar();
		magic.setVisible(false);
	}

	@FXML
	private void magic2(MouseEvent event) {
		mEvent = event;
		magic.setVisible(false);
		cachermagicbar();
		magic.setVisible(true);
	}

	@FXML
	private void Myownspace(ActionEvent event) {
	}

	@FXML
	private void Event(ActionEvent event) {
	}

	@FXML
	private void Tunisiancraft(ActionEvent event) {
	}

	/*******************************************************************************************************************/
	/********************************************************** Ines ***************************************************/
	@FXML
	private void music(ActionEvent event) {
		MusicPane.setVisible(true);
		musicBarAnchor.setVisible(true);
		muteVolume.setVisible(false);
		pause.setVisible(false);
		
		displayMusic();

	}

	public void displayMusic() {
		tableMusic.getItems().clear();
		List<Music> listMusic = forumManagement.findAllMusic();
	
		for (Music m : listMusic) {
			dataMusic.add(m);
		}
		/******************************************/
		// picture.setResizable(false);
		// picture.setSortable(false);

		picture.setCellValueFactory(new PropertyValueFactory<Music, byte[]>("picture"));
		picture.setCellFactory(new Callback<TableColumn<Music, byte[]>, TableCell<Music, byte[]>>() {
			@Override
			public TableCell<Music, byte[]> call(TableColumn<Music, byte[]> param) {
				TableCell<Music, byte[]> cell = new TableCell<Music, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File(selectedSong);
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});

		/**********************************************/

		name.setCellValueFactory(new PropertyValueFactory<Music, String>("name"));
		user.setCellValueFactory(new Callback<CellDataFeatures<Music, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Music, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
		tableMusic.setItems(dataMusic);

	}

	/**** On mouse clicked on tableMusic ***/
	@FXML
	void selectSong(MouseEvent event) {
		musicBarAnchor.setVisible(true);
		muteVolume.setVisible(false);
		pause.setVisible(false);
		musicBarAnchor.setDisable(false);



		selectedSong = tableMusic.getSelectionModel().getSelectedItem().getPath();
		hit = new Media(new File("src/main/java/music/shape.mp4").toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		
	


	}



	@FXML
	void playSong(ActionEvent event) {

		mediaPlayer.play();
		mediaPlayer.setRate(1); // come back to original speed
		playSong.setVisible(false);
		pause.setVisible(true);

	}

	@FXML
	void stopSong(ActionEvent event) {

		mediaPlayer.stop();
		playSong.setVisible(true);
		pause.setVisible(false);

	}

	@FXML
	void pauseSong(ActionEvent event) {
		mediaPlayer.pause();
		playSong.setVisible(true);
		pause.setVisible(false);

	}

	@FXML
	void repeatSong(ActionEvent event) {
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

	}

	@FXML
	void restartSong(ActionEvent event) {
		mediaPlayer.setRate(1);
		mediaPlayer.seek(mediaPlayer.getStartTime());
		mediaPlayer.play();

	}

	@FXML
	void slowSpeedSong(ActionEvent event) {
		double Rate = mediaPlayer.getRate();
		mediaPlayer.setRate(Rate - 0.5);

	}

	@FXML
	void fastSpeedSong(ActionEvent event) {
		double Rate = mediaPlayer.getRate();
		mediaPlayer.setRate(Rate + 0.5);

	}

	@FXML
	void volume(ActionEvent event) {

	}

	@FXML
	void muteVolume(ActionEvent event) {

	}

	/*ENDINES*/

	/*******************************************************************************************/
	/**************************************************** AYMENE *******************************/
	/*******************************************************************************************/

	@FXML
	public void showProfile(MouseEvent event) {
		ActionEvent ae = new ActionEvent(event.getSource(), event.getTarget());
		showProfileBTN(ae);
	}

	@FXML
	public void showProfileBTN(ActionEvent event) {
		searchTable.setVisible(false);
		AymensPane.setVisible(true);
		ManelsPane.setVisible(false);
		if (LoginController.userLogedIn instanceof Artist) {
			OwnProfileArtist.setVisible(true);
			OwnProfileGallery.setVisible(false);
			bioEDIT.setVisible(false);
			mailEDIT.setVisible(false);
			mailEDITGallery.setVisible(false);
			addressEDITGallery.setVisible(false);
			descriptionEDIT.setVisible(false);
			mailTF.setEditable(false);
			BIOTF.setEditable(false);
			FirstNameTF.setEditable(false);
			LastNameTF.setEditable(false);
			SaveButton.setVisible(false);
			FirstNameTFG.setEditable(false);
			LastNameTFG.setEditable(false);
			mailTFGallery.setEditable(false);
			description.setEditable(false);
			AddressTFGallery.setEditable(false);
			FirstNameTF.setTooltip(new Tooltip("Click To Edit"));
			LastNameTF.setTooltip(new Tooltip("Click To Edit"));
			FirstNameTF.setText(LoginController.userLogedIn.getFirstName());
			LastNameTF.setText(LoginController.userLogedIn.getLastName());
			mailTF.setText(LoginController.userLogedIn.getEmail());
			BIOTF.setText(((Artist) LoginController.userLogedIn).getBio());
			if (LoginController.userLogedIn.getPicture() == null) {
				File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageViewProfile.setImage(image);
				} catch (IOException e) {
				}

			} else {
				try {
					byte[] b = LoginController.userLogedIn.getPicture();
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
					imageViewProfile.setImage(wr);
				} catch (IOException e) {
				}

			}
		} else {
			OwnProfileArtist.setVisible(false);
			OwnProfileGallery.setVisible(true);
			bioEDIT.setVisible(false);
			mailEDIT.setVisible(false);
			mailEDITGallery.setVisible(false);
			addressEDITGallery.setVisible(false);
			descriptionEDIT.setVisible(false);
			mailTF.setEditable(false);
			BIOTF.setEditable(false);
			FirstNameTF.setEditable(false);
			LastNameTF.setEditable(false);
			SaveButton.setVisible(false);
			FirstNameTFG.setEditable(false);
			LastNameTFG.setEditable(false);
			mailTFGallery.setEditable(false);
			description.setEditable(false);
			AddressTFGallery.setEditable(false);
			FirstNameTFG.setTooltip(new Tooltip("Click To Edit"));
			LastNameTFG.setTooltip(new Tooltip("Click To Edit"));
			FirstNameTFG.setText(LoginController.userLogedIn.getFirstName());
			LastNameTFG.setText(LoginController.userLogedIn.getLastName());
			mailTFGallery.setText(LoginController.userLogedIn.getEmail());
			description.setText(((Gallery)LoginController.userLogedIn).getDescription());
			AddressTFGallery.setText(((Gallery)LoginController.userLogedIn).getAddress());
			if (LoginController.userLogedIn.getPicture() == null) {
				File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
				BufferedImage bufferedImage;
				try {
					bufferedImage = ImageIO.read(file);
					Image image = SwingFXUtils.toFXImage(bufferedImage, null);
					imageViewProfileG.setImage(image);
				} catch (IOException e) {
				}

			} else {
				try {
					byte[] b = LoginController.userLogedIn.getPicture();
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
					imageViewProfileG.setImage(wr);
				} catch (IOException e) {
				}

			}

		}

		magic2(mEvent);

	}

	@FXML
	private void mailEDITPERFORM(ActionEvent event) {
		mailTF.setEditable(true);
		mailTF.requestFocus();
		BIOTF.setEditable(false);
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void bioEDITPERFORM(ActionEvent event) {
		BIOTF.setEditable(true);
		BIOTF.requestFocus();
		mailTF.setEditable(false);
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void ChangeProfilePIC(MouseEvent event) {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this change ?");

		if (confirm) {
			imageViewProfile.setImage(image);
			LoginController.userLogedIn.setPicture(bFile);
			isChanged = true;
			SaveButton.setVisible(true);
		}

	}

	@FXML
	private void EmailEDIT(MouseEvent event) {
		mailEDIT.setVisible(true);
		bioEDIT.setVisible(false);
	}

	@FXML
	private void BIOEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(true);
	}

	@FXML
	private void FirstNameEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(false);
		mailTF.setEditable(false);
		BIOTF.setEditable(false);
		FirstNameTF.setEditable(true);
		LastNameTF.setEditable(false);
		FirstNameTF.requestFocus();
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void LastNameEDIT(MouseEvent event) {
		mailEDIT.setVisible(false);
		bioEDIT.setVisible(false);
		mailTF.setEditable(false);
		BIOTF.setEditable(false);
		LastNameTF.setEditable(true);
		FirstNameTF.setEditable(false);
		LastNameTF.requestFocus();
		isChanged = true;
		SaveButton.setVisible(true);
	}

	@FXML
	private void SaveChanges(ActionEvent event) {
		if (isChanged) {
			LoginController.userLogedIn.setFirstName(FirstNameTF.getText());
			LoginController.userLogedIn.setLastName(LastNameTF.getText());
			if (!LoginController.userLogedIn.getEmail().equals(mailTF.getText())) {
				if (!LoginController.userManagment.checkMailExistance(mailTF.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("Mail entered exist in our database please try another one");
					alert.showAndWait();
					mailTF.requestFocus();
					return;
				}
			}

			LoginController.userLogedIn.setEmail(mailTF.getText());
			((Artist) LoginController.userLogedIn).setBio(BIOTF.getText());
			LoginController.userManagment.updateUser((Artist) LoginController.userLogedIn);
		}
	}

	@FXML
	public void findUsers(KeyEvent event) throws IOException {
		LrecherchFN.clear();
		Lremove.clear();
		UsersData.clear();
		searchTable.setVisible(true);
		OwnProfileArtist.setVisible(false);
		OwnProfileGallery.setVisible(false);
		if (searchTF.getText().trim().isEmpty()) {
			searchTable.setVisible(false);
			return;
		}
		if (searchTF.getText().trim().length() < 1) {
			searchTable.setVisible(false);
			return;
		}
		LrecherchFN = LoginController.userManagment.filterLastNameAndLastName(searchTF.getText());
		if (LrecherchFN == null || LrecherchFN.equals(UsersData)) {
			return;
		}

		for (User user : LrecherchFN) {
			if (user.isBlocked() || (!user.isActive()) || (user.equals(LoginController.userLogedIn))
					|| (user instanceof Admin)) {
				Lremove.add(user);
			}
		}
		LrecherchFN.removeAll(Lremove);
		Picture.setResizable(false);
		Picture.setSortable(false);
		Picture.setCellValueFactory(new PropertyValueFactory<User, byte[]>("Picture"));
		Picture.setCellFactory(new Callback<TableColumn<User, byte[]>, TableCell<User, byte[]>>() {
			@Override
			public TableCell<User, byte[]> call(TableColumn<User, byte[]> param) {
				TableCell<User, byte[]> cell = new TableCell<User, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});
		Action.setCellValueFactory(new PropertyValueFactory<>("Action"));
		Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory = //
				new Callback<TableColumn<User, String>, TableCell<User, String>>() {
					@Override
					public TableCell call(final TableColumn<User, String> param) {
						final TableCell<User, String> cell = new TableCell<User, String>() {

							final JFXButton btn = new JFXButton();

							@Override
							public void updateItem(String item, boolean empty) {
								super.updateItem(item, empty);
								if (empty) {
									setGraphic(null);
									setText(null);
								} else {
									if (getTableView().getItems().get(getIndex()) instanceof Gallery) {
										btn.setText("More Information");
										btn.setOnAction((ActionEvent event) -> {
										});
									} else {
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
									}
									setGraphic(btn);
									setText(null);
								}
							}
						};
						return cell;
					}
				};

		Action.setCellFactory(cellFactory);

		/**************************************************************/
		UsersData.addAll(LrecherchFN);
		FirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
		LastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
		searchTable.setItems(UsersData);
		searchTable.setVisible(true);

	}

	@FXML
	public void OpenSearchedProfile(MouseEvent event) throws IOException {
		if (PopupUserController.userChoosen == searchTable.getSelectionModel().getSelectedItem()) {
			return;
		}
		PopupUserController.userChoosen = searchTable.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("popupUser.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.setOnCloseRequest(e ->{
            e.consume();
            Sc.close();
            PopupUserController.userChoosen = null ;
		});
		Sc.showAndWait();

	}

	@FXML
	private void EmailEDITGallery(MouseEvent event) {
		mailEDITGallery.setVisible(true);
		descriptionEDIT.setVisible(false);
		addressEDITGallery.setVisible(false);
	}

	@FXML
	private void gallerymailEDITPERFORM(ActionEvent event) {
		mailTFGallery.setEditable(true);
		mailTFGallery.requestFocus();
		AddressTFGallery.setEditable(false);
		description.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void descriptionEDITPERFORM(ActionEvent event) {
		description.setEditable(true);
		description.requestFocus();
		AddressTFGallery.setEditable(false);
		mailTFGallery.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void DescriptionEDIT(MouseEvent event) {
		mailEDITGallery.setVisible(false);
		descriptionEDIT.setVisible(true);
		addressEDITGallery.setVisible(false);
	}

	@FXML
	private void galleryaddressEDITPERFORM(ActionEvent event) {
		AddressTFGallery.setEditable(true);
		AddressTFGallery.requestFocus();
		description.setEditable(false);
		mailTFGallery.setEditable(false);
		isChanged = true;
		SaveGalleryButton.setVisible(true);
	}

	@FXML
	private void AddressEDITGallery(MouseEvent event) {
		mailEDITGallery.setVisible(false);
		descriptionEDIT.setVisible(false);
		addressEDITGallery.setVisible(true);
	}

	@FXML
	private void SaveChangesGallery(ActionEvent event) {
		if (isChanged) {
			LoginController.userLogedIn.setFirstName(FirstNameTFG.getText());
			LoginController.userLogedIn.setLastName(LastNameTFG.getText());
			if (!LoginController.userLogedIn.getEmail().equals(mailTFGallery.getText())) {
				if (!LoginController.userManagment.checkMailExistance(mailTFGallery.getText())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Fanny");
					alert.setHeaderText(null);
					alert.setContentText("Mail entered exist in our database please try another one");
					alert.showAndWait();
					mailTFGallery.requestFocus();
					return;
				}
			}
			LoginController.userLogedIn.setEmail(mailTFGallery.getText());
			((Gallery) LoginController.userLogedIn).setDescription(description.getText());
			((Gallery) LoginController.userLogedIn).setAddress(AddressTFGallery.getText());
			LoginController.userManagment.updateUser((Gallery) LoginController.userLogedIn);
		}
	}
	
	@FXML
	private void ChangeProfilePICG(MouseEvent event) {
		Image image = null;
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
		FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
		fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
		File file = fileChooser.showOpenDialog(null);
		if (file == null) {
			return;
		}
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();
		} catch (Exception e) {
		}

		boolean confirm = ConfirmBox.display("", "Do you confirm this change ?");

		if (confirm) {
			imageViewProfile.setImage(image);
			LoginController.userLogedIn.setPicture(bFile);
			isChanged = true;
			SaveGalleryButton.setVisible(true);
		}

	}
	
	/*FINAYMEN*/
	/*DEBUTMANEL*/
	@FXML
	public void showArtDetail(ActionEvent event) throws IOException {
		System.out.println("hi");
		chosenArtwork = AllArtworkTable2.getSelectionModel().getSelectedItem();
		Parent adminScene = FXMLLoader.load(getClass().getResource("MyArtworlDetail.fxml"));
		Scene scene = new Scene(adminScene);
		Stage Sc = new Stage();
		Sc.setScene(scene);
		Sc.setScene(scene);
		Sc.setTitle("FannyTUNISIA");
		Sc.show();	
		}
	
	public void DisplayVisualArts() 
	{
		
		
		AllArtworkTable2.getItems().clear();
		picture1.setResizable(false);
		picture1.setSortable(false);
		picture1.setCellValueFactory(new PropertyValueFactory<VisualArt, byte[]>("picture"));
		picture1.setCellFactory(new Callback<TableColumn<VisualArt, byte[]>, TableCell<VisualArt, byte[]>>() {
			@Override
			public TableCell<VisualArt, byte[]> call(TableColumn<VisualArt, byte[]> param) {
				TableCell<VisualArt, byte[]> cell = new TableCell<VisualArt, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});

		/**************************************************************/
		AllArtworkTable2.getItems().clear();
		List<VisualArt> ListArtworks = proxy.findAllVisualArt2();
		for (VisualArt a : ListArtworks) {
			Myartdata.add(a);
		}
		
	
		
		name1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("name"));
		category1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("category"));
		lenght1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("length"));
		width1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("width"));
		desc1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("Description"));
		price1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("price"));
		date1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("dateOfOublication"));
		state1.setCellValueFactory(new PropertyValueFactory<Artwork, String>("state"));

		AllArtworkTable2.setItems(Myartdata);
		AllArtworkTable2.setVisible(true);
	}
	
public void DisplayArts() 
	{
		
	/*	
		allart.getItems().clear();
		pitart.setResizable(false);
		pitart.setSortable(false);
		pitart.setCellValueFactory(new PropertyValueFactory<Artwork, byte[]>("picture"));
		pitart.setCellFactory(new Callback<TableColumn<Artwork, byte[]>, TableCell<Artwork, byte[]>>() {
			@Override
			public TableCell<Artwork, byte[]> call(TableColumn<Artwork, byte[]> param) {
				TableCell<Artwork, byte[]> cell = new TableCell<Artwork, byte[]>() {
					@Override
					public void updateItem(byte[] item, boolean empty) {
						if (item != null) {
							HBox box = new HBox();
							box.setSpacing(10);
							VBox vbox = new VBox();
							ImageView imageview = new ImageView();
							imageview.setFitHeight(50);
							imageview.setFitWidth(50);
							if (item == null) {
								File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
								BufferedImage bufferedImage;
								try {
									bufferedImage = ImageIO.read(file);
									Image image = SwingFXUtils.toFXImage(bufferedImage, null);
									imageview.setImage(image);
								} catch (IOException e) {
								}

							} else {
								try {
									byte[] b = item;
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
									imageview.setImage(wr);
								} catch (IOException e) {
								}

							}

							box.getChildren().addAll(imageview, vbox);
							// SETTING ALL THE GRAPHICS COMPONENT FOR CELL
							setGraphic(box);
						}
					}
				};
				return cell;
			}

		});

		/**************************************************************/
	/*	allart.getItems().clear();
		List<Artwork> Artworks = proxy.findAllVisualArt();
		for (Artwork a : Artworks) {
			artdata.add(a);
		}
		
	
		
		nameart.setCellValueFactory(new PropertyValueFactory<Artwork, String>("name"));
		

		allart.setItems(artdata);
		AllArtworkTable2.setVisible(true);
		*/
	}
@FXML
public void showPopuppp(MouseEvent event) throws IOException {
	
	
}

@FXML 
public void visualart(ActionEvent actionEvent)
{
	ManelsPane.setVisible(true);
	AymensPane.setVisible(false);
	searchTable.setVisible(false);
	DisplayVisualArts();
}
	
	/*FNMANEL*/

}