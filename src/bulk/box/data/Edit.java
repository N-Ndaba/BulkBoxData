package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class Edit {

	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<BoxType> boxtype = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	@SuppressWarnings("unchecked")
	public static VBox editRecord(HBox menuBar) {
		Accordion accordion = new Accordion();

		TitledPane tpProduct = new TitledPane();
		tpProduct.setText("Box & Bulk QTY");

		TitledPane tpDimensions = new TitledPane();
		tpDimensions.setText("Dimensions");

	
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
		
		GridPane gridZ = new GridPane();
		gridZ.setAlignment(Pos.CENTER);
		gridZ.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridZ.setHgap(7);
		gridZ.setVgap(3);
		
		BorderPane pane = new BorderPane(); 
		VBox paneForCheckBoxes = new VBox(20);
		paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
				products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
				for(Product p : products) {
					CheckBox chProduct = new CheckBox(p.getName());
					chProduct.setFont(Font.font("Arial", 13));
					chProduct.setAlignment(Pos.CENTER);
					paneForCheckBoxes.getChildren().addAll(chProduct);

					EventHandler<ActionEvent> handler = e -> {
						if (chProduct.isSelected()) {
							tableView.getItems().add(p);
						} else	{
							tableView.getItems().remove(p);
						} 
					};
					chProduct.setOnAction(handler);
					 
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
		
		pane.setCenter(paneForCheckBoxes);
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

		GridPane gridX = new GridPane();
		gridX.setAlignment(Pos.CENTER);
		gridX.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridX.setHgap(7);
		gridX.setVgap(3);
		
		BorderPane paneTwo = new BorderPane(); 
		VBox paneForCheckBoxesTwo = new VBox(20);
		paneForCheckBoxesTwo.setPadding(new Insets(5, 5, 5, 5));
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String queryBox = "SELECT * FROM box"; 
			ResultSet rs = statement.executeQuery(queryBox); 

			while(rs.next()) {

				boxes = FXCollections.observableArrayList(new BoxCode(Integer.valueOf(rs.getString("id")), rs.getString("name"), Integer.valueOf(rs.getString("length")), Integer.valueOf(rs.getString("width")), Integer.valueOf(rs.getString("height"))));	
				for(BoxCode b : boxes) {
					CheckBox chProduct = new CheckBox(b.getName());
					chProduct.setFont(Font.font("Arial", 13));
					chProduct.setAlignment(Pos.CENTER);
					paneForCheckBoxesTwo.getChildren().addAll(chProduct);

					EventHandler<ActionEvent> handler = e -> {
						if (chProduct.isSelected()) {
							tableBox.getItems().add(b); 
						} else	{
							tableBox.getItems().remove(b); 
						} 
					};
					chProduct.setOnAction(handler);
					
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
		paneTwo.setCenter(paneForCheckBoxesTwo);

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
		
		
		/**
		 * 
		 */

		
		
		gridZ.add(pane, 0, 0);
		gridZ.add(gridOne, 5, 0);

		gridX.add(paneTwo, 0, 0);
		gridX.add(gridTwo, 5, 0);
		
		
		tpProduct.setContent(gridZ);
		tpDimensions.setContent(gridX);

		accordion.getPanes().add(tpProduct);
		accordion.getPanes().add(tpDimensions);

		VBox screen = new VBox();
		screen.getChildren().addAll(menuBar, accordion);
		return screen;
	}
}
