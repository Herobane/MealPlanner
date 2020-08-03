package fr.herobane.mealplanner.models.beans;

import fr.herobane.mealplanner.utils.Measure;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Ingredient extends ObservableObject {

	private ObjectProperty<Measure> measure;
	
	public Ingredient() {
		super();
		
		measure = new SimpleObjectProperty<Measure>();
	}
	
	public Ingredient(String name) {
		super(name);
		
		measure = new SimpleObjectProperty<Measure>();
	}
	
	public Ingredient(long ID, String name, Measure measure) {
		super(ID, name);
		
		this.measure = new SimpleObjectProperty<Measure>(measure);
	}
	
	public Measure getMeasure() { return measure.get(); }
	public void setMeasure(Measure measure) { this.measure.set(measure); }
	public ObjectProperty<Measure> getMeasureProperty() { return measure; }
	
}
