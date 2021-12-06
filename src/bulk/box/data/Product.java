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
	
	private Product[] products;
	
	
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
		this.products = new Product[0];	
	}
	
	public Product getProduct(int index) {
	      if (index >= 0 && index < this.products.length) {
	         return this.products[index];
	      } else {
	         System.err.println("Invalid index: " + index);
	         return null;
	      }
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
					return "CH1EF4D | CH1EF4P";
				else if(this.quantity >= 6 && this.quantity <= 15)
					return "PAC0005";
				else if(this.quantity == 20)
					return "PAC0019";
				else if(this.quantity == 45)
					return "PAC0025";	
				else if(this.quantity == 60)
					return "PAC0002"; 
			}
			break; 
			
			case "Flare 6 pack": 
			{
				if(this.quantity >= 2 && this.quantity <= 7)
					return "PAC0002";
			}
			break;
			
			case "Ban Beacon":
			{
				if(this.quantity == 1)
					return "PAC0003";
				else if(this.quantity > 1 && this.quantity <= 4)
					return "PAC0010";
				else if(this.quantity >= 6 && this.quantity <= 10)
					return "PAC0005";
				else if(this.quantity == 10)
					return "PAC0004"; 
				else if(this.quantity == 8)
					return "PAC0008";
				else if(this.quantity == 25)
					return "PAC0025";
			}
			break; 
			
			case "Sir Beacon / portable":
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019"; 
				else if(this.quantity == 10)
					return "PAC0025";	
			}
			break; 
			
			
			case "Mono S":
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019"; 
				else if(this.quantity == 10)
					return "PAC0025";
			}
			break;
			
			case "Duplo S": 
			{
				if(this.quantity == 1)
					return "PAC0005";
				else if(this.quantity == 6)
					return "PAC0025";
			}
			
			case "Mono/ Duplo Combo": 
			{
				if(this.quantity == 9)
					return "PAC0002";
				else if (this.quantity == 1)
					return "PAC0010";
				else if(this.quantity == 2)
					return "PAC0019";
				else if(this.quantity == 4)
					return "PAC0025"; 
			}
			break;
			
			case "1500S": 
			{
				if(this.quantity == 1)
					return "PAC0013";
				else if(this.quantity == 2) 
					return "PAC0018 | PAC0019";
			}
			break;
			
			case "1500S Vert": 
			{
				if(this.quantity == 1)
					return "PAC0009";
			}
			break;
			
			case "2D": 
			{
				if(this.quantity == 1)
					return "PAC0013";
				else if(this.quantity == 2)
					return "PAC0018 | PAC0019"; 
			}
			break;
			
			case "Hooter": 
			{
				if(this.quantity == 1)
					return "PAC0010 | PAC0011";
				if(this.quantity == 9)
					return "PAC0002";
				if(this.quantity == 2)
					return "PAC0018";
				else if(this.quantity == 4)
					return "PAC0025";  
			}
			break;
			
			case "Bell": 
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019";
			}
			break;
			
			
			case "Ban Ex S1/S2": 
			{
				if(this.quantity == 1)
					return "PAC0010";
			}
			break;
			
			case "Ban EX Combo S1/S2": 
			{
				if(this.quantity == 1)
					return "PAC0009";
				else if(this.quantity == 24)
					return "PAC0024";
			}
			break;
			
			case "Ban EX S3": 
			{
				if(this.quantity == 1)
					return "PAC0004";
				else if(this.quantity == 2)
					return "PAC0018";
			}
			break;
			
			
			case "Ban Ex S3 Light": 
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019";
			}
			break;
			
			case "660HZ": 
			{
				if(this.quantity == 3)
					return "PAC0003";
				else if(this.quantity == 1)
					return "PAC0010 | PAC0013";
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0017");
			}
			break;
			
			case "370HZ": 
			{
				if(this.quantity == 1)
					return "PAC009 | PAC0019";
				else if(this.quantity == 2)
					return "PAC0025";
			}
			break;
			
			case "Blaster 300ml": 
			{
				if(this.quantity == 1)
					return "PAC0030";
				else if(this.quantity == 16)
					return "PAC0004";
			}
			break;
			
			case "Blaster 40/100/135mll": 
			{
				if(this.quantity == 16)
					return "PAC0004";
				else if(this.quantity == 20)
					return "PAC0018";
				else if(this.quantity == 1)
					return "PAC0031";;
			}
			break;
			
			case "A100": 
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019";
				else if(this.quantity == 10)
					return "PAC0025"; 
			}
			break;
			
			
			case "A105": 
			{
				if(this.quantity == 6)
					return "PAC0018";
			}
			break;
			
			case "A112": 
			{
				if(this.quantity == 1)
					return "PAC0013";
				else if(this.quantity == 2)
					return "PAC0018";
				else if(this.quantity == 4)
					return "PAC0025";
				else if(this.quantity == 6)
					return "PAC0002";
			}
			break;
			
			
			case "A121": 
			{
				if(this.quantity == 4)
					return "PAC0002";
				else if(this.quantity == 1)
					return ("PAC0025");
			}
			break;
			
			
			case "AL100": 
			{
				if(this.quantity == 6)
					return "PAC0018";
			}
			break;
			
			
			case "AL105": 
			{
				if(this.quantity == 1)
					return "PAC0021 | PAC0022";
				else if(this.quantity == 2)
					return "PAC0018";
				else if(this.quantity == 4)
					return "PAC0025";
				else if(this.quantity == 6)
					return "PAC0002";
			}
			break;
			
			
			case "AL112": 
			{
				if(this.quantity == 2)
					return "PAC002";
				else if (this.quantity == 1)
					return "PAC0013"; 
				else if(this.quantity == 4)
					return "PAC0025";
			}
			break;
			
			
			case "AL121": 
			{
				if(this.quantity == 1)
					return "PAC0009";
				else if(this.quantity == 2)
					return "PAC0002 | PAC0018";
			}
			break;
			
			
			case "B300": 
			{
				if(this.quantity == 1)
					return "PAC0018";  
				else if(this.quantity == 4)
					return "PAC0019"; 
				else if(this.quantity == 10)
					return  "PAC0025";
			}
			break;
			
			case "B400": 
			{
				if(this.quantity == 1)
					return "PAC0013";
				else if(this.quantity == 2)
					return "PAC0025";
				else if(this.quantity == 4)
					return "PAC0002"; 
			}
			break;
			
			case "H100": 
			{
				if(this.quantity == 1)
					return "PAC0013"; 
				else if(this.quantity == 4)
					return "PAC0025";
				else if(this.quantity == 8)
					return "PAC0002"; 
			}
			break;
			
			case "H150": 
			{
				
			}
			break;
			
			case "H200": 
			{
				if(this.quantity == 1)
					return "PAC0002"; 
			}
			break;
			
			case "500SA":
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019";
				else if(this.quantity == 10)
					return "PAC0025";
			}
			break; 
			
			case "1000SA":
			{
				if(this.quantity == 1)
					return "PAC0018";
				else if(this.quantity == 6)
					return "PAC0019"; 
				else if (this.quantity == 10)
					return "PAC0025"; 
			}
			break; 
			
			case "MA112":
			{
				if(this.quantity == 2)
					return "PAC0002"; 
				else if(this.quantity == 4)
					return "PAC0025"; 
			}
			break; 
			
			case "MA121":
			{
				if(this.quantity == 2)
					return "PAC0002";
			}
		}
		return "No box available";
	}
	
	public String getBoxType()
	{ 
		return this.boxType; 
	}
	
	public void addProduct(Product product)
	{	
		Product[] tempProducts = new Product[this.products.length + 1];
	    System.arraycopy(this.products, 0, tempProducts, 0, this.products.length);
	    tempProducts[tempProducts.length - 1] = product;
	    this.products = tempProducts;
	}
	
	public Product[] getProducts() 
	{
		return this.products;
	}
}
