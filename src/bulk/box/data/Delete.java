package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class Delete {

	private static ObservableList<BoxType> boxtype = null;
	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	@SuppressWarnings("unchecked")
	public static VBox deleteRecord(MenuBar menuBar) {

		Accordion accordion = new Accordion();

		TitledPane tpProduct = new TitledPane();
		tpProduct.setText("Box & Bulk QTY");

		TitledPane tpDimensions = new TitledPane();
		tpDimensions.setText("Dimensions");

		TitledPane tpLink = new TitledPane();
		tpLink.setText("Assign");

		ComboBox<String> cbProducts = new ComboBox<>();
		ComboBox<String> cbBoxCode = new ComboBox<>();
		ComboBox<String> cbcodetype = new ComboBox<>();

		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		cbProducts.setMaxWidth(200);
		GridPane gridOne = new GridPane(); 
		gridOne.setAlignment(Pos.CENTER);
		gridOne.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridOne.setHgap(7);
		gridOne.setVgap(3);	

		gridOne.add(cbProducts, 0, 0, 2,1);


		Button btn = new Button("Delete"); 
		btn.setPrefWidth(200);
		gridOne.add(btn, 0, 3, 2, 3);

		tableView.getColumns().addAll(productName, productWeight);
		gridOne.add(tableView, 4, 0, 1, 10); 

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


		/**
		 * 
		 */

		TableView<BoxCode> tableBox = new TableView<>(); 
		TableColumn<BoxCode, String> boxName = new TableColumn<>("Box Code");
		TableColumn<BoxCode, Number> boxLength = new TableColumn<>("Length");
		TableColumn<BoxCode, Number> boxWidth = new TableColumn<>("Width");
		TableColumn<BoxCode, Number> boxHeight = new TableColumn<>("Height ");




		cbBoxCode.setMaxWidth(200);
		GridPane gridTwo = new GridPane(); 
		gridTwo.setAlignment(Pos.CENTER);
		gridTwo.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridTwo.setHgap(7);
		gridTwo.setVgap(3);	

		gridTwo.add(cbBoxCode, 0, 0, 2,1);

		Button btnBox = new Button("Delete"); 
		btnBox.setPrefWidth(200);
		gridTwo.add(btnBox, 0, 2, 2, 3);



		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		gridTwo.add(tableBox, 7, 0, 1, 10); 

		ObservableList<String> ps = null;
		ObservableList<String> bc = null;
		ObservableList<String> bt = null;

		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT name FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
				ps = FXCollections.observableArrayList(rs.getString("name"));
				cbProducts.getItems().addAll(ps);
			}

			String queryBox = "SELECT name FROM box"; 
			ResultSet rx = statement.executeQuery(queryBox); 
			while(rx.next()) {
				bc = FXCollections.observableArrayList(rx.getString("name"));
				cbBoxCode.getItems().addAll(bc);
			}

			String queryBoxType = "SELECT iname FROM ItemBox"; 
			ResultSet rc = statement.executeQuery(queryBoxType); 
			while(rc.next()) {
				bt = FXCollections.observableArrayList(rc.getString("iname"));
				cbcodetype.getItems().addAll(bt);
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


		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
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
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}

		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM box"; 
			ResultSet rs = statement.executeQuery(query); 
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
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}



		btn.setOnAction(e -> {

			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 

				String sql = "DELETE from item WHERE name = '" + cbProducts.getSelectionModel().getSelectedItem() + "'"; 
				products.clear(); 
				tableView.getItems().clear();


				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}

				String query = "SELECT * FROM item"; 
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

			cbProducts.getSelectionModel().clearSelection();
		});

		btnBox.setOnAction(e -> {

			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 




				String sql = "DELETE from box WHERE name = '" + cbBoxCode.getSelectionModel().getSelectedItem() + "'"; 
				boxes.clear(); 
				tableBox.getItems().clear();


				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}

				String query = "SELECT * FROM box"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {


					boxes = FXCollections.observableArrayList(new BoxCode(Integer.valueOf(rs.getString("id")), rs.getString("name"), Integer.valueOf(rs.getString("length")), Integer.valueOf(rs.getString("width")), Integer.valueOf(rs.getString("height"))));	
					for(BoxCode b : boxes) {
						tableBox.getItems().add(b); 
					}
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
			cbBoxCode.getSelectionModel().clearSelection();
		});

		tableBox.setEditable(true);
		tableBox.setPrefHeight(430);

		boxName.setMinWidth(160);
		boxName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxCode, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		boxLength.setMinWidth(160);
		boxLength.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().lengthProperty();
			}
		});


		boxWidth.setMinWidth(160);
		boxWidth.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().widthProperty();
			}		
		});

		boxHeight.setMinWidth(160);
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

		cbcodetype.setMaxWidth(200);
		GridPane gridThree = new GridPane();
		gridThree.setAlignment(Pos.CENTER);
		gridThree.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridThree.setHgap(7);
		gridThree.setVgap(3);

		TextField txtName = new TextField(); 
		TextField txtBoxCode = new TextField(); 
		TextField txtMinimum = new TextField(); 
		TextField txtMaximum = new TextField(); 
		txtName.setEditable(false);
		gridThree.add(txtName, 0, 0, 2, 1);

		Button btnBoxType = new Button("Delete"); 
		btnBoxType.setPrefWidth(200);
		gridThree.add(btnBoxType, 0, 2, 2, 3);

		tableAssign.getColumns().addAll(taProduct, taBoxCode, taMinimum, taMaximum);
		gridThree.add(tableAssign, 7, 0, 1, 10);

		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM ItemBox"; 
			ResultSet rs = statement.executeQuery(query); 
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
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}


		btnBoxType.disableProperty().bind(
				Bindings.isEmpty(txtName.textProperty()));
		
		tableAssign.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BoxType>() {
			@Override
			public void changed(ObservableValue<? extends BoxType> observable, BoxType oldValue, BoxType newValue) {
				if(newValue != null) {
					txtName.setText(newValue.getName());
					txtBoxCode.setText(newValue.getBoxcode());
					txtMinimum.setText(String.valueOf(newValue.getMinimum()));
					txtMaximum.setText(String.valueOf(newValue.getMaximum()));
				}
			}
		});
		
		btnBoxType.setOnAction(e -> {

			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 

				String sql = "DELETE from ItemBox WHERE iname = '" + txtName.getText() + "' and bname = '" + txtBoxCode.getText() + "' and minimum = " + txtMinimum.getText() + " and maximum = " + txtMaximum.getText(); 
				boxtype.clear(); 
				tableAssign.getItems().clear();


				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}

				String query = "SELECT * FROM ItemBox"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {


					boxtype = FXCollections.observableArrayList(new BoxType(Integer.valueOf(rs.getString("id")), rs.getString("iname"), rs.getString("bname"), Integer.valueOf(rs.getString("minimum")), Integer.valueOf(rs.getString("maximum"))));	
					for(BoxType b : boxtype) {
						tableAssign.getItems().add(b); 
					}
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
			cbBoxCode.getSelectionModel().clearSelection();
		});

		taProduct.setMinWidth(160);
		taProduct.setReorderable(false);
		taProduct.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		taBoxCode.setMinWidth(160);
		taBoxCode.setReorderable(false);
		taBoxCode.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxcodeProperty();
			}
		});


		taMinimum.setMinWidth(160);
		taMinimum.setReorderable(false);
		taMinimum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().minimumProperty();
			}		
		});

		taMaximum.setMinWidth(160);
		taMaximum.setReorderable(false);
		taMaximum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().maximumProperty();
			}
		});



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
