package bulk.box.data;

import java.awt.Color;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;


public class BulkBoxPane extends StackPane { 

	private MenuBar menuBar = null; 

	MenuItem miHome = new MenuItem("Home"); 
	MenuItem miAdd = new MenuItem("Add"); 
	MenuItem miEdit = new MenuItem("Edit"); 
	MenuItem miDelete = new MenuItem("Delete"); 

	public BulkBoxPane() {
		menuBar = new MenuBar(); 
		menuBar.setPrefHeight(30);
		//menuBar.setStyle("-fx-");


		Menu menu = new Menu("Menu");
		Menu addMode = new Menu("ADD");
		Menu editMode = new Menu("EDIT");
		Menu deleteMode = new Menu("DELETE");

		Menu lome1 = new Menu("lome1");
		Menu lome2 = new Menu("lome2");
		Menu lome3 = new Menu("lome3");

		deleteMode.getItems().addAll(lome1, lome2, lome3); 

		menuBar.getMenus().addAll(menu, addMode, editMode, deleteMode); 

		menu.setOnAction(e -> System.out.println("<Ordin>"));
		lome2.setOnAction(e -> System.out.println("Ordin Help Us"));

		menu.getItems().add(miHome);
		menu.getItems().add(miAdd);
		menu.getItems().add(miEdit); 
		menu.getItems().add(miDelete); 

		/*getChildren().add(Delete.deleteRecord(menuBar)); 
		//getChildren().add(Home.home(menuBar)); 
		miHome.setVisible(false);
		miHome.setOnAction(e -> {
			getChildren().clear(); 
			miHome.setVisible(false);
			miAdd.setVisible(true);
			miEdit.setVisible(true);
			miDelete.setVisible(true);
			getChildren().add(Home.home(menuBar)); 
		});

		miAdd.setOnAction(e -> {
			getChildren().clear();
			miAdd.setVisible(false);
			miHome.setVisible(true);
			miEdit.setVisible(true);
			miDelete.setVisible(true);
			getChildren().add(Add.addRecord(menuBar)); 
		});

		miEdit.setOnAction(e -> {
			getChildren().clear(); 
			miEdit.setVisible(false);
			miHome.setVisible(true);
			miAdd.setVisible(true);
			miDelete.setVisible(true); 
			getChildren().add(Edit.editRecord(menuBar)); 
		});

		miDelete.setOnAction(e -> {
			getChildren().clear();
			miDelete.setVisible(false);
			miHome.setVisible(true);
			miAdd.setVisible(true);
			miEdit.setVisible(true);			 
			//getChildren().add(Delete.deleteRecord(menuBar));
		});
		 */


		/*GridPane gridBar = new GridPane(); 
		gridBar.setAlignment(Pos.TOP_LEFT);
		gridBar.setPadding(new Insets(0.5, 14, 2, 7));
		gridBar.setHgap(3);
		gridBar.setVgap(3);

		Button home = new Button("Home");
		Button add = new Button("Add");
		Button edit = new Button("Edit");
		Button delete = new Button("Delete");

		gridBar.add(home, 0, 0);
		gridBar.add(add, 2, 0);
		gridBar.add(edit, 4, 0);
		gridBar.add(delete, 6, 0);

		getChildren().add(Home.home(gridBar));
		home.setOnAction(e -> getChildren().add(Home.home(gridBar)));
		add.setOnAction(e -> getChildren().add(Add.addRecord(gridBar)));
		edit.setOnAction(e -> getChildren().add(Edit.editRecord(gridBar)));
		delete.setOnAction(e -> getChildren().add(Delete.deleteRecord(gridBar)));*/

		HBox gridBar = new HBox(30); 


		Button home = new Button("Home", new ImageView("home.png"));
		home.setStyle("-fx-background-color: transparent;");
		Button add = new Button("Add", new ImageView("add.png"));
		add.setStyle("-fx-background-color: transparent;");
		Button edit = new Button("Edit", new ImageView("edit.png"));
		edit.setStyle("-fx-background-color: transparent;");
		Button delete = new Button("Delete", new ImageView("delete.png"));
		delete.setStyle("-fx-background-color: transparent;");

		gridBar.getChildren().addAll(home, add, edit, delete);
		gridBar.setAlignment(Pos.CENTER);
		gridBar.setStyle("-fx-border-color: black");
		/*gridBar.add(add, 2, 0);
		gridBar.add(edit, 4, 0);
		gridBar.add(delete, 6, 0);*/

		getChildren().add(Home.home(gridBar));
		home.setPrefWidth(100);
		home.setOnAction(e -> {
			getChildren().clear();
			//home.setStyle("-fx-background-color: red;");
			
			//home.setStyle("-fx-underline: true;");
			getChildren().add(Home.home(gridBar));
		});
		
		add.setPrefWidth(100);
		add.setOnAction(e -> {
			getChildren().clear();
			getChildren().add(Add.addRecord(gridBar));
		});
		
		edit.setPrefWidth(100);
		edit.setOnAction(e -> {
			getChildren().clear();
			getChildren().add(Edit.editRecord(gridBar));
		});
		
		delete.setPrefWidth(100);
		delete.setOnAction(e -> {
			getChildren().clear();
			getChildren().add(Delete.deleteRecord(gridBar));
		});
	}
}
