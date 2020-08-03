package fr.herobane.mealplanner.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import fr.herobane.mealplanner.models.beans.Ingredient;
import fr.herobane.mealplanner.models.beans.Meal;
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
	IngredientEditorController ingredientEditorController;
	
	private Parent library;
	private Parent mealEditor;
	private Parent ingredientEditor;
	
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
			
			FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/Library.fxml"));
			FXMLLoader mealEditorLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/MealEditor.fxml"));
			FXMLLoader ingredientEditorLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/IngredientEditor.fxml"));
			
			library = libraryLoader.load();
			mealEditor = mealEditorLoader.load();
			ingredientEditor = ingredientEditorLoader.load();
			
			libraryController = libraryLoader.getController();
			mealEditorController = mealEditorLoader.getController();
			ingredientEditorController = ingredientEditorLoader.getController();
			
			libraryController.setMainController(this);
			mealEditorController.setMainController(this);
			ingredientEditorController.setMainController(this);
			
			libraryPane.getChildren().add(library);
			// TODO : autosize
			
			// TODO : Planning Tab
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showMealEditor(Meal meal, boolean editMode) {
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(mealEditor);
		
		if(editMode)
			mealEditorController.initEditMode(meal);
		else
			mealEditorController.initAddMode();
	}
	
	public void showIngredientEditor(Ingredient ingredient, boolean editMode) {
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(ingredientEditor);
		
		if(editMode)
			ingredientEditorController.initEditMode(ingredient);
		else
			ingredientEditorController.initAddMode();
	}
	
	public void showLibrary() {
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(library);
	}

}
