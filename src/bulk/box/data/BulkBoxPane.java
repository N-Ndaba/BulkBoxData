package bulk.box.data;

import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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


		menuBar.getMenus().add(menu); 

		
		menu.getItems().add(miHome);
		menu.getItems().add(miAdd);
		menu.getItems().add(miEdit); 
		menu.getItems().add(miDelete); 

		getChildren().add(Delete.deleteRecord(menuBar)); 
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
			getChildren().add(Delete.deleteRecord(menuBar));
		});
	}
}
