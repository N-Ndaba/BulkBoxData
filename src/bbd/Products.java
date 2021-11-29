package bbd;

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
	
	public void process()
	{
		/*if(this.name.equals("Flare") &&  this.quantity == 1)
		{
			this.boxType = "BOX: CH1EF4D or CH1EF4P"; 
			System.out.println(this.boxType);
		}*/
		
		
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
				if(this.quantity == 1)
					boxType = Box.CH1EF4D.toString(); 
				System.out.println(boxType);
			}
			break;
			
			
			case "AL121": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "B300": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "B400": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "H100": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "H150": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "H200": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
		}
	}
}
