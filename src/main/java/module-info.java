module mealplanner.core {
	requires transitive javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	
	opens fr.herobane.mealplanner to javafx.fxml;
	exports fr.herobane.mealplanner;
}