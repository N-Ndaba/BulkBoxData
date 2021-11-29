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
					System.out.println("BOX TYPE: CH1EF4D or CH1EF4P");
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
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Ban Beacon": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "Sir Beacon / portable": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Mono S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Duplo S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Mono/ Duplo Combo": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "1500S": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "1500S Vert": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "2D": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Hooter": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Bell": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "Ban Ex S1/S2": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Ban EX Combo": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Ban EX S3": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "Ban Ex S3 Light": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "660HZ": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "370HZ": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Blaster 300ml": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "Blaster 40/100/135mll": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "A100": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "A105": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			case "A112": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "A121": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "AL100": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "AL105": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
			}
			break;
			
			
			case "AL112": 
			{
				if(this.quantity == 1)
					System.out.println("BOX: CH1EF4D or CH1EF4P");
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
