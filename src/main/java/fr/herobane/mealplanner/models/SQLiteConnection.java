package fr.herobane.mealplanner.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p> Singleton object for the Connection to SQLite database</p>
 * <p> Provides a unique Connection to the SQLite database </p>
 * @author herobane
 *
 */
public class SQLiteConnection {

	/**
	 * The unique instance of the SQLite Connection
	 */
	private static Connection connection;
	
	private SQLiteConnection() {}
	
	// TODO : database initialization/creation
	/**
	 * Gets the unique instance of the Connection to the SQLite database.
	 * @return the instance of the Connection to the SQLite database.
	 */
	public static Connection getInstance() {
		if(connection == null) {
			try {
				connection = DriverManager.getConnection("jdbc:sqlite:mealplanner.db");
				
				connection.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS meals ("
						+ "meal_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
						+ "meal_name VARCHAR, "
						+ "meal_lunch BOOLEAN,"
						+ "meal_dinner BOOLEAN"
						+ ")");
				
				connection.createStatement().executeUpdate(
						"CREATE TABLE IF NOT EXISTS ingredients ("
						+ "ingredient_id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE, "
						+ "ingredient_name VARCHAR, "
						+ "ingredient_measure VARCHAR"
						+ ")");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
}
