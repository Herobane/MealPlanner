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

public class MainController extends Controller implements Initializable {
	
	LibraryController libraryController;
	MealEditorController mealEditorController;
	
	private Parent library;
	private Parent mealEditor;
	
	// ***** FXML ELEMENTS *****
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
			
			FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("/views/Library.fxml"));
			FXMLLoader mealEditorLoader = new FXMLLoader(getClass().getResource("/views/MealEditor.fxml"));
			
			library = libraryLoader.load();
			mealEditor = mealEditorLoader.load();
			
			libraryController = libraryLoader.getController();
			mealEditorController = mealEditorLoader.getController();
			
			libraryController.setMainController(this);
			mealEditorController.setMainController(this);
			
			libraryPane.getChildren().add(library);
			
			// TODO : Planning Tab
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// FIXME
	public void toggleLibraryPane() {
		if(libraryPane.getChildren().get(0).equals(library)) {
			libraryPane.getChildren().clear();
			libraryPane.getChildren().add(mealEditor);
		}
		else {
			libraryPane.getChildren().clear();
			libraryPane.getChildren().add(library);
		}
	}

}
