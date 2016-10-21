import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class UICharts {
	NumberAxis xAxis;
	NumberAxis yAxis;
	private BarChart<Number,Number> scores;
	
	public UICharts()
	{
		xAxis = new NumberAxis();
		yAxis = new NumberAxis();
		scores = new BarChart<Number,Number>(xAxis,yAxis);
	}
	
	public void generateScoresChart(Team t)
	{
		xAxis.setLabel("Game Week");
		yAxis.setLabel("Number of Wins and Losses");
		scores.setTitle("Performance");
		XYChart.Series<Number, Number> series = new XYChart.Series<>();
		
		for(Result result: t.getResults())
		{
			int week = 0;
			Game[] games = result.getGames();
			for(Game g: games)
			{
				XYChart.Data<Number, Number> data;
				if(t.getTeamName().equals(g.getTeam1().getTeamName()))
					data = new XYChart.Data<>(week++, g.getTeam1Score());
				else
					data = new XYChart.Data<>(week++, g.getTeam2Score());
				series.getData().add(data);
			}
		}
		this.scores.getData().addAll(series);
	}

	public BarChart<Number,Number> getScores() {
		return scores;
	}

	public void setScores(BarChart<Number,Number> scores) {
		this.scores = scores;
	}

}
