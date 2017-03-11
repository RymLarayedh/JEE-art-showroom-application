/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationInes;

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
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.jfoenix.controls.JFXSlider;

import entities.Category;
import entities.Music;
import entities.User;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javafx.util.Duration;
import services.ForumManagementRemote;
import services.UserManagmentRemote;
import utils.ConfirmBox;

/**
 * FXML Controller class
 *
 * @author Oussamabh
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

	InitialContext ctx;
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
		
		
		try {
			ctx = new InitialContext();
			forumManagement = (ForumManagementRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/ForumManagement!services.ForumManagementRemote");
			userManagement = (UserManagmentRemote) ctx
					.lookup("/Fanny-ear/Fanny-ejb/UserManagment!services.UserManagmentRemote");
			Category p = new Category();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MusicPane.setVisible(false);
		musicBarAnchor.setDisable(true);
		magicbar.setVisible(false);

	}

	@FXML
	private void profil(ActionEvent event) {
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
		magic.setVisible(false);
		cachermagicbar();
		magic.setVisible(true);
	}

	@FXML
	private void Myownspace(ActionEvent event) {
	}

	@FXML
	private void visualart(ActionEvent event) {
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
								File file = new File("./src/main/java/music/1.jpg");
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

}
