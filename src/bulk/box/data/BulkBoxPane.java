package bulk.box.data;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class BulkBoxPane extends StackPane
{
	private Product product = null;

		
	public BulkBoxPane()
	{	
		setProduct(FileHandler.readProduct(new File("data/bulkboxdata.txt"))); 
	}
	
	public void setProduct(Product product) {
		this.product = product;

		createChildren(); 
	}
	
	public void createChildren() 
	{
		GridPane grid = new GridPane();
		/*grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		grid.setHgap(7);
		grid.setVgap(5.5);*/
		
		
		
		grid.add(new Label("PRODUCTS:"), 0, 0);
		grid.add(new Label("QUANTITY:"), 1, 0);
		grid.add(new Label("BOX:"), 2, 0);
		grid.add(new Label("DIMENSIONS (L x W x H):"), 3, 0);
		grid.add(new Label("TOTAL WEIGHT (g | kg):"), 4, 0);
		
		VBox vbProduct = new VBox();
		for(Product p : FileHandler.listProduct)
		{
			vbProduct.getChildren().add(createProductGrid(p)); 
		}
		
		
		/*grid.add(new Label("TOTAL DIMENSIONS:"), 0, 0);
		
		TextField txtTotalDimensions = new TextField(); 
		txtTotalDimensions.setEditable(false);
		txtTotalDimensions.setAlignment(Pos.CENTER);
		txtTotalDimensions.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalDimensions, 2, 1);
		
		grid.add(new Label("TOTAL WEIGHT:"), 5, 0);
		TextField txtTotalWeight = new TextField(); 
		txtTotalWeight.setEditable(false);
		txtTotalWeight.setAlignment(Pos.CENTER);
		txtTotalWeight.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalWeight, 2, 1);*/
		
		
		vbProduct.getChildren().add(grid);
		ScrollPane sp = new ScrollPane(); 
		sp.setContent(vbProduct); 
		
		
		
		getChildren().addAll(grid, sp);
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
		lblName.setText(product.getName()+ ":");
		grid.add(lblName, 0, 1);
		
		TextField txtQuantity = new TextField(); 
		txtQuantity.setFont(Font.font("Cambria", 13));
		txtQuantity.setAlignment(Pos.CENTER);
		grid.add(txtQuantity, 1, 1);
		
		
		TextField txtBox = new TextField(); 
		txtBox.setEditable(false);
		txtBox.setAlignment(Pos.CENTER);
		txtBox.setFont(Font.font("Cambria", 13));
		grid.add(txtBox, 2, 1);
		
		TextField txtDimensions  = new TextField(); 
		txtDimensions.setEditable(false);
		txtDimensions.setAlignment(Pos.CENTER);
		txtDimensions.setFont(Font.font("Cambria", 13));
		grid.add(txtDimensions, 3, 1);
		
		TextField txtTotalWeight  = new TextField(); 
		txtTotalWeight.setEditable(false);
		txtTotalWeight.setId("O");
		txtTotalWeight.setAlignment(Pos.CENTER);
		txtTotalWeight.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalWeight , 4, 1);
		
		TextField txtSum  = new TextField(); 
		txtSum.setEditable(false);
		txtSum.setAlignment(Pos.CENTER);
		txtSum.setFont(Font.font("Cambria", 13));
		grid.add(txtSum , 4, 5);
		
		setOnKey(txtQuantity, txtBox, txtDimensions, txtTotalWeight, product.getName(), product.getWeight(), product.getMeasurement(), grid, txtSum);
		
		return grid;
	} 
	
	private double Sum = 0; 
	private void setOnKey(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight,  String strProductName, double dblWeight, String dblMeasurement, GridPane grid, TextField txtSum)
	{
		txtQuantity.setOnKeyReleased(e -> 
		{
			Product products = null;
			Box box = new Box(); 
			
			 try
			 {
				 if(!txtQuantity.getText().isEmpty())
				 {
					 products = new Product(strProductName, Integer.valueOf(txtQuantity.getText()));
				 
					 txtBoxType.setText(products.process());
					 txtDimensions.setText(box.getBox(products.process())); 
					// txtTotalWeight.setText(String.format("%.2f", Double.valueOf(txtQuantity.getText()) * dblWeight)); 	
					 txtTotalWeight.setText(String.valueOf(Double.valueOf(txtQuantity.getText()) * dblWeight)); 	
					 
					 
					 /***********/
					// sumList(Double.valueOf(Double.valueOf(txtQuantity.getText()) * dblWeight));
					 
					/************/
					 
						
					/// fd(txtDimensions.getText()); 
					// txtSum.setText(String.valueOf(list));
					 
				//	 grid.
					 
					 for(Node node : grid.getChildren())
					 {
						 if(node instanceof TextField && ((TextField) node).getId() == "O" && ((TextField) node).getText() != "-")
						{
							 Sum += Double.valueOf(((TextField) node).getText()).doubleValue(); 
						}
					 }
					 txtSum.setText(String.valueOf(Sum));
					 
				 }
				 else
				 {

					 for(Node node : grid.getChildren())
					 {
						if(node instanceof TextField && ((TextField) node).getId() == "O" && ((TextField) node).getText() != "-")
						{
							 Sum -= Double.valueOf(txtTotalWeight.getText()); 
						}
					 }
					 txtSum.setText(String.valueOf(Sum));
					 
					// decrementTotal(grid, Double.valueOf(txtTotalWeight.getText()), );
					 
					 txtBoxType.clear();
					 txtDimensions.clear(); 
					 txtTotalWeight.clear(); 
					 
					 txtBoxType.setText("-");
					 txtDimensions.setText("-");
					 txtTotalWeight.setText("-");
					  
				 }
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
		}); 
	}
	
	public void decrementTotal(GridPane grid, double p)
	{
		for(Node node : grid.getChildren())
		 {
			if(node instanceof TextField && ((TextField) node).getId() == "O" && ((TextField) node).getText() != "-")
			{
				 Sum -= p; 
			}
		 }
		 System.out.println("Sum: " + Sum);
	}
	
	private ArrayList<Double> list = new ArrayList<Double>(); 
	
	
	public void sumList(double listDouble)
	{
		list.add(listDouble); 
		
		double Sum = 0; 
		for(Double d : list)
		{
			Sum += d;
		}
		System.out.print("=");
		System.out.printf("%.2f", Sum);
		System.out.println();
	}
	
	public void fd(String s)
	{
		ArrayList<Integer> Length = new ArrayList<Integer>(); 
		ArrayList<Integer> Weight = new ArrayList<Integer>();
		ArrayList<Integer> Height = new ArrayList<Integer>();
		
		if(!s.equals("-"))
		 {
			 String[] tokens = s.split("\s");
		
			
			
			Length.add(Integer.valueOf(tokens[0])); 
			Weight.add(Integer.valueOf(tokens[2]));
			Height.add(Integer.valueOf(tokens[4]));
			
			System.out.println(Collections.max(Length) + " x " + Collections.max(Weight) + " x " + Collections.max(Height));

		 }
		else 
		{
			Length.clear(); 
			Weight.clear();
			Height.clear();
		}
	}
	
	public void finalDimension(int L, int W, int H)
	{
		ArrayList<Integer> Length = new ArrayList<Integer>(); 
		ArrayList<Integer> Weight = new ArrayList<Integer>();
		ArrayList<Integer> Height = new ArrayList<Integer>();
		
		Length.add(L); 
		Weight.add(W);
		Height.add(H);
		
		System.out.println(Collections.max(Length) + " x " + Collections.max(Weight) + " x " + Collections.max(Height));
	}
}
