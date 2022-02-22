package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javafx.util.converter.NumberStringConverter;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;

import javafx.event.EventHandler;

public class BulkBoxPane extends StackPane { 
	private ObservableList<BoxType> bt = null;
	private String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

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

		getChildren().add(Home.home(menuBar)); 
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
