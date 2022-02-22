package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.VBox;
import javafx.util.Callback;


public class Add {

	private static ObservableList<BoxType> bt = null;
	private static ObservableList<BoxCode> boxes = null;
	private static ObservableList<Product> products = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	@SuppressWarnings("unchecked")
	public static VBox addRecord(MenuBar menuBar) {
		Accordion accordion = new Accordion();
		
		TitledPane tpProduct = new TitledPane();
		tpProduct.setText("Box & Bulk QTY");
		
		TitledPane tpDimensions = new TitledPane();
		tpDimensions.setText("Dimensions");
		
		TitledPane tpLink = new TitledPane();
		tpLink.setText("Assign");	
		
		/**
		 * ----------------------------
		 */
		
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

		GridPane gridOne = new GridPane(); 
		gridOne.setAlignment(Pos.CENTER);
		gridOne.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridOne.setHgap(7);
		gridOne.setVgap(3);
		
		Label lblName = new Label("Item:");
		TextField txtName = new TextField();
		Label lbLWeight = new Label("Weight:");
		TextField txtWeight = new TextField(); 
		Button btnItem = new Button("Add Item"); 
		btnItem.setPrefWidth(200);
		
		gridOne.add(lblName, 0, 0);
		gridOne.add(txtName, 1, 0);
		gridOne.add(lbLWeight, 0, 1);
		gridOne.add(txtWeight, 1, 1);
		gridOne.add(btnItem, 0, 4, 2, 3);
		
		tableView.getColumns().addAll(productName, productWeight);
		gridOne.add(tableView, 5, 0, 1, 14);
		
		btnItem.disableProperty().bind(
				Bindings.isEmpty(txtName.textProperty()));
		btnItem.disableProperty().bind(
				Bindings.isEmpty(txtWeight.textProperty()));
		
		btnItem.setOnAction(e -> {
			
			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Connection connection = DriverManager.getConnection(jdbcURL);
				String sql = "Insert into item (name, weight) values ('" +txtName.getText()+ "', "+txtWeight.getText()+")";

				Statement statement = connection.createStatement(); 
				int rows = statement.executeUpdate(sql);
		

				String query = "SELECT * FROM item WHERE name = '" + txtName.getText() +"'"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
					for(Product p : products) {
						tableView.getItems().add(p); 
					}
				}

				String shutdown = "jdbc:derby:;shutdown=true";
				DriverManager.getConnection(shutdown); 
			} catch (SQLException ex) {
				if(ex.getSQLState().equals("XJ015")) {
					System.out.println("");
				} else {
					ex.printStackTrace();
				}
			}
			txtName.setText(null);
			txtWeight.setText(null); 
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
		
		/**
		 * ---------
		 */
		
		Label lblBoxCode = new Label("Box Code:");
		TextField txtBoxCode = new TextField();
		Label lblLength = new Label("Length:");
		TextField txtLength = new TextField(); 
		Label lblWidth = new Label("Width:");
		TextField txtWidth = new TextField(); 
		Label lblHeight = new Label("Hieght:");
		TextField txtHeight = new TextField(); 
		Button btnDimensions = new Button("Add Item"); 
		btnDimensions.setPrefWidth(210);

		TableView<BoxCode> tableBox = new TableView<>(); 
		TableColumn<BoxCode, String> boxName = new TableColumn<>("Box Code");
		TableColumn<BoxCode, Number> boxLength = new TableColumn<>("Length");
		TableColumn<BoxCode, Number> boxWidth = new TableColumn<>("Width");
		TableColumn<BoxCode, Number> boxHeight = new TableColumn<>("Height ");

		GridPane gridTwo = new GridPane(); 
		gridTwo.setAlignment(Pos.CENTER);
		gridTwo.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridTwo.setHgap(7);
		gridTwo.setVgap(3);
		
		gridTwo.add(lblBoxCode, 0, 0);
		gridTwo.add(txtBoxCode, 1, 0);
		gridTwo.add(lblLength, 0,1);
		gridTwo.add(txtLength, 1, 1); 
		gridTwo.add(lblWidth, 0, 2);
		gridTwo.add(txtWidth, 1, 2); 
		gridTwo.add(lblHeight, 0, 3);
		gridTwo.add(txtHeight, 1, 3);
		gridTwo.add(btnDimensions, 0, 6, 2, 3); 


		tableBox.getColumns().addAll(boxName, boxLength, boxWidth, boxHeight);
		gridTwo.add(tableBox, 7, 0, 1, 14);
		
		btnDimensions.disableProperty().bind(
				Bindings.isEmpty(txtBoxCode.textProperty()));
		btnDimensions.disableProperty().bind(
				Bindings.isEmpty(txtLength.textProperty()));
		btnDimensions.disableProperty().bind(
				Bindings.isEmpty(txtWidth.textProperty()));
		btnDimensions.disableProperty().bind(
				Bindings.isEmpty(txtHeight.textProperty()));
		
		btnDimensions.setOnAction(e -> {



			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Connection connection = DriverManager.getConnection(jdbcURL);
				String sql = "Insert into box (name, length, width, height) values ('" + txtBoxCode.getText()+ "', "+ txtLength.getText()+", " + txtWidth.getText()+", " + txtHeight.getText()+ ")";

				Statement statement = connection.createStatement(); 

				int rows = statement.executeUpdate(sql);
				
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



		boxName.setMinWidth(160);
		boxName.setReorderable(false);
		boxName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxCode, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		boxLength.setMinWidth(160);
		boxLength.setReorderable(false);
		boxLength.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().lengthProperty();
			}
		});


