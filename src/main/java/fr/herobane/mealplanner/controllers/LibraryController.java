package fr.herobane.mealplanner.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fr.herobane.mealplanner.models.beans.Ingredient;
import fr.herobane.mealplanner.models.beans.Meal;
import fr.herobane.mealplanner.models.beans.ObservableObject;
import fr.herobane.mealplanner.models.dao.IngredientDAO;
import fr.herobane.mealplanner.models.dao.MealDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * <p> The controller for the library sub-view. </p>
 * <p> Controls the library components and associated data </p>
 * <p> Can call the editor pane to edit new or existing elements </p>
 * 
 * @author herobane
 *
 */
public class LibraryController extends Controller implements Initializable {

	
	// ***** FIELDS *****
	
	/**
	 * <p> Reference to the meal Data Access Object (DAO) used by this controller. </p>
	 * <p>
	 * 	Ensure that only one instance is used in this class. <br>
	 * 	Instantiated in {@link #initialize(URL, ResourceBundle)}
	 * </p>
	 */
	private MealDAO mealDAO;
	/**
	 * <p> Reference to the ingredient Data Access Object (DAO) used by this controller. </p>
	 * <p>
	 * 	Ensure that only one instance is used in this class. <br>
	 * 	Instantiated in {@link #initialize(URL, ResourceBundle)}
	 * </p>
	 */
	private IngredientDAO ingredientDAO;
	/**
	 * String for the current view mode (Either meal library or ingredient library).
	 */
	private String viewMode;
	
	
	// ***** FXML ELEMENTS *****
	
	/**
	 * Toolbar's add button.
	 */
	@FXML
	private Button addButton;
	/**
	 * Toolbar's delete button.
	 */
	@FXML
	private Button deleteButton;
	/**
	 * Toolbar's update button.
	 */
	@FXML
	private Button updateButton;
	/**
	 * Toolbar's swap button.
	 */
	@FXML
	private Button swapButton;
	
	/**
	 * Library's list view for either meals list or ingredients list.
	 */
	@FXML
	private ListView<ObservableObject> mealListView;
	
	// TODO : Separated summary pane ?
	// XXX : field names : should not contain meal since it can handle ingredients
	/**
	 * Summary pane's image view associated to the selected element.
	 */
	@FXML
	private ImageView mealImageSummary;
	/**
	 * Summary pane's label for the name of the selected element.
	 */
	@FXML
	private Label mealNameSummary;
	/**
	 * Summary pane's image view associated to the selected element {@code isLunch} property.
	 */
	@FXML
	private ImageView mealLunchSummary;
	/**
	 * Summary pane's image view associated to the selected element {@code isDinner} property.
	 */
	@FXML
	private ImageView mealDinnerSummary;
	
	
	// ***** OTHER CONTROLS *****
	
	/**
	 * Add button's image view for its icon.
	 */
	private ImageView addButtonIcon;
	/**
	 * Delete button's image view for its icon.
	 */
	private ImageView deleteButtonIcon;
	/**
	 * Update button's image view for its icon.
	 */
	private ImageView updateButtonIcon;
	
	
	// ***** OVERRIDEN METHODS *****
	// FIXME : duplicated code in Meal/IngredientEditorController
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// Gets the buttons' icons and display them
		addButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png")));
		deleteButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete.png")));
		updateButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/update.png")));
		
		addButton.setGraphic(addButtonIcon);
		deleteButton.setGraphic(deleteButtonIcon);
		updateButton.setGraphic(updateButtonIcon);
		
		// Initialize DAOs instances and view mode (Meal/Ingredient)
		mealDAO = new MealDAO();
		ingredientDAO = new IngredientDAO();
		viewMode = "Meals";
		
