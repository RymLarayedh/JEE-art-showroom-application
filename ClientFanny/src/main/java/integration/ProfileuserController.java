/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package integration;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import entities.Admin;
import entities.Artist;
import entities.Artwork;
import entities.Event;
import entities.EventUser;
import entities.EventUserID;
import entities.Gallery;
import entities.Music;
import entities.TunisianCraft;
import entities.User;
import entities.VisualArt;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Label;
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
import services.ArtworkManagemetRemote;
import services.EventManagmentRemote;
import services.EventUserManagmentRemote;
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
	
	/*****************DEBUT RYM*******************/
	
	 @FXML
	    private TableView<Event> eventsPane;
	    @FXML
	    private TableColumn<Event, Date> DateBeginEvents;
	    @FXML
	    private TableColumn<Event, String> TitleEvents;
		ObservableList<Event> dataEvent = FXCollections.observableArrayList();

	    //***
		public static User userLogedIn;
		@FXML
	    private TableView<EventUser> eventsPane1;
	    @FXML
	    private TableColumn<EventUser, Date> DateBeginEvents1;
	    @FXML
	    private TableColumn<EventUser, String> TitleEvents1;
	    ObservableList<EventUser> dataEvent1 = FXCollections.observableArrayList();
		//***
	    @FXML
	    private Label nbrParticipant;
	    @FXML
	    private Label usernameArtist;
	    @FXML
	    private Label usernameGallery;
	    //***
		private int selected;
		//*****
		@FXML
	    private TableView<EventUser> tableP;
	    @FXML
	    private TableColumn<EventUser, String> usernameP;
	    @FXML
	    private TableColumn<EventUser, String> firstNameP;
	    @FXML
	    private TableColumn<EventUser, String> lastNameP;
	    @FXML
	    private TableColumn<EventUser, String> emailP;
	    ObservableList<EventUser> dataEventUser = FXCollections.observableArrayList();
	    
	    @FXML
	    private ImageView yes;
	    @FXML
	    private Label yesLabel;
	    @FXML
	    private ImageView no;
	    @FXML
	    private Label noLabel;

		//*****
	    @FXML
	    private Label eventMax1;
	    @FXML
	    private Label eventMax2;
	    @FXML
	    private Label eventMax3;
	    @FXML
	    private Label nbrMax1;
	    @FXML
	    private Label nbrMax2;
	    @FXML
	    private Label nbrMax3;
	    @FXML
	    private ImageView imgMax1;
	    @FXML
	    private ImageView imgMax2;
	    @FXML
	    private ImageView imgMax3;
	    
	    //*****
	    @FXML
	    private AnchorPane EventsPane;
	    private static Event GlobalPTP;
	    
	    //***
	    @FXML
	    private Label bodyLabel;
	    @FXML
	    private Label dateBeginLabel;
	    @FXML
	    private Label dateBeginLabel1;
		
	    
	
	/*****************FIN RYM********************/

	    /*Debut oussama*/
	    @FXML
	    private AnchorPane OussamasPane;
		@FXML
		private Label artist;
		@FXML
		private Label artwork;
		@FXML
		private Label price;
		@FXML
		private Label date;
		 @FXML
		    private ImageView img;
		InitialContext ctxO;
	 int pos = 0;
		@FXML
		private AnchorPane OussamaCraft;
		
	    @FXML
	    private JFXComboBox<String> TypeT;
	    @FXML
	    private JFXTextArea DescT;
	    @FXML
	    private JFXTextField QuantityT;
	    @FXML
	    private ImageView ImageT;
	    
	    @FXML
	    private JFXTextField  pricet;
	    @FXML
	    private JFXTextField namet;
	    int idar = 1 ;  
		InitialContext ctxo;

	    @FXML
	    private TableColumn< TunisianCraft,byte[] > pictureo;

	    @FXML
	    private TableColumn<TunisianCraft,String > nameo;

	    @FXML
	    private TableColumn<TunisianCraft,String> priceo;

	    @FXML
	    private TableColumn<TunisianCraft,String> Typeo;

	    @FXML
	    private TableColumn<TunisianCraft,String> stateo;

	    @FXML
	    private TableColumn<TunisianCraft,String> dateo;

	    @FXML
	    private TableColumn<TunisianCraft,String> desco;
	    @FXML
	    private TableView<TunisianCraft> TunisianTable;
		ObservableList<TunisianCraft> TCdata = FXCollections.observableArrayList();

		File pictureoo;
	 
	 /*FIN OUSSAMA*/
	 
	 
	 
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
		OussamasPane.setVisible(false);
		OussamaCraft.setVisible(false);
		

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
		
		/*RYM*/
        EventsPane.setVisible(false);
        yes.setVisible(false);
        yesLabel.setVisible(false);
        no.setVisible(false);
        noLabel.setVisible(false);
		/*END RYM*/
        
        /*D.Oussama*/
        AfficherTn();
        /*F.Oussama*/

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