		boxWidth.setMinWidth(160);
		boxWidth.setReorderable(false);
		boxWidth.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().widthProperty();
			}		
		});

		boxHeight.setMinWidth(160);
		boxHeight.setReorderable(false);
		boxHeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxCode, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxCode, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().heightProperty();
			}
		});
		
		/**
		 * ----------------
		 */
		
		
		ComboBox<String> cbProducts = new ComboBox<>();
		ComboBox<String> cbBoxCode = new ComboBox<>();
		TableView<BoxType> tableLink = new TableView<>(); 
		TableColumn<BoxType, String> product = new TableColumn<>("Product");
		TableColumn<BoxType, String> boxtype = new TableColumn<>("Box Code");
		TableColumn<BoxType, Number> minimum = new TableColumn<>("Minimum");
		TableColumn<BoxType, Number> maximum = new TableColumn<>("Maximum");

		GridPane gridThree = new GridPane();
		gridThree.setAlignment(Pos.CENTER);
		gridThree.setPadding(new Insets(3, 12.5, 5, 14.5));
		gridThree.setHgap(7);
		gridThree.setVgap(3);

		tableLink.getColumns().addAll(product, boxtype, minimum, maximum);
		Label lblMinimum = new Label("Minimum:");
		TextField txtMinimum = new TextField();
		Label lblMaximum = new Label("Maximum:");
		TextField txtMaximum = new TextField();
		Button btnAssign = new Button("Assign"); 
		btnAssign.setPrefWidth(200);

		cbProducts.setMaxWidth(200);
		cbBoxCode.setMaxWidth(200);
		txtMinimum.setPrefWidth(80);
		txtMaximum.setPrefWidth(80);
		gridThree.add(cbProducts, 0, 0, 2,1);
		gridThree.add(cbBoxCode, 0, 2, 2, 1);
		gridThree.add(lblMinimum, 0, 3);
		gridThree.add(txtMinimum, 1, 3);
		gridThree.add(lblMaximum, 0, 4);
		gridThree.add(txtMaximum, 1, 4);
		gridThree.add(btnAssign, 0, 6, 2, 3);

		gridThree.add(tableLink, 7, 0, 1, 10); 
		ObservableList<String> ps = null;
		ObservableList<String> bc = null;
	
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
		
		btnAssign.disableProperty().bind(
				Bindings.isEmpty(txtMinimum.textProperty()));
		btnAssign.disableProperty().bind(
				Bindings.isEmpty(txtMaximum.textProperty()));
		
		btnAssign.setOnAction(e -> {
			try { 
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 

				String sql = "Insert into ItemBox (iname, bname, minimum, maximum) values ('" + cbProducts.getSelectionModel().getSelectedItem() + "', '"+ cbBoxCode.getSelectionModel().getSelectedItem()+"', " + txtMinimum.getText() + ", " + txtMaximum.getText() + ")";


				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}


				String query = "SELECT * FROM ItemBox WHERE iname = '" +  cbProducts.getSelectionModel().getSelectedItem() + "' and bname = '" + cbBoxCode.getSelectionModel().getSelectedItem() + "' and minimum = " + txtMinimum.getText() + " and maximum = " + txtMaximum.getText(); 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					bt = FXCollections.observableArrayList(new BoxType(Integer.valueOf(rs.getString("id")), rs.getString("iname"), rs.getString("bname"), Integer.valueOf(rs.getString("minimum")), Integer.valueOf(rs.getString("maximum"))));	
					for(BoxType b : bt) {
						tableLink.getItems().add(b); 
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
			
			txtMinimum.setText(null);
			txtMaximum.setText(null);
		});

		product.setMinWidth(160);
		product.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		boxtype.setMinWidth(160);
		boxtype.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxcodeProperty();
			}
		});


		minimum.setMinWidth(160);
		minimum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().minimumProperty();
			}		
		});

		maximum.setMinWidth(160);
		maximum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().maximumProperty();
			}
		});

		

		
		
		
		/**
		 * ----------------------
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
