package bulk.box.data;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BulkBoxPane extends StackPane
{
	public BulkBoxPane()
	{
		//Accordion accordion = new Accordion();
		
		 GridPane pane = new GridPane();
		 pane.setAlignment(Pos.TOP_LEFT);
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
		 pane.add(tdTotalWeight , 4, 10);
		 
		 pane.add(new Label("Hooter:"), 0, 11);
		 TextField hooterQuantity  = new TextField(); 
		 pane.add(hooterQuantity, 1, 11);
		 TextField hooterBoxType = new TextField(); 
		 hooterBoxType.setEditable(false); 
		 hooterBoxType.setAlignment(Pos.CENTER);
		 pane.add(hooterBoxType, 2, 11);
		 TextField hooterDimensions  = new TextField(); 
		 hooterDimensions.setEditable(false);
		 pane.add(hooterDimensions, 3, 11);
		 TextField hooterTotalWeight  = new TextField();
		 hooterTotalWeight.setEditable(false);
		 pane.add(hooterTotalWeight , 4, 11);
		 
		 pane.add(new Label("Bell:"), 0, 12);
		 TextField bellQuantity  = new TextField(); 
		 pane.add(bellQuantity, 1, 12);
		 TextField bellBoxType = new TextField(); 
		 bellBoxType.setEditable(false); 
		 bellBoxType.setAlignment(Pos.CENTER);
		 pane.add(bellBoxType, 2, 12);
		 TextField bellDimensions  = new TextField(); 
		 bellDimensions.setEditable(false);
		 pane.add(bellDimensions, 3, 12);
		 TextField bellTotalWeight  = new TextField();
		 bellTotalWeight.setEditable(false);
		 pane.add(bellTotalWeight , 4, 12);
		 
		 pane.add(new Label("Ban Ex S1/S2:"), 0, 13);
		 TextField banexQuantity  = new TextField(); 
		 pane.add(banexQuantity, 1, 13);
		 TextField banexBoxType = new TextField(); 
		 banexBoxType.setEditable(false); 
		 banexBoxType.setAlignment(Pos.CENTER);
		 pane.add(banexBoxType, 2, 13);
		 TextField banexDimensions  = new TextField(); 
		 banexDimensions.setEditable(false);
		 pane.add(banexDimensions, 3, 13);
		 TextField banexTotalWeight  = new TextField();
		 banexTotalWeight.setEditable(false);
		 pane.add(banexTotalWeight , 4, 13);
		 
		 pane.add(new Label("Ban EX Combo S1/S2:"), 0, 14);
		 TextField banexcQuantity  = new TextField(); 
		 pane.add(banexcQuantity, 1, 14);
		 TextField banexcBoxType = new TextField(); 
		 banexcBoxType.setEditable(false); 
		 banexcBoxType.setAlignment(Pos.CENTER);
		 pane.add(banexcBoxType, 2, 14);
		 TextField banexcDimensions  = new TextField(); 
		 banexcDimensions.setEditable(false);
		 pane.add(banexcDimensions, 3, 14);
		 TextField banexcTotalWeight  = new TextField();
		 banexcTotalWeight.setEditable(false);
		 pane.add(banexcTotalWeight , 4, 14);
		 
		 pane.add(new Label("Ban EX S3:"), 0, 15);
		 TextField banexsQuantity  = new TextField(); 
		 pane.add(banexsQuantity, 1, 15);
		 TextField banexsBoxType = new TextField(); 
		 banexsBoxType.setEditable(false); 
		 banexsBoxType.setAlignment(Pos.CENTER);
		 pane.add(banexsBoxType, 2, 15);
		 TextField banexsDimensions  = new TextField(); 
		 banexsDimensions.setEditable(false);
		 pane.add(banexsDimensions, 3, 15);
		 TextField banexsTotalWeight  = new TextField();
		 banexsTotalWeight.setEditable(false);
		 pane.add(banexsTotalWeight , 4, 15);
		 
		 pane.add(new Label("Ban Ex S3 Light:"), 0, 16);
		 TextField banexslQuantity  = new TextField(); 
		 pane.add(banexslQuantity, 1, 16);
		 TextField banexslBoxType = new TextField(); 
		 banexslBoxType.setEditable(false); 
		 banexslBoxType.setAlignment(Pos.CENTER);
		 pane.add(banexslBoxType, 2, 16);
		 TextField banexslDimensions  = new TextField(); 
		 banexslDimensions.setEditable(false);
		 pane.add(banexslDimensions, 3, 16);
		 TextField banexslTotalWeight  = new TextField();
		 banexslTotalWeight.setEditable(false);
		 pane.add(banexslTotalWeight , 4, 16);
		 
		 pane.add(new Label("660HZ:"), 0, 17);
		 TextField shzQuantity  = new TextField(); 
		 pane.add(shzQuantity, 1, 17);
		 TextField shzBoxType = new TextField(); 
		 shzBoxType.setEditable(false); 
		 shzBoxType.setAlignment(Pos.CENTER);
		 pane.add(shzBoxType, 2, 17);
		 TextField shzDimensions  = new TextField(); 
		 shzDimensions.setEditable(false);
		 pane.add(shzDimensions, 3, 17);
		 TextField shzTotalWeight  = new TextField();
		 shzTotalWeight.setEditable(false);
		 pane.add(shzTotalWeight , 4, 17);
		 
		 pane.add(new Label("370HZ:"), 0, 18);
		 TextField thzQuantity  = new TextField(); 
		 pane.add(thzQuantity, 1, 18);
		 TextField thzBoxType = new TextField(); 
		 thzBoxType.setEditable(false); 
		 thzBoxType.setAlignment(Pos.CENTER);
		 pane.add(thzBoxType, 2, 18);
		 TextField thzDimensions  = new TextField(); 
		 thzDimensions.setEditable(false);
		 pane.add(thzDimensions, 3, 18);
		 TextField thzTotalWeight  = new TextField();
		 thzTotalWeight.setEditable(false);
		 pane.add(thzTotalWeight , 4, 18);
		 
		 pane.add(new Label("Blaster 300ml:"), 0, 19);
		 TextField blasterQuantity  = new TextField(); 
		 pane.add(blasterQuantity, 1, 19);
		 TextField blasterBoxType = new TextField(); 
		 blasterBoxType.setEditable(false); 
		 blasterBoxType.setAlignment(Pos.CENTER);
		 pane.add(blasterBoxType, 2, 19);
		 TextField blasterDimensions  = new TextField(); 
		 blasterDimensions.setEditable(false);
		 pane.add(blasterDimensions, 3, 19);
		 TextField blasterTotalWeight  = new TextField();
		 blasterTotalWeight.setEditable(false);
		 pane.add(blasterTotalWeight , 4, 19);
		 
		 pane.add(new Label("Blaster 40/100/135mll:"), 0, 20);
		 TextField blasterfQuantity  = new TextField(); 
		 pane.add(blasterfQuantity, 1, 20);
		 TextField blasterfBoxType = new TextField(); 
		 blasterfBoxType.setEditable(false); 
		 blasterfBoxType.setAlignment(Pos.CENTER);
		 pane.add(blasterfBoxType, 2, 20);
		 TextField blasterfDimensions  = new TextField(); 
		 blasterfDimensions.setEditable(false);
		 pane.add(blasterfDimensions, 3, 20);
		 TextField blasterfTotalWeight  = new TextField();
		 blasterfTotalWeight.setEditable(false);
		 pane.add(blasterfTotalWeight , 4, 20);
		 
		 pane.add(new Label("A100:"), 0, 21);
		 TextField aQuantity  = new TextField(); 
		 pane.add(aQuantity, 1, 21);
		 TextField aBoxType = new TextField(); 
		 aBoxType.setEditable(false); 
		 aBoxType.setAlignment(Pos.CENTER);
		 pane.add(aBoxType, 2, 21);
		 TextField aDimensions  = new TextField(); 
		 aDimensions.setEditable(false);
		 pane.add(aDimensions, 3, 21);
		 TextField aTotalWeight  = new TextField();
		 aTotalWeight.setEditable(false);
		 pane.add(aTotalWeight , 4, 21);
		 
		 pane.add(new Label("A105:"), 0, 22);
		 TextField a5Quantity  = new TextField(); 
		 pane.add(a5Quantity, 1, 22);
		 TextField a5BoxType = new TextField(); 
		 a5BoxType.setEditable(false); 
		 a5BoxType.setAlignment(Pos.CENTER);
		 pane.add(a5BoxType, 2, 22);
		 TextField a5Dimensions  = new TextField(); 
		 a5Dimensions.setEditable(false);
		 pane.add(a5Dimensions, 3, 22);
		 TextField a5TotalWeight  = new TextField();
		 a5TotalWeight.setEditable(false);
		 pane.add(a5TotalWeight , 4, 22);
		 
		 pane.add(new Label("A112:"), 0, 23);
		 TextField a2Quantity  = new TextField(); 
		 pane.add(a2Quantity, 1, 23);
		 TextField a2BoxType = new TextField(); 
		 a2BoxType.setEditable(false); 
		 a2BoxType.setAlignment(Pos.CENTER);
		 pane.add(a2BoxType, 2, 23);
		 TextField a2Dimensions  = new TextField(); 
		 a2Dimensions.setEditable(false);
		 pane.add(a2Dimensions, 3, 23);
		 TextField a2TotalWeight  = new TextField();
		 a2TotalWeight.setEditable(false);
		 pane.add(a2TotalWeight , 4, 23);
		
		 pane.add(new Label("A112:"), 0, 24);
		 TextField a1Quantity  = new TextField(); 
		 pane.add(a1Quantity, 1, 24);
		 TextField a1BoxType = new TextField(); 
		 a1BoxType.setEditable(false); 
		 a1BoxType.setAlignment(Pos.CENTER);
		 pane.add(a1BoxType, 2, 24);
		 TextField a1Dimensions  = new TextField(); 
		 a1Dimensions.setEditable(false);
		 pane.add(a1Dimensions, 3, 24);
		 TextField a1TotalWeight  = new TextField();
		 a1TotalWeight.setEditable(false);
		 pane.add(a1TotalWeight , 4, 24);
		 
		 pane.add(new Label("AL100:"), 0, 25);
		 TextField alQuantity  = new TextField(); 
		 pane.add(alQuantity, 1, 25);
		 TextField alBoxType = new TextField(); 
		 alBoxType.setEditable(false); 
		 alBoxType.setAlignment(Pos.CENTER);
		 pane.add(alBoxType, 2, 25);
		 TextField alDimensions  = new TextField(); 
		 alDimensions.setEditable(false);
		 pane.add(alDimensions, 3, 25);
		 TextField alTotalWeight  = new TextField();
		 alTotalWeight.setEditable(false);
		 pane.add(alTotalWeight , 4, 25);
		 
		 
		 pane.add(new Label("AL105:"), 0, 26);
		 TextField al5Quantity  = new TextField(); 
		 pane.add(al5Quantity, 1, 26);
		 TextField al5BoxType = new TextField(); 
		 al5BoxType.setEditable(false); 
		 al5BoxType.setAlignment(Pos.CENTER);
		 pane.add(al5BoxType, 2, 26);
		 TextField al5Dimensions  = new TextField(); 
		 al5Dimensions.setEditable(false);
		 pane.add(al5Dimensions, 3, 26);
		 TextField al5TotalWeight  = new TextField();
		 al5TotalWeight.setEditable(false);
		 pane.add(al5TotalWeight , 4, 26);
		 
		 
		 pane.add(new Label("AL112:"), 0, 27);
		 TextField al2Quantity  = new TextField(); 
		 pane.add(al2Quantity, 1, 27);
		 TextField al2BoxType = new TextField(); 
		 al2BoxType.setEditable(false); 
		 al2BoxType.setAlignment(Pos.CENTER);
		 pane.add(al2BoxType, 2, 27);
		 TextField al2Dimensions  = new TextField(); 
		 al2Dimensions.setEditable(false);
		 pane.add(al2Dimensions, 3, 27);
		 TextField al2TotalWeight  = new TextField();
		 al2TotalWeight.setEditable(false);
		 pane.add(al2TotalWeight , 4, 27);
		 
		 pane.add(new Label("AL121:"), 0, 28);
		 TextField al1Quantity  = new TextField(); 
		 pane.add(al1Quantity, 1, 28);
		 TextField al1BoxType = new TextField(); 
		 al1BoxType.setEditable(false); 
		 al1BoxType.setAlignment(Pos.CENTER);
		 pane.add(al1BoxType, 2, 28);
		 TextField al1Dimensions  = new TextField(); 
		 al1Dimensions.setEditable(false);
		 pane.add(al1Dimensions, 3, 28);
		 TextField al1TotalWeight  = new TextField();
		 al1TotalWeight.setEditable(false);
		 pane.add(al1TotalWeight , 4, 28);
		 
		 pane.add(new Label("B300:"), 0, 29);
		 TextField a3Quantity  = new TextField(); 
		 pane.add(a3Quantity, 1, 29);
		 TextField a3BoxType = new TextField(); 
		 a3BoxType.setEditable(false); 
		 a3BoxType.setAlignment(Pos.CENTER);
		 pane.add(a3BoxType, 2, 29);
		 TextField a3Dimensions  = new TextField(); 
		 a3Dimensions.setEditable(false);
		 pane.add(a3Dimensions, 3, 29);
		 TextField a3TotalWeight  = new TextField();
		 a3TotalWeight.setEditable(false);
		 pane.add(a3TotalWeight , 4, 29);
		 
		 pane.add(new Label("B400:"), 0, 30);
		 TextField a4Quantity  = new TextField(); 
		 pane.add(a4Quantity, 1, 30);
		 TextField a4BoxType = new TextField(); 
		 a4BoxType.setEditable(false); 
		 a4BoxType.setAlignment(Pos.CENTER);
		 pane.add(a4BoxType, 2, 30);
		 TextField a4Dimensions  = new TextField(); 
		 a4Dimensions.setEditable(false);
		 pane.add(a4Dimensions, 3, 30);
		 TextField a4TotalWeight  = new TextField();
		 a4TotalWeight.setEditable(false);
		 pane.add(a4TotalWeight , 4, 30);
		 
		 pane.add(new Label("H100:"), 0, 31);
		 TextField h1Quantity  = new TextField(); 
		 pane.add(h1Quantity, 1, 31);
		 TextField h1BoxType = new TextField(); 
		 h1BoxType.setEditable(false); 
		 h1BoxType.setAlignment(Pos.CENTER);
		 pane.add(h1BoxType, 2, 31);
		 TextField h1Dimensions  = new TextField(); 
		 h1Dimensions.setEditable(false);
		 pane.add(h1Dimensions, 3, 31);
		 TextField h1TotalWeight  = new TextField();
		 h1TotalWeight.setEditable(false);
		 pane.add(h1TotalWeight , 4, 31);
		 
		 pane.add(new Label("H150:"), 0, 32);
		 TextField h15Quantity  = new TextField(); 
		 pane.add(h15Quantity, 1, 32);
		 TextField h15BoxType = new TextField(); 
		 h15BoxType.setEditable(false); 
		 h15BoxType.setAlignment(Pos.CENTER);
		 pane.add(h15BoxType, 2, 32);
		 TextField h15Dimensions  = new TextField(); 
		 h15Dimensions.setEditable(false);
		 pane.add(h15Dimensions, 3, 32);
		 TextField h15TotalWeight  = new TextField();
		 h15TotalWeight.setEditable(false);
		 pane.add(h15TotalWeight , 4, 32);
		 
		 pane.add(new Label("H200:"), 0, 33);
		 TextField h2Quantity  = new TextField(); 
		 pane.add(h2Quantity, 1, 33);
		 TextField h2BoxType = new TextField(); 
		 h2BoxType.setEditable(false); 
		 h2BoxType.setAlignment(Pos.CENTER);
		 pane.add(h2BoxType, 2, 33);
		 TextField h2Dimensions  = new TextField(); 
		 h2Dimensions.setEditable(false);
		 pane.add(h2Dimensions, 3, 33);
		 TextField h2TotalWeight  = new TextField();
		 h2TotalWeight.setEditable(false);
		 pane.add(h2TotalWeight , 4, 33);
		 
		 pane.add(new Label("500SA:"), 0, 34);
		 TextField saQuantity  = new TextField(); 
		 pane.add(saQuantity, 1, 34);
		 TextField saBoxType = new TextField(); 
		 saBoxType.setEditable(false); 
		 saBoxType.setAlignment(Pos.CENTER);
		 pane.add(saBoxType, 2, 34);
		 TextField saDimensions  = new TextField(); 
		 saDimensions.setEditable(false);
		 pane.add(saDimensions, 3, 34);
		 TextField saTotalWeight  = new TextField();
		 saTotalWeight.setEditable(false);
		 pane.add(saTotalWeight , 4, 34);
		 
		 pane.add(new Label("1000SA:"), 0, 35);
		 TextField sa1Quantity  = new TextField(); 
		 pane.add(sa1Quantity, 1, 35);
		 TextField sa1BoxType = new TextField(); 
		 sa1BoxType.setEditable(false); 
		 sa1BoxType.setAlignment(Pos.CENTER);
		 pane.add(sa1BoxType, 2, 35);
		 TextField sa1Dimensions  = new TextField(); 
		 sa1Dimensions.setEditable(false);
		 pane.add(sa1Dimensions, 3, 35);
		 TextField sa1TotalWeight  = new TextField();
		 sa1TotalWeight.setEditable(false);
		 pane.add(sa1TotalWeight , 4, 35);
		 
		 
		 pane.add(new Label("MA112:"), 0, 36);
		 TextField ma2Quantity  = new TextField(); 
		 pane.add(ma2Quantity, 1, 36);
		 TextField ma2BoxType = new TextField(); 
		 ma2BoxType.setEditable(false); 
		 ma2BoxType.setAlignment(Pos.CENTER);
		 pane.add(ma2BoxType, 2, 36);
		 TextField ma2Dimensions  = new TextField(); 
		 ma2Dimensions.setEditable(false);
		 pane.add(ma2Dimensions, 3, 36);
		 TextField ma2TotalWeight  = new TextField();
		 ma2TotalWeight.setEditable(false);
		 pane.add(ma2TotalWeight , 4, 36);
		 
		 pane.add(new Label("MA121:"), 0, 37);
		 TextField sa12Quantity  = new TextField(); 
		 pane.add(sa12Quantity, 1, 37);
		 TextField sa12BoxType = new TextField(); 
		 sa12BoxType.setEditable(false); 
		 sa12BoxType.setAlignment(Pos.CENTER);
		 pane.add(sa12BoxType, 2, 37);
		 TextField sa12Dimensions  = new TextField(); 
		 sa12Dimensions.setEditable(false);
		 pane.add(sa12Dimensions, 3, 37);
		 TextField sa12TotalWeight  = new TextField();
		 sa12TotalWeight.setEditable(false);
		 pane.add(sa12TotalWeight , 4, 37);
		 
		 setOnKey(flare, flareBox, FlareDimensions, totalWeight, "Flare", 332);
		 setOnKey(flareSix, flareSixBox, FlareSixDimensions, totalSixWeight, "Flare 6 pack", 1.9);
		 setOnKey(BanBeacon, BanBeaconBox, BanBeaconDimensions, totalBanBeaconWeight, "Ban Beacon", 740);
		 setOnKey(SirBeaconportable, SirBeaconportableBox, SirBeaconportableDimensions, totalSirBeaconportableWeight, "Sir Beacon / portable", 1.1);
			
		 
		 ScrollPane sp = new ScrollPane(); 
		 sp.setContent(pane); 
		
		 
		 getChildren().addAll(sp, pane);
		
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