/*RYM*/
@FXML
private void Event(ActionEvent event) throws NamingException {
	yes.setVisible(false);
    yesLabel.setVisible(false);
    no.setVisible(false);
    noLabel.setVisible(false);
	EventsPane.setVisible(true);
	remplirEvents();
	remplirEvents1();
	updateMaxEvents();
	
}
public void updateMaxEvents() throws NamingException{
	eventMax1.setText("");
	eventMax2.setText("");
	eventMax3.setText("");
	nbrMax1.setText("");
	nbrMax2.setText("");
	nbrMax3.setText("");
	
	InitialContext ctxRym = new InitialContext();
	Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
	EventManagmentRemote proxyRym = (EventManagmentRemote) objet;
	List<Event> au = proxyRym.getAllEvents();	
	InitialContext ctxEU = new InitialContext();
	Object objetEU = ctxEU.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
	EventUserManagmentRemote proxyEU = (EventUserManagmentRemote) objetEU;	
	
	//********************
	List<Event> EventBySize =new ArrayList<Event>();
	List<EventUser> Leu = new ArrayList<EventUser>();
	List<Event> EventBySize2 = new ArrayList<Event>();
//    for (Event e : au ){
//    	Leu = proxyEU.findByEventId(e.getIdEvent());
//    	for (EventUser eventUser : Leu) {
//			if(eventUser.getStatus()==1){
//				EventBySize.add(e);
//				//EventBySize.add(proxyRym.findEventById(eventUser.getEvent().getIdEvent()));
//			}
//		}  
//    }
//    
//    System.out.println(EventBySize.get(1).getIdEvent());
	EventBySize2=proxyRym.getAllEvents();
	
      for (Event event : EventBySize2) {
    	  int t=0;
		Leu=event.getListEventUser();
		for (EventUser eventUser : Leu) {
			if(eventUser.getStatus()==1){
				
				t=t+1;
			}
	}
		System.out.println(t);
		EventBySize.add(event);
		System.out.println();
      
	}
    //************************
   
    //*************************
    
	Collections.sort(EventBySize, new Comparator<Event>() {
        public int compare(Event o1, Event o2) {
        	List<EventUser> aaa = new ArrayList<EventUser>();
        	for (EventUser eu : o2.getListEventUser()) {
				if(eu.getStatus()==1){
					aaa.add(eu);
				}
			}
        	List<EventUser> aaa2 = new ArrayList<EventUser>();
        	for (EventUser eu : o1.getListEventUser()) {
				if(eu.getStatus()==1){
					aaa2.add(eu);
				}
			}
            return aaa.size() - aaa2.size();
        }
    });
	if(EventBySize.size()>=3)
	{
		eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
			      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(0).getGallery().getUsername());
		eventMax2.setText("Title = "+EventBySize.get(1).getTitle()+"\nArtist = "
			      +EventBySize.get(1).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(1).getGallery().getUsername());
		
		eventMax3.setText("Title = "+EventBySize.get(2).getTitle()+"\nArtist = "
			      +EventBySize.get(2).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(2).getGallery().getUsername());
		
		List<EventUser> aaab = new ArrayList<EventUser>();
    	for (EventUser eu : EventBySize.get(0).getListEventUser()) {
			if(eu.getStatus()==1){
				aaab.add(eu);
			}
		}
    	
    	List<EventUser> aaab2 = new ArrayList<EventUser>();
    	for (EventUser eu : EventBySize.get(1).getListEventUser()) {
			if(eu.getStatus()==1){
				aaab2.add(eu);
			}
		}
    	
    	List<EventUser> aaab3 = new ArrayList<EventUser>();
    	for (EventUser eu : EventBySize.get(2).getListEventUser()) {
			if(eu.getStatus()==1){
				aaab3.add(eu);
			}
		}
    	
		Integer i1=aaab.size();
		nbrMax1.setText(i1.toString());
		Integer i2=aaab2.size();
		nbrMax2.setText(i2.toString());
		Integer i3=aaab3.size();
		nbrMax3.setText(i3.toString());
	}
	if(EventBySize.size()==2)
	{
		eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
			      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(0).getGallery().getUsername());
		eventMax2.setText("Title = "+EventBySize.get(1).getTitle()+"\nArtist = "
			      +EventBySize.get(1).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(1).getGallery().getUsername());
		eventMax3.setText("");
		
		Integer i1=EventBySize.get(0).getListEventUser().size();
		nbrMax1.setText(i1.toString());
		Integer i2=EventBySize.get(1).getListEventUser().size();
		nbrMax2.setText(i2.toString());
	}
	if(EventBySize.size()==1)
	{
		eventMax1.setText("Title = "+EventBySize.get(0).getTitle()+"\nArtist = "
			      +EventBySize.get(0).getArtist().getUsername()+"\nGallery="
						+EventBySize.get(0).getGallery().getUsername());
		eventMax2.setText("");
		eventMax3.setText("");
		Integer i1=EventBySize.get(0).getListEventUser().size();
		nbrMax1.setText(i1.toString());
	}	
}
public void remplirEvents() throws NamingException{
	InitialContext ctxRym = new InitialContext();
	Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventManagment!services.EventManagmentRemote");
	EventManagmentRemote proxyRym = (EventManagmentRemote) objet;
	ObservableList<Event> eventSelected, allEvent;
    allEvent = eventsPane.getItems();
    allEvent.clear();
    for (Event e : proxyRym.getAllEvents()) {
        dataEvent.add(e);
    }
    DateBeginEvents.setCellValueFactory(new PropertyValueFactory<Event, Date>("dateBegin"));
    TitleEvents.setCellValueFactory(new PropertyValueFactory<Event, String>("title"));
   
    	eventsPane.setItems(dataEvent);
	
}
public void remplirEvents1() throws NamingException{
		
		try {
		InitialContext ctxRym = new InitialContext();
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxyRym = (EventUserManagmentRemote) objet;	
		ObservableList<EventUser> eventUSelected, allEvent;
        allEvent = eventsPane1.getItems();
        allEvent.clear();
        //******************************change***************************
        List<EventUser> abc=new ArrayList<EventUser>();
        for (EventUser e : proxyRym.findByUserId(LoginController.userLogedIn.getIdUser()) ){
            if(e.getStatus()==1)
            {
            	abc.add(e);
            }
        }
        for (EventUser e : abc ){
            dataEvent1.add(e);
        }
        
        DateBeginEvents1.setCellValueFactory(new Callback<CellDataFeatures<EventUser, Date>, ObservableValue<Date>>() {

			@Override
			public ObservableValue<Date> call(CellDataFeatures<EventUser, Date> param) {
				// TODO Auto-generated method stub
				return new SimpleObjectProperty<Date>(param.getValue().getEvent().getDateBegin());
			}

		});
        TitleEvents1.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getEvent().getTitle());
			}

		});

        eventsPane1.setItems(dataEvent1);
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	


