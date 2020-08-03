package fr.herobane.mealplanner.models.beans;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class ObservableObject {

	private LongProperty ID;
	private StringProperty name;
	
	public ObservableObject() {
		ID = new SimpleLongProperty();
		name = new SimpleStringProperty();
	}
	
	public ObservableObject(String name) {
		ID = new SimpleLongProperty();
		this.name = new SimpleStringProperty(name);
	}
	
	public ObservableObject(long ID, String name) {
		this.ID = new SimpleLongProperty(ID);
		this.name = new SimpleStringProperty(name);
	}
	
	public long getID() { return ID.get(); }
	public void setID(long ID) { this.ID.set(ID); }
	public LongProperty getIDProperty() { return ID; }
	
	public String getName() { return name.get(); }
	public void setName(String name) { this.name.set(name); }
	public StringProperty getNameProperty() { return name; }
	
}
