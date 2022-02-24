package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class BulkBoxPane extends StackPane { 

	public BulkBoxPane() {	
		//create(); 

		HBox gridBar = new HBox(30); 

		Button home = new Button("Home", new ImageView("home.png"));
		Button add = new Button("Add", new ImageView("add.png"));
		Button edit = new Button("Edit", new ImageView("edit.png"));
		Button delete = new Button("Delete", new ImageView("delete.png"));

		edit.setStyle("-fx-background-color: transparent;");
		add.setStyle("-fx-background-color: transparent;");
		delete.setStyle("-fx-background-color: transparent;");

		gridBar.getChildren().addAll(home, add, edit, delete);
		gridBar.setAlignment(Pos.CENTER);
		gridBar.setStyle("-fx-border-color: grey");

		getChildren().add(Home.home(gridBar));
		home.setStyle("-fx-font-weight: bold;"
				+ "-fx-background-color: transparent;"
				+ "-fx-underline: true;");
		home.setPrefWidth(100);
		home.setOnAction(e -> {
			getChildren().clear();
			home.setStyle("-fx-font-weight: bold;"
					+ "-fx-background-color: transparent;"
					+ "-fx-underline: true;");
			add.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			edit.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			delete.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			getChildren().add(Home.home(gridBar));
		});

		add.setPrefWidth(100);
		add.setOnAction(e -> {
			getChildren().clear();
			add.setStyle("-fx-font-weight: bold;"
					+ "-fx-background-color: transparent;"
					+ "-fx-underline: true;");
			home.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			edit.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			delete.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			getChildren().add(Add.addRecord(gridBar));
		});

		edit.setPrefWidth(100);
		edit.setOnAction(e -> {
			getChildren().clear();
			edit.setStyle("-fx-font-weight: bold;"
					+ "-fx-background-color: transparent;"
					+ "-fx-underline: true;");	
			add.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			home.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			delete.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			getChildren().add(Edit.editRecord(gridBar));
		});

		delete.setPrefWidth(100);
		delete.setOnAction(e -> {
			getChildren().clear();
			delete.setStyle("-fx-font-weight: bold;"
					+ "-fx-background-color: transparent;"
					+ "-fx-underline: true;");
			edit.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");	
			add.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			home.setStyle("-fx-font-weight: normal;"
					+ "-fx-background-color: transparent;");
			getChildren().add(Delete.deleteRecord(gridBar));
		});

	}

	private void create() {
		String jdbcURL = "jdbc:derby:boxbulkdb;create=true";
		try {
			Connection connection = DriverManager.getConnection(jdbcURL);
			//String sql = "DROP Table ItemBox";
			//(name, length, width, height) ;  
			// (iname, bname, minimum, maximum)
			String sql = "Create Table ItemBox ("
					+ "id int not null generated always as identity,"
					+ "iname varchar(255) not null,"
					+ "bname varchar(255) not null,"
					+ "minimum int not null,"
					+ "maximum int not null,"
					+ "primary key(iname, bname, minimum, maximum)"
					+ ")";





			Statement statement = connection.createStatement(); 
			statement.executeUpdate(sql);
			System.out.println(">>>>[][]<<");
		}catch(SQLException e) {
			e.printStackTrace();
		}
		/*

		 //String sql = "DROP Table item";  
			String sql = "Create Table item ("
				   + "id int not null generated always as identity,"
				   + "name varchar(255) unique not null,"
				   + "weight double not null"
				   + ")";

			String sql = "Create Table box ("
				   + "id int not null generated always as identity,"
				   + "name varchar(255) unique not null,"
				   + "length int not null,"
				   + "width int not null,"
				   + "height int not null"
				   + ")";


		 */
	}
}