@FXML
private void Tunisiancraft(ActionEvent event) {
	OussamasPane.setVisible(true);
}
@FXML
private void complain(ActionEvent event) throws IOException {
	Parent ReclamationScene = FXMLLoader.load(getClass().getResource("ReclamationAdd.fxml"));
	Scene scene = new Scene(ReclamationScene);
	//scene.getStylesheets().add(getClass().getResource("Gallery.css").toExternalForm());
	Stage Sc = new Stage();
	Sc.setScene(scene);
	Sc.show();
}
@FXML
private void detailsMyEvents(MouseEvent event) {
	
	EventUser PTP = eventsPane1.getSelectionModel().getSelectedItem();
    if (PTP != null) {
    	yes.setVisible(true);
    	yesLabel.setVisible(true);
    	no.setVisible(false);
    	noLabel.setVisible(false);
    	
        this.selected = 1;
        remplirTableParticipant(PTP.getEvent());
        usernameArtist.setText(PTP.getEvent().getArtist().getUsername());
        usernameGallery.setText(PTP.getEvent().getGallery().getUsername());
        //*****************NBR Participant********************
        List<EventUser> dFinal = new ArrayList<EventUser>();
        List<EventUser> d=PTP.getEvent().getListEventUser(); 
        for (EventUser eventUser : d) {
			if(eventUser.getStatus()==1){
				dFinal.add(eventUser);
			}
		}
        Integer iFinal= dFinal.size();
        nbrParticipant.setText(iFinal.toString()+" participants");
        //*****************************************************
    } else {
        this.selected = 0;
        yes.setVisible(false);
    	yesLabel.setVisible(false);
    	no.setVisible(false);
    	noLabel.setVisible(false);
    }
}
@FXML
private void detailsEvents(MouseEvent event) {
	ObservableList<EventUser> eventUSelected, allEvent;
    allEvent = tableP.getItems();
    allEvent.clear();
	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
    if (PTP != null) {
    	remplirTableParticipant(PTP);
    	Boolean test =false;
    	
    	
    	for (EventUser e : PTP.getListEventUser()) {
    		//*******************change***************
			if ((e.getUser().getIdUser()==LoginController.userLogedIn.getIdUser())&&(e.getStatus()==1)){
				test =true;	
			}
		}
    	if (test ==true){
    		yes.setVisible(true);
        	yesLabel.setVisible(true);
        	no.setVisible(false);
        	noLabel.setVisible(false);
    	}
    	else{
    		yes.setVisible(false);
        	yesLabel.setVisible(false);
        	no.setVisible(true);
        	noLabel.setVisible(true);
    	}
        this.selected = 1;
        
        usernameArtist.setText(PTP.getArtist().getUsername());
        usernameGallery.setText(PTP.getGallery().getUsername()+"\n"+PTP.getGallery().getAddress());
        
        dateBeginLabel.setText(PTP.getDateBegin().toString());
        dateBeginLabel1.setText(PTP.getDateEnd().toString());
        bodyLabel.setText(PTP.getDescription());
        
//        Integer d=PTP.getListEventUser().size(); 
//        nbrParticipant.setText(d.toString());
        
    	//********* NBR Participant ******************
        List<EventUser> dFinal = new ArrayList<EventUser>();
        List<EventUser> d=PTP.getListEventUser(); 
        for (EventUser eventUser : d) {
			if(eventUser.getStatus()==1){
				dFinal.add(eventUser);
			}
		}
        Integer iFinal= dFinal.size();
        nbrParticipant.setText(iFinal.toString()+" participants");
        GlobalPTP=PTP;
    } else {
        this.selected = 0;
    }
}
public void remplirTableParticipant(Event ptp){
		Integer idEvent=ptp.getIdEvent();
		try {
		InitialContext ctxRym = new InitialContext();
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxyRym = (EventUserManagmentRemote) objet;	
		ObservableList<EventUser> eventUSelected, allEvent;
        allEvent = tableP.getItems();
        allEvent.clear();
        List<EventUser> aEU = new ArrayList<EventUser>();
        for (EventUser eventUser : proxyRym.findByEventId(idEvent)) {
        	if(eventUser.getStatus()==1){
        		aEU.add(eventUser);
        	}	
		}
        for (EventUser e : aEU) {
            dataEventUser.add(e);
        }
        
        usernameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getUsername());
			}

		});
        firstNameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getFirstName());
			}

		});
        lastNameP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getLastName());
			}

		});
        emailP.setCellValueFactory(new Callback<CellDataFeatures<EventUser, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<EventUser, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getUser().getEmail());
			}

		});

        tableP.setItems(dataEventUser);
		
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
	
}
public void updatenbrParticipant(){
	//*******ryyym
	
	//********* NBR Participant ******************
    List<EventUser> dFinal = new ArrayList<EventUser>();
    System.out.println("********"+GlobalPTP.getIdEvent());
    List<EventUser> d=GlobalPTP.getListEventUser(); 
    for (EventUser eventUser : d) {
		if(eventUser.getStatus()==1){
			dFinal.add(eventUser);
		}
	}
    Integer iFinal= dFinal.size();
    nbrParticipant.setText(iFinal.toString()+" participants");
    //***********************************
	
}
@FXML
private void yesParticipate(MouseEvent event) throws NamingException {
	
	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
    if (PTP != null) {
        this.selected = 1;
        InitialContext ctxRym = new InitialContext();
		Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
    	EventUserManagmentRemote proxyRym = (EventUserManagmentRemote) objet;
    	//*************************change****************
    	
    	if(proxyRym.findByUserEventId(LoginController.userLogedIn.getIdUser(), PTP.getIdEvent())==null){
    		EventUser eu = new EventUser();
        	//eu.setEvent(PTP);
        	//*******************change*********
        	InitialContext ctxU = new InitialContext();	
    		Object object = ctxU.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
    		UserManagmentRemote proxyU = (UserManagmentRemote) object;
    		User u = proxyU.findById(LoginController.userLogedIn.getIdUser());
        	//eu.setUser(u);
        	
        	EventUserID euID = new EventUserID();
        	euID.setIdEventPK(PTP.getIdEvent());
        	euID.setIdUserPK(LoginController.userLogedIn.getIdUser());
        	eu.setEtudiantCoursID(euID);
        	eu.setStatus(1);
        	proxyRym.addEventUser(eu);
    	}
    	
    	else{
    		//************change********
    		EventUser eu = proxyRym.findByUserEventId(LoginController.userLogedIn.getIdUser(), PTP.getIdEvent());
        	eu.setStatus(1);
        	proxyRym.updateEventUser(eu);
    			
    	}       	
    	//************************************
    	
    	yes.setVisible(true);
    	yesLabel.setVisible(true);
    	no.setVisible(false);
    	noLabel.setVisible(false);
    	remplirEvents();
    	remplirEvents1();
    	updateMaxEvents();
    	updatenbrParticipant();
    } else {
        this.selected = 0;
    }
    
	
}
@FXML
private void noParticipate(MouseEvent event) throws NamingException {
//	eventMax1.setText("");
//	eventMax2.setText("");
//	eventMax3.setText("");
	
	yes.setVisible(false);
	yesLabel.setVisible(false);
	no.setVisible(true);
	noLabel.setVisible(true);
	Event PTP = eventsPane.getSelectionModel().getSelectedItem();
	 if (PTP != null) {
	InitialContext ctxRym = new InitialContext();
	Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
	EventUserManagmentRemote proxyRym = (EventUserManagmentRemote) objet;
	//********change
	EventUser eu = proxyRym.findByUserEventId(LoginController.userLogedIn.getIdUser(), PTP.getIdEvent());
	eu.setStatus(9);
	proxyRym.updateEventUser(eu);
//	ObservableList<Event> eventSelected, allEvent;
//    allEvent = eventsPane.getItems();
//    allEvent.clear();
	remplirEvents();
//	ObservableList<EventUser> eventSelected1, allEvent1;
//    allEvent1 = eventsPane1.getItems();
//    allEvent1.clear();
	remplirEvents1();
	updateMaxEvents();
	updatenbrParticipant();
	 } 
	
	 else {
	 this.selected = 0;
 		}
	 EventUser PTP2 = eventsPane1.getSelectionModel().getSelectedItem();
	 if (PTP2 != null) {
	InitialContext ctxRym = new InitialContext();
	Object objet = ctxRym.lookup("/Fanny-ear/Fanny-ejb/EventUserManagment!services.EventUserManagmentRemote");
	EventUserManagmentRemote proxyRym = (EventUserManagmentRemote) objet;
	//********change
	
	PTP2.setStatus(9);
	proxyRym.updateEventUser(PTP2);
//	ObservableList<Event> eventSelected, allEvent;
//    allEvent = eventsPane.getItems();
//    allEvent.clear();
	remplirEvents();
//	ObservableList<EventUser> eventSelected1, allEvent1;
//    allEvent1 = eventsPane1.getItems();
//    allEvent1.clear();
	remplirEvents1();
	updateMaxEvents();
	updatenbrParticipant();
	 } 
	
	 else {
	 this.selected = 0;
 		}
}
/*FIN RYM*/

