package fr.herobane.mealplanner.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.herobane.mealplanner.models.beans.Meal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MealDAO extends DAO<Meal> {
	
	private static ObservableList<Meal> meals = FXCollections.observableArrayList();
	
	@Override
	public Meal find(long ID) {
		
		Meal meal = null;
		
		try {
			ResultSet result = this.connection.createStatement()
					.executeQuery(
							"SELECT * FROM meals "
							+ "WHERE meal_id=" + ID);
			
			if(result.first()) {
				meal = new Meal(result.getString("meal_name"));
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
		return null;
	}
	
	public ObservableList<Meal> getMeals() throws SQLException {
		meals.clear();
		
		ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM meals");
		
		while(result.next()) {
			Meal meal = new Meal(result.getString("meal_name"));
			meal.setID(result.getLong("meal_id"));
			meal.setLunch(result.getBoolean("meal_lunch"));
			meal.setDinner(result.getBoolean("meal_dinner"));

			meals.add(meal);
		}
		
		return meals;
	}

}
