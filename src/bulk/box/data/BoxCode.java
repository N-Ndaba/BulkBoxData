package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BoxCode {

	private IntegerProperty id; 
	private StringProperty name; 
	private IntegerProperty length;
	private IntegerProperty width;
	private IntegerProperty height;



	public BoxCode(int id, String name, int length, int width, int height) {
		this.id = new SimpleIntegerProperty(this, "id", id); 
		this.name = new SimpleStringProperty(this, "name", name); 
		this.length = new SimpleIntegerProperty(this, "length", length);
		this.width = new SimpleIntegerProperty(this, "width", width); 
		this.height = new SimpleIntegerProperty(this, "height", height); 
	}

	public Integer getId() {
		return this.id.get(); 
	}
	
	public String getName() {
		return this.name.get(); 
	}

	public void setName(String name) {
		this.name.set(name);
		updateRecord();
	}

	public final StringProperty nameProperty() {
		return this.name; 
	}
	
	public Integer getLength() {
		return this.length.get(); 
	}

	public void setLength(int length) {
		this.length.set(length);
		updateRecord();
	}

	public final IntegerProperty lengthProperty() {
		return this.length; 
	}
	
	public Integer getWidth() {
		return this.width.get(); 
	}

	public void setWidth(int width) {
		this.width.set(width);
		updateRecord();
	}

	public final IntegerProperty widthProperty() {
		return this.width; 
	}
	
	public Integer getHeight() {
		return this.height.get(); 
	}

	public void setHeight(int height) {
		this.height.set(height);
		updateRecord();
	}

	public final IntegerProperty heightProperty() {
		return this.height; 
	}
	
	private void updateRecord() {
		String jdbcURL = "jdbc:derby:boxbulkdb;create=true";
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 
			String query = "UPDATE box SET name = '" + getName() +  "', length = " + getLength() + ", width = " + getWidth() + ", height = " + getHeight() +  " WHERE id = " + getId(); 
			int num = statement.executeUpdate(query); 
			System.out.println("Number of record updated are: " + num);
			String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown); 
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("Derby shutdown normally");
			} else {
				ex.printStackTrace();
			}
		}
	}
}