package bulk.box.data;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Total  
{
	private static double total;
	private GridPane grid;
	
	public Total(GridPane grid)
	{
		this.grid = grid;
	}
	
	public static void calcSumTotal(GridPane grid)
	{
		/*for(Node node : grid.getChildren())
		{
			if(node instanceof TextField &&  ((TextField) node).getId() == "O")
			{
				total += Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
			}
			
			if(node instanceof TextField &&  ((TextField) node).getId() == "O")
			{
				total -= Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
			}
		}
		//txtSum.setText(String.valueOf(String.format("%.2f", Sum)).replace(",", "."));
		
		System.out.println("-) " + total);*/						
		new Thread(( ) -> 
		{
			try
			{
				while(true)
				{
					Platform.runLater(() ->
					{
						for(Node node : grid.getChildren())
						{
							if(node instanceof TextField &&  ((TextField) node).getId() == "O")
							{
								total += Double.parseDouble(((TextField) node).getText().replace(",", ".")); 
							}
							System.out.println(">" + (String.valueOf(String.format("%.2f", total)).replace(",", ".")));
						}
					});
					Thread.sleep(200);
				}
			}
			catch(InterruptedException ex)
			{
				
			}
		}).start();
		
		
	}
	
	public static double getT()
	{
		return total; 
	}
}