		// Sets the default items to show in the list view
		try {
			mealListView.setItems(mealDAO.getMeals());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Changes the display method of the list view cells
		mealListView.setCellFactory(cell -> {
			return new ListCell<ObservableObject>() {

				@Override
				protected void updateItem(ObservableObject item, boolean empty) {
					super.updateItem(item, empty);
					
					if(!empty) {
						// Sets the text value of the cell to the name of the corresponding ObservableObject, and the font size to 15
						setText(item.getName());
						setFont(new Font(15));
					}
					else {
						// If there's no item to show then text is null
						setText(null);
					}
				}
				
			};
		});
		
		// Link the selected item to the summary pane at the right
		mealListView.setOnMousePressed(event -> {
			
			if(mealListView.getSelectionModel().getSelectedItem() instanceof Meal) {
				
				// Fills the summary pane fields with the selected object values if it's an instance of Meal
				Meal selectedMeal = (Meal) mealListView.getSelectionModel().getSelectedItem();
				
				mealNameSummary.setText(selectedMeal.getName());
				
				if(selectedMeal.isLunch())
					mealLunchSummary.setImage(new Image(getClass().getResourceAsStream("/icons/lunch.png")));
				else
					mealLunchSummary.setImage(null);
				
				if(selectedMeal.isDinner())
					mealDinnerSummary.setImage(new Image(getClass().getResourceAsStream("/icons/dinner.png")));
				else
					mealDinnerSummary.setImage(null);
			}
		});
		
	}
	
	
	// TODO : Right pane
	// ***** FXML ELEMENTS HANDLING *****
	
	/**
	 * <p> Shows the editor corresponding to the current view mode (Meal/Ingredient) in add mode. </p>
	 * <p> Invoked when add button is clicked. </p>
	 */
	@FXML
	private void handleAddButton() {
		if(viewMode.equals("Meals"))
			mainController.showMealEditor(null, false);
		else
			mainController.showIngredientEditor(null, false);
	}
	
	/**
	 * <p> Deletes the selected item from the list and the database after confirmation. If no item is selected, nothing happens. </p>
	 * <p> Invoked when delete button is clicked. </p>
	 */
	@FXML
	private void handleDeleteButton() {
		if(viewMode.equals("Meals") && mealListView.getSelectionModel().getSelectedItem() != null) {
			
			// If the selected item is a meal, removes it from the meals list and the meals table in the database
			Meal selectedMeal = (Meal) mealListView.getSelectionModel().getSelectedItem();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selectedMeal.getName() + " from your library ?");
			
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				mealDAO.delete(selectedMeal);
		}
		else {
			
			// If the selected item is an ingredient, removes it from the ingredients list and the ingredients table in the database
			Ingredient selectedIngredient = (Ingredient) mealListView.getSelectionModel().getSelectedItem();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selectedIngredient.getName() + " from your library ?");
			
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				ingredientDAO.delete(selectedIngredient);
		}
	}
	
	/**
	 * <p> Shows the editor corresponding to the current view mode (Meal/Ingredient) in edit mode. </p>
	 * <p> Invoked when update button is clicked. </p>
	 */
	@FXML
	private void handleUpdateButton() {
		if(viewMode.equals("Meals") && mealListView.getSelectionModel().getSelectedItem() != null)
			mainController.showMealEditor((Meal) mealListView.getSelectionModel().getSelectedItem(), true);
		else if(viewMode.equals("Ingredients") && mealListView.getSelectionModel().getSelectedItem() != null)
			mainController.showIngredientEditor((Ingredient) mealListView.getSelectionModel().getSelectedItem(), true);
	}
	
	/**
	 * <p> Swap the current view mode to the other one. </p>
	 * <p> Invoked when swap button is clicked. </p>
	 */
	@FXML
	private void handleSwapButton() {
		if(viewMode.equals("Meals")) {
			// Swap to ingredients
			viewMode = "Ingredients";
			swapButton.setText(viewMode);
			
			addButton.setText("Add new ingredient...");
			deleteButton.setText("Delete ingredient");
			updateButton.setText("Edit ingredient");
			
			// Updates the list view
			try {
				mealListView.setItems(ingredientDAO.getIngredients());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			// Swap to meals
			viewMode = "Meals";
			swapButton.setText(viewMode);
			
			addButton.setText("Add new meal...");
			deleteButton.setText("Delete meal");
			updateButton.setText("Edit meal");
			
			// Updates the list view
			try {
				mealListView.setItems(mealDAO.getMeals());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
