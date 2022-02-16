package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

public class Delete extends StackPane{
	TableView<Product> tableView = new TableView<>(); 
	TableColumn<Product, String> productName = new TableColumn<>("Product");
	TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");
	private ObservableList<Product> products = null;
	private String jdbcURL = "jdbc:derby:boxbulkdb;create=true"; 

	public Delete() {
		try { 
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT id, name, weight FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
				System.out.println("id" + rs.getString("id"));
				System.out.println("name: " + rs.getString("name"));
				System.out.println("weight: " + rs.getString("weight"));

				products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
				for(Product p : products) {
					tableView.getItems().add(p); 
				}
			}
			String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown);
			connection.close();
			statement.close();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}

		insert(); 
	}

	@SuppressWarnings("unchecked")
	public void insert() {
		Label lbllabel = new Label("Name:");
		TextField name = new TextField();
		Label lblnumber = new Label("Weight:");
		TextField number = new TextField(); 
		GridPane grid = new GridPane(); 
		grid.add(lbllabel, 0, 0);
		grid.add(name, 1, 0);
		grid.add(lblnumber, 0, 1);
		grid.add(number, 1, 1);
		Button btn = new Button("Save"); 
		grid.add(btn, 3, 3);

		grid.add(tableView, 5, 5); 
		tableView.getColumns().addAll(productName, productWeight);



		btn.setOnAction(e -> {

			Product pro = tableView.getSelectionModel().getSelectedItem(); 
			name.setText(productName.getText());
			number.setText(productWeight.getText());


			try {
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				String sql = "DELETE from item WHERE id = " + pro.getId(); 

				Statement statement = connection.createStatement(); 

				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}


				String query = "SELECT id, name, weight FROM item"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					System.out.println("name: " + rs.getString("name"));
					System.out.println("weight: " + rs.getString("weight"));

					products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
				}

				for(Product p : products) {
					tableView.getItems().add(p); 
				}




				String shutdown = "jdbc:derby:;shutdown=true";
				DriverManager.getConnection(shutdown); 
			} catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}
			catch (SQLException ex) {
				if(ex.getSQLState().equals("XJ015")) {
					System.out.println("Derby shutdown normally");
				} else {
					ex.printStackTrace();
				}
			}

			name.setText(null);
			number.setText(null); 


		});

		productName.setMinWidth(160);
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		productWeight.setMinWidth(160);
		productWeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().sumProperty();
			}
		});



		getChildren().add(grid);
	}
}
