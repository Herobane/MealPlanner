package fr.herobane.mealplanner.utils;

/**
 * <p> Measure enumeration for an ingredient </p>
 * <p> Represent a measure that can be used to quantify an ingredient. </p>
 * <p> Current values : </p>
 * <ul>
 * 	<li>UNIT : Used for an ingredient that can be measured with its count (i.e. an egg) </li>
 * 	<li>GRAMS : Used for an ingredient that can be measured with its weight (i.e. flour) </li>
 * 	<li>LITER : Used for an ingredient that can be measured with its volume (i.e. water) </li>
 * </ul>
 * 
 * @author herobane
 *
 */
public enum Measure {
	
	
	// ***** ENUM VALUES *****
	
	/**
	 * <p> A number of an ingredient </p>
	 * <p> Used for an ingredient that can be measured with its count (i.e. an egg) </p>
	 */
	UNIT("Unit", ""),
	/**
	 * <p> A weight of an ingredient </p>
	 * <p>Used for an ingredient that can be measured with its weight (i.e. flour)</p>
	 */
	GRAMS("Grams", "g"),
	/**
	 * <p> A volume of an ingredient </p>
	 * <p> Used for an ingredient that can be measured with its volume (i.e. water) </p>
	 */
	LITER("Liter", "L");
	
	
	// ***** FIELDS *****
	
	/**
	 * A user-friendly name for the measure.
	 */
	String name;
	/**
	 * The abbreviation suffix used for this measure.
	 */
	String abbreviation;
	

	// ***** PRIVATE CONSTRUCTOR *****
	
	/**
	 * <p> Unique private constructor for this enumeration values </p>
	 * <p> Instantiate each field with its corresponding constructor value. </p>
	 * 
	 * @param name the user-friendly name of the measure
	 * @param abbreviation the abbreviation suffix used for this measure
	 */
	private Measure(String name, String abbreviation) {
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	// ***** PUBLIC METHODS *****
	
	/**
	 * Gets the abbreviation suffix used for this measure.
	 * @return the abbreviation suffix used for this measure
	 */
	public String getAbbrviation() {
		return abbreviation;
	}
}
