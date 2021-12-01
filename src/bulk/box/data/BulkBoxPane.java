package bulk.box.data;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BulkBoxPane extends StackPane
{
	public BulkBoxPane()
	{
		 GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(7);
		 pane.setVgap(5.5);
		 
		 TitledPane tpcrisis = new TitledPane();
		 tpcrisis.setText("Crisis Information");
		
		 // Place nodes in the pane
		 pane.add(new Label("Product:"), 0, 0);
		 pane.add(new Label("Quantity:"), 1, 0);
		 pane.add(new Label("BoxType:"), 2, 0);
		 pane.add(new Label("Dimensions (L x W x H):"), 3, 0);
		 pane.add(new Label("Total Weight (kg):"), 4, 0);
		 
		 pane.add(new Label("Flare:"), 0, 1);
		 pane.add(new TextField(), 1, 1);
		 TextField flare = new TextField(); 
		 pane.add(flare, 1, 1);
		 TextField flareBox = new TextField(); 
		 flareBox.setEditable(false);
		 pane.add(flareBox, 2, 1);
		 TextField FlareDimensions  = new TextField(); 
		 pane.add(FlareDimensions, 3, 1);
		 TextField totalWeight  = new TextField(); 
		 pane.add(totalWeight , 4, 1);
		 
		 pane.add(new Label("Flare 6 Pack:"), 0, 2);
		 pane.add(new TextField(), 1, 2);
		 TextField flareSix = new TextField(); 
		 pane.add(flareSix, 1, 2);
		 TextField flareSixBox= new TextField(); 
		 flareSixBox.setEditable(false);
		 pane.add(flareSixBox, 2, 2);
		 
		 pane.add(new Label("Ban Beacon:"), 0, 3);
		 pane.add(new TextField(), 1, 3);
		 TextField BanBeacon  = new TextField(); 
		 pane.add(BanBeacon, 1, 3);
		 TextField BanBeaconBox= new TextField(); 
		 BanBeaconBox.setEditable(false);
		 pane.add(BanBeaconBox, 2, 3);
		 
		 pane.add(new Label("Sir Beacon / portable:"), 0, 4);
		 pane.add(new TextField(), 1, 4);
		 TextField SirBeaconportable  = new TextField(); 
		 pane.add(SirBeaconportable, 1, 4);
		 TextField SirBeaconportableBox= new TextField(); 
		 SirBeaconportableBox.setEditable(false); 
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
			 Box box = new Box(); 
			 try
			 {
				 products = new Products("Flare", Integer.valueOf(flare.getText()));  
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
			 
			 double flareWeight = Integer.valueOf(flare.getText()) * 332; 
			 
			 totalWeight.setText(String.valueOf(flareWeight + "g")); 
			 FlareDimensions.setText(box.getBox(products.process())); 
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
		 
		 tpcrisis.setContent(pane); 
		 getChildren().add(pane);
	}
	
	public void setOnKey(String itemName, int quantity)
	{
		
	}
}
