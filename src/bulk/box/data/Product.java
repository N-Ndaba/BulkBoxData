package bulk.box.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

public class Product {

	private final StringProperty name; 
	private final DoubleProperty weight;

	private DoubleProperty sum;
	private StringProperty boxtype;
	private IntegerProperty quantity; 
	private StringProperty dimension; 
	private IntegerProperty id; 

	
	private String jdbcURL = "jdbc:derby:boxbulkdb;create=true";


	public Product(int id, String name, double weight) {
		this.name = new SimpleStringProperty(this, "name", name); 
		this.weight = new SimpleDoubleProperty(this, "weight", weight); 
		this.quantity = new SimpleIntegerProperty(this, "quantity", 1); 
		this.boxtype = new SimpleStringProperty(this, "boxtype");
		this.sum = new SimpleDoubleProperty(this, "sum", weight); 
		this.dimension = new SimpleStringProperty(this, "dimension", "");
		this.id = new SimpleIntegerProperty(this, "id", id); 
		setQuantity(1);
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

	public final String getDimension() {
		return this.dimension.get(); 
	}

	public void setDimension(String dimension) {
		this.dimension.set(dimension);
	}

	public final StringProperty dimensionProperty() {
		return this.dimension; 
	}

	public final String getName() {
		return this.name.get(); 
	}

	public void setName(String name) {
		this.name.set(name);
		updateRecord(); 
	}

	public final StringProperty nameProperty() {
		return this.name; 
	}

	public final Double getWeight() {
		return this.weight.get(); 
	}

	public void setWeight(double weight) {
		this.weight.set(weight);
		updateRecord(); 
	}

	public final DoubleProperty weightProperty() {
		return this.weight; 
	}








	public final Double getSumWeight() {
		return this.sum.get(); 
	}

	public final void setSumWeight(double weight) {
		this.sum.set(weight);
	}

	public final DoubleProperty sumProperty() {
		return this.sum; //Double.valueOf(String.format("%", null))
	}

	public final String getBoxType() {
		return this.boxtype.get(); 
	}

	public final void setBoxType(String boxtype) {
		this.boxtype.set(boxtype);
	}

	public final StringProperty boxtypeProperty() {
		return this.boxtype; 
	}

	public Integer getQuantity() {
		return this.quantity.get(); 
	}

	public void setQuantity(int number) {

		System.out.println("< " +number + ">");
		if(number <= 0) {
			number = 1; 
		}

		if(number != 0) {
	 
			this.quantity.set(number);
			setSumWeight(calcSum(number));
		}
		
		String iname = "";
		String bname = "";
		int minimum = 0;
		int maximum = 0;
		
	
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String query = "SELECT * FROM ItemBox WHERE iname = '" + getName() + "' and minimum = " + number + " or maximum = " + number; 
			ResultSet rs = statement.executeQuery(query); 

			while(rs.next()) {
				
				iname = rs.getString("iname"); 
				bname = rs.getString("bname"); 
				minimum = Integer.valueOf(rs.getString("minimum")); 
				maximum = Integer.valueOf(rs.getString("maximum")); 
			}
	
			System.out.println("minimum " + minimum);
			System.out.println("maximum " + maximum);
			System.out.println(getName().equals(iname));
			if(number >= minimum && number <= maximum && getName().equals(iname)) {
				
				System.out.println("[ "+ number  +" ]");
				String queryItem = "SELECT * FROM box WHERE name = '" + bname +"'"; 
				ResultSet rx = statement.executeQuery(queryItem); 
				while(rx.next()) {

	
					setBoxType(rx.getString("name"));
					setDimension(rx.getString("length") + " x " + rx.getString("width") + " x " + rx.getString("height"));
				}
			} else {
				setBoxType("-");
				setDimension("-");
			}

			/*String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown); 
			
			*/
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

	private double calcSum(int number) {
		return new BigDecimal(number * this.getWeight()).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}


	public IntegerProperty quantityProperty() {
		return this.quantity; 
	}


	private void updateRecord() {
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 
			String query = "UPDATE item SET weight = " + getWeight() +  ", name = '" + getName() + "' WHERE id = " + getId(); 
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

	private void joinIB(int quantity) {
		String iname = "";
		String bname = "";
		int minimum = 0;
		int maximum = 0;
		
	
		try {
			DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection = DriverManager.getConnection(jdbcURL);
			Statement statement = connection.createStatement(); 

			String query = "SELECT * FROM ItemBox"; 
			ResultSet rs = statement.executeQuery(query); 

			while(rs.next()) {
				
				iname = rs.getString("iname"); 
				bname = rs.getString("bname"); 
				minimum = Integer.valueOf(rs.getString("minimum")); 
				maximum = Integer.valueOf(rs.getString("maximum")); 
			}
	
			System.out.println("[ "+ getQuantity()  +" ]");
			if(minimum >= getQuantity() && getQuantity() <= maximum && getName().equals(iname)) {
				
				
				String queryItem = "SELECT * FROM box WHERE name = '" + bname +"'"; 
				ResultSet rx = statement.executeQuery(queryItem); 
				while(rx.next()) {

					System.out.println(rx.getString("name"));
					System.out.println(rx.getString("length"));
					System.out.println(rx.getString("width"));
					System.out.println(rx.getString("height"));
				}
			}

			/*String shutdown = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection(shutdown); 
			
			*/
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
