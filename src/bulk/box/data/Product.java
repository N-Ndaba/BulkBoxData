package bulk.box.data;

import java.io.File;
import java.util.ArrayList;

public class Product 
{
	private String name; 
	private int quantity; 
	private String boxType; 
	private double weight;
	private String measurement; 
	
	
	public Product(String name, int quantity)
	{ 
		this.name = name; 
		this.quantity = quantity; 
	}
	
	public Product(String name, double weight, String measurement)
	{
		this.name = name; 
		this.weight = weight; 
		this.measurement = measurement; 	
	}
	
	public int getQuantity()
	{
		return this.quantity; 
	}
	
	public double getWeight()
	{
		return this.weight; 
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity; 
	}
	
	public void setName(String name)
	{
		this.name = name; 
	}
	
	public String getName()
	{
		return this.name; 
	}
	
	
	public String process()
	{
		switch(this.name)
		{
			case "Flare": 
			{
				if(this.quantity == 1)
					return Boxtype.CH1EF4D.toString() + " | " + Boxtype.CH1EF4P.toString();  
				else if(this.quantity >= 6 && this.quantity <= 15)
					return Boxtype.PAC0005.toString(); 
				else if(this.quantity == 20)
					return Boxtype.PAC0019.toString(); 
				else if(this.quantity == 45)
					return Boxtype.PAC0025.toString(); 	
				else if(this.quantity == 60)
					return Boxtype.PAC0002.toString(); ; 
			}
			break; 
			
			case "Flare 6 pack": 
			{
				if(this.quantity >= 2 && this.quantity <= 7)
					return Boxtype.PAC0002.toString(); 
			}
			break;
			
			case "Sir Beacon 60":
			{
				if(this.quantity == 1)
					return Boxtype.PAC0003.toString(); 
				else if(this.quantity > 1 && this.quantity <= 4)
					return Boxtype.PAC0010.toString(); 
				else if(this.quantity >= 6 && this.quantity <= 10)
					return Boxtype.PAC0005.toString(); 
				else if(this.quantity == 10)
					return Boxtype.PAC0004.toString();  
				else if(this.quantity == 25)
					return Boxtype.PAC0025.toString(); 
			}
			break; 
			
			case "Sir Beacon / portable":
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString(); 
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString(); 
				else if(this.quantity == 10)
					return Boxtype.PAC0025.toString(); 
			}
			break; 
			
			
			case "Mono S":
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString(); 
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString(); 
				else if(this.quantity == 10)
					return Boxtype.PAC0025.toString(); 
			}
			break;
			
