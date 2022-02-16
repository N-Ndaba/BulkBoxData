package bulk.box.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Product {

	private final StringProperty name; 
	private final DoubleProperty weight;

	private DoubleProperty sum;
	private StringProperty boxtype;
	private IntegerProperty quantity; 
	private StringProperty dimension; 
	private IntegerProperty id; 

	private Box box = new Box(); 
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
		//int number = i.intValue(); 
		if(number <= 0) {
			number = 1; 
		}

		if(number != 0) {
			this.quantity.set(number);
			setSumWeight(calcSum(number));
			process(number, this.getName());



			if(number == 1 && this.getName().equals("Flare 6 pack")) {
				setDimension("45 x 37 x 8"); 
			} else if(number == 1 && this.getName().equals("3LF")) {
				setDimension("38 x 32 x 48"); 
			} else if(number == 1 && this.getName().equals("5S")) {
				setDimension("60 x 60 x 58"); 
			} else if(number == 1 && this.getName().equals("10s")) {
				setDimension("60 x 60 x 74"); 
			} else if(number == 1 && this.getName().equals("15D")) {
				setDimension("60 x 65 x 74"); 
			} else {
				setDimension(box.getBox(getBoxType())); 
			}
		}
	}

	private double calcSum(int number) {
		return new BigDecimal(number * this.getWeight()).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}


	public IntegerProperty quantityProperty() {
		return this.quantity; 
	}



	public void process(int e, String s)
	{
		switch(s)
		{
		case "Flare": 
		{
			if(e == 1)
				setBoxType(Boxtype.CH1EF4P.toString()); 
			else if(e == 5)
				setBoxType(Boxtype.PAC0013.toString());
			else if(e >= 6 && e <= 15)
				setBoxType(Boxtype.PAC0005.toString()); 
			else if(e == 20)
				setBoxType(Boxtype.PAC0019.toString()); 
			else if(e == 45)
				setBoxType(Boxtype.PAC0025.toString()); 	
			else if(e == 60)
				setBoxType(Boxtype.PAC0002.toString());
			else 
				setBoxType("-");
		}
		break; 

		case "Flare 6 pack": 
		{
			if(e == 1) {
				setBoxType("-");
			} else if(e >= 2 && e <= 7)
				setBoxType(Boxtype.PAC0002.toString());
			else 
				setBoxType("-");
		}
		break;

		case "Sir Beacon 60":
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0003.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 3)
				setBoxType(Boxtype.PAC0011.toString()); 
			else if(e == 4)
				setBoxType(Boxtype.PAC0009.toString()); 
			else if(e >= 6 && e <= 10)
				setBoxType(Boxtype.PAC0005.toString()); 
			else 
				setBoxType("-");
		}
		break; 

		case "Sir Beacon / portable":
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString()); 
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString()); 
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString()); 
			else 
				setBoxType("-");
		}
		break; 


		case "Mono S":
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString()); 
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString()); 
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString()); 
			else 
				setBoxType("-");
		}
		break;

		case "Duplo s": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0006.toString()); 
			else if(e == 6)
				setBoxType(Boxtype.PAC0025.toString()); 
			else 
				setBoxType("-");
		}

		case "Mono/ Duplo Combo": 
		{
			if(e == 9)
				setBoxType(Boxtype.PAC0002.toString()); 
			else if (e == 1)
				setBoxType(Boxtype.PAC0011.toString()); 
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString()); 
			else if(e == 4)
				setBoxType(Boxtype.PAC0025.toString()); 
			else
				setBoxType("-"); 
		}
		break;

		case "1500S": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0013.toString()); 
			else if(e == 2) 
				setBoxType(Boxtype.PAC0019.toString());
			else
				setBoxType("-"); 
		}
		break;

		case "1500S Vert": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0009.toString()); 
			else
				setBoxType("-");
		}
		break;

		case "2D": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0013.toString()); 
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString()); 
			else
				setBoxType("-");
		}
		break;

		case "Hooter": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0011.toString()); 
			if(e == 9)
				setBoxType(Boxtype.PAC0002.toString()); 
			if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;

		case "Bell": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;


		case "Ban Ex S1/S2": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0010.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else
				setBoxType("-");
		}
		break;

		case "Ban EX Combo S1/S2": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0009.toString());
			else
				setBoxType("-");
		}
		break;

		case "Ban EX S3": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0004.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else
				setBoxType("-");
		}
		break;


		case "Ban Ex S3 Light": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;

		case "660HZ": 
		{
			if(e == 3)
				setBoxType(Boxtype.PAC0003.toString());
			else if(e == 1)
				setBoxType(Boxtype.PAC0010.toString());
			else
				setBoxType("-");
		}
		break;

		case "370HZ": 
		{
			if(e== 1)
				setBoxType(Boxtype.PAC0009.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;

		case "Blaster 300ml": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0030.toString());
			else if(e == 16)
				setBoxType(Boxtype.PAC0004.toString());
			else if(e == 20)
				setBoxType(Boxtype.PAC0019.toString());
			else
				setBoxType("-");
		}
		break;

		case "Blaster 40/100/135mll": 
		{
			if(e == 16)
				setBoxType(Boxtype.PAC0004.toString());
			else if(e == 20)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 1)
				setBoxType(Boxtype.PAC0031.toString());
			else
				setBoxType("-");
		}
		break;

		case "A100": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;


		case "A105": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0006.toString());
			if(e == 6)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;

		case "A112": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0013.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0002.toString());
			else
				setBoxType("-");
		}
		break;


		case "A121": 
		{
			if(e == 4)
				setBoxType(Boxtype.PAC0002.toString());
			else if(e == 1)
				setBoxType(Boxtype.PAC0009.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else
				setBoxType("-");
		}
		break;


		case "AL100": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;


		case "AL105": 
		{
			if(e == 6)
				setBoxType(Boxtype.PAC0002.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;


		case "AL112": 
		{
			if(e == 2)
				setBoxType(Boxtype.PAC0019.toString());
			else if (e == 1)
				setBoxType(Boxtype.PAC0013.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0002.toString());
			else
				setBoxType("-");
		}
		break;


		case "AL121": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0009.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0025.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0002.toString());
			else
				setBoxType("-");
		}
		break;


		case "B300": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString()); 
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break;

		case "B400": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0013.toString());
			else if(e == 2)
				setBoxType(Boxtype.PAC0025.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0002.toString());
			else
				setBoxType("-");
		}
		break;

		case "H100": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0013.toString());
			else if(e == 4)
				setBoxType(Boxtype.PAC0025.toString());
			else if(e == 8)
				setBoxType(Boxtype.PAC0002.toString());
			else
				setBoxType("-");
		}
		break;

		case "H150": 
		{
			setBoxType("-");
		}
		break;

		case "H200": 
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0002.toString()); 
			else
				setBoxType("-");
		}
		break;

		case "500SA":
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if(e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break; 

		case "1000SA":
		{
			if(e == 1)
				setBoxType(Boxtype.PAC0018.toString());
			else if(e == 6)
				setBoxType(Boxtype.PAC0019.toString());
			else if (e == 10)
				setBoxType(Boxtype.PAC0025.toString());
			else
				setBoxType("-");
		}
		break; 

		case "MA112":
		{
			if(e == 2)
				setBoxType(Boxtype.PAC0002.toString()); 
			else if(e == 4)
				setBoxType(Boxtype.PAC0025.toString()); 
			else
				setBoxType("-");
		}
		break; 

		case "MA121":
		{
			if(e == 2)
				setBoxType(Boxtype.PAC0002.toString()); 
			else
				setBoxType("-");
		}
		break; 

		case "3LF":
		{
			setBoxType("-");
		}
		break; 

		case "5S":
		{
			setBoxType("-");
		}
		break; 

		case "10s":
		{
			setBoxType("-");
		}
		break; 

		case "15D":
		{
			setBoxType("-");
		}
		break; 

		default: 
			setBoxType("-");
		}
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
}
