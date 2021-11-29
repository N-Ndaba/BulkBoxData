package bbd;

import javafx.geometry.Insets;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class BulkBoxPane extends StackPane
{
	public BulkBoxPane()
	{
		Label label = new Label("Sample label");
		
		//label.add
		//createChildren();
	}
	
	public void createChildren()
	{
		//Make root node for the Scene
		Accordion accordion = new Accordion();
		//Create the Crisis Pane
		TitledPane tpcrisis = new TitledPane();
		tpcrisis.setText("Box & Bulk QTY");
		GridPane grid = new GridPane();
		grid.setVgap(4); //Add some spacing
		
		grid.setPadding(new Insets(5,5,5,5)); //Add some padding
		grid.add(new Label("Name: "), 0, 0);
		//grid.add(new TextField(crisis.getCrisisName()), 1, 0);
		grid.add(new Label("ID: "), 0, 1);
		//grid.add(new TextField(crisis.getCrisisID()), 1, 1);
		grid.add(new Label("Team: "), 0, 2);
		//grid.add(new TextField(crisis.getCrisisTeam()), 1, 2);
		grid.add(new Label("Priority: "), 0, 3);
		//ProgressBar pgb = new ProgressBar();		
		//pgb.setProgress(Math.round(crisis.getCrisisPriority())/10.0);
		//grid.add(pgb, 1, 3);
		//Add child node
		tpcrisis.setContent(grid);
		
	}
}
