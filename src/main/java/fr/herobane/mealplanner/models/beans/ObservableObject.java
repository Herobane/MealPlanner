package fr.herobane.mealplanner.models.beans;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * <p> Java bean Superclass for any observable object (an object that can be displayed and stored in the database) </p>
 * @author herobane
 *
 */
public abstract class ObservableObject {

	
	// ***** FIELDS *****
	
	/**
	 * Long property for the ID of this object.
	 */
	private LongProperty ID;
	/**
	 * String property for the name of this object.
	 */
	private StringProperty name;
	
	
	// ***** CONSTRUCTORS *****
	
	/**
	 * <p> Default constructor </p>
	 * <p> Creates a new observable object with {@code null} fields.
	 */
	public ObservableObject() {
		ID = new SimpleLongProperty();
		name = new SimpleStringProperty();
	}
	
	/**
	 * <p> Light constructor </p>
	 * <p> Creates a new observable object with the specified name and {@code null} for all other fields.
	 * @param name the name of the observable object
	 */
	public ObservableObject(String name) {
		ID = new SimpleLongProperty();
		this.name = new SimpleStringProperty(name);
	}
	
	/**
	 * <p> Complete constructor </p>
	 * <p> Creates a new observable object with the specified values for all of it's fields. </p>
	 * 
	 * @param ID the ID of the observable object in the database
	 * @param name the name of the observable object
	 */
	public ObservableObject(long ID, String name) {
		this.ID = new SimpleLongProperty(ID);
		this.name = new SimpleStringProperty(name);
	}
	
	
	// ***** GETTERS AND SETTERS *****
	
	/**
	 * Gets the ID of this observable object.
	 * @return the ID of this observable object
	 */
	public long getID() { return ID.get(); }
	/**
	 * Sets the ID of this observable object to the specified value.
	 * @param ID the ID of this observable object in the database
	 */
	public void setID(long ID) { this.ID.set(ID); }
	/**
	 * Gets the ID property of this observable object. Used for JavaFX bindings.
	 * @return the ID property of this observable object
	 */
	public LongProperty getIDProperty() { return ID; }
	
	/**
	 * Gets the name of this observable object.
	 * @return the name of this observable object
	 */
	public String getName() { return name.get(); }
	/**
	 * Sets the name of this observable object to the specified value.
	 * @param name the name of this observable object
	 */
	public void setName(String name) { this.name.set(name); }
	/**
	 * Gets the name property of this observable object. Used for JavaFX bindings.
	 * @return the name property of this observable object
	 */
	public StringProperty getNameProperty() { return name; }
	
}
