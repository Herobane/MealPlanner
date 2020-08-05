package fr.herobane.mealplanner.controllers;

import javafx.stage.Stage;

/**
 * <p> Superclass for any controller </p>
 * 
 * <p> Allow any controller to be linked with the main controller.
 * Main controller provides access to the primary stage and other controllers. </p>
 * 
 * @author herobane
 *
 */
public abstract class Controller {

	
	// ***** FIELDS *****
	
	/**
	 * Reference to the main controller of the application.
	 * @see #setMainController(MainController)
	 */
	protected MainController mainController;
	/**
	 * Reference to the primary stage of the application.
	 * @see #setPrimaryStage(Stage)
	 */
	protected Stage primaryStage;
	
	
	// ***** PUBLIC METHODS *****
	
	/**
	 * Sets the primary stage of this controller.
	 * @param primaryStage the primary stage of the application
	 */
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	/**
	 * Sets the main controller of this controller.
	 * @param c the main controller of this controller
	 */
	public void setMainController(MainController c) {
		mainController = c;
	}
	
}
