package bulk.box.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BoxType {

	private IntegerProperty id;
	private StringProperty name; 
	private StringProperty boxcode;
	private IntegerProperty minimum;
	private IntegerProperty maximum; 
	
	private String jdbcURL = "jdbc:derby:boxbulkdb;create=true";

	public BoxType(int id, String name, String boxcode, int minimum, int maximum) {
		this.id = new SimpleIntegerProperty(this, "id", id); 
		this.name = new SimpleStringProperty(this, "name", name);
		this.boxcode = new SimpleStringProperty(this, "boxcode", boxcode); 		
		this.minimum = new SimpleIntegerProperty(this, "minimum", minimum);
		this.maximum = new SimpleIntegerProperty(this, "maximum", maximum); 
	}

	public Integer getId() {
		return this.id.get(); 
	}

	public void setId(int id) {
		this.id.set(id);
	}

	public final IntegerProperty idProperty() {
		return this.id; 
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
	
	public String getBoxcode() {
		return this.boxcode.get(); 
	}

	public void setBoxcode(String boxcode) {
		this.boxcode.set(boxcode);
		updateRecord();
	}

	public final StringProperty boxcodeProperty() {
		return this.boxcode; 
	}
	
	public Integer getMinimum() {
		return this.minimum.get(); 
	}

	public void setMinimum(int minimum) {
		this.minimum.set(minimum);
		updateRecord(); 
	}

	public final IntegerProperty minimumProperty() {
		return this.minimum; 
	}
	
	public Integer getMaximum() {
		return this.maximum.get(); 
	}

	public void setMaximum(int maximum) {
		this.maximum.set(maximum);
		updateRecord();
	}

	public final IntegerProperty maximumProperty() {
		return this.maximum; 
	}
	
	private void updateRecord() {
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 
			String query = "UPDATE ItemBox SET iname = '" + getName() +  "', bname = '" + getBoxcode() + "', minimum = " +getMinimum()+ ", maximum = " + getMaximum() + "  WHERE id = " + getId(); 
			int num = statement.executeUpdate(query); 
			String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown); 
		}  catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException ex) {
			if(ex.getSQLState().equals("XJ015")) {
				System.out.println("");
			} else {
				ex.printStackTrace();
			}
		}
	}
}
