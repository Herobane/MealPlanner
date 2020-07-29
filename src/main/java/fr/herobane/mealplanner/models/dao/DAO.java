package fr.herobane.mealplanner.models.dao;

import java.sql.Connection;

import fr.herobane.mealplanner.models.SQLiteConnection;

/**
 * Base SuperClass for any Data Access Object
 * @param <T> the generic type of the DAO
 * 
 * @author herobane
 * 
 */
public abstract class DAO<T> {

	/**
	 * Connection object to interact with the SQLite DataBase
	 */
	protected Connection connection = SQLiteConnection.getInstance();
	
	/**
	 * Finds the corresponding object with its ID
	 * @param ID the ID of the object
	 * @return object corresponding to the specified ID
	 */
	public abstract T find(long ID);
	
	/**
	 * Creates the specified object in the DataBase
	 * @param obj the object to create
	 * @return the created object
	 */
	public abstract T create(T obj);
	
	/**
	 * Deletes the specified object in the DataBase
	 * @param obj the object to delete
	 */
	public abstract void delete(T obj);
	
	/**
	 * Updates the specified object
	 * @param ID the ID of the object to update
	 * @param obj the object after update
	 * @return updated object
	 */
	public abstract T update(long ID, T obj);
	
}
