
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class UIController extends Application implements Initializable {
	private final ObservableList<String> teamChoices = FXCollections.observableArrayList("Boston Patriots",
			"Buffalo Bills", "New York Jets", "Miami Dolphins", "Pittsburgh Steelers", "Baltimore Ravens",
			"Cincinnati Bengals", "Cleveland Browns", "Houston Texans", "Jacksonville Jaguars", "Tennessee Titans",
			"Indianapolis Colts", "Denver Broncos", "Oakland Raiders", "Kansas City Chiefs", "San Diego Chargers");

	private static Main m1;
	// ----Scene 1
	@FXML
	private Button startSimulation;
	@FXML
	private TextField numSeasons;
	// ---- Scene 2
	@FXML
	private Button button2;
	@FXML
	private ChoiceBox gameResultsChoiceBox;
	@FXML
	private ChoiceBox winLossChoiceBox;
	@FXML
	private ChoiceBox teamRosterChoiceBox;
	@FXML
	private ChoiceBox teamRevenueChoiceBox;
	@FXML
	private TextField weeksToRun;
	@FXML
	private Button contSim;
	@FXML
	private Button subGameResults;
	@FXML
	private Button subWinLoss;
	@FXML
	private Button subTeamRoster;
	@FXML
	private Button subWeekGames;
	@FXML
	private Button subTeamRevenue;
	@FXML
	private TextField weekNum;
	@FXML
	private TextArea statOutput;
	@FXML
	private Label textHeader;
	// @FXML
	// private BarChart<String, Number> uiChart;

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		// System.out.println("in button action");
		// System.out.println(event);
		// System.out.println(event.getSource());
		Stage stage = null;
		Parent root = null;
		if (event.getSource() == button2) {
			stage = (Stage) button2.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("UIMain.fxml"));
			// System.out.println("Button2 pressed");
		}

	}

	@FXML
	public void startSimulation() throws IOException {
		try {
			if (Integer.parseInt((String) numSeasons.getText()) != 0) {
				m1.setSeasonsToSim(Integer.parseInt((String) numSeasons.getText()));
				if (m1 == null)
					System.out.println("null");
				m1.step1(16, m1.getSeasonsToSim());
				Stage stage = null;
				Parent root = null;
				stage = (Stage) startSimulation.getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("UITest.fxml"));
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			else
				numSeasons.setText("Not Valid");
		} catch (NumberFormatException e)
		{
			numSeasons.setText("Not Valid");
		}
		
	}

	@FXML
	public void initScene2() {
		gameResultsChoiceBox.setItems(teamChoices);
		teamRosterChoiceBox.setItems(teamChoices);
		try {
			textHeader.setText(
					"Get Statistics: " + " Week " + (m1.getCurrWeek() + 1) + " Season " + (m1.getCurrSeasonNum() + 1));
		} catch (NullPointerException e) {

		}
	}

	@FXML
	public void handleSubmitAction(ActionEvent e) throws IOException {
		int currSeason = m1.getCurrSeasonNum();
		int currWeek = m1.getCurrWeek();
		int seasonsToSim = m1.getSeasonsToSim();
		String teamName = null;
		if (e.getSource() == contSim) {
			// continue to next weeks
			int weeksToContinue = Integer.parseInt((String) weeksToRun.getText());
			if (currSeason <= seasonsToSim) {
				m1.step2(currSeason, weeksToContinue);
				m1.setCurrWeek(currWeek + weeksToContinue);
				if (m1.getCurrWeek() > 15) {
					m1.setCurrSeasonNum(++currSeason);
					m1.setCurrWeek(0);
				}
			} else {
				statOutput.setText("Simulation Complete");
			}

		} else if (e.getSource() == subGameResults) {
			teamName = (String) gameResultsChoiceBox.getValue();
			System.out.println(m1.getCurrWeek() + " " + m1.getCurrSeasonNum());
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0) {
				//System.out.println("In print");
				statOutput.setText((Main.printTeamGames(m1.getPrevSeason(), teamName,true)));
				//m1.getPrevSeason().printRosterDetails(teamName);
			} else
				if(m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
					statOutput.setText("The season hasn't started yet");
				else
					statOutput.setText((Main.printTeamGames(m1.getCurrSeason(), teamName,false)));
		} else if (e.getSource() == subWinLoss) {
			// teamName = (String) winLossChoiceBox.getValue();
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
				statOutput.setText(Main.printWinLossRecords(m1.getPrevSeason()));
			else
				statOutput.setText(Main.printWinLossRecords(m1.getCurrSeason()));
		} else if (e.getSource() == subTeamRoster) {
			teamName = (String) teamRosterChoiceBox.getValue();
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
				statOutput.setText(Main.printTeamDetails(m1.getPrevSeason(), teamName));
			else
				statOutput.setText(Main.printTeamDetails(m1.getCurrSeason(), teamName));
		} else if (e.getSource() == subWeekGames) {
			int week = Integer.parseInt((String) weekNum.getText());
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
				statOutput.setText(Main.printWeekDetails(m1.getPrevSeason(), week));
			else
				statOutput.setText(Main.printWeekDetails(m1.getCurrSeason(), week));

		} else if (e.getSource() == subTeamRevenue) {
			// teamName = (String) teamRevenueChoiceBox.getValue();
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
				statOutput.setText(Main.printTeamRevenues(m1.getPrevSeason()));
			else
				statOutput.setText(Main.printTeamRevenues(m1.getCurrSeason()));
		}
		statOutput.setEditable(false);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// try {
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root,400,400);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// primaryStage.setScene(scene);
		// primaryStage.show();
		// } catch(Exception e) {
		// e.printStackTrace();
		// }
		BorderPane root = FXMLLoader.load(getClass().getResource("UIMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) throws Exception {
		m1 = new Main();
		launch(args);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