/*Debut Oussama*/
@FXML
public void First(ActionEvent event)  {
	
pos =0 ;
AfficherTn();
	
}
// Event Listener on Button.onAction
@FXML
public void Next(ActionEvent event) throws NamingException {
	ctxo = new InitialContext();
	Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
	ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
	List <TunisianCraft> lst= new ArrayList<>();
	lst= proxyo.getAllTunisianCraft();
	int size = lst.size();

    if (pos < size-1) {
        pos++;
        AfficherTn(); 
    }	}
// Event Listener on Button.onAction
@FXML
public void Prec(ActionEvent event) throws NamingException {
	ctxo = new InitialContext();
	Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
	ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
	List <TunisianCraft> lst= new ArrayList<>();
	lst= proxyo.getAllTunisianCraft(); 
	int size = lst.size();

        if (pos >= 0) {
            pos--;
            AfficherTn();
            
            
        }
}
// Event Listener on Button.onAction
@FXML
public void Last(ActionEvent event) throws NamingException {
	ctxo = new InitialContext();
	Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
	ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
	List <TunisianCraft> lst= new ArrayList<>();
	lst= proxyo.getAllTunisianCraft(); 
	int size = lst.size();

       pos=size-1;
            AfficherTn();
        }

public void AfficherTn()
{
	try {
		ctxo = new InitialContext();
		Object objeto = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
		ArtworkManagemetRemote proxyo = (ArtworkManagemetRemote) objeto;
		List <TunisianCraft> lst= new ArrayList<>();
		lst= proxyo.getAllTunisianCraft();
		
		
		System.out.println(lst);
    //artist.setText(lst.get(pos).getUser().getUsername());
    String P = String.valueOf(lst.get(pos).getPrice());
    price.setText(P);
   artwork.setText(lst.get(pos).getName());
  // artist.setText(lst.get(pos).getUser().getUsername());
	if (lst.get(pos).getPicture()== null) {
		File file = new File("./src/main/java/buttons/PasDePhotoDeProfil.png");
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(file);
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			img.setImage(image);
		} catch (IOException e) {
		}}
		else
		{
   BufferedImage imgbf = null;
   byte[] b = lst.get(pos).getPicture();

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

     
		}} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	
	
}

