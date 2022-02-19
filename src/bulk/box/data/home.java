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
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;

public class Home {

	private static TextField txtSum = new TextField();
	private static TextField txtFinalDimension = new TextField(); 
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";
	

	public static ScrollPane home(MenuBar menuBar) {



		GridPane sideGrid = new GridPane(); 
		sideGrid.setAlignment(Pos.TOP_LEFT);
		sideGrid.setPadding(new Insets(3, 12.5, 5, 14.5));
		sideGrid.setHgap(7);
		sideGrid.setVgap(3);

		VBox vbProduct = new VBox();
		VBox vbGrid = new VBox();

		BorderPane pane = new BorderPane(); 
		VBox paneForCheckBoxes = new VBox(20);
		paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));


		ObservableList<Product> products = null;
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
				products = FXCollections.observableArrayList(new Product(Integer.valueOf(rs.getString("id")), rs.getString("name"), Double.valueOf(rs.getString("weight"))));

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
		return sp;
	}

	private static void calcSum(TableView<Product> tableView, TableColumn<Product, String> dimension, TableColumn<Product, Number> productWeight) {
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
