package fr.herobane.mealplanner.utils;

public enum Measure {
	UNIT(""),
	GRAMS("g"),
	LITER("L");
	
	String abbreviation;
	
	private Measure(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	@Override
	public String toString() {
		return abbreviation;
	}
}
