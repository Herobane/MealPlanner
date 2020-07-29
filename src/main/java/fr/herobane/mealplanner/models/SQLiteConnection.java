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
				connection = DriverManager.getConnection("jdbc:sqlite:/test.db");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
}
