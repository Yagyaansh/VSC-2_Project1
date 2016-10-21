
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import javafx.stage.Stage;

public class UIController implements Initializable {
	@FXML
	private CheckBox testbox;
	@FXML
	private Button button1;
	@FXML
	private Button button2;

	@FXML
	private void testCheck() {
		System.out.println("Checked the box");
	}

	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException {
		System.out.println("in button action");
		System.out.println(event);
		System.out.println(event.getSource());
		Stage stage = null;
		Parent root = null;
		System.out.println(button1);
		if (event.getSource() == button1) {
			stage = (Stage) button1.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("UITest.fxml"));
			System.out.println("Button1 pressed");

		}
		if (event.getSource() == button2)
		{
			stage = (Stage) button2.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("UIMain.fxml"));
			System.out.println("Button2 pressed");
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
