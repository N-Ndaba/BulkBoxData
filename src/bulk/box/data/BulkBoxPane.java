package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import javafx.event.EventHandler;

public class BulkBoxPane extends StackPane { 
	private TextField txtSum = new TextField();
	private TextField txtFinalDimension = new TextField(); 
	private ObservableList<Product> products = null;
	
	 
	
	
	
	private String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	private MenuBar menuBar = null; 

	MenuItem miHome = new MenuItem("Home"); 
	MenuItem miAdd = new MenuItem("Add"); 
	MenuItem miEdit = new MenuItem("Edit"); 
	MenuItem miDelete = new MenuItem("Delete"); 

	public BulkBoxPane() {
		/*try {
			Connection connection = DriverManager.getConnection(jdbcURL);
			String sql = "Create Table item (id int not null generated always as identity,"
					+ "name varchar(255) not null, weight double not null)";
			Statement statement = connection.createStatement(); 
			statement.executeUpdate(sql);
			System.out.println(">");
		}catch(SQLException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		menuBar = new MenuBar(); 
		menuBar.setPrefHeight(30);
		//menuBar.setStyle("-fx-");

		Menu menu = new Menu("Menu");


		menu.setOnAction(e -> {
			System.out.println(">------------------<");
		});

		menuBar.getMenus().add(menu); 

		menu.getItems().add(miHome);
		menu.getItems().add(miAdd);
		menu.getItems().add(miEdit); 
		menu.getItems().add(miDelete); 


		setSide();
		miHome.setOnAction(e -> {
			getChildren().clear(); 
			/*products = FXCollections.observableArrayList( 
					new Product(1, "Flare", 0.322));*/
			setSide();
		});

		miAdd.setOnAction(e -> {
			getChildren().clear(); 
			addRecord();
		});

		miEdit.setOnAction(e -> {
			getChildren().clear(); 
			editRecord(); 
		});

		miDelete.setOnAction(e -> {
			getChildren().clear();
			deleteRecord();  
		});
	}

	@SuppressWarnings("unchecked")
	public void deleteRecord() {
		
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");
		
		Label lbllabel = new Label("Name:");
		TextField name = new TextField();
		GridPane grid = new GridPane(); 
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(3, 12.5, 5, 14.5));
		grid.setHgap(7);
		grid.setVgap(3);		
		grid.add(lbllabel, 0, 0);
		grid.add(name, 1, 0);	
		Button btn = new Button("Delete"); 
		btn.setPrefWidth(200);
		grid.add(btn, 0, 3, 2, 3);
		

		grid.add(tableView, 4, 0, 1, 10); 
		tableView.getColumns().addAll(productName, productWeight);

		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT * FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
				System.out.println("id|||>>????" + rs.getString("id"));
				System.out.println("name: " + rs.getString("name"));
				System.out.println("weight: " + rs.getString("weight"));

				
				products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
				for(Product p : products) {
					System.out.println("[ " + p.getWeight() + " ]");
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



		btn.setOnAction(e -> {

			try {
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 

				/*String query = "SELECT * FROM item"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					System.out.println("name: " + rs.getString("name"));
					System.out.println("weight: " + rs.getString("weight"));

					products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));
					for(Product p : products) {
						tableView.getItems().add(p); 
					}
				}*/


				String sql = "DELETE from item WHERE name = '" + name.getText() + "'"; 
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

			name.setText(null);
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




		VBox vbox = new VBox(); 
		vbox.getChildren().addAll(menuBar, grid); 

		getChildren().add(vbox);
	}

	@SuppressWarnings("unchecked")
	public void addRecord() {
		Label lbllabel = new Label("Name:");
		TextField name = new TextField();
		Label lblnumber = new Label("Weight:");
		TextField number = new TextField(); 
		Button btn = new Button("Add Item"); 
		
		btn.setPrefWidth(200);
		
		
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");

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

		grid.add(tableView, 4, 0, 1, 10); 
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

		getChildren().add(vbox);
	}

	
	@SuppressWarnings("unchecked")
	public void editRecord() {
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");
		
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

		tableView.setEditable(true);
		tableView.setPrefHeight(430);

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
		tableView.getColumns().addAll(productName, productWeight);
		grid.add(tableView, 5, 5, 5, 5); 
		
		VBox vbox = new VBox(); 
		vbox.getChildren().addAll(menuBar, grid); 
		getChildren().add(vbox);
	}


	@SuppressWarnings("unchecked")
	private void setSide() {
		
		
		
		GridPane sideGrid = new GridPane(); 
		sideGrid.setAlignment(Pos.TOP_LEFT);
		sideGrid.setPadding(new Insets(3, 12.5, 5, 14.5));
		sideGrid.setHgap(7);
		sideGrid.setVgap(3);

		
		TableView<Product> tableView = new TableView<>(); 
		TableColumn<Product, String> productName = new TableColumn<>("Product");
		TableColumn<Product, String> boxtype = new TableColumn<>("BoxType");
		TableColumn<Product, String> dimension = new TableColumn<>("Dimensions (L x W x H)");
		TableColumn<Product, Number> productWeight = new TableColumn<>("Weight (kg)");
		TableColumn<Product, Number> quantity = new TableColumn<Product, Number>("Quantity");
		
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

		VBox vbProduct = new VBox();
		VBox vbGrid = new VBox();

		BorderPane pane = new BorderPane(); 
		VBox paneForCheckBoxes = new VBox(20);
		paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));

		for(Product p : products) {
			CheckBox chProduct = new CheckBox(p.getName());
			chProduct.setFont(Font.font("Arial", 13));
			chProduct.setAlignment(Pos.CENTER);
			paneForCheckBoxes.getChildren().addAll(chProduct);

			EventHandler<ActionEvent> handler = e -> {
				if (chProduct.isSelected()) {
					tableView.getItems().add(p);
					calcSum(tableView, dimension, productWeight);
				} else	{
					tableView.getItems().remove(p);
					calcSum(tableView, dimension, productWeight);
				} 
			};
			chProduct.setOnAction(handler);
		}





		tableView.setEditable(true);
		tableView.setPrefHeight(430);
		// table column for the name of the person 

		productName.setMinWidth(160);
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		// column for the size of the person

		boxtype.setMinWidth(160);
		boxtype.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxtypeProperty();
			}
		});


		dimension.setMinWidth(160);
		dimension.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Product, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().dimensionProperty();
			}
		});

		// column for the size of the person

		productWeight.setMinWidth(160);
		productWeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().sumProperty();
			}
		});


		quantity.setMinWidth(160);
		quantity.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		quantity.setOnEditCommit(
				(CellEditEvent<Product, Number> n) ->{
					((Product) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setQuantity(n.getNewValue().intValue());

					calcSum(tableView, dimension, productWeight);
				});

		quantity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Product, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Product, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().quantityProperty();
			}
		});






		tableView.getColumns().addAll(productName,quantity, boxtype, dimension, productWeight);
		vbGrid.getChildren().addAll(tableView); 

		pane.setLeft(paneForCheckBoxes);

		vbProduct.getChildren().addAll(pane);
		vbProduct.setPrefHeight(330);

		ScrollPane scrollPane = new ScrollPane(vbProduct); 

		sideGrid.add(scrollPane, 0, 0, 2, 1); 
		//-----

		Label lblSum = new Label("Total Weight (kg):"); 
		lblSum.setPrefWidth(125);
		lblSum.setFont(Font.font("Arial", 13));
		sideGrid.add(lblSum, 0, 10);


		txtSum.setFont(Font.font("Arial", 13));
		txtSum.setAlignment(Pos.CENTER);
		txtSum.setId("txtS");
		sideGrid.add(txtSum, 1, 10);

		Label lblFinalDimension = new Label("Final Dimension:"); 
		lblFinalDimension.setPrefWidth(125);
		lblFinalDimension.setFont(Font.font("Arial", 13));
		sideGrid.add(lblFinalDimension, 0, 12);


		txtFinalDimension.setFont(Font.font("Arial", 13));
		txtFinalDimension.setAlignment(Pos.CENTER);
		txtFinalDimension.setId("txtFD");
		sideGrid.add(txtFinalDimension, 1, 12);



		//ScrollPane sp = new ScrollPane(Lu); 
		//???????/


		ColumnConstraints column = new ColumnConstraints(100, 100, 300);
		column.setHgrow(Priority.NEVER);
		column.setPercentWidth(40);
		column.setHalignment(HPos.LEFT);
		sideGrid.getColumnConstraints().add(column); 

		GridPane.setHalignment(lblFinalDimension, HPos.RIGHT);
		GridPane.setHalignment(txtFinalDimension, HPos.RIGHT);

		//Calc(vbGrid); 
		VBox layout = new VBox(); 


		HBox h = new HBox(); 
		h.setPadding(new Insets(13, 8, 12, 8));
		h.getChildren().addAll(sideGrid, vbGrid); 
		layout.getChildren().addAll(menuBar, h); 
		ScrollPane sp = new ScrollPane(layout); 
		getChildren().add(sp);
	}

	private void calcSum(TableView<Product> tableView, TableColumn<Product, String> dimension, TableColumn<Product, Number> productWeight) {
		double sum = 0; 
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			sum += productWeight.getCellData(i).doubleValue(); 
		}
		if(sum == 0) {
			txtSum.setText(""); 
		} else {
			txtSum.setText(String.valueOf(String.format("%.2f", sum)).replace(",", ".")); 
		}


		ArrayList<Integer>[] dimList = new ArrayList[3]; 

		for(int i = 0; i < 3 ; ++i) {
			dimList[i] =  new ArrayList<Integer>();
		}

		String dim = ""; 
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			if(!dimension.getCellData(i).equals("-")) {
				String[] tokens = dimension.getCellData(i).split("\\s"); 

				dimList[0].add(Integer.valueOf(tokens[0]));
				dimList[1].add(Integer.valueOf(tokens[2]));
				dimList[2].add(Integer.valueOf(tokens[4]));
				dim = Collections.max(dimList[0]) + " x " + Collections.max(dimList[1]) + " x " + Collections.max(dimList[2]);   
			} else if(dimension.getCellData(i).equals("-")) {
				txtFinalDimension.setText("-"); return; 
			}
		}
		txtFinalDimension.setText(dim);
	}	
}
