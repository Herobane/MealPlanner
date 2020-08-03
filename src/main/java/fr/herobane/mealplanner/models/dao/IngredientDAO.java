package fr.herobane.mealplanner.models.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.herobane.mealplanner.models.beans.Ingredient;
import fr.herobane.mealplanner.models.beans.ObservableObject;
import fr.herobane.mealplanner.utils.Measure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class IngredientDAO extends DAO<Ingredient> {

	private static ObservableList<ObservableObject> ingredients = FXCollections.observableArrayList();
	
	@Override
	public Ingredient find(long ID) {
		
		Ingredient ingredient = null;
		
		try {
			ResultSet result = this.connection.createStatement()
					.executeQuery(
							"SELECT * FROM ingredients"
							+ "WHERE ingredient_id=" + ID);
			
			if(result.next()) {
				ingredient = new Ingredient(ID, result.getString("ingredient_name"), Measure.valueOf(result.getString("ingredient_measure")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return ingredient;
	}

	@Override
	public Ingredient create(Ingredient obj) {
		
		try {
			this.connection.createStatement()
				.executeUpdate(
						"INSERT INTO ingredients (ingredient_name, ingredient_measure) "
						+ "VALUES ("
						+ "'" + obj.getName() + "',"
						+ "'" + obj.getMeasure().name()
						+ "')");
			getIngredients();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return obj;
	}

	@Override
	public void delete(Ingredient obj) {
		
		try {
			this.connection.createStatement()
				.executeUpdate(
						"DELETE FROM ingredients "
						+ "WHERE ingredient_id=" + obj.getID());
			getIngredients();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Ingredient update(long ID, Ingredient obj) {
		
		try {
			this.connection.createStatement()
				.executeUpdate(
						"UPDATE ingredients SET "
							+ "ingredient_name='" + obj.getName() + "', "
							+ "ingredient_measure='" + obj.getMeasure().name() + "'"
						+ "WHERE ingredient_id=" + ID);
			getIngredients();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return find(ID);
	}
	
	/**
	 * Fetch the database and populate ingredients {@code ObservableList} according to request's results
	 * @return a list containing fetched ingredients
	 * @throws SQLException
	 */
	public ObservableList<ObservableObject> getIngredients() throws SQLException {
		ingredients.clear();
		
		ResultSet result = this.connection.createStatement().executeQuery("SELECT * FROM ingredients");
		
		while(result.next()) {
			ingredients.add(new Ingredient(result.getLong("ingredient_id"), result.getString("ingredient_name"), Measure.valueOf(result.getString("ingredient_measure"))));
		}
		
		return ingredients;
	}

}
