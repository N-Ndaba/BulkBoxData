import java.io.IOException;
import java.util.Scanner;

import bbd.BulkBoxPane;
import bbd.FileHandler;
import bbd.Products;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Main extends Application
{
	private BulkBoxPane pane = null;
	
	public static void main(String[] args) 
	{
		launch(args);
		/*FileHandler fileHandler = new FileHandler(); 
		
		
		
		
		
		Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();
        int inputQuantity = scanner.nextInt();
        Products products = new Products(inputName, inputQuantity); 
        products.process();/*
       // System.out.println("Box Name: " + inputName + " & Quantity: " + inputQuantity);
		
		/*try {
			fileHandler.fileHandler("eKSE", 1, 2, 3);
			fileHandler.fileHandler("Molo", 21, 22, 3);
			//System.out.println("Hallo");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			fileHandler.readBoxData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        //scanner.close();
	}

	@Override
	public void start(Stage arg0) throws Exception 
	{
		arg0.setTitle("Bulk Box Data");

		//pane = new BulkBoxPane();
		
		 GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(5.5);
		 pane.setVgap(5.5);
		
		 // Place nodes in the pane
		 pane.add(new Label("Product:"), 0, 0);
		 pane.add(new Label("Quantity:"), 1, 0);
		 pane.add(new Label("BoxType:"), 2, 0);

		 
		 pane.add(new Label("Flare:"), 0, 1);
		 pane.add(new TextField(), 1, 1);
		 TextField flare = new TextField(); 
		 pane.add(flare, 1, 1);
		 TextField flareBox = new TextField(); 
		 pane.add(flareBox, 2, 1);
		 
		pane.add(new Label("Flare 6 Pack:"), 0, 2);
		 pane.add(new TextField(), 1, 2);
		 TextField flareSix = new TextField(); 
		 pane.add(flareSix, 1, 2);
		 TextField flareSixBox= new TextField(); 
		 pane.add(flareSixBox, 2, 2);
		 
			pane.add(new Label("Total Items:"), 0, 5);
			 pane.add(new TextField(), 1, 5);
			 TextField TotalItems = new TextField(); 
			 pane.add(TotalItems, 1, 5);
			 TextField TotalItemsBox = new TextField(); 
			 pane.add(TotalItemsBox, 2, 5);
		 
	
		 
			 TotalItems.setOnKeyReleased(e -> 
			 {
				 
			 });
		 
		 flare.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 products = new Products("Flare", Integer.valueOf(flare.getText())); 
				 //products = new Products("Flare 6 Pack", Integer.valueOf(flareSix.getText())); 
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 flareBox.setText(products.process()); 
			// flareSixBox.setText(products.process());
		 });
		 
		 flareSix.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 //products = new Products("Flare", Integer.valueOf(flare.getText())); 
				 products = new Products("Flare 6 pack", Integer.valueOf(flareSix.getText())); 
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 //flareBox.setText(products.process()); 
			flareSixBox.setText(products.process());
		 });
		 
		 /*btAdd.setOnAction(e -> 
		 {
			 Products products = new Products("Flare", Integer.valueOf(flare.getText())); 
			 flareBox.setText(products.process()); 
			 
		        
		 });*/
		
		/*
		//Creating a Label
	      Label label = new Label("Products");
	      //Setting font to the label
	      Font font = Font.font("Brush Script MT", FontWeight.BOLD, FontPosture.REGULAR, 25);
	      label.setFont(font);
	      //Filling color to the label
	      label.setTextFill(Color.BROWN);
	      //Setting the position
	     // label.setTranslateX(150);
	      //label.setTranslateY(25);
	      Group root = new Group();
	      root.getChildren().add(label);
	      //Setting the stage*/
		
		//Set the Scene
		Scene scene = new Scene(pane);
		arg0.setWidth(900);
		arg0.setHeight(700);
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}
