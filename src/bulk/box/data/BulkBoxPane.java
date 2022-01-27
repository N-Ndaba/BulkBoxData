package bulk.box.data;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback; 

public class BulkBoxPane extends StackPane { 
	private double sum = 0;
	private TextField txtSum = new TextField();
	private TextField txtFinalDimension = new TextField(); 
	private ObservableList<Product> products = null;
	private ObservableList<Win> win = null;
	private ArrayList<Integer>[] dimList = new ArrayList[3]; 
	
	TableView<Win> tableView = new TableView<>(); 
	TableColumn<Win, String> productName = new TableColumn<>("Product");
	TableColumn<Win, String> boxtype = new TableColumn<>("BoxType");
	TableColumn<Win, Number> productWeight = new TableColumn<>("Weight");
	TableColumn<Win, Integer> quantity = new TableColumn<Win, Integer>("Quantity"); 

	public BulkBoxPane() {
		products = FXCollections.observableArrayList( 
				new Product("Flare", 0.322),
				new Product("Flare 6 pack", 1.9),
				new Product("Sir Beacon 60", 0.74),
				new Product("Sir Beacon / portable", 1.1), 
				new Product("Mono S", 1.66),
				new Product("Duplo s", 2.9),
				new Product("Mono/ Duplo Combo", 3), 
				new Product("1500S", 6.6),
				new Product("1500S Vert", 6.6),
				new Product("2D", 8.2), 
				new Product("Hooter", 1.1),
				new Product("Bell", 0.42), 
				new Product("Ban Ex S1/S2", 13),
				new Product("Ban EX Combo S1/S2", 16),
				new Product("Ban EX S3", 10), 
				new Product("Ban Ex S3 Light", 5),
				new Product("660HZ", 2.4),
				new Product("370HZ", 2.8), 
				new Product("Blaster 300ml", 0.5),
				new Product("Blaster 40/100/135mll", 0.32),
				new Product("A100", 0.37), 
				new Product("A105", 1),
				new Product("A112", 2.1),
				new Product("A121", 2.7),
				new Product("AL100", 570), 
				new Product("AL105", 1.2),
				new Product("AL112", 2.3),
				new Product("AL121", 2.9), 
				new Product("B300", 1),
				new Product("B400", 1.2),
				new Product("H100", 1.2), 
				new Product("H150", 5.1),
				new Product("H200", 12),
				new Product("500SA", 1.1),
				new Product("1000SA", 1.2), 
				new Product("MA112", 3),
				new Product("MA121", 3),
				new Product("3LF", 22), 
				new Product("5S", 50),
				new Product("10s", 68),
				new Product("15D", 118)
				); 

		win = FXCollections.observableArrayList( 
				new Win("Flare", 0.322),
				new Win("Flare 6 pack", 1.9),
				new Win("Sir Beacon 60", 0.74),
				new Win("Sir Beacon / portable", 1.1), 
				new Win("Mono S", 1.66),
				new Win("Duplo s", 2.9),
				new Win("Mono/ Duplo Combo", 3), 
				new Win("1500S", 6.6),
				new Win("1500S Vert", 6.6),
				new Win("2D", 8.2), 
				new Win("Hooter", 1.1),
				new Win("Bell", 0.42), 
				new Win("Ban Ex S1/S2", 13),
				new Win("Ban EX Combo S1/S2", 16),
				new Win("Ban EX S3", 10), 
				new Win("Ban Ex S3 Light", 5),
				new Win("660HZ", 2.4),
				new Win("370HZ", 2.8), 
				new Win("Blaster 300ml", 0.5),
				new Win("Blaster 40/100/135mll", 0.32),
				new Win("A100", 0.37), 
				new Win("A105", 1),
				new Win("A112", 2.1),
				new Win("A121", 2.7),
				new Win("AL100", 570), 
				new Win("AL105", 1.2),
				new Win("AL112", 2.3),
				new Win("AL121", 2.9), 
				new Win("B300", 1),
				new Win("B400", 1.2),
				new Win("H100", 1.2), 
				new Win("H150", 5.1),
				new Win("H200", 12),
				new Win("500SA", 1.1),
				new Win("1000SA", 1.2), 
				new Win("MA112", 3),
				new Win("MA121", 3),
				new Win("3LF", 22), 
				new Win("5S", 50),
				new Win("10s", 68),
				new Win("15D", 118)
				);

		for(int i = 0; i < 3 ; ++i) {
			dimList[i] =  new ArrayList<Integer>();
		}


		setSide(); 
	}

