package fr.herobane.mealplanner.models.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * Java bean for a meal object.
 * @author herobane
 *
 */
public class Meal extends ObservableObject {

	
	// ***** FIELDS *****
	// TODO : Add more attributes
	
	/**
	 * Boolean property for whether this meal is enabled for lunch or not.
	 */
	private BooleanProperty isLunch;
	/**
	 * Boolean property for whether this meal is enabled for dinner or not.
	 */
	private BooleanProperty isDinner;
	
	
	// ***** CONSTRUCTORS *****
	
	/**
	 * <p> Default constructor </p>
	 * <p> Creates a new meal object with {@code null} fields.
	 */
	public Meal() {
		super();
		
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	/**
	 * <p> Light constructor </p>
	 * <p> Creates a new meal object with the specified name and {@code null} for all other fields.
	 * @param name the name of the meal
	 */
	public Meal(String name) {
		super(name);
		
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	/**
	 * <p> Complete constructor </p>
	 * <p> Creates a new meal object with the specified values for all of it's fields. </p>
	 * 
	 * @param ID the ID of the meal in the database
	 * @param name the name of the meal
	 * @param isLunch whether this meal is enabled for lunch or not ({@code true} for enabled)
	 * @param isDinner whether this meal is enabled for dinner or not ({@code true} for enabled)
	 */
	public Meal(long ID, String name, boolean isLunch, boolean isDinner) {
		super(ID, name);
		
		this.isLunch = new SimpleBooleanProperty(isLunch);
		this.isDinner = new SimpleBooleanProperty(isDinner);
	}
	
	@Override
	public String toString() {
		return getName() + " " + (this.isLunch() ? "lunch" : "nlunch") + " " + (this.isDinner() ? "dinner" : "ndinner");
	}
	
	
	// ***** GETTERS AND SETTERS *****
	
	/**
	 * Gets whether this meal is enabled for lunch or not ({@code true} for enabled).
	 * @return {@code true} if enabled, {@code false otherwise}
	 */
	public boolean isLunch() { return isLunch.get(); }
	/**
	 * Sets whether this meal is enabled for lunch or not ({@code true} for enabled).
	 * @param isLunch {@code true} if enabled, {@code false} if not
	 */
	public void setLunch(boolean isLunch) { this.isLunch.set(isLunch); }
	/**
	 * Gets whether this meal is enabled for lunch or not, as a property. Used for JavaFX bindings.
	 * @return whether this meal is enabled for lunch or not, as a property
	 */
	public BooleanProperty isLunchProperty() { return isLunch; }
	
	/**
	 * Gets whether this meal is enabled for dinner or not ({@code true} for enabled).
	 * @return {@code true} if enabled, {@code false otherwise}
	 */
	public boolean isDinner() { return isDinner.get(); }
	/**
	 * Sets whether this meal is enabled for dinner or not ({@code true} for enabled).
	 * @param isDinner {@code true} if enabled, {@code false} if not
	 */
	public void setDinner(boolean isDinner) { this.isDinner.set(isDinner); }
	/**
	 * Gets whether this meal is enabled for dinner or not, as a property. Used for JavaFX bindings.
	 * @return whether this meal is enabled for dinner or not, as a property
	 */
	public BooleanProperty isDinnerProperty() { return isDinner; }
	
}
