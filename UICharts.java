import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UICharts {
	CategoryAxis xAxis;
	NumberAxis yAxis;
	private BarChart<String,Number> scores;
	
	public UICharts()
	{
		xAxis = new CategoryAxis();
		yAxis = new NumberAxis();
		scores = new BarChart<String,Number>(xAxis,yAxis);
	}
	
	public void generateScoresChart(Team t)
	{
		xAxis.setLabel("Game Week");
		yAxis.setLabel("Number of Wins and Losses");
		scores.setTitle("Performance");
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		
		for(Result result: t.getResults())
		{
			int week = 0;
			Game[] games = result.getGames();
			for(Game g: games)
			{
				XYChart.Data<String, Number> data;
				if(t.getTeamName().equals(g.getTeam1().getTeamName()))
					data = new XYChart.Data<>("Week : " + week++, g.getTeam1Score());
				else
					data = new XYChart.Data<>("Week : " + week++, g.getTeam2Score());
				series.getData().add(data);
			}
		}
		this.scores.getData().addAll(series);
	}

	public BarChart<String,Number> getScores() {
		return scores;
	}

	public void setScores(BarChart<String,Number> scores) {
		this.scores = scores;
	}

}
