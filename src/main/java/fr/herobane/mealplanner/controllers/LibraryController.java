package fr.herobane.mealplanner.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LibraryController implements Initializable {

	//	***** FXML ELEMENTS *****
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button updateButton;
	
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
		
		// TODO : populate ListView
		
	}
	
}
