package fr.herobane.mealplanner;

import fr.herobane.mealplanner.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MealPlanner extends Application {

	public static void main(String[] args) { 
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MealPlanner.fxml"));
		Parent root = loader.load();
		
		MainController mainController = loader.getController();
		
		mainController.setPrimaryStage(primaryStage);
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("MealPlanner");
		primaryStage.setScene(scene);
//		primaryStage.setResizable(false);
		primaryStage.show();
		
	}

}
