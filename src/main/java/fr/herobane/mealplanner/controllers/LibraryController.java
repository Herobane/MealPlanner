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

public class LibraryController extends Controller implements Initializable {

	// ***** FIELDS *****
	private MealDAO mealDAO;
	private IngredientDAO ingredientDAO;
	private String mode;
	
	// ***** FXML ELEMENTS *****
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	@FXML
	private Button swapButton;
	
	@FXML
	private ImageView mealImageSummary;
	@FXML
	private Label mealNameSummary;
	@FXML
	private ImageView mealLunchSummary;
	@FXML
	private ImageView mealDinnerSummary;
	
	@FXML
	private ListView<ObservableObject> mealListView;
	
	// ***** OTHER CONTROLS *****
	private ImageView addButtonIcon;
	private ImageView deleteButtonIcon;
	private ImageView updateButtonIcon;
	
	// FIXME : duplicated code in Meal/IngredientEditorController
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png")));
		deleteButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete.png")));
		updateButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/update.png")));
		
		addButton.setGraphic(addButtonIcon);
		deleteButton.setGraphic(deleteButtonIcon);
		updateButton.setGraphic(updateButtonIcon);
		
		mealDAO = new MealDAO();
		ingredientDAO = new IngredientDAO();
		mode = "Meals";
		
		try {
			mealListView.setItems(mealDAO.getMeals());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		mealListView.setCellFactory(cell -> {
			return new ListCell<ObservableObject>() {

				@Override
				protected void updateItem(ObservableObject item, boolean empty) {
					super.updateItem(item, empty);
					
					if(!empty) {
						setText(item.getName());
						setFont(new Font(15));
					}
					else {
						setText(null);
					}
				}
				
			};
		});
		
		mealListView.setOnMousePressed(event -> {
			if(mealListView.getSelectionModel().getSelectedItem() instanceof Meal) {
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
	@FXML
	private void handleAddButton() {
		if(mode.equals("Meals"))
			mainController.showMealEditor(null, false);
		else
			mainController.showIngredientEditor(null, false);
	}
	
	@FXML
	private void handleDeleteButton() {
		if(mode.equals("Meals") && mealListView.getSelectionModel().getSelectedItem() != null) {
			Meal selectedMeal = (Meal) mealListView.getSelectionModel().getSelectedItem();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selectedMeal.getName() + " from your library ?");
			
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				mealDAO.delete(selectedMeal);
		}
		else {
			Ingredient selectedIngredient = (Ingredient) mealListView.getSelectionModel().getSelectedItem();
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "Delete " + selectedIngredient.getName() + " from your library ?");
			
			alert.showAndWait();
			
			if(alert.getResult() == ButtonType.OK)
				ingredientDAO.delete(selectedIngredient);
		}
	}
	
	@FXML
	private void handleUpdateButton() {
		if(mode.equals("Meals") && mealListView.getSelectionModel().getSelectedItem() != null)
			mainController.showMealEditor((Meal) mealListView.getSelectionModel().getSelectedItem(), true);
		else
			mainController.showIngredientEditor((Ingredient) mealListView.getSelectionModel().getSelectedItem(), true);
	}
	
	@FXML
	private void handleSwapButton() {
		if(mode.equals("Meals")) {
			// Swap to ingredients
			mode = "Ingredients";
			swapButton.setText(mode);
			
			addButton.setText("Add new ingredient...");
			deleteButton.setText("Delete ingredient");
			updateButton.setText("Edit ingredient");
			
			try {
				mealListView.setItems(ingredientDAO.getIngredients());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			// Swap to meals
			mode = "Meals";
			swapButton.setText(mode);
			
			addButton.setText("Add new meal...");
			deleteButton.setText("Delete meal");
			updateButton.setText("Edit meal");
			
			try {
				mealListView.setItems(mealDAO.getMeals());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
