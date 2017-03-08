package applicationRym;
	
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
			Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
			Scene scene = new Scene(root,900,600);
			//scene.getStylesheets().add(getClass().getResource("Login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("FannyTUNISIA");
            primaryStage.setOnCloseRequest(e ->{
                e.consume();
                closeProgram(primaryStage);
            });
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
