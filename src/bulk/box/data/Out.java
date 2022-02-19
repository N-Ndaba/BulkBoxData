package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class Out {

	private static ObservableList<BoxType> bt = null;
	private static String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	public static VBox printCat(MenuBar menuBar) {
		ComboBox<String> cbProducts = new ComboBox<>();
		ComboBox<String> cbBoxCode = new ComboBox<>();
		TableView<BoxType> tableView = new TableView<>(); 
		TableColumn<BoxType, String> product = new TableColumn<>("Product");
		TableColumn<BoxType, String> boxtype = new TableColumn<>("Box Code");
		TableColumn<BoxType, Number> minimum = new TableColumn<>("Minimum");
		TableColumn<BoxType, Number> maximum = new TableColumn<>("Maximum");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_LEFT);
		grid.setPadding(new Insets(3, 12.5, 5, 14.5));
		grid.setHgap(7);
		grid.setVgap(3);

		tableView.getColumns().addAll(product, boxtype, minimum, maximum);
		Label lblMinimum = new Label("Minimum:");
		TextField txtMinimum = new TextField();
		Label lblMaximum = new Label("Maximum:");
		TextField txtMaximum = new TextField();
		Button btnAdd = new Button("Add"); 
		btnAdd.setPrefWidth(200);

		grid.add(cbProducts, 0, 0);
		grid.add(cbBoxCode, 0, 2);
		grid.add(lblMinimum, 0, 4);
		grid.add(txtMinimum, 1, 4);
		grid.add(lblMaximum, 0, 5);
		grid.add(txtMaximum, 1, 5);
		grid.add(btnAdd, 0, 6, 2, 3);

		grid.add(tableView, 7, 0, 1, 10); 
		ObservableList<String> ps = null;
		ObservableList<String> bc = null;
	

		try { 
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 



			String query = "SELECT name FROM item"; 
			ResultSet rs = statement.executeQuery(query); 
			while(rs.next()) {
				ps = FXCollections.observableArrayList(rs.getString("name"));
				cbProducts.getItems().addAll(ps);
			}

			String queryBox = "SELECT name FROM box"; 
			ResultSet rx = statement.executeQuery(queryBox); 
			while(rx.next()) {
				bc = FXCollections.observableArrayList(rx.getString("name"));
				cbBoxCode.getItems().addAll(bc);
			}


			String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown);
			connection.close();
			statement.close();
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		}catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}

		
		btnAdd.setOnAction(e -> {
			try { 
				DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
				Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
				Connection connection = DriverManager.getConnection(jdbcURL);
				Statement statement = connection.createStatement(); 



				String sql = "Insert into ItemBox (iname, bname, minimum, maximum) values ('" + cbProducts.getSelectionModel().getSelectedItem() + "', '"+ cbBoxCode.getSelectionModel().getSelectedItem()+"', " + txtMinimum.getText() + ", " + txtMaximum.getText() + ")";


				int rows = statement.executeUpdate(sql);
				if(rows > 0) {
					System.out.println("A row created.");
				}


				String query = "SELECT * FROM ItemBox"; 
				ResultSet rs = statement.executeQuery(query); 
				while(rs.next()) {
					bt = FXCollections.observableArrayList(new BoxType(Integer.valueOf(rs.getString("id")), rs.getString("iname"), rs.getString("bname"), Integer.valueOf(rs.getString("minimum")), Integer.valueOf(rs.getString("maximum"))));	
					for(BoxType b : bt) {
						tableView.getItems().add(b); 
					}
				}

				String shutdown = "jdbc:derby:;shutdown=true";
				DriverManager.getConnection(shutdown);
				connection.close();
				statement.close();
			} catch(ClassNotFoundException ex) {
				ex.printStackTrace();
			}catch (SQLException ex) {
				if(ex.getSQLState().equals("XJ015")) {
					System.out.println("Derby shutdown normally");
				} else {
					ex.printStackTrace();
				}
			}
			
			txtMinimum.setText(null);
			txtMaximum.setText(null);
		});

		product.setMinWidth(160);
		product.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().nameProperty();
			}		
		});

		boxtype.setMinWidth(160);
		boxtype.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxType, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().boxcodeProperty();
			}
		});


		minimum.setMinWidth(160);
		minimum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().minimumProperty();
			}		
		});

		maximum.setMinWidth(160);
		maximum.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BoxType, Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(TableColumn.CellDataFeatures<BoxType, Number> param) {
				// TODO Auto-generated method stub
				return param.getValue().maximumProperty();
			}
		});

		VBox vbox = new VBox(); 
		vbox.getChildren().addAll(menuBar, grid); 

		return vbox;
	}
}
