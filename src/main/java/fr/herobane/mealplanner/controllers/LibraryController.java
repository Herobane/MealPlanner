package fr.herobane.mealplanner.controllers;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import fr.herobane.mealplanner.models.beans.Meal;
import fr.herobane.mealplanner.models.dao.MealDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LibraryController extends Controller implements Initializable {

	private MealDAO mealDAO;
	
	// ***** FXML ELEMENTS *****
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	
	@FXML
	private ListView<Meal> mealListView;
	
	// ***** OTHER CONTROLS *****
	private ImageView addButtonIcon;
	private ImageView deleteButtonIcon;
	private ImageView updateButtonIcon;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		addButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/add.png")));
		deleteButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/delete.png")));
		updateButtonIcon = new ImageView(new Image(getClass().getResourceAsStream("/icons/update.png")));
		
		addButton.setGraphic(addButtonIcon);
		deleteButton.setGraphic(deleteButtonIcon);
		updateButton.setGraphic(updateButtonIcon);
		
		mealDAO = new MealDAO();
		
		try {
			mealListView.setItems(mealDAO.getMeals());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// TODO : Right pane
	// ***** FXML ELEMENTS HANDLING *****
	@FXML
	private void handleAddButton() {
		mainController.showMealEditor(null, false);
	}
	
	@FXML
	private void handleDeleteButton() {
		mealDAO.delete(mealListView.getSelectionModel().getSelectedItem());
	}
	
	@FXML
	private void handleUpdateButton() {
		mainController.showMealEditor(mealListView.getSelectionModel().getSelectedItem(), true);
	}
	
}
