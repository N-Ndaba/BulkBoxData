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
	private TextField txtSum  = new TextField();
	private TextField txtFD = new TextField(); 
	private ArrayList<Integer> Length = new ArrayList<Integer>(); 
	private ArrayList<Integer> Weight = new ArrayList<Integer>();
	private ArrayList<Integer> Height = new ArrayList<Integer>();
	private double Sum = 0.0; 
		
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
		
		
		
		/*grid.add(new Label("PRODUCTS:"), 0, 0);
		grid.add(new Label("QUANTITY:"), 1, 0);
		grid.add(new Label("BOX:"), 2, 0);
		grid.add(new Label("DIMENSIONS (L x W x H):"), 3, 0);
		grid.add(new Label("TOTAL WEIGHT (g | kg):"), 4, 0);*/
		
		VBox vbProduct = new VBox();
		for(Product p : FileHandler.listProduct)
		{
			vbProduct.getChildren().add(createProductGrid(p)); 
		}
	
		txtSum.setEditable(false);
		txtSum.setAlignment(Pos.CENTER);
		txtSum.setFont(Font.font("Cambria", 13));
		grid.add(txtSum , 10, 1);
		
		
		
		txtFD.setEditable(false);
		txtFD.setAlignment(Pos.CENTER);
		txtFD.setFont(Font.font("Cambria", 13));
		grid.add(txtFD, 13, 1);
		
		vbProduct.getChildren().add(grid);
		ScrollPane sp = new ScrollPane(); 
		sp.setContent(vbProduct); 
		
		
		
		getChildren().addAll(sp);
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
		txtDimensions.setId("D");
		grid.add(txtDimensions, 3, 1);
		
		TextField txtTotalWeight  = new TextField(); 
		txtTotalWeight.setEditable(false);
		txtTotalWeight.setId("O");
		txtTotalWeight.setAlignment(Pos.CENTER);
		txtTotalWeight.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalWeight , 4, 1);
		
		
		
		setOnKey(txtQuantity, txtBox, txtDimensions, txtTotalWeight, product.getName(), product.getWeight(), product.getMeasurement(), grid);
		
		return grid;
	} 
	
	private void setOnKey(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight,  String strProductName, double dblWeight, String dblMeasurement, GridPane grid)
	{
		txtQuantity.setOnKeyTyped(e -> 
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
					 txtTotalWeight.setText(String.valueOf(String.format("%.2f", Double.valueOf(txtQuantity.getText()) * dblWeight).replace(",", "."))); 	
					 
					 
					 Sum += Double.parseDouble(txtTotalWeight.getText());
					 
					 for(Node node : grid.getChildren())
					 {
						/*if(node instanceof TextField && ((TextField) node).getId() == "O")
						{
							Sum += Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
						}*/
						
						if(node instanceof TextField && ((TextField) node).getId() == "D")
						{
							if(!txtDimensions.getText().equals("-"))
							 {
								 String[] tokens = txtDimensions.getText().split("\s");
							
								
								
								Length.add(Integer.valueOf(tokens[0])); 
								Weight.add(Integer.valueOf(tokens[2]));
								Height.add(Integer.valueOf(tokens[4]));
							
							 }
							/*else 
							{
								Length.clear(); 
								Weight.clear();
								Height.clear();
							}*/
						}
					 } 
				 }
				 else
				 {		
					 Sum -= Double.parseDouble(txtTotalWeight.getText());
					 
					 for(Node node : grid.getChildren())
					 {
						if(node instanceof TextField &&  ((TextField) node).getId() == "O")
						{
							// Sum -= Double.valueOf(txtTotalWeight.getText().replace(",", ".")); 
							//Sum -= Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
						}
					 }

					 txtBoxType.clear();
					 txtDimensions.clear(); 
					 txtTotalWeight.clear(); 
					 
					 txtBoxType.setText("-");
					 txtDimensions.setText("-");
					 txtTotalWeight.setText("0.0");
					  
				 }
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
				
				//System.out.println(Collections.max(Length) + " x " + Collections.max(Weight) + " x " + Collections.max(Height));

			 txtSum.setText(String.valueOf(String.format("%.2f", Sum)).replace(",", "."));
			 printFD(grid); 
		}); 
		
		
	}
	
	
	public void printFD(GridPane grid)
	{
		 for(Node node : grid.getChildren())
		 {
			if(node instanceof TextField &&  ((TextField) node).getId() == "D")
			{		
				if(((TextField) node).getText().equals("-"))
				{
					 txtFD.setText("-"); 
				}
				else if(!((TextField) node).getText().equals("-"))
				{
					String[] tokens = ((TextField) node).getText().split("\s");	
					Length.add(Integer.valueOf(tokens[0])); 
					Weight.add(Integer.valueOf(tokens[2]));
					Height.add(Integer.valueOf(tokens[4]));
					txtFD.setText(Collections.max(Length) + " x " + Collections.max(Weight) + " x " + Collections.max(Height));
				}
			}
		 }	 
	}
}
