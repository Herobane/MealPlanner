package fr.herobane.mealplanner.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.herobane.mealplanner.models.beans.Meal;
import fr.herobane.mealplanner.models.beans.ObservableObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MealDAO extends DAO<Meal> {
	
	private static ObservableList<ObservableObject> meals = FXCollections.observableArrayList();
	
	@Override
	public Meal find(long ID) {
		
		Meal meal = null;
		
		try {
			ResultSet result = this.connection.createStatement()
					.executeQuery(
							"SELECT * FROM meals "
							+ "WHERE meal_id=" + ID);
			
			if(result.first()) {
				meal = new Meal(ID, result.getString("meal_name"), result.getBoolean("meal_lunch"), result.getBoolean("meal_dinner"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return meal;
	}

	@Override
	public Meal create(Meal obj) {
		
		try {
			this.connection.createStatement()
				.executeUpdate(
						"INSERT INTO meals (meal_name, meal_lunch, meal_dinner) "
						+ "VALUES ("
						+ "'" + obj.getName() + "',"
						+ "'" + (obj.isLunch() ? "1" : "0") + "',"
						+ "'" + (obj.isDinner() ? "1" : "0") + "'"
						+ ")");
			getMeals();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return obj;
	}

	@Override
	public void delete(Meal obj) {
		try {
			this.connection.createStatement()
				.executeUpdate(
						"DELETE FROM meals "
						+ "WHERE meal_id=" + obj.getID());
			
			getMeals();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Meal update(long ID, Meal obj) {
		try {
			this.connection.createStatement()
				.executeUpdate(
						"UPDATE meals SET "
							+ "meal_name='" + obj.getName() + "', "
							+ "meal_lunch='" + (obj.isLunch() ? "1" : "0") + "', "
							+ "meal_dinner='" + (obj.isDinner() ? "1" : "0") + "'"
						+ "WHERE meal_id=" + ID);
			
			getMeals();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return find(ID);
	}
	
	/**
	 * Fetch the database and populate meals {@code ObservableList} according to request's results
	 * @return a list containing fetched meals
	 * @throws SQLException
	 */
	public ObservableList<ObservableObject> getMeals() throws SQLException {
		meals.clear();
		
		ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM meals");
		
		while(result.next()) {
			meals.add(new Meal(result.getLong("meal_id"), result.getString("meal_name"), result.getBoolean("meal_lunch"), result.getBoolean("meal_dinner")));
		}
		
		return meals;
	}

}