			case "Duplo s": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0005.toString(); 
				else if(this.quantity == 6)
					return Boxtype.PAC0025.toString(); 
			}
			
			case "Mono/ Duplo Combo": 
			{
				if(this.quantity == 9)
					return Boxtype.PAC0002.toString(); 
				else if (this.quantity == 1)
					return Boxtype.PAC0010.toString(); 
				else if(this.quantity == 2)
					return Boxtype.PAC0019.toString(); 
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString(); 
			}
			break;
			
			case "1500S": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0013.toString(); 
				else if(this.quantity == 2) 
					return  Boxtype.PAC0018.toString() + " | " + Boxtype.PAC0019.toString(); 
			}
			break;
			
			case "1500S Vert": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0009.toString(); 
			}
			break;
			
			case "2D": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0013.toString(); 
				else if(this.quantity == 2)
					return Boxtype.PAC0018.toString() + " | " + Boxtype.PAC0019.toString(); 
			}
			break;
			
			case "Hooter": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0010.toString() + " | " + Boxtype.PAC0011.toString(); 
				if(this.quantity == 9)
					return Boxtype.PAC0002.toString(); 
				if(this.quantity == 2)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString();
			}
			break;
			
			case "Bell": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString(); 
			}
			break;
			
			
			case "Ban Ex S1/S2": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0010.toString(); 
			}
			break;
			
			case "Ban EX Combo S1/S2": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0009.toString();
			}
			break;
			
			case "Ban EX S3": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0004.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0018.toString();
			}
			break;
			
			
			case "Ban Ex S3 Light": 
			{
				if(this.quantity == 1)
					return  Boxtype.PAC0018.toString();
				else if(this.quantity == 6)
					return  Boxtype.PAC0019.toString();
			}
			break;
			
			case "660HZ": 
			{
				if(this.quantity == 3)
					return  Boxtype.PAC0003.toString();
				else if(this.quantity == 1)
					return Boxtype.PAC0010.toString() + " | " +  Boxtype.PAC0013.toString();
			}
			break;
			
			case "370HZ": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0009.toString() + " | " +  Boxtype.PAC0019.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0025.toString();
			}
			break;
			
			case "Blaster 300ml": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0030.toString();
				else if(this.quantity == 16)
					return Boxtype.PAC0004.toString();
			}
			break;
			
			case "Blaster 40/100/135mll": 
			{
				if(this.quantity == 16)
					return Boxtype.PAC0004.toString();
				else if(this.quantity == 20)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 1)
					return Boxtype.PAC0031.toString();
			}
			break;
			
			case "A100": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString();
				else if(this.quantity == 10)
					return Boxtype.PAC0025.toString();
			}
			break;
			
			
			case "A105": 
			{
				if(this.quantity == 6)
					return Boxtype.PAC0018.toString();;
			}
			break;
			
			case "A112": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0013.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0002.toString();
			}
			break;
			
			
			case "A121": 
			{
				if(this.quantity == 4)
					return Boxtype.PAC0002.toString();
				else if(this.quantity == 1)
					return Boxtype.PAC0025.toString();
			}
			break;
			
			
			case "AL100": 
			{
				if(this.quantity == 6)
					return Boxtype.PAC0018.toString();
			}
			break;
			
			
			case "AL105": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0021.toString() + " | "+ Boxtype.PAC0022.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0002.toString();
			}
			break;
			
			
			case "AL112": 
			{
				if(this.quantity == 2)
					return Boxtype.PAC0002.toString();
				else if (this.quantity == 1)
					return Boxtype.PAC0013.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString();
			}
			break;
			
			
			case "AL121": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0009.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0002.toString() + " | " + Boxtype.PAC0018.toString();
			}
			break;
			
			
			case "B300": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString(); 
				else if(this.quantity == 4)
					return Boxtype.PAC0019.toString();
				else if(this.quantity == 10)
					return  Boxtype.PAC0025.toString();
			}
			break;
			
			case "B400": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0013.toString();
				else if(this.quantity == 2)
					return Boxtype.PAC0025.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0002.toString(); 
			}
			break;
			
			case "H100": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0013.toString();
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString();
				else if(this.quantity == 8)
					return Boxtype.PAC0002.toString(); 
			}
			break;
			
			case "H150": 
			{
				
			}
			break;
			
			case "H200": 
			{
				if(this.quantity == 1)
					return Boxtype.PAC0002.toString(); 
			}
			break;
			
			case "500SA":
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString();
				else if(this.quantity == 10)
					return Boxtype.PAC0025.toString();
			}
			break; 
			
			case "1000SA":
			{
				if(this.quantity == 1)
					return Boxtype.PAC0018.toString();
				else if(this.quantity == 6)
					return Boxtype.PAC0019.toString();
				else if (this.quantity == 10)
					return Boxtype.PAC0025.toString();
			}
			break; 
			
			case "MA112":
			{
				if(this.quantity == 2)
					return Boxtype.PAC0002.toString(); 
				else if(this.quantity == 4)
					return Boxtype.PAC0025.toString(); 
			}
			break; 
			
			case "MA121":
			{
				if(this.quantity == 2)
					return Boxtype.PAC0002.toString(); 
			}
		}
		return "No box available";
	}
	
	public String getBoxType()
	{ 
		return this.boxType; 
	}
}