	@SuppressWarnings("unchecked")
	private void setSide() {
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
		paneForCheckBoxes.setStyle("−fx−border−color: green");



		
		for(Win p : win) {
			CheckBox chProduct = new CheckBox(p.getName());
			paneForCheckBoxes.getChildren().addAll(chProduct);

			EventHandler<ActionEvent> handler = e -> {
				if (chProduct.isSelected()) {
					tableView.getItems().add(p);
					ShataWale();
				} else	{
					tableView.getItems().remove(p);
					ShataWale();
				} 
			};
			chProduct.setOnAction(handler);
		}
		
		
		tableView.setEditable(true);
		// table column for the name of the person 
	
		productName.setMinWidth(200);
		productName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Win, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Win, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		// column for the size of the person
	
		boxtype.setMinWidth(200);
		boxtype.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Win, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<Win, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxtypeProperty();
			}
		});

		// column for the size of the person
	
		productWeight.setMinWidth(200);
		productWeight.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Win, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<Win, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().weightProperty();
			}
		});
				
		
		quantity.setMinWidth(200);
		quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));		
		quantity.setOnEditCommit(
			
				(CellEditEvent<Win, Integer> n) ->{
					((Win) n.getTableView().getItems().get(
							n.getTablePosition().getRow())
							).setQuantity(n.getNewValue());
					
				ShataWale(); 
		});

		



		
		
		tableView.getColumns().addAll(productName,quantity, boxtype, productWeight);
		vbGrid.getChildren().addAll(tableView); 

		pane.setLeft(paneForCheckBoxes);

		vbProduct.getChildren().addAll(pane);
		vbProduct.setPrefHeight(420);

		ScrollPane scrollPane = new ScrollPane(vbProduct); 

		sideGrid.add(scrollPane, 0, 0, 2, 1); 
		//-----

		Label lblSum = new Label("Total Weight:"); 
		lblSum.setPrefWidth(125);
		lblSum.setFont(Font.font("Cambria", 13));
		sideGrid.add(lblSum, 0, 10);


		txtSum.setFont(Font.font("Cambria", 13));
		txtSum.setAlignment(Pos.CENTER);
		txtSum.setId("txtS");
		sideGrid.add(txtSum, 1, 10);

		Label lblFinalDimension = new Label("Final Dimension:"); 
		lblFinalDimension.setPrefWidth(125);
		lblFinalDimension.setFont(Font.font("Cambria", 13));
		sideGrid.add(lblFinalDimension, 0, 12);


		txtFinalDimension.setFont(Font.font("Cambria", 13));
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

		HBox h = new HBox(); 
		h.setPadding(new Insets(13, 8, 12, 8));
		h.getChildren().addAll(sideGrid, vbGrid); 
		ScrollPane sp = new ScrollPane(h); 
		getChildren().add(sp);
	}

	private void ShataWale() {
		double sum = 0; 
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			sum += productWeight.getCellData(i).doubleValue(); 
		}
		txtSum.setText(String.valueOf(String.format("%.3f", sum)).replace(",", ".")); 
	}
	private void Calc(VBox v, Product product) {
		TextField qualtity = new TextField(); 
		TextField boxtype = new TextField(); 
		TextField dimension = new TextField(); 
		TextField weight = new TextField(); 
		GridPane grid = new GridPane(); 


		//		grid = (GridPane) v.getChildren(); 
		//		System.out.println("->" + grid);

		for(Node vbox : v.getChildren()) {
			for(Node node : ((GridPane) vbox).getChildren()) {
				if(node instanceof TextField && ((TextField) node).getId().equals("Q")) {
					qualtity = ((TextField) node); 
				}

				if(node instanceof TextField && ((TextField) node).getId().equals("B")) {
					boxtype = ((TextField) node); 
				}

				if(node instanceof TextField && ((TextField) node).getId().equals("D")) {
					dimension = ((TextField) node); 
				}

				if(node instanceof TextField && ((TextField) node).getId().equals("O")) {
					weight = ((TextField) node); 
				}


				grid = (GridPane) v.getChildren(); 


				setOnKeyCalc(qualtity, boxtype, dimension, weight, product.getName(), product.getWeight(), grid); 
			}
		}		
		//	grid =  (GridPane) v.getChildren();
	}

	private void setOnKeyCalc(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight, String strProductName, double dblWeight, GridPane grid)
	{	
		txtQuantity.setOnKeyReleased(e -> 
		{

			Product products = null;
			Box box = new Box(); 

			try
			{
				if(!txtQuantity.getText().isEmpty()) {

					products = new Product(strProductName, Integer.valueOf(txtQuantity.getText()));
					txtBoxType.setText(products.process());
					txtDimensions.setText(box.getBox(products.process()));  	
					txtTotalWeight.setText(String.valueOf(String.format("%.2f", Double.valueOf(txtQuantity.getText()) * dblWeight).replace(",", "."))); 	
					calcTotalWeight(grid, true);
					calcDimension(grid, true);


				} else {
					/*calcTotalWeight(grid, false);
						calcDimension(grid, false); 
						txtBoxType.setText(null);
						txtDimensions.setText(null);
						txtTotalWeight.setText(null); */
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();  
			}
		}); 
	}

	private void deleteProductGrid(VBox v, Product p) {
		try {
			for(Node n : v.getChildren()) {
				for(Node i : ((GridPane) n).getChildren()) {
					if(i instanceof Label && ((Label) i).getText().equals(p.getName()+":")) {
						v.getChildren().remove(n); 

						if(((TextField) i).getId().equals("O")) {
							sum -= Double.valueOf(((TextField) i).getText());
						}
					}
					System.out.println(i);
				}
			}
		}catch(Exception e) {
		}
	}

	public GridPane createProductGrid(Product product) 
	{
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(3, 12.5, 5, 14.5));
		grid.setHgap(7);
		grid.setVgap(3);

		Label lblName = new Label(); 
		lblName.setPrefWidth(125);
		lblName.setFont(Font.font("Cambria", 13));
		lblName.setText(product.getName() + ":");
		grid.add(lblName, 0, 1);

		TextField txtQuantity = new TextField(); 
		txtQuantity.setFont(Font.font("Cambria", 13));
		txtQuantity.setAlignment(Pos.CENTER);
		txtQuantity.setId("Q");
		grid.add(txtQuantity, 1, 1);


		TextField txtBox = new TextField(); 
		txtBox.setEditable(false);
		txtBox.setAlignment(Pos.CENTER);
		txtBox.setFont(Font.font("Cambria", 13));
		txtBox.setId("B");
		grid.add(txtBox, 2, 1);

		TextField txtDimensions  = new TextField(); 
		txtDimensions.setEditable(false);
		txtDimensions.setAlignment(Pos.CENTER);
		txtDimensions.setFont(Font.font("Cambria", 13));
		txtDimensions.setId("D");
		grid.add(txtDimensions, 3, 1);

		TextField txtTotalWeight  = new TextField(); 
		txtTotalWeight.setEditable(false);
		txtTotalWeight.setId("O");
		txtTotalWeight.setAlignment(Pos.CENTER);
		txtTotalWeight.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalWeight , 4, 1);		

		setOnKey(txtQuantity, txtBox, txtDimensions, txtTotalWeight, product.getName(), product.getWeight(), grid);

		return  grid;
	} 

	private void setOnKey(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight,  String strProductName, double dblWeight, GridPane grid)
	{	 
		txtQuantity.setOnKeyReleased(e -> 
		{
			Product products = null;
			Box box = new Box(); 

			try
			{
				if(!txtQuantity.getText().isEmpty()) {

					products = new Product(strProductName, Integer.valueOf(txtQuantity.getText()));
					txtBoxType.setText(products.process());
					txtDimensions.setText(box.getBox(products.process()));  	
					txtTotalWeight.setText(String.valueOf(String.format("%.2f", Double.valueOf(txtQuantity.getText()) * dblWeight).replace(",", "."))); 	

					calcTotalWeight(grid, true);
					calcDimension(grid, true); 


				} else {
					calcTotalWeight(grid, false);
					calcDimension(grid, false); 
					txtBoxType.setText(null);
					txtDimensions.setText(null);
					txtTotalWeight.setText(null); 
				}
			}
			catch(Exception ex)
			{
				ex.printStackTrace();  
			}										
		}); 
	}

	private void calcTotalWeight(GridPane grid, boolean status) {
		for(Node node : grid.getChildren()) {
			if(node instanceof TextField && ((TextField) node).getId() == "O" && status == true) {
				sum += Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
			}

			if(node instanceof TextField && ((TextField) node).getId() == "O" && status == false) {
				sum -= Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
			}
		}
		/**
		 	target[txtSum].bind(grid.TextValue);
			txtSum.textProperty().bind(null);
		 */

		if(sum <= 0) {
			sum = 0;
			txtSum.setText(null);
			return; 
		}

		txtSum.setText(String.valueOf(String.format("%.2f", sum)).replace(",", "."));
	}



	private void calcDimension(GridPane grid, boolean status) {
		String dim = ""; 
		for(Node node : grid.getChildren()) {
			if(node instanceof TextField &&  ((TextField) node).getId() == "D")	{

				if(!((TextField)node).getText().contains("-") && status == true) {
					String[] tokens = ((TextField) node).getText().split("\s");
					dimList[0].add(Integer.valueOf(tokens[0]));
					dimList[1].add(Integer.valueOf(tokens[2]));
					dimList[2].add(Integer.valueOf(tokens[4]));

					dim = Collections.max(dimList[0]) + " x " + Collections.max(dimList[1]) + " x " + Collections.max(dimList[2]);   
				} else if (!((TextField)node).getText().contains("-") && status == false) {
					String[] tokens = ((TextField) node).getText().split("\s");
					dimList[0].remove(Integer.valueOf(tokens[0]));
					dimList[1].remove(Integer.valueOf(tokens[2]));
					dimList[2].remove(Integer.valueOf(tokens[4]));

					if(!dimList[0].isEmpty() && !dimList[1].isEmpty() && !dimList[2].isEmpty()) {
						dim = Collections.max(dimList[0]) + " x " + Collections.max(dimList[1]) + " x " + Collections.max(dimList[2]);   
					} else {
						txtFinalDimension.setText(null); return; 
					}
				}
				else {
					txtFinalDimension.setText("-"); return; 
				}
			}
		}

		txtFinalDimension.setText(dim);
	}
}
