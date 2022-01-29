package bulk.box.data;

import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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


	TableView<Product> tableView = new TableView<>(); 
	TableColumn<Product, String> productName = new TableColumn<>("Product");
	TableColumn<Product, String> boxtype = new TableColumn<>("BoxType");
	TableColumn<Product, String> dimension = new TableColumn<>("Dimensions");
	TableColumn<Product, Number> productWeight = new TableColumn<>("Weight");
	TableColumn<Product, Number> quantity = new TableColumn<Product, Number>("Quantity"); 

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
				new Product("3LF", 22000), 
				new Product("5S", 50000),
				new Product("10s", 68000),
				new Product("15D", 188000)
				);

	
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




		for(Product p : products) {
			CheckBox chProduct = new CheckBox(p.getName());
			paneForCheckBoxes.getChildren().addAll(chProduct);

			EventHandler<ActionEvent> handler = e -> {
				if (chProduct.isSelected()) {
					tableView.getItems().add(p);
					calcSum();
				} else	{
					tableView.getItems().remove(p);
					calcSum();
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

			calcSum();
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

	private void calcSum() {
		double sum = 0; 
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			sum += productWeight.getCellData(i).doubleValue(); 
		}
		txtSum.setText(String.valueOf(String.format("%.3f", sum)).replace(",", ".")); 

		ArrayList<Integer>[] dimList = new ArrayList[3]; 
		
		for(int i = 0; i < 3 ; ++i) {
			dimList[i] =  new ArrayList<Integer>();
		}
		
		String dim = ""; 
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			if(!dimension.getCellData(i).equals("-")) {
				String[] tokens = dimension.getCellData(i).split("\s"); 
			
				dimList[0].add(Integer.valueOf(tokens[0]));
				dimList[1].add(Integer.valueOf(tokens[2]));
				dimList[2].add(Integer.valueOf(tokens[4]));
				dim = Collections.max(dimList[0]) + " x " + Collections.max(dimList[1]) + " x " + Collections.max(dimList[2]);   
			}
		}
		
		for(int i = 0; i < tableView.getItems().size(); ++i) {
			if(dimension.getCellData(i).equals("-")) {
				txtFinalDimension.setText("-");
			}
		}
		
		txtFinalDimension.setText(dim);
	}
}
