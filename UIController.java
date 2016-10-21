
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class UIController implements Initializable {
	private final ObservableList<String> teamChoices = FXCollections.observableArrayList("Boston Patriots",
			"Buffalo Bills", "New York Jets", "Miami Dolphins", "Pittsburgh Steelers", "Baltimore Ravens",
			"Cincinnati Bengals", "Cleveland Browns", "Houston Texans", "Jacksonville Jaguars", "Tennessee Titans",
			"Indianapolis Colts", "Denver Broncos", "Oakland Raiders", "Kansas City Chiefs", "San Diego Chargers");
	@FXML
	private CheckBox testbox;
	@FXML
	private Button button1;
	@FXML
	private Button button2;
	@FXML
	private ChoiceBox gameResultsChoiceBox;
	@FXML private ChoiceBox winLossChoiceBox;
	@FXML private ChoiceBox teamRosterChoiceBox;
	@FXML private ChoiceBox teamRevenueChoiceBox;
	//@FXML
	//private BarChart<String, Number> uiChart;

	@FXML
	private void testCheck() {
		System.out.println("Checked the box");
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		// System.out.println("in button action");
		// System.out.println(event);
		// System.out.println(event.getSource());
		Stage stage = null;
		Parent root = null;
		System.out.println(button1);
		if (event.getSource() == button1) {
			stage = (Stage) button1.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("UITest.fxml"));

			// System.out.println("Button1 pressed");

		}
		if (event.getSource() == button2) {
			stage = (Stage) button2.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("UIMain.fxml"));
			// System.out.println("Button2 pressed");
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);

		stage.show();
	}
	
	@FXML
	public void initScene2()
	{
		gameResultsChoiceBox.setItems(teamChoices);
		teamRevenueChoiceBox.setItems(teamChoices);
		teamRosterChoiceBox.setItems(teamChoices);
		winLossChoiceBox.setItems(teamChoices);
		
		
//		XYChart.Series<String, Number> series = new XYChart.Series<>();
//		
//		series.getData().add(new XYChart.Data<String,Number>("Week 1", 34));
//		series.getData().add(new XYChart.Data<String,Number>("Week 2", 45));
//		uiChart.getData().addAll(series);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
