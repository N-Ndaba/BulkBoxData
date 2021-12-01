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
		 
		 // Place nodes in the pane
		 pane.add(new Label("Product:"), 0, 0);
		 pane.add(new Label("Quantity:"), 1, 0);
		 pane.add(new Label("BoxType:"), 2, 0);
		 pane.add(new Label("Dimensions (L x W x H):"), 3, 0);
		 pane.add(new Label("Total Weight (g | kg):"), 4, 0);
		 
		 pane.add(new Label("Flare:"), 0, 1);
		 TextField flare = new TextField(); 
		 pane.add(flare, 1, 1);
		 TextField flareBox = new TextField(); 
		 flareBox.setEditable(false);
		 pane.add(flareBox, 2, 1);
		 TextField FlareDimensions  = new TextField(); 
		 FlareDimensions.setEditable(false);
		 pane.add(FlareDimensions, 3, 1);
		 TextField totalWeight  = new TextField(); 
		 totalWeight.setEditable(false);
		 pane.add(totalWeight , 4, 1);
		 
		 pane.add(new Label("Flare 6 Pack:"), 0, 2);
		 pane.add(new TextField(), 1, 2);
		 TextField flareSix = new TextField(); 
		 pane.add(flareSix, 1, 2);
		 TextField flareSixBox= new TextField(); 
		 flareSixBox.setEditable(false);
		// flareSixBox.setStyle("-fx-background-color: light gray;");
		 pane.add(flareSixBox, 2, 2);
		 TextField FlareSixDimensions  = new TextField(); 
		 FlareSixDimensions.setEditable(false);
	//	 FlareSixDimensions.setStyle("-fx-background-color: light gray;");
		 pane.add(FlareSixDimensions, 3, 2);
		 TextField totalSixWeight  = new TextField(); 
		 totalSixWeight.setEditable(false);
		// totalSixWeight.setStyle("-fx-background-color: light gray;");
		 pane.add(totalSixWeight , 4, 2);
		 
		 pane.add(new Label("Sir Beacon 60:"), 0, 3);
		 pane.add(new TextField(), 1, 3);
		 TextField BanBeacon  = new TextField(); 
		 pane.add(BanBeacon, 1, 3);
		 TextField BanBeaconBox= new TextField(); 
		 BanBeaconBox.setEditable(false);
		 pane.add(BanBeaconBox, 2, 3);
		 TextField BanBeaconDimensions  = new TextField(); 
		 BanBeaconDimensions.setEditable(false);
		 pane.add(BanBeaconDimensions, 3, 3);
		 TextField totalBanBeaconWeight  = new TextField(); 
		 totalBanBeaconWeight.setEditable(false);
		 pane.add(totalBanBeaconWeight , 4, 3);
		 
		 pane.add(new Label("Sir Beacon / portable:"), 0, 4);
		 TextField SirBeaconportable  = new TextField(); 
		 pane.add(SirBeaconportable, 1, 4);
		 TextField SirBeaconportableBox= new TextField(); 
		 SirBeaconportableBox.setEditable(false); 
		 SirBeaconportableBox.setAlignment(Pos.CENTER);
		 pane.add(SirBeaconportableBox, 2, 4);
		 TextField SirBeaconportableDimensions  = new TextField(); 
		 SirBeaconportableDimensions.setEditable(false);
		 pane.add(SirBeaconportableDimensions, 3, 4);
		 TextField totalSirBeaconportableWeight  = new TextField();
		 totalSirBeaconportableWeight.setEditable(false);
		 pane.add(totalSirBeaconportableWeight , 4, 4);
		 
		 
		 setOnKey(flare, flareBox, FlareDimensions, totalWeight, "Flare", 332);
		 setOnKey(flareSix, flareSixBox, FlareSixDimensions, totalSixWeight, "Flare 6 pack", 1.9);
		 setOnKey(BanBeacon, BanBeaconBox, BanBeaconDimensions, totalBanBeaconWeight, "Ban Beacon", 740);
		 setOnKey(SirBeaconportable, SirBeaconportableBox, SirBeaconportableDimensions, totalSirBeaconportableWeight, "Sir Beacon / portable", 1.1);
			
		 getChildren().add(pane);
	}
	
	private GridPane createLabel(String productName)
	{
		GridPane grid = new GridPane();
		grid.setVgap(4);
		grid.setPadding(new Insets(5,5,5,5));
		
		
		grid.add(new Label(productName), 0, 4);
		//grid.add(new Sir, 1, 4);
		return grid; 
		/*
		 pane.add(new Label("Sir Beacon / portable:"), 0, 4);
		 //pane.add(new TextField(), 1, 4);
		 TextField SirBeaconportable  = new TextField(); 
		 pane.add(SirBeaconportable, 1, 4);
		 TextField SirBeaconportableBox= new TextField(); 
		 SirBeaconportableBox.setEditable(false); 
		 pane.add(SirBeaconportableBox, 2, 4);
		 TextField SirBeaconportableDimensions  = new TextField(); 
		 SirBeaconportableDimensions.setEditable(false);
		 pane.add(SirBeaconportableDimensions, 3, 4);
		 TextField totalSirBeaconportableWeight  = new TextField();
		 totalSirBeaconportableWeight.setEditable(false);
		 pane.add(totalSirBeaconportableWeight , 4, 4);
		 	
		 */
	}
	
	private void setOnKey(TextField txtQuantity, TextField txtBoxType, TextField txtDimensions, TextField txtTotalWeight,  String strProductName, double dblWeight)
	{
		txtQuantity.setOnKeyReleased(e -> 
		{
			Products products = null;
			Box box = new Box(); 
			 
			 try
			 {
				 if(txtQuantity.getText().isEmpty())
				 {
					 txtBoxType.clear();
					 txtDimensions.clear(); 
					 txtTotalWeight.clear(); 
					 
					 txtBoxType.setText("\t\t-");
					 txtDimensions.clear(); 
					 txtTotalWeight.clear(); 
				 }
				 
				 products = new Products(strProductName, Integer.valueOf(txtQuantity.getText()));
				 
				 txtBoxType.setText(products.process());
				 txtDimensions.setText(box.getBox(products.process())); 
				 txtTotalWeight.setText(String.format("%.1f", Integer.valueOf(txtQuantity.getText()) * dblWeight)); 
			 }
			 catch(Exception ex)
			 {
				ex.printStackTrace();  
			 }
		}); 
	}
}
