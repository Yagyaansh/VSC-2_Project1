import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class WinLossGraphController implements Initializable{

	
	@FXML
	private BarChart<?, ?> WinLossChart;
	
	@FXML
	private NumberAxis Y;
	
	@FXML
	private CategoryAxis X;
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		XYChart.Series set1 = new XYChart.Series<>();
		
		
		set1.getData().add(new XYChart.Data("Patriots", 99));
		set1.getData().add(new XYChart.Data("Cowboys", 1));
		set1.getData().add(new XYChart.Data("Redskins", 50));
		set1.getData().add(new XYChart.Data("Scouts", 99));
		set1.getData().add(new XYChart.Data("Patriots", 99));
	
		
		WinLossChart.getData().addAll(set1);
	}
}
