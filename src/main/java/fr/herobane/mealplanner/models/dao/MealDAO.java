package fr.herobane.mealplanner.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.herobane.mealplanner.models.beans.Meal;

public class MealDAO extends DAO<Meal> {

	@Override
	public Meal find(long ID) {
		
		Meal meal = null;
		
		try {
			ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM meals WHERE meal_id=" + ID);
			
			if(result.first()) {
				// TODO : Meal object construction
				meal = new Meal();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return meal;
	}

	@Override
	public Meal create(Meal obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Meal obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public Meal update(long ID, Meal obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
