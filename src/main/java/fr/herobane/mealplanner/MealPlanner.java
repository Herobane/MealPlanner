package fr.herobane.mealplanner;

import fr.herobane.mealplanner.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class of MealPlanner app
 * @author herobane
 *
 */
public class MealPlanner extends Application {

	public static void main(String[] args) { 
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Loads the main frame's FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/MainFrame.fxml"));
		Parent root = loader.load();
		
		//  Gets the main frame controller instance and provides it with the primary stage
		MainController mainController = loader.getController();
		mainController.setPrimaryStage(primaryStage);
		
		// Creates the Scene and initialize it in our stage
		Scene scene = new Scene(root);
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icons/appicon.png")));
		primaryStage.setTitle("MealPlanner");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
