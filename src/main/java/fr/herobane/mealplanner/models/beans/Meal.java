package fr.herobane.mealplanner.models.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Meal {

	// TODO : Add more attributes
	private LongProperty ID;
	private StringProperty name;
	private BooleanProperty isLunch;
	private BooleanProperty isDinner;
	
	public Meal() {
		ID = new SimpleLongProperty();
		name = new SimpleStringProperty();
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	public Meal(String name) {
		ID = new SimpleLongProperty();
		this.name = new SimpleStringProperty(name);
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	@Override
	public String toString() {
		return getName() + " " + (this.isLunch() ? "lunch" : "nlunch") + " " + (this.isDinner() ? "dinner" : "ndinner");
	}
	
	public long getID() { return ID.get(); }
	public void setID(long ID) { this.ID.set(ID); }
	public LongProperty getIDProperty() { return ID; }
	
	public String getName() { return name.get(); }
	public void setName(String name) { this.name.set(name); }
	public StringProperty getNameProperty() { return name; }
	
	public boolean isLunch() { return isLunch.get(); }
	public void setLunch(boolean isLunch) { this.isLunch.set(isLunch); }
	public BooleanProperty isLunchProperty() { return isLunch; }
	
	public boolean isDinner() { return isDinner.get(); }
	public void setDinner(boolean isDinner) { this.isDinner.set(isDinner); }
	public BooleanProperty isDinnerProperty() { return isDinner; }
	
}
