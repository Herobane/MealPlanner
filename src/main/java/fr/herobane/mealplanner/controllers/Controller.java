package fr.herobane.mealplanner.controllers;

import javafx.stage.Stage;

public abstract class Controller {

	protected MainController mainController;
	protected Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public void setMainController(MainController c) {
		mainController = c;
	}
	
}
