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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
	
	private TableView table = new TableView();

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
		 
		 pane.add(new Label("Ban Beacon:"), 0, 3);
		 pane.add(new TextField(), 1, 3);
		 TextField BanBeacon  = new TextField(); 
		 pane.add(BanBeacon, 1, 3);
		 TextField BanBeaconBox= new TextField(); 
		 pane.add(BanBeaconBox, 2, 3);
		 
		 pane.add(new Label("Sir Beacon / portable:"), 0, 4);
		 pane.add(new TextField(), 1, 4);
		 TextField SirBeaconportable  = new TextField(); 
		 pane.add(SirBeaconportable, 1, 4);
		 TextField SirBeaconportableBox= new TextField(); 
		 pane.add(SirBeaconportableBox, 2, 4);
		 
		 SirBeaconportable.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 products = new Products("Sir Beacon / portable", Integer.valueOf(SirBeaconportable.getText()));  
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 SirBeaconportableBox.setText(products.process()); 
		 });
		 
		 BanBeacon.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 products = new Products("Ban Beacon", Integer.valueOf(BanBeacon.getText()));  
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 BanBeaconBox.setText(products.process()); 
		 });
		 
		 flare.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 products = new Products("Flare", Integer.valueOf(flare.getText()));  
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 flareBox.setText(products.process()); 
		 });
		 
		 flareSix.setOnKeyReleased(e -> 
		 {
			 Products products = null; 
			 try
			 {
				 products = new Products("Flare 6 pack", Integer.valueOf(flareSix.getText())); 
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }			 
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
		 
		 final Label label = new Label("Box & Bulk TQY");
	        label.setFont(new Font("Arial", 20));
	 
	        table.setEditable(true);
	 
	        TableColumn firstNameCol = new TableColumn("Products");
	        TableColumn lastNameCol = new TableColumn("Quantity");
	        TableColumn emailCol = new TableColumn("Box Type");
	        
	        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);
	 
	        final VBox vbox = new VBox();
	        vbox.setSpacing(5);
	        vbox.setPadding(new Insets(10, 0, 0, 10));
	        vbox.getChildren().addAll(label, table);
	 
	        pane.add(vbox, 4, 4);
		//Set the Scene
		Scene scene = new Scene(pane);
		arg0.setWidth(900);
		arg0.setHeight(700);
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}
