package bulk.box.data;

public class Products 
{
	private String name; 
	private int quantity; 
	private String boxType; 
	
	public Products(String name, int quantity)
	{
		this.name = name; 
		this.quantity = quantity; 
	}
	
	public int getQuantity()
	{
		return this.quantity; 
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
				else if(this.quantity >= 5 && this.quantity <= 15)
					return "PAC0002";
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
					return "PAC0001";
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
					return "PAC0017";
				else if(this.quantity == 6)
					return "PAC0019"; 
				else if(this.quantity == 10)
					return "PAC0024";	
			}
			break; 
		}
		return "No box available";
	}
	
	public String getBoxType()
	{ 
		return this.boxType; 
	}
	
	/*public void process()
	{
		switch(this.name)
		{
			case "Flare": 
			{
				if(this.quantity == 1)
					System.out.println("BOX TYPE: CH1EF4D | CH1EF4P");
				else if(this.quantity == 15)
					System.out.println("BOX TYPE: PAC0004");
				else if(this.quantity == 20)
					System.out.println("BOX TYPE: PAC0018");
				else if(this.quantity == 45)
					System.out.println("BOX TYPE: PAC0024");
			}
			break;
			
			case "Flare 6 pack": 
			{
				//if(this.quantity == 1)
					//System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Ban Beacon": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0002 | PAC0011 | PACACD02");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0004");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0008");
				else if(this.quantity == 25)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "Sir Beacon / portable": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0017");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "Mono S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0017");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0018"); 
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "Duplo S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC009");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "Mono/ Duplo Combo": 
			{
				if(this.quantity == 9)
					System.out.println("BOX: PAC0001");
				else if (this.quantity == 1)
					System.out.println("BOX: PAC0010");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0017");
			}
			break;
			
			case "1500S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0012");
				else if(this.quantity == 2) 
					System.out.println("Box: PAC0018");
			}
			break;
			
			case "1500S Vert": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0008");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "2D": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0012");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0018");
			}
			break;
			
			case "Hooter": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0010");
				if(this.quantity == 9)
					System.out.println("BOX: PAC0001");
				if(this.quantity == 2)
					System.out.println("BOX: PAC0018");
			}
			break;
			
			case "Bell": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0017");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "Ban Ex S1/S2": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0009");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0017");
			}
			break;
			
			case "Ban EX Combo S1/S2": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0008");
				else if(this.quantity == 24)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "Ban EX S3": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0003");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0018");
			}
			break;
			
			
			case "Ban Ex S3 Light": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0017");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0018");
			}
			break;
			
			case "660HZ": 
			{
				if(this.quantity == 3)
					System.out.println("BOX: PAC0003");
				else if(this.quantity == 1)
					System.out.println("BOX: PAC0012");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0017");
			}
			break;
			
			case "370HZ": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "Blaster 300ml": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0030");
				else if(this.quantity == 16)
					System.out.println("BOX: PAC0004");
			}
			break;
			
			case "Blaster 40/100/135mll": 
			{
				if(this.quantity == 16)
					System.out.println("BOX: PAC0004");
				else if(this.quantity == 20)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 1)
					System.out.println("BOX: PAC0031");
			}
			break;
			
			case "A100": 
			{
				if(this.quantity == 10)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 20)
					System.out.println("BOX: PAC0020");
			}
			break;
			
			
			case "A105": 
			{
				if(this.quantity == 6)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			case "A112": 
			{
				if(this.quantity == 6)
					System.out.println("BOX: PAC0001");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 4)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "A121": 
			{
				if(this.quantity == 4)
					System.out.println("BOX: PAC0001");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "AL100": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0017");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 10)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "AL105": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: PAC0021");
				else if(this.quantity == 2)
					System.out.println("BOX: PAC0018");
				else if(this.quantity == 6)
					System.out.println("BOX: PAC0024");
			}
			break;
			
			
			case "AL112": 
			{
				if(this.quantity == 3)
					boxType = Box.PAC0001.toString(); 
				else if(this.quantity == 1)
					boxType = Box.PAC0012.toString(); 
				else if(this.quantity == 2)
					boxType = Box.PAC0018.toString(); 
			}
			break;
			
			
			case "AL121": 
			{
				if(this.quantity == 2)
					boxType = Box.PAC0001.toString(); 
				else if(this.quantity == 1)
					boxType = "PAC0008";
				else if(this.quantity == 2)
					boxType = "PAC0018";
			}
			break;
			
			
			case "B300": 
			{
				if(this.quantity == 6)
					boxType = "PAC0024";  
			}
			break;
			
			case "B400": 
			{
				if(this.quantity == 4)
					boxType = "PAC0001"; 
				else if(this.quantity == 2)
					boxType = "PAC0024";
			}
			break;
			
			case "H100": 
			{
				if(this.quantity == 8)
					boxType = "PAC0001"; 
			}
			break;
			
			case "H150": 
			{
				
			}
			break;
			
			case "H200": 
			{
				if(this.quantity == 2)
					boxType = "PAC0001"; 
			}
			break;
		}
	}*/
}
