package fr.herobane.mealplanner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class MainController implements Initializable {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private Menu fileMenu;
	@FXML
	private Menu editMenu;
	@FXML
	private Menu helpMenu;
	
	@FXML
	private MenuItem closeItem;
	@FXML
	private MenuItem deleteItem;
	@FXML
	private MenuItem aboutItem;
	
	@FXML
	private AnchorPane libraryPane;
	@FXML
	private AnchorPane planningPane;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			
			Parent library = FXMLLoader.load(getClass().getResource("/views/Library.fxml"));
			libraryPane.getChildren().add(library);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
