package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainFX extends Application {
	
	@Override
	public void start(Stage primaryStage) {
				
		/*
		 * Write now I coding with the assumption that I can get the OutputData as an object
		 */
			
//	    BorderPane root = new BorderPane();
//		Player_Pool playerPool = new Player_Pool(20);
//		ArrayList<Player> players = playerPool.getPlayerPool();
//		primaryStage.setTitle("Player Statistics");
//        final NumberAxis xAxis = new NumberAxis();
//        final CategoryAxis yAxis = new CategoryAxis();
//        final BarChart<Number, String> bc = new BarChart<>(xAxis,yAxis);
////        xAxis.setTickLabelRotation(90);
////        XYChart.Series<Number,String> series1 = new XYChart.Series<>();
////        ArrayList<XYChart.Data<Number, String>> c = new ArrayList<>();
////        for(Player p: players)
////        {
////        	XYChart.Data<Number, String> data = new XYChart.Data<Number, String>(p.getAthleticism(),p.getName());
////        	c.add(data);
////        }
//        
//        bc.setTitle("Athleticism Comparison");
////        series1.getData().addAll(c);
//  
//        Scene scene  = new Scene(bc,800,600);
//		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
////        bc.getData().addAll(series1);
//        primaryStage.setScene(scene);
//        primaryStage.show();

		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Player Statistics");
			Player_Pool playerPool = new Player_Pool(20);
			ArrayList<Player> players = playerPool.getPlayerPool();
	        final NumberAxis xAxis = new NumberAxis();
	        final CategoryAxis yAxis = new CategoryAxis();
	        final BarChart<Number, String> bc = new BarChart<>(xAxis,yAxis);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void graphPlayerOutput(ArrayList<Player> players) {
		
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
