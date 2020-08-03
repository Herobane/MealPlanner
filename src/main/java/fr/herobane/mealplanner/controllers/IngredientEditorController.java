package fr.herobane.mealplanner.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import fr.herobane.mealplanner.models.beans.Ingredient;
import fr.herobane.mealplanner.models.dao.IngredientDAO;
import fr.herobane.mealplanner.utils.Measure;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class IngredientEditorController extends Controller implements Initializable {

	private IngredientDAO ingredientDAO;
	
	private boolean editMode;
	private Ingredient lastSelected;
	
	@FXML
	private Button browseButton;
	@FXML
	private Button confirmButton;
	@FXML
	private Button cancelButton;
	
	@FXML
	private ComboBox<Measure> ingredientMeasureCombo;
	
	@FXML
	private TextField ingredientNameField;
	
	@FXML
	private ImageView ingredientImage;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ingredientDAO = new IngredientDAO();		
		ingredientMeasureCombo.setItems(FXCollections.observableArrayList(Measure.values()));
	}
	
	public void initEditMode(Ingredient selectedIngredient) {
		editMode = true;
		lastSelected = selectedIngredient;
		
		ingredientNameField.setText(selectedIngredient.getName());
		ingredientMeasureCombo.setValue(selectedIngredient.getMeasure());
	}
	
	public void initAddMode() {
		editMode = false;
		
		ingredientNameField.setText("");
		ingredientMeasureCombo.setValue(null);
	}
	
	@FXML
	private void handleConfirmButton() {
		Ingredient lastIngredient = new Ingredient(ingredientNameField.getText());
		lastIngredient.setMeasure(ingredientMeasureCombo.getValue());
		
		if(!editMode)
			ingredientDAO.create(lastIngredient);
		else
			ingredientDAO.update(lastSelected.getID(), lastIngredient);
		
		mainController.showLibrary();
	}
	
	@FXML
	private void handleCancelButton() {
		mainController.showLibrary();
	}

}
