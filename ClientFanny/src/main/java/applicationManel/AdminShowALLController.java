package applicationManel;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.InitialContext;

import entities.Category;
import entities.VisualArt;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import services.ForumManagementRemote;
import services.UserManagmentRemote;
import services.VisualArtworkEJBRemote;
import javafx.scene.control.TableColumn;

public class AdminShowALLController implements Initializable {
	@FXML
	private TableView AllVisualArt;
	@FXML
	private TableColumn Pict;
	@FXML
	private TableColumn Name;
	@FXML
	private TableColumn Price;
	@FXML
	private TableColumn Category;
	@FXML
	private TableColumn length;
	@FXML
	private TableColumn width;
	@FXML
	private TableColumn dateOfOublication;
	@FXML
	private TableColumn Description;
	@FXML
	private TableColumn State;
	@FXML
	private TableColumn Delete;
	
	

	ObservableList<VisualArt> data = FXCollections.observableArrayList();

	InitialContext ctx;
	public static VisualArtworkEJBRemote proxy;
//	public static UserManagmentRemote userManagement;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		
	}
	public void displayAllArtwork() {
		AllVisualArt.getItems().clear();
		List<VisualArt> ListAllVisualArt = proxy.findAllVisualArt();
		for (VisualArt c : ListAllVisualArt) {
			data.add(c);
		}
		System.out.println(data);
		Name.setCellValueFactory(new PropertyValueFactory<Category, String>("Name"));
//		addedAt.setCellValueFactory(new PropertyValueFactory<Category, Date>("addedAt"));
		/*addedBy.setCellValueFactory(new Callback<CellDataFeatures<Category, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Category, String> param) {
				// TODO Auto-generated method stub
				return new SimpleStringProperty(param.getValue().getAddedBy().getUsername());
			}

		});*/
		AllVisualArt.setItems(data);
	}

}
