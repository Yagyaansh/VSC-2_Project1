import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WinLossGraphController implements Initializable{

	public static ArrayList<Team> teams;
	
	@FXML
	private BarChart<?, ?> WinLossChart;
	
	@FXML
	private NumberAxis Y;
	
	@FXML
	private CategoryAxis X;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		XYChart.Series set1 = new XYChart.Series<>();
		for (int x = 0; x < teams.size(); x++){
			set1.getData().add(new XYChart.Data(teams.get(x).getTeamName(), (teams.get(x).getWins()/((double)(teams.get(x).getLosses()+teams.get(x).getWins())))));
		}

		
		WinLossChart.getData().addAll(set1);
	}
	
	public static void setTeams(ArrayList<Team> inTeams){
		teams = inTeams;
	}
}