@FXML
public void Myownspace(ActionEvent event) {
	OussamaCraft.setVisible(true);
	ObservableList<String> Types = FXCollections.observableArrayList();
    Types.add("Pottery");
    Types.add("Ceramique");
    Types.add("Mosaic");
    Types.add("Traditional Clothing");
    Types.add("Other");
	TypeT.setItems(Types);
    try {
		DisplayTunisianCraft();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        }

@FXML
private void choosePic(ActionEvent event) {
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
	FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
	fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
	pictureoo = fileChooser.showOpenDialog(null);
	if (picture == null) {
		return;
	}
	try {
		BufferedImage bufferedImage = ImageIO.read(pictureoo);
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		ImageT.setImage(image);
	} catch (IOException ex) {
	}
	
}

@FXML
private void AddTunisianCraft(ActionEvent event) throws NamingException {
	ctx = new InitialContext();
	Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
	ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;	
    TunisianCraft TunC = new TunisianCraft() ;
    LocalDateTime now = LocalDateTime.now();
	Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
	Date date = Date.from(instant);
	System.out.println(date);
	TunC.setDateOfOublication(date);
    TunC = new TunisianCraft();
	TunC.setType(TypeT.getValue());
	TunC.setDescription(DescT.getText());
	TunC.setName(namet.getText());
	
	float f = Float.parseFloat(pricet.getText());
	

	TunC.setPrice(f);
	int Q = Integer.parseInt(QuantityT.getText());
	TunC.setQuantity(Q);
	
	Object object = ctx.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
	UserManagmentRemote proxyU = (UserManagmentRemote) object;
	User U =new Artist();
	U= proxyU.findById(idar);
	System.out.println(U);
	TunC.setUser(U);
	byte[] bFile = new byte[(int) pictureoo.length()];
	try {
		FileInputStream fileInputStream = new FileInputStream(pictureoo);
		fileInputStream.read(bFile);
		fileInputStream.close();
		TunC.setPicture(bFile);

	} catch (Exception e) {
	}
	proxy.addTunisianCraft(TunC);
	DisplayTunisianCraft();
}
 
public void DisplayTunisianCraft() throws NamingException 
{
	TunisianTable.getItems().clear();
	pictureo.setResizable(false);
	pictureo.setSortable(false);
	pictureo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, byte[]>("picture"));
	pictureo.setCellFactory(new Callback<TableColumn<TunisianCraft, byte[]>, TableCell<TunisianCraft, byte[]>>() {
		@Override
		public TableCell<TunisianCraft, byte[]> call(TableColumn<TunisianCraft, byte[]> param) {
			TableCell<TunisianCraft, byte[]> cell = new TableCell<TunisianCraft, byte[]>() {
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
	ctx = new InitialContext();
	Object objet = ctx.lookup("/Fanny-ear/Fanny-ejb/ArtworkManagemet!services.ArtworkManagemetRemote");
	ArtworkManagemetRemote proxy = (ArtworkManagemetRemote) objet;	

	TunisianTable.getItems().clear();
	List<TunisianCraft> ListTc = proxy.findByArtist(idar);
	for (TunisianCraft T : ListTc ) {
		TCdata.add(T);
	}
	

	
	nameo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("name"));
	Typeo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("Type"));
	desco.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("Description"));
	priceo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("price"));
	dateo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("dateOfOublication"));
	stateo.setCellValueFactory(new PropertyValueFactory<TunisianCraft, String>("state"));
	
	TunisianTable.setItems(TCdata);
	TunisianTable.setVisible(true);
	
	
	
	

}

/*FIN Oussama*/

}
