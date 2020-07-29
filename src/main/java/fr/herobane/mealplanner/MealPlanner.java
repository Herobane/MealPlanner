package fr.herobane.mealplanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MealPlanner extends Application {

	public static void main(String[] args) { 
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = new AnchorPane();
		
		Scene scene = new Scene(root, 200, 100);
		
//		*****	SQLITE JDBC TEST	*****
//		
//		Connection c = DriverManager.getConnection("jdbc:sqlite:test.db");
//		Statement st = c.createStatement();
//		
//		st.executeUpdate("CREATE TABLE IF NOT EXISTS testtable (id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE ON CONFLICT ABORT, name VARCHAR);");
//		
//		st.executeUpdate("INSERT INTO testtable (name) VALUES ('flavien');");
//		
//		ResultSet s = st.executeQuery("SELECT * FROM testtable;");
//		
//		while(s.next()) {
//			System.out.println(s.getString("name"));
//		}
//		
		
		primaryStage.setTitle("MealPlanner");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
