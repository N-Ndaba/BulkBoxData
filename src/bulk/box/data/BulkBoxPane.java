package bulk.box.data;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BulkBoxPane extends StackPane
{
	public BulkBoxPane()
	{
		 GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(7);
		 pane.setVgap(5.5);
		 /*
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
		 
		 pane.add(new Label("Mono S:"), 0, 5);
		 TextField monoQuantity  = new TextField(); 
		 pane.add(monoQuantity, 1, 5);
		 TextField monoBoxType = new TextField(); 
		 monoBoxType.setEditable(false); 
		 monoBoxType.setAlignment(Pos.CENTER);
		 pane.add(monoBoxType, 2, 5);
		 TextField monoDimensions  = new TextField(); 
		 monoDimensions.setEditable(false);
		 pane.add(monoDimensions, 3, 5);
		 TextField monoTotalWeight  = new TextField();
		 monoTotalWeight.setEditable(false);
		 pane.add(monoTotalWeight , 4, 5);
		 
		 pane.add(new Label("Duplo S:"), 0, 6);
		 TextField duploQuantity  = new TextField(); 
		 pane.add(duploQuantity, 1, 6);
		 TextField duploBoxType = new TextField(); 
		 duploBoxType.setEditable(false); 
		 duploBoxType.setAlignment(Pos.CENTER);
		 pane.add(duploBoxType, 2, 6);
		 TextField duploDimensions  = new TextField(); 
		 duploDimensions.setEditable(false);
		 pane.add(duploDimensions, 3, 6);
		 TextField duploTotalWeight  = new TextField();
		 duploTotalWeight.setEditable(false);
		 pane.add(duploTotalWeight , 4, 6);
		 
		 pane.add(new Label("Mono/ Duplo Combo:"), 0, 7);
		 TextField monoduploQuantity  = new TextField(); 
		 pane.add(monoduploQuantity, 1, 7);
		 TextField monoduploBoxType = new TextField(); 
		 monoduploBoxType.setEditable(false); 
		 monoduploBoxType.setAlignment(Pos.CENTER);
		 pane.add(monoduploBoxType, 2, 7);
		 TextField monoduploDimensions  = new TextField(); 
		 monoduploDimensions.setEditable(false);
		 pane.add(monoduploDimensions, 3, 7);
		 TextField monoduploTotalWeight  = new TextField();
		 monoduploTotalWeight.setEditable(false);
		 pane.add(monoduploTotalWeight , 4, 7);
		 
		 pane.add(new Label("1500S:"), 0, 8);
		 TextField fsQuantity  = new TextField(); 
		 pane.add(fsQuantity, 1, 8);
		 TextField fsBoxType = new TextField(); 
		 fsBoxType.setEditable(false); 
		 fsBoxType.setAlignment(Pos.CENTER);
		 pane.add(fsBoxType, 2, 8);
		 TextField fsDimensions  = new TextField(); 
		 fsDimensions.setEditable(false);
		 pane.add(fsDimensions, 3, 8);
		 TextField fsTotalWeight  = new TextField();
		 fsTotalWeight.setEditable(false);
		 pane.add(fsTotalWeight , 4, 8);
		 
		 pane.add(new Label("1500S Vert:"), 0, 9);
		 TextField fvQuantity  = new TextField(); 
		 pane.add(fvQuantity, 1, 9);
		 TextField fvBoxType = new TextField(); 
		 fvBoxType.setEditable(false); 
		 fvBoxType.setAlignment(Pos.CENTER);
		 pane.add(fvBoxType, 2, 9);
		 TextField fvDimensions  = new TextField(); 
		 fvDimensions.setEditable(false);
		 pane.add(fvDimensions, 3, 9);
		 TextField fvTotalWeight  = new TextField();
		 fvTotalWeight.setEditable(false);
		 pane.add(fvTotalWeight , 4, 9);
		 
		 pane.add(new Label("2D:"), 0, 10);
		 TextField tdQuantity  = new TextField(); 
		 pane.add(tdQuantity, 1, 10);
		 TextField tdBoxType = new TextField(); 
		 tdBoxType.setEditable(false); 
		 tdBoxType.setAlignment(Pos.CENTER);
		 pane.add(tdBoxType, 2, 10);
		 TextField tdDimensions  = new TextField(); 
		 tdDimensions.setEditable(false);
		 pane.add(tdDimensions, 3, 10);
		 TextField tdTotalWeight  = new TextField();
		 tdTotalWeight.setEditable(false);
		 pane.add(tdTotalWeight , 4, 10);*/
		
		// setOnKey(flare, flareBox, FlareDimensions, totalWeight, "Flare", 332);
		 //setOnKey(flareSix, flareSixBox, FlareSixDimensions, totalSixWeight, "Flare 6 pack", 1.9);
		 //setOnKey(BanBeacon, BanBeaconBox, BanBeaconDimensions, totalBanBeaconWeight, "Ban Beacon", 740);
		// setOnKey(SirBeaconportable, SirBeaconportableBox, SirBeaconportableDimensions, totalSirBeaconportableWeight, "Sir Beacon / portable", 1.1);
			
		 //getChildren().add(pane);
		
		 pane.add(new Label("Product:"), 0, 0);
		 pane.add(new Label("Quantity:"), 1, 0);
		 pane.add(new Label("BoxType:"), 2, 0);
		 pane.add(new Label("Dimensions (L x W x H):"), 3, 0);
		 pane.add(new Label("Total Weight (g | kg):"), 4, 0);
		 
		VBox vbEvents = new VBox();
		vbEvents.getChildren().add(setupGUI("Flare", new TextField(),  new TextField(), new TextField(), new TextField())); 
		vbEvents.getChildren().add(setupGUI("ntttttttttt", new TextField(),  new TextField(), new TextField(), new TextField())); 
		getChildren().addAll(pane, vbEvents);
	}
	
	public GridPane setupGUI(String label, TextField quantity, TextField boxType, TextField dimensions, TextField totalWeight)
	{
		 GridPane pane = new GridPane();
		 pane.setAlignment(Pos.CENTER);
		 pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 pane.setHgap(7);
		 pane.setVgap(5.5);
		 
		 
		 
		 
		 pane.add(new Label(label), 0, 1);
		 pane.add(quantity, 1, 1);
		 boxType.setEditable(false); 
		 boxType.setAlignment(Pos.CENTER);
		 pane.add(boxType, 2, 1);
		 dimensions.setEditable(false);
		 pane.add(dimensions, 3, 1);
		 totalWeight.setEditable(false);
		 pane.add(totalWeight , 4, 1);
		 
		 return pane; 
		// getChildren().add(pane);
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
