package fr.herobane.mealplanner.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {

	private static Connection connection;
	
	private SQLiteConnection() {}
	
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
}
