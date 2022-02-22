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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class Delete {

	private static ObservableList<BoxType> boxtype = null;
	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	public static VBox deleteRecord(HBox menuBar) {

		Accordion accordion = new Accordion();

		accordion.getPanes().add(bolkbox());
		accordion.getPanes().add(dimensions());
		accordion.getPanes().add(assign());

		VBox screen = new VBox();
		screen.getChildren().addAll(menuBar, accordion);

		return screen;
	}
	
	
	@SuppressWarnings("unchecked")
	private static TitledPane bolkbox() {
		TitledPane tpProduct = new TitledPane();
		tpProduct.setText("Box & Bulk QTY");

		ComboBox<String> cbProducts = new ComboBox<>();
		cbProducts.setMaxWidth(200);

		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		
		GridPane gridOne = new GridPane(); 
		gridOne.setAlignment(Pos.CENTER);
		gridOne.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridOne.setHgap(7);
		gridOne.setVgap(3);	

		gridOne.add(cbProducts, 0, 0, 2,1);
		Button btnDelete = new Button("Delete"); 
		btnDelete.setPrefWidth(200);
		gridOne.add(btnDelete, 0, 3, 2, 3);

		tableView.getColumns().addAll(productName, productWeight);
		gridOne.add(tableView, 4, 0, 1, 10); 
		getProducts(cbProducts); 	
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
		
		btnDelete.setOnAction(e -> {

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
			getProducts(cbProducts); 
			cbProducts.getSelectionModel().clearSelection();
		});
		
	

		productName.setMinWidth(160);
		productName.setReorderable(false);
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		productWeight.setMinWidth(160);
		productWeight.setReorderable(false);
		productWeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().sumProperty();
			}
		});

		VBox vbOne = new VBox(); 
		vbOne.getChildren().addAll(gridOne); 

		tpProduct.setContent(vbOne);
	
		return tpProduct; 
	}
	
	
	private static void getProducts(ComboBox<String> cbProducts) {
		ObservableList<String> ps = null;
		cbProducts.getItems().clear();
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
	}
	
	@SuppressWarnings("unchecked")
	private static TitledPane dimensions() {

		TitledPane tpDimensions = new TitledPane();
		tpDimensions.setText("Dimensions");
		
		ComboBox<String> cbBoxCode = new ComboBox<>();
		 
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

		Button btnDeleteBox = new Button("Delete"); 
		btnDeleteBox.setPrefWidth(200);
		gridTwo.add(btnDeleteBox, 0, 2, 2, 3);

		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		gridTwo.add(tableBox, 7, 0, 1, 10); 
		getBoxes(cbBoxCode);

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

		btnDeleteBox.setOnAction(e -> {

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
			getBoxes(cbBoxCode);
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
		
		VBox vbTwo = new VBox(); 
		vbTwo.getChildren().addAll(gridTwo); 
		
		tpDimensions.setContent(vbTwo);

		return tpDimensions; 
	}
	
	private static void getBoxes(ComboBox<String> cbBoxCode) {
		ObservableList<String> bc = null;
		cbBoxCode.getItems().clear();
		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String queryBox = "SELECT name FROM box"; 
			ResultSet rx = statement.executeQuery(queryBox); 
			while(rx.next()) {
				bc = FXCollections.observableArrayList(rx.getString("name"));
				cbBoxCode.getItems().addAll(bc);
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
	}
	
	@SuppressWarnings("unchecked")
	private static TitledPane assign() {
		TitledPane tpLink = new TitledPane();
		tpLink.setText("Assign");
		
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

		Label lblName = new Label("Name: ");
		TextField txtName = new TextField(); 
		txtName.setEditable(false);
		Label lblBoxCode = new Label("Box Code: ");
		TextField txtBoxCode = new TextField(); 
		txtBoxCode.setEditable(false);
		Label lblMinimum = new Label("Minimum: ");
		TextField txtMinimum = new TextField(); 
		txtMinimum.setEditable(false);
		Label lblMaximum = new Label("Maximum: ");
		TextField txtMaximum = new TextField(); 
		txtMaximum.setEditable(false);
		
		gridThree.add(lblName, 0, 0);
		gridThree.add(txtName, 1, 0);
		gridThree.add(lblBoxCode, 0, 1);
		gridThree.add(txtBoxCode, 1, 1);
		gridThree.add(lblMinimum, 0, 2);
		gridThree.add(txtMinimum, 1, 2);
		gridThree.add(lblMaximum, 0, 3);
		gridThree.add(txtMaximum, 1, 3);
		

		Button btnBoxType = new Button("Delete"); 
		btnBoxType.setPrefWidth(210);
		gridThree.add(btnBoxType, 1, 6, 1, 3);

		tableAssign.getColumns().addAll(taProduct, taBoxCode, taMinimum, taMaximum);
		gridThree.add(tableAssign, 3, 0, 1, 10);

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

		VBox vbThree = new VBox(); 
		vbThree.getChildren().addAll(gridThree); 
		
		tpLink.setContent(vbThree);
		
		return tpLink; 
	}
}
