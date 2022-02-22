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
import javafx.scene.control.Accordion;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class Edit {

	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<BoxType> boxtype = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	@SuppressWarnings("unchecked")
	public static VBox editRecord(MenuBar menuBar) {
		Accordion accordion = new Accordion();

		TitledPane tpProduct = new TitledPane();
		tpProduct.setText("Box & Bulk QTY");

		TitledPane tpDimensions = new TitledPane();
		tpDimensions.setText("Dimensions");

		TitledPane tpLink = new TitledPane();
		tpLink.setText("Assign");


		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		tableView.setEditable(true);
		tableView.setPrefHeight(430);
		
		GridPane gridOne = new GridPane();
		gridOne.setAlignment(Pos.CENTER);
		gridOne.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridOne.setHgap(7);
		gridOne.setVgap(3);

		tableView.getColumns().addAll(productName, productWeight);
		gridOne.add(tableView, 0, 0); 
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
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

		/**
		 * 
		 */

		TableView<BoxCode> tableBox = new TableView<>(); 
		TableColumn<BoxCode, String> boxName = new TableColumn<>("Box Code");
		TableColumn<BoxCode, Number> boxLength = new TableColumn<>("Length");
		TableColumn<BoxCode, Number> boxWidth = new TableColumn<>("Width");
		TableColumn<BoxCode, Number> boxHeight = new TableColumn<>("Height ");

		tableBox.setEditable(true);
		tableBox.setPrefHeight(430);
		
		GridPane gridTwo = new GridPane();
		gridTwo.setAlignment(Pos.CENTER);
		gridTwo.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridTwo.setHgap(7);
		gridTwo.setVgap(3);
		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		gridTwo.add(tableBox, 0, 0); 

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
		
		/**
		 * 
		 */
		TableView<BoxType> tableAssign = new TableView<>(); 
		TableColumn<BoxType, String> taProduct = new TableColumn<>("Product");
		TableColumn<BoxType, String> taBoxCode = new TableColumn<>("Box Code");
		TableColumn<BoxType, Number> taMinimum = new TableColumn<>("Minimum");
		TableColumn<BoxType, Number> taMaximum = new TableColumn<>("Maximum");

		tableAssign.setEditable(true);
		tableAssign.setPrefHeight(430);
		
		GridPane gridThree = new GridPane();
		gridThree.setAlignment(Pos.CENTER);
		gridThree.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridThree.setHgap(7);
		gridThree.setVgap(3);
		tableAssign.getColumns().addAll(taProduct, taBoxCode, taMinimum, taMaximum);
		gridThree.add(tableAssign, 0, 0);
		
		
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String queryBox = "SELECT * FROM ItemBox"; 
			ResultSet rs = statement.executeQuery(queryBox); 

			while(rs.next()) {

				boxtype = FXCollections.observableArrayList(new BoxType(Integer.valueOf(rs.getString("id")), rs.getString("iname"), rs.getString("bname"), Integer.valueOf(rs.getString("minimum")), Integer.valueOf(rs.getString("maximum"))));	
				for(BoxType b : boxtype) {
					tableAssign.getItems().add(b); 
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
		
		taProduct.setMinWidth(160);
		taProduct.setCellFactory(TextFieldTableCell.forTableColumn());
		taProduct.setOnEditCommit(
				(CellEditEvent<BoxType, String> n) ->{
					((BoxType) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setName(n.getNewValue());
				});
		taProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		taBoxCode.setMinWidth(160);
		taBoxCode.setCellFactory(TextFieldTableCell.forTableColumn());
		taBoxCode.setOnEditCommit(
				(CellEditEvent<BoxType, String> n) ->{
					((BoxType) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setBoxcode(n.getNewValue());
				});
		taBoxCode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxcodeProperty();
			}
		});


		taMinimum.setMinWidth(160);
		taMinimum.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		taMinimum.setOnEditCommit(
				(CellEditEvent<BoxType, Number> n) ->{
					((BoxType) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setMinimum(n.getNewValue().intValue());
				});
		taMinimum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().minimumProperty();
			}		
		});

		taMaximum.setMinWidth(160);
		taMaximum.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		taMaximum.setOnEditCommit(
				(CellEditEvent<BoxType, Number> n) ->{
					((BoxType) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setMaximum(n.getNewValue().intValue());
				});
		taMaximum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().maximumProperty();
			}
		});

		
		/**
		 * 
		 */

		VBox vbOne = new VBox(); 
		vbOne.getChildren().addAll(gridOne); 

		VBox vbTwo = new VBox(); 
		vbTwo.getChildren().addAll(gridTwo); 
		
		VBox vbThree = new VBox(); 
		vbThree.getChildren().addAll(gridThree); 

		tpProduct.setContent(vbOne);
		tpDimensions.setContent(vbTwo);
		tpLink.setContent(vbThree);

		accordion.getPanes().add(tpProduct);
		accordion.getPanes().add(tpDimensions);
		accordion.getPanes().add(tpLink);

		VBox screen = new VBox();
		screen.getChildren().addAll(menuBar, accordion);
		return screen;
	}
}
