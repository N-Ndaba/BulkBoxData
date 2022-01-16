package bulk.box.data;

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

public class BulkBoxPane extends StackPane { 
	private double sum = 0;
	private TextField txtSum = new TextField();
	private TextField txtFinalDimension = new TextField(); 
	private ObservableList<Product> products = null;
	private ArrayList<Integer>[] dimList = new ArrayList[3]; 

	public BulkBoxPane() {
		products = FXCollections.observableArrayList( 
				new Product("Flare 6 pack", 1.9),
				new Product("Sir Beacon 60", 0.74),
				new Product("Sir Beacon / portable", 1.1), 
				new Product("Mono S", 6.6),
				new Product("Duplo s", 9.2),
				new Product("Flare", 0.322), 
				new Product("Flare 6 pack", 1.9),
				new Product("Sir Beacon 60", 0.74),
				new Product("Sir Beacon / portable", 1.1), 
				new Product("Mono S", 6.6),
				new Product("Duplo s", 9.2), 
				new Product("Flare 6 pack", 1.9),
				new Product("Sir Beacon 60", 0.74),
				new Product("Sir Beacon / portable", 1.1), 
				new Product("Mono S", 6.6),
				new Product("Duplo s", 9.2),
				new Product("Flare", 0.322), 
				new Product("Flare 6 pack", 1.9),
				new Product("Sir Beacon 60", 0.74),
				new Product("Sir Beacon / portable", 1.1), 
				new Product("Mono S", 6.6),
				new Product("Duplo s", 9.2)
				); 


		for(int i = 0; i < 3 ; ++i) {
			dimList[i] =  new ArrayList<Integer>();
		}


		setSide(); 
	}

	private void setSide() {
		GridPane sideGrid = new GridPane(); 
		sideGrid.setAlignment(Pos.TOP_LEFT);
		sideGrid.setPadding(new Insets(3, 12.5, 5, 14.5));
		sideGrid.setHgap(7);
		sideGrid.setVgap(3);


		VBox vbProduct = new VBox();
		VBox Lu = new VBox();



		/*TableView<Product> tableView = new TableView<Product>(products);

		tableView.setEditable(true);

		TableColumn<Product, String> nameColumn = new TableColumn<>("Name"); 
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

		TableColumn<Product, Number> quantityColumn = new TableColumn<>("Quantity"); 
		quantityColumn.setMinWidth(200);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		quantityColumn.setCellFactory(TextFieldTableCell.<Product>forTableColumn());
		/*quantityColumn.setOnEditCommit(
				(CellEditEvent<Product, String> t) -> {
					((Product) t.getTableView().getItems().get(
							t.getTablePosition().getRow())
							).setQuantity(t.getNewValue()); 

		});*/

		/*TableColumn<Product, String> dimensionColumn = new TableColumn<>("Dimension"); 
		dimensionColumn.setMinWidth(200);
		dimensionColumn.setCellValueFactory(new PropertyValueFactory<>("dimension"));

		TableColumn<Product, Double> weightColumn = new TableColumn<>("Weight"); 
		weightColumn.setMinWidth(200);
		weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));


		tableView.setItems(products);
		tableView.getColumns().addAll(nameColumn, quantityColumn, dimensionColumn, weightColumn);*/ 

		BorderPane pane = new BorderPane(); 
		VBox paneForCheckBoxes = new VBox(20);
		paneForCheckBoxes.setPadding(new Insets(5, 5, 5, 5));
		paneForCheckBoxes.setStyle("−fx−border−color: green");

		for(Product p : products) {
			CheckBox chProduct = new CheckBox(p.getName());
			paneForCheckBoxes.getChildren().addAll(chProduct);

			EventHandler<ActionEvent> handler = e -> {
				if (chProduct.isSelected()) {
					Lu.getChildren().add(createProductGrid(p));


				} else	{
					//-> pane.getChildren().remove(node)
					//-> pane.getChildren().removeAll(node)
					//Lu.getChildren().add(deleteProductGrid(p));
					deleteProductGrid(Lu, p); 
				} 
			};
			chProduct.setOnAction(handler);
		}

		pane.setLeft(paneForCheckBoxes);
		vbProduct.setStyle("−fx−border−color: red");

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

		HBox h = new HBox(); 
		h.setPadding(new Insets(13, 8, 12, 8));
		h.getChildren().addAll(sideGrid, Lu); 
		ScrollPane sp = new ScrollPane(h); 
		getChildren().add(sp);
	}

	private void deleteProductGrid(VBox v, Product p) {
		for(Node n : v.getChildren()) {
			for(Node i : ((GridPane) n).getChildren()) {
				System.out.println("[]" + i);
				if(i instanceof Label && ((Label) i).getText().equals(p.getName()+":")) {
					v.getChildren().remove(n); 
					//((TextField) i).getId().equals("D") == null;
				}
				
				/*if(i instanceof TextField) {
					((TextField) i).setText(null);
				}*/
				txtSum.setText(null);
				txtFinalDimension.setText(null);
			}
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
			if(e.getCode() != KeyCode.ENTER) {


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
			}
		}); 
	}

	private void calcTotalWeight(GridPane grid, boolean status) {
		for(Node node : grid.getChildren()) {
			if(node instanceof TextField &&  ((TextField) node).getId() == "O" && status == true) {
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
