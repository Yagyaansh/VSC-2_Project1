
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
import javafx.stage.Modality;
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
	@FXML
	private TextField homeFieldAdvantage;
	@FXML
	private TextField injuryRate;
	@FXML
	private TextField injuryMean;
	@FXML
	private TextField stochStdev;
	// ---- Scene 2
	@FXML
	private Button button2;
	@FXML
	private ChoiceBox teamChoiceBox;
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
	private Label textHeader1;

	//Triggered by startSimulation in UI Main
	//Begins simulation
	@FXML
	public void startSimulation() throws IOException {
		boolean validSim = true;
		//Validates each field before beginning simulation
		try {
			if (Integer.parseInt((String) numSeasons.getText()) != 0) {
				m1.setSeasonsToSim(Integer.parseInt((String) numSeasons.getText()));

			} else {
				numSeasons.setText("Not Valid");
				validSim = false;
			}
		} catch (NumberFormatException e) {
			numSeasons.setText("Not Valid");
			validSim = false;
		}
		
		if (!homeFieldAdvantage.getText().equals("")) {
			try {
				if (Integer.parseInt((String) homeFieldAdvantage.getText()) != 0) {
					Game.setTeam1Advantage(Integer.parseInt((String) homeFieldAdvantage.getText()));
				} else {
					homeFieldAdvantage.setText("Not Valid");
					validSim = false;
				}
			} catch (NumberFormatException e) {
				homeFieldAdvantage.setText("Not Valid");
				validSim = false;
			}
		}
		
		if (!injuryRate.getText().equals("")) {
			try {
				if (Double.parseDouble((String) injuryRate.getText()) >= 0
						&& Double.parseDouble((String) injuryRate.getText()) <= 1.0) {
					Game.setInjuryRate(Double.parseDouble((String) injuryRate.getText()));
				} else {
					injuryRate.setText("Not Valid");
					validSim = false;
				}
			} catch (NumberFormatException e) {
				injuryRate.setText("Not Valid");
				validSim = false;
			}
		}
		
		if (!injuryMean.getText().equals("")) {
			try {
				if (Integer.parseInt((String) injuryMean.getText()) >= 0) {
					Player.setInjuryMean(Integer.parseInt((String) injuryMean.getText()));
				} else {
					injuryMean.setText("Not Valid");
					validSim = false;
				}
			} catch (NumberFormatException e) {
				injuryMean.setText("Not Valid");
				validSim = false;
			}
		}
		
		if (!stochStdev.getText().equals("")) {
			try {
				if (Integer.parseInt((String) stochStdev.getText()) >= 0) {
					Game.setStochStdev(Integer.parseInt((String) stochStdev.getText()));
				} else {
					stochStdev.setText("Not Valid");
					validSim = false;
				}
			} catch (NumberFormatException e) {
				stochStdev.setText("Not Valid");
				validSim = false;
			}
		}
		//If fields are correct, switch scene
		if (validSim) {

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
	}
	//Handles all button actions on scene 2
	//Detects source of Action event and then validates inputs 
	@FXML
	public void handleSubmitAction(ActionEvent e) throws IOException {
		int currSeason = m1.getCurrSeasonNum();
		int currWeek = m1.getCurrWeek();
		int seasonsToSim = m1.getSeasonsToSim();
		String teamName = null;
		//Continue Simulation by weeksToRun
		if (e.getSource() == contSim) {
			//attempt to continue correct number of weeks
			try {
				int weeksToContinue = Integer.parseInt((String) weeksToRun.getText());

				teamChoiceBox.setItems(teamChoices);
				textHeader1.setText("Season " + (m1.getCurrSeasonNum() + 1) + ": Week "
						+ ((m1.getCurrWeek() + weeksToContinue) <= 16 ? (m1.getCurrWeek() + weeksToContinue) : 16));

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
			} catch (NumberFormatException exc) {
				statOutput.setText("Please enter a valid number of weeks");
			}
		//Obtain all game results for one team
		} else if (e.getSource() == subGameResults) {
			try {
				teamName = (String) teamChoiceBox.getValue();
				if (!teamName.equals("")) {
					if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0) {
						statOutput.setText((Main.printTeamGames(m1.getPrevSeason(), teamName, true)));
					} else if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
						statOutput.setText("The season hasn't started yet");
					else
						statOutput.setText((Main.printTeamGames(m1.getCurrSeason(), teamName, false)));
				}
			} catch (NullPointerException exc) {
				if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
					statOutput.setText("The season hasn't started yet");
				else
					statOutput.setText("Please choose a team");
			}
		//Obtain win loss records for all teams
		} else if (e.getSource() == subWinLoss) {
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
				statOutput.setText("The season hasn't started yet");
			else
				statOutput.setText(Main.printWinLossRecords(m1.getCurrSeason()));
		//Obtain roster details for one team
		} else if (e.getSource() == subTeamRoster) {
			try {
				teamName = (String) teamChoiceBox.getValue();
				if (!teamName.equals("")) {
					if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
						statOutput.setText("The season hasn't started yet");
					else {
						if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
							statOutput.setText(Main.printTeamDetails(m1.getPrevSeason(), teamName));
						else
							statOutput.setText(Main.printTeamDetails(m1.getCurrSeason(), teamName));
					}
				}
			} catch (NullPointerException exc) {
				if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
					statOutput.setText("The season hasn't started yet");
				else
					statOutput.setText("Please choose a team");
			}
		//Obtain all games played in specific week
		} else if (e.getSource() == subWeekGames) {
			try {
				int week = Integer.parseInt((String) weekNum.getText());
				if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
					statOutput.setText("The season hasn't started yet");
				else {
					if (week > m1.getCurrWeek())
						statOutput.setText("That week has not been played yet.");
					else {
						if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
							statOutput.setText(Main.printWeekDetails(m1.getPrevSeason(), week));
						else
							statOutput.setText(Main.printWeekDetails(m1.getCurrSeason(), week));
					}
				}
			} catch (NumberFormatException exc) {
				statOutput.setText("Please enter a valid week number");
			} catch (NullPointerException exc) {
				statOutput.setText("You must enter a value");
			} catch (ArrayIndexOutOfBoundsException exc)
			{
				statOutput.setText("You must enter a valid week number");
			}
		//Obtain Revenue statistics for all teams
		} else if (e.getSource() == subTeamRevenue) {
			if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
				statOutput.setText("The season hasn't started yet");
			else {
				if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() != 0)
					statOutput.setText(Main.printTeamRevenues(m1.getPrevSeason()));
				else
					statOutput.setText(Main.printTeamRevenues(m1.getCurrSeason()));
			}
		}
		statOutput.setEditable(false);

	}
	//Load UIMain at init
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = FXMLLoader.load(getClass().getResource("UIMain.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	//Displays win loss graph on button press
	@FXML
	private void showGraph() throws IOException {
		if (m1.getCurrWeek() == 0 && m1.getCurrSeasonNum() == 0)
			statOutput.setText("The season hasn't started yet");
		else {

			WinLossGraphController.setTeams(m1.getTeams());
			BorderPane Graph = FXMLLoader.load((getClass().getResource("WinLossRatioGraph.fxml")));
			Stage addDialogStage = new Stage();
			addDialogStage.setTitle("Graph");
			addDialogStage.initModality(Modality.WINDOW_MODAL);
			Scene scene = new Scene(Graph);
			addDialogStage.setScene(scene);
			addDialogStage.showAndWait();
		}
	}
	//Launch Main
	public static void main(String[] args) throws Exception {
		m1 = new Main();
		launch(args);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
