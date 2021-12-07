package bulk.box.data;

import java.io.File;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
		
		HBox hbProduct = new HBox();
		
		grid.add(new Label("PRODUCTS:"), 0, 0);
		grid.add(new Label("QUANTITY:"), 1, 0);
		grid.add(new Label("BOX:"), 2, 0);
		grid.add(new Label("DIMENSIONS (L x W x H):"), 3, 0);
		grid.add(new Label("TOTAL WEIGHT (g | kg):"), 4, 0);
		
		hbProduct.setSpacing(10);
		hbProduct.getChildren().addAll(grid); 
		
	
		
	
		
		VBox vbProduct = new VBox();
		for(Product p : FileHandler.listProduct)
		{
			vbProduct.getChildren().add(createProductGrid(p)); 
		}
		
		
		
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
		grid.add(txtDimensions, 3, 1);
		
		TextField txtTotalWeight  = new TextField(); 
		txtTotalWeight.setEditable(false);
		txtTotalWeight.setAlignment(Pos.CENTER);
		txtTotalWeight.setFont(Font.font("Cambria", 13));
		grid.add(txtTotalWeight , 4, 1);
		 
		setOnKey(txtQuantity, txtBox, txtDimensions, txtTotalWeight, product.getName(), product.getWeight(), product.getMeasurement());
		 	
		
		return grid;
	}
	
	private void setOnKey(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight,  String strProductName, double dblWeight, String dblMeasurement)
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
					 txtTotalWeight.setText(String.format("%.2f", Integer.valueOf(txtQuantity.getText()) * dblWeight) + dblMeasurement); 	
				 }	
				 else
				 {
					 txtBoxType.clear();
					 txtDimensions.clear(); 
					 txtTotalWeight.clear(); 
					 
					 txtBoxType.setText("\t\t-");
					 txtDimensions.setText("\t\t-");
					 txtTotalWeight.setText("\t\t-");
				 }
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
		}); 
	}
}
