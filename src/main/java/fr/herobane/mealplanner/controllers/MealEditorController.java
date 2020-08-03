package fr.herobane.mealplanner.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fr.herobane.mealplanner.models.beans.Meal;
import fr.herobane.mealplanner.models.dao.MealDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class MealEditorController extends Controller implements Initializable {

	private MealDAO mealDAO;
	
	// Fields for edit mode
	private boolean editMode;
	private Meal lastSelected;
	
	@FXML
	private Button browseButton;
	@FXML
	private Button confirmButton;
	@FXML
	private Button cancelButton;
	
	@FXML
	private CheckBox mealLunchCheck;
	@FXML
	private CheckBox mealDinnerCheck;
	
	@FXML
	private TextField mealNameField;
	
	@FXML
	private ImageView mealImage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mealDAO = new MealDAO();
	}
	
	public void initEditMode(Meal selectedMeal) {
		editMode = true;
		lastSelected = selectedMeal;
		
		mealNameField.setText(selectedMeal.getName());
		mealLunchCheck.setSelected(selectedMeal.isLunch());
		mealDinnerCheck.setSelected(selectedMeal.isDinner());
	}
	
	public void initAddMode() {
		editMode = false;
		
		mealNameField.setText("");
		mealLunchCheck.setSelected(false);
		mealDinnerCheck.setSelected(false);
	}
	
	@FXML
	private void handleConfirmButton() {
		Meal lastMeal = new Meal(mealNameField.getText());
		lastMeal.setLunch(mealLunchCheck.isSelected());
		lastMeal.setDinner(mealDinnerCheck.isSelected());
		
		if(!editMode)
			mealDAO.create(lastMeal);
		else
			mealDAO.update(lastSelected.getID(), lastMeal);
		
		mainController.showLibrary();
	}
	
	@FXML
	private void handleCancelButton() {
		mainController.showLibrary();
	}

}
