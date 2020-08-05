package fr.herobane.mealplanner.models.beans;

import fr.herobane.mealplanner.utils.Measure;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Java bean for an ingredient object.
 * @author herobane
 *
 */
public class Ingredient extends ObservableObject {

	
	// ***** FIELDS *****
	
	/**
	 * Object property for the measure of the ingredient.
	 * @see Measure
	 */
	private ObjectProperty<Measure> measure;
	
	
	// ***** CONSTRUCTORS *****
	
	/**
	 * <p> Default constructor </p>
	 * <p> Creates a new ingredient object with {@code null} fields.
	 */
	public Ingredient() {
		super();
		
		measure = new SimpleObjectProperty<Measure>();
	}
	
	/**
	 * <p> Light constructor </p>
	 * <p> Creates a new ingredient object with the specified name and {@code null} for all other fields.
	 * @param name the name of the ingredient
	 */
	public Ingredient(String name) {
		super(name);
		
		measure = new SimpleObjectProperty<Measure>();
	}
	
	/**
	 * <p> Complete constructor </p>
	 * <p> Creates a new ingredient object with the specified values for all of it's fields. </p>
	 * 
	 * @param ID the ID of the ingredient in the database
	 * @param name the name of the ingredient
	 * @param measure the measure used for this ingredient
	 */
	public Ingredient(long ID, String name, Measure measure) {
		super(ID, name);
		
		this.measure = new SimpleObjectProperty<Measure>(measure);
	}
	
	
	// ***** GETTERS AND SETTERS *****
	
	/**
	 * Gets the measure used for this ingredient.
	 * @return the measure used for this ingredient
	 */
	public Measure getMeasure() { return measure.get(); }
	/**
	 * Sets the measure used for this ingredient to the specified value.
	 * @param measure the measure used for this ingredient
	 */
	public void setMeasure(Measure measure) { this.measure.set(measure); }
	/**
	 * Gets the measure property of this ingredient. Used for JavaFX bindings.
	 * @return the measure property of this ingredient
	 */
	public ObjectProperty<Measure> getMeasureProperty() { return measure; }
	
}
