import java.util.ArrayList;

import javafx.collections.ObservableListBase;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class UICharts {	
	
	/*
	 * Graphs the team's score week by week.
	 */
	public static void generateScoresChart(BarChart<String,Number> chart, Team t)
	{
		chart.getXAxis().setLabel("Game Week");
		chart.getYAxis().setLabel("Score");
		chart.setTitle("Performance");
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
		chart.getData().addAll(series);	
	}
	
	/*
	 * Graphs the teams performance by season
	 */
	public static void generateSeasonResultsChart(BarChart<String,Number> chart, Team t)
	{
		chart.getXAxis().setLabel("Season");
		chart.getYAxis().setLabel("Win-Loss");
		chart.setTitle("Performace");
		XYChart.Series<String, Number> seriesWins = new XYChart.Series<>();
		XYChart.Series<String, Number> seriesLosses = new XYChart.Series<>();
		
		seriesWins.setName("Wins");
		seriesLosses.setName("Losses");

		int season = 0;
		for(Result result: t.getResults())
		{
			XYChart.Data<String, Number> dataWins = new XYChart.Data<>("Season : " + season, result.getWins());
			XYChart.Data<String, Number> dataLosses = new XYChart.Data<>("Season : " + season++, result.getLosses());
			seriesWins.getData().add(dataWins);
			seriesLosses.getData().add(dataLosses); 
		}
		chart.getData().addAll(seriesWins, seriesLosses);	
	}
	
	/*
	 * Generates revenue and profit for all the teams till current week
	 */
	public static void generateAllTeamRevenuesChart(BarChart<String,Number> chart, ArrayList<Team> teams)
	{
		chart.getXAxis().setLabel("Team");
		chart.getYAxis().setLabel("Revenue");
		chart.setTitle("Revenues");
		
		XYChart.Series<String, Number> seriesRevenue = new XYChart.Series<>();
		XYChart.Series<String, Number> seriesProfit = new XYChart.Series<>();
		
		seriesRevenue.setName("Revenue");
		seriesRevenue.setName("Profit");
		
		for(Team t: teams)
		{
			XYChart.Data<String, Number> dataRevenue = new XYChart.Data<>(t.getTeamName(), t.getGrossRevenue());
			XYChart.Data<String, Number> dataProfit = new XYChart.Data<>(t.getTeamName(), t.getProfit());
			seriesRevenue.getData().add(dataRevenue);
			seriesProfit.getData().add(dataProfit); 
		}
		chart.getData().addAll(seriesRevenue,seriesProfit);
	}
	
	/*
	 * Compare team head to head with Wins Draws and Losses
	 * 
	 * TODO : Havent completed yet, got confused with all the naming confusion 
	 */
	public static void compareTeamHeadToHead(PieChart chart, Team t1, Team t2)
	{
		ArrayList<PieChart.Data> pieChartData = new ArrayList<PieChart.Data>();
		String team1Name = t1.getTeamName();
		String team2Name = t2.getTeamName();
		
		int team1Wins = 0;
		int team2Wins = 0;
		
		for(Result r: t1.getResults())
		{
			for(Game g: r.getGames())
			{
				if(g.getTeam2().getTeamName().equals(team1Name))
				{
					if(g.getVictor().getTeamName().equals(team1Name))
					{
						
					}
				}
				else if(g.getTeam2().getTeamName().equals(team2Name))
				{
					
				}
			}
		}
	}
}
