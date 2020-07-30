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
	
	@FXML
	private void handleConfirmButton() {
		Meal lastMeal = new Meal(mealNameField.getText());
		lastMeal.setLunch(mealLunchCheck.isSelected());
		lastMeal.setDinner(mealDinnerCheck.isSelected());
		
		mealDAO.create(lastMeal);
		
		mealNameField.setText("");
		mealLunchCheck.setSelected(false);
		mealDinnerCheck.setSelected(false);
		
		mainController.toggleLibraryPane();
	}
	
	@FXML
	private void handleCancelButton() {
		mealNameField.setText("");
		mealLunchCheck.setSelected(false);
		mealDinnerCheck.setSelected(false);
		
		mainController.toggleLibraryPane();
	}

}
