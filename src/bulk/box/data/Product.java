package bulk.box.data;

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
	
	Box box = new Box(); 

	
	public Product(String name, double weight) {
		this.name = new SimpleStringProperty(this, "name", name); 
		this.weight = new SimpleDoubleProperty(this, "weight", weight); 
		this.quantity = new SimpleIntegerProperty(this, "quantity", 1); 
		this.boxtype = new SimpleStringProperty(this, "boxtype");
		this.sum = new SimpleDoubleProperty(this, "sum", weight); 
		this.dimension = new SimpleStringProperty(this, "dimension", ""); 
		setQuantity(1);
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
	
	public final StringProperty nameProperty() {
		return this.name; 
	}
	
	public final Double getWeight() {
		return this.weight.get(); 
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
			setSumWeight(number * this.getWeight());
			process(number, this.getName());
			
			setDimension(box.getBox(getBoxType())); 
		}
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
					setBoxType(Boxtype.CH1EF4D.toString()); 
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
				if(e >= 2 && e <= 7)
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
			}
			 
			default: 
				setBoxType("-");
		}
	}
}
