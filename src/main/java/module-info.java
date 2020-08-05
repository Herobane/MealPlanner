module mealplanner.core {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.base;
	
	opens fr.herobane.mealplanner to javafx.fxml;
	opens fr.herobane.mealplanner.controllers to javafx.fxml;
	
	exports fr.herobane.mealplanner;
	exports fr.herobane.mealplanner.controllers;
	exports fr.herobane.mealplanner.models;
	exports fr.herobane.mealplanner.models.dao;
	exports fr.herobane.mealplanner.models.beans;
	exports fr.herobane.mealplanner.utils;
}