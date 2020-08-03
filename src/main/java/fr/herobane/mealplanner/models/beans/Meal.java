package fr.herobane.mealplanner.models.beans;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Meal extends ObservableObject {

	// TODO : Add more attributes
	private BooleanProperty isLunch;
	private BooleanProperty isDinner;
	
	public Meal() {
		super();
		
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	public Meal(String name) {
		super(name);
		
		isLunch = new SimpleBooleanProperty();
		isDinner = new SimpleBooleanProperty();
	}
	
	public Meal(long ID, String name, boolean isLunch, boolean isDinner) {
		super(ID, name);
		
		this.isLunch = new SimpleBooleanProperty(isLunch);
		this.isDinner = new SimpleBooleanProperty(isDinner);
	}
	
	@Override
	public String toString() {
		return getName() + " " + (this.isLunch() ? "lunch" : "nlunch") + " " + (this.isDinner() ? "dinner" : "ndinner");
	}
	
	public boolean isLunch() { return isLunch.get(); }
	public void setLunch(boolean isLunch) { this.isLunch.set(isLunch); }
	public BooleanProperty isLunchProperty() { return isLunch; }
	
	public boolean isDinner() { return isDinner.get(); }
	public void setDinner(boolean isDinner) { this.isDinner.set(isDinner); }
	public BooleanProperty isDinnerProperty() { return isDinner; }
	
}
