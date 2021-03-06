package sk.upjs.paz1c.project.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainScene extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		MainSceneController controller = new MainSceneController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MainScene.fxml"));
		loader.setController(controller);
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.setTitle("FlightBooker");
		primaryStage.show();
	}



	public static void main(String[] args) {
		launch(args);
	}

}
