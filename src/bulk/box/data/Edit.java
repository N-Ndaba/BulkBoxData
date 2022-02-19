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
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class Edit {

	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";
	
	public static VBox editRecord(MenuBar menuBar) {
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		TableView<BoxCode> tableBox = new TableView<>(); 
		TableColumn<BoxCode, String> boxName = new TableColumn<>("Box Code");
		TableColumn<BoxCode, Number> boxLength = new TableColumn<>("Length");
		TableColumn<BoxCode, Number> boxWidth = new TableColumn<>("Width");
		TableColumn<BoxCode, Number> boxHeight = new TableColumn<>("Height ");


		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM item"; 
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
		} catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}

		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String queryBox = "SELECT * FROM box"; 
			ResultSet rs = statement.executeQuery(queryBox); 

			while(rs.next()) {

				boxes = FXCollections.observableArrayList(new BoxCode(Integer.valueOf(rs.getString("id")), rs.getString("name"), Integer.valueOf(rs.getString("length")), Integer.valueOf(rs.getString("width")), Integer.valueOf(rs.getString("height"))));	
				for(BoxCode b : boxes) {
					tableBox.getItems().add(b); 
				}
			}

			String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown);
			connection.close();
			statement.close();
		} catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}

		tableView.setEditable(true);
		tableView.setPrefHeight(430);

		tableBox.setEditable(true);
		tableBox.setPrefHeight(430);

		boxName.setMinWidth(160);
		boxName.setCellFactory(TextFieldTableCell.forTableColumn());
		boxName.setOnEditCommit(
				(CellEditEvent<BoxCode, String> n) ->{
					((BoxCode) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setName(n.getNewValue());
				});
		boxName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxCode, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		boxLength.setMinWidth(160);
		boxLength.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		boxLength.setOnEditCommit(
				(CellEditEvent<BoxCode, Number> n) ->{
					((BoxCode) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setLength(n.getNewValue().intValue());
				});
		boxLength.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().lengthProperty();
			}
		});


		boxWidth.setMinWidth(160);
		boxWidth.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		boxWidth.setOnEditCommit(
				(CellEditEvent<BoxCode, Number> n) ->{
					((BoxCode) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setWidth(n.getNewValue().intValue());
				});
		boxWidth.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().widthProperty();
			}		
		});

		boxHeight.setMinWidth(160);
		boxHeight.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		boxHeight.setOnEditCommit(
				(CellEditEvent<BoxCode, Number> n) ->{
					((BoxCode) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setHeight(n.getNewValue().intValue());
				});
		boxHeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().heightProperty();
			}
		});
		//---------------------
		productName.setMinWidth(160);
		productName.setCellFactory(TextFieldTableCell.forTableColumn());
		productName.setOnEditCommit(
				(CellEditEvent<Product, String> n) ->{
					((Product) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setName(n.getNewValue());
				});
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		productWeight.setMinWidth(160);
		productWeight.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		productWeight.setOnEditCommit(
				(CellEditEvent<Product, Number> n) ->{
					((Product) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setWeight(n.getNewValue().doubleValue());
				});
		productWeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().sumProperty();
			}
		});


		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(3, 12.5, 5, 14.5));
		grid.setHgap(7);
		grid.setVgap(3);
		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		tableView.getColumns().addAll(productName, productWeight);
		grid.add(tableBox, 7, 5, 5, 5); 
		grid.add(tableView, 1, 5, 5, 5); 

		VBox vbox = new VBox(); 
		vbox.getChildren().addAll(menuBar, grid); 
		return vbox;
	}
}
