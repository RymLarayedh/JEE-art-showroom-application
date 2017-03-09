package applicationOussema;
	
import javafx.application.Application;
import javafx.stage.Stage;
import utils.ConfirmBox;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("DetailArtwork.fxml"));
			Scene scene = new Scene(root,920,970);

			primaryStage.setScene(scene);
			primaryStage.setTitle("FannyTUNISIA");
            primaryStage.setOnCloseRequest(e ->{
                e.consume();
                closeProgram(primaryStage);
            });
            primaryStage.show();
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
    public static void closeProgram(Stage s)
    {
        boolean answer = ConfirmBox.display("Exit", "Sure you want to exit ?");
        if(answer)
        {
            s.close();
        }
        
    }
}
