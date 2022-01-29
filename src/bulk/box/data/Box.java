package bulk.box.data;

public class Box 
{	
	public String getBox(String name)
	{
		switch(name)
		{
			case "CH1EF4D":
				return "110 x 80 x 110";  
				
			case "PAC0001":
				return "110 x 60 x 70";
				
			case "PAC0002":
				return "460 x 390 x 590";
				
			case "PAC0003":
				return "120 x 120 x 130";  
				
			case "PAC0004":
				return "230 x 120 x 230";
				
			case "PAC0005":
				return "650 x 280 x 130";
			
			case "PAC0006":
				return "270 x 150 x 150";
				
			case "PAC0009":
				return "270 x 260 x 220";
				
			case "PAC0010":
				return "280 x 210 x 250";  
				
			case "PAC0011":
				return "390 x 150 x 140";
				
			case "PAC0012":
				return "120 x 120 x 130";
			
			case "PAC0013":
				return "270 x 220 x 220";
			
			case "PAC0018":
				return "200 x 140 x 140";
				
			case "PAC0019":
				return "430 x 260 x 220";
				
			case "PAC0022":
				return "230 x 150 x 150";  
				
			case "PAC0025":
				return "390 x 320 x 480";
				
			case "PAC0027":
				return "460 x 460 x 520";
			
			case "PAC0031":
				return "290 x 60 x 60";  
				
			case "PAC0032":
				return "240 x 60 x 60";
				
			case "PACACD03":
				return "120 x 120 x 130";
			
			default: 
				return "-"; 
		} 
	}
}
