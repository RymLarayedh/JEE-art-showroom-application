/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationOussema;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Text;
import javafx.util.Duration;

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
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        polyline.getPoints().addAll(new Double[]{
            -85.0, 320.0,
            85.0, 320.0,
            75.0, 320.0,
            85.0, 320.0,
            80.0, 320.0,
            85.0, 320.0
        });
        PathTransition transation = new PathTransition();
        transation.setNode(magicbar);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();
    }
      public void cachermagicbar() {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            85.0, 320.0,
            -85.0, 320.0,});
        PathTransition transation = new PathTransition();
        transation.setNode(magicbar);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();
    }
        public void afficherbouton(Button x, Button y, Button z) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 52.0
        });

        PathTransition transation = new PathTransition();
        transation.setNode(x);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();

        Polyline polyline2 = new Polyline();
        polyline2.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 92.0,});
        PathTransition transation2 = new PathTransition();
        transation2.setNode(y);
        transation2.setDuration(Duration.seconds(2));
        transation2.setPath(polyline2);
        transation2.play();

        Polyline polyline3 = new Polyline();
        polyline3.getPoints().addAll(new Double[]{
            100.0, 30.0,
            100.0, 132.0,});
        PathTransition transation3 = new PathTransition();
        transation3.setNode(z);
        transation3.setDuration(Duration.seconds(2));
        transation3.setPath(polyline3);
        transation3.play();

    }

    public void cacherbouton(Button x, Button y, Button z) {
        Polyline polyline = new Polyline();
        polyline.getPoints().addAll(new Double[]{
            100.0, 52.0,
            100.0, 20.0
        });

        PathTransition transation = new PathTransition();
        transation.setNode(x);
        transation.setDuration(Duration.seconds(2));
        transation.setPath(polyline);
        transation.play();

        Polyline polyline2 = new Polyline();
        polyline2.getPoints().addAll(new Double[]{
            100.0, 92.0,
            100.0, 20.0,});
        PathTransition transation2 = new PathTransition();
        transation2.setNode(y);
        transation2.setDuration(Duration.seconds(2));
        transation2.setPath(polyline2);
        transation2.play();

        Polyline polyline3 = new Polyline();
        polyline3.getPoints().addAll(new Double[]{
            100.0, 132.0,
            100.0, 20.0,});
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
    	System.out.println("oussamaaaaa");
    }

    @FXML
    private void music(ActionEvent event) {
    }

    @FXML
    private void Event(ActionEvent event) {
    }

    @FXML
    private void Tunisiancraft(ActionEvent event) {
    }

}
