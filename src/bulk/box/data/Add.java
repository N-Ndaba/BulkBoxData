package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class Add {

	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	public static VBox addRecord(MenuBar menuBar) {
		Label lbllabel = new Label("Name:");
		TextField name = new TextField();
		Label lblnumber = new Label("Weight:");
		TextField number = new TextField(); 
		Button btn = new Button("Add Item"); 
		btn.setPrefWidth(200);

		Label lblBoxCode = new Label("Box Code:");
		TextField txtBoxCode = new TextField();
		Label lblLength = new Label("Length:");
		TextField txtLength = new TextField(); 
		Label lblWidth = new Label("Width:");
		TextField txtWidth = new TextField(); 
		Label lblHeight = new Label("Hieght:");
		TextField txtHeight = new TextField(); 
		Button btnDimensions = new Button("Add Item"); 

		btnDimensions.setPrefWidth(200);


		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		TableView<BoxCode> tableBox = new TableView<>(); 
		TableColumn<BoxCode, String> boxName = new TableColumn<>("Box Code");
		TableColumn<BoxCode, Number> boxLength = new TableColumn<>("Length");
		TableColumn<BoxCode, Number> boxWidth = new TableColumn<>("Width");
		TableColumn<BoxCode, Number> boxHeight = new TableColumn<>("Height ");

		GridPane grid = new GridPane(); 
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(3, 12.5, 5, 14.5));
		grid.setHgap(7);
		grid.setVgap(3);
		grid.add(lbllabel, 0, 0);
		grid.add(name, 1, 0);
		grid.add(lblnumber, 0, 1);
		grid.add(number, 1, 1);
		grid.add(btn, 0, 4, 2, 3);


		grid.add(lblBoxCode, 0, 9);
		grid.add(txtBoxCode, 1, 9);
		grid.add(lblLength, 0,10);
		grid.add(txtLength, 1, 10); 
		grid.add(lblWidth, 0, 11);
		grid.add(txtWidth, 1, 11); 
		grid.add(lblHeight, 0, 12);
		grid.add(txtHeight, 1, 12);
		grid.add(btnDimensions, 0, 12, 2, 3); 

		grid.add(tableView, 5, 0, 1, 14);
		grid.add(tableBox, 7, 0, 1, 14);
		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		tableView.getColumns().addAll(productName, productWeight);




		btn.setOnAction(e -> {


			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Connection connection = DriverManager.getConnection(jdbcURL);
				String sql = "Insert into item (name, weight) values ('" +name.getText()+ "', "+number.getText()+")";

				Statement statement = connection.createStatement(); 

				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}


				String query = "SELECT * FROM item WHERE name = '" + name.getText() +"'"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					System.out.println("name: " + rs.getString("name"));
					System.out.println("weight: " + rs.getString("weight"));

					products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
					for(Product p : products) {
						tableView.getItems().add(p); 
					}
				}






				String shutdown = "jdbc:derby:;shutdown=true";
				DriverManager.getConnection(shutdown); 
			} catch (SQLException ex) {
				if(ex.getSQLState().equals("XJ015")) {
					System.out.println("Derby shutdown normally");
				} else {
					ex.printStackTrace();
				}
			}

			name.setText(null);
			number.setText(null); 
		});

		btnDimensions.setOnAction(e -> {



			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Connection connection = DriverManager.getConnection(jdbcURL);
				String sql = "Insert into box (name, length, width, height) values ('" + txtBoxCode.getText()+ "', "+ txtLength.getText()+", " + txtWidth.getText()+", " + txtHeight.getText()+ ")";

				Statement statement = connection.createStatement(); 

				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}


				String query = "SELECT * FROM box WHERE name = '" + txtBoxCode.getText() +"'"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					boxes = FXCollections.observableArrayList(new BoxCode(Integer.valueOf(rs.getString("id")), rs.getString("name"), Integer.valueOf(rs.getString("length")), Integer.valueOf(rs.getString("width")), Integer.valueOf(rs.getString("height"))));	
					for(BoxCode b : boxes) {
						tableBox.getItems().add(b); 
					}
				}






				String shutdown = "jdbc:derby:;shutdown=true";
				DriverManager.getConnection(shutdown); 
			} catch (SQLException ex) {
				if(ex.getSQLState().equals("XJ015")) {
					System.out.println("Derby shutdown normally");
				} else {
					ex.printStackTrace();
				}
			}

			txtBoxCode.setText(null);
			txtLength.setText(null); 
			txtWidth.setText(null);
			txtHeight.setText(null);
		});



		//boxName.setMinWidth(160);
		boxName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxCode, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		//boxLength.setMinWidth(160);
		boxLength.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().lengthProperty();
			}
		});


		//boxWidth.setMinWidth(160);
		boxWidth.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().widthProperty();
			}		
		});

		//boxHeight.setMinWidth(160);
		boxHeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().heightProperty();
			}
		});
		//----------------------------

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

		VBox vbox = new VBox(); 
		vbox.getChildren().addAll(menuBar, grid); 

		return vbox;
	}
}
