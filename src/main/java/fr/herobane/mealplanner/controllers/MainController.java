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

/**
 * <p> The main controller of the application. </p>
 * 
 * <p> 
 * 	Controls the main frame's views, invoked in the Main class,
 * 	and handles all its sub-views and their controller.
 * </p>
 * <p> Also provides access to the primary stage. </p>
 * 
 * @author herobane
 *
 */
public class MainController extends Controller implements Initializable {
	
	
	// ***** CONTROLLERS *****
	
	/**
	 * <p> Reference to the library controller. </p>
	 * <p> It's instance is set in {@link #initialize(URL, ResourceBundle)} </p>
	 */
	protected LibraryController libraryController;
	/**
	 * <p> Reference to the meal editor controller. </p>
	 * <p> It's instance is set in {@link #initialize(URL, ResourceBundle)} </p>
	 */
	protected MealEditorController mealEditorController;
	/**
	 * <p> Reference to the ingredient editor controller. </p>
	 * <p> It's instance is set in {@link #initialize(URL, ResourceBundle)} </p>
	 */
	protected IngredientEditorController ingredientEditorController;
	
	
	// ***** FXML VIEWS *****
	
	/**
	 * Library's root node once loaded.
	 * @see Library.fxml
	 */
	private Parent library;
	/**
	 * Meal editor's root node once loaded.
	 * @see MealEditor.fxml
	 */
	private Parent mealEditor;
	/**
	 * Ingredient editor's root node once loaded.
	 * @see IngredientEditor.fxml
	 */
	private Parent ingredientEditor;
	
	
	// ***** FXML ELEMENTS *****
	
	/**
	 * Main frame's menu bar.
	 */
	@FXML
	private MenuBar menuBar;
	
	/**
	 * Menu bar's "File" menu.
	 */
	@FXML
	private Menu fileMenu;
	/**
	 * Menu bar's "Edit" menu.
	 */
	@FXML
	private Menu editMenu;
	/**
	 * Menu bar's "Help" menu.
	 */
	@FXML
	private Menu helpMenu;
	
	/**
	 * File menu's "Close" item.
	 */
	@FXML
	private MenuItem closeItem;
	/**
	 * Edit menu's "Delete" item.
	 */
	@FXML
	private MenuItem deleteItem;
	/**
	 * Help menu's "About" item.
	 */
	@FXML
	private MenuItem aboutItem;
	
	/**
	 * <p> Library tab's anchor pane. </p>
	 * <p> Contains library/editors sub-views. </p>
	 */
	@FXML
	private AnchorPane libraryPane;
	/**
	 * <p> Planning tab's anchor pane. </p>
	 * <p> Contains ? sub-views. </p>
	 */
	@FXML
	private AnchorPane planningPane;
	
	
	// ***** OVERRIDEN METHODS *****
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			// Loads all sub-views
			FXMLLoader libraryLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/Library.fxml"));
			FXMLLoader mealEditorLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/MealEditor.fxml"));
			FXMLLoader ingredientEditorLoader = new FXMLLoader(getClass().getResource("/fr/herobane/mealplanner/views/IngredientEditor.fxml"));
			
			library = libraryLoader.load();
			mealEditor = mealEditorLoader.load();
			ingredientEditor = ingredientEditorLoader.load();
			
			// Gets their respective controllers and links them to the main controller (this)
			libraryController = libraryLoader.getController();
			mealEditorController = mealEditorLoader.getController();
			ingredientEditorController = ingredientEditorLoader.getController();
			
			libraryController.setMainController(this);
			mealEditorController.setMainController(this);
			ingredientEditorController.setMainController(this);
			
			// Shows the library sub-view by default
			libraryPane.getChildren().add(library);
			// TODO : auto-size
			
			// TODO : Planning Tab
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// ***** PUBLIC METHODS ***** 
	
	// FIXME : duplicated code : should be something like showEditor(ObservableObject, editMode)
	
	/**
	 * <p> Shows the meal editor sub-view either in edit mode or in add mode. </p>
	 * <ul> 
	 * 	<li> 
	 * 		Edit mode allow selected item edition by pre-filling the fields with all it's known information (like its name or whatever), 
	 * 		and updates it in the database after confirmation
	 * 	</li>
	 * 	<li> Add mode allow new meal creation with empty fields, and creates a new entry in the database after confirmation. </li>
	 * </ul>
	 * 
	 * @param meal the selected meal if set to edit mode, {@code null} otherwise
	 * @param editMode whether the meal editor should be opened in edit mode or not, {@code true} for edit mode
	 */
	public void showMealEditor(Meal meal, boolean editMode) {
		
		// Replaces library sub-view by the meal editor sub-view
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(mealEditor);
		
		// Initializes meal editor either in edit mode or in add mode
		if(editMode)
			mealEditorController.initEditMode(meal);
		else
			mealEditorController.initAddMode();
	}
	
	/**
	 * <p> Shows the ingredient editor sub-view either in edit mode or in add mode. </p>
	 * <ul> 
	 * 	<li> 
	 * 		Edit mode allow selected item edition by pre-filling the fields with all it's known information (like its name or whatever), 
	 * 		and updates it in the database after confirmation
	 * 	</li>
	 * 	<li> Add mode allow new ingredient creation with empty fields, and creates a new entry in the database after confirmation. </li>
	 * </ul>
	 * 
	 * @param ingredient the selected ingredient if set to edit mode, {@code null} otherwise
	 * @param editMode whether the ingredient editor should be opened in edit mode or not, {@code true} for edit mode
	 */
	public void showIngredientEditor(Ingredient ingredient, boolean editMode) {
		
		// Replaces library sub-view by the ingredient editor sub-view
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(ingredientEditor);
		
		// Initializes ingredient editor either in edit mode or in add mode
		if(editMode)
			ingredientEditorController.initEditMode(ingredient);
		else
			ingredientEditorController.initAddMode();
	}
	
	/**
	 * Shows back the library sub-view.
	 */
	public void showLibrary() {
		// Replaces current sub-view by the library sub-view
		libraryPane.getChildren().clear();
		libraryPane.getChildren().add(library);
	}

}
