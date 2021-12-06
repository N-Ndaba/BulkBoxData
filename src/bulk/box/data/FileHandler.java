package bulk.box.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileHandler 
{
	private static final Pattern productHeaderPattern = Pattern.compile("\\w+.*\\t\\*[0-9]+\\.[0-9]+\\*\\t(g|kg)"); 
	
	public static Product readProduct(File productFile)
	{
		Product product = null; 
		if(!productFile.exists())
		{
			return product;
		}	
		
		Scanner scProduct = null;
		try
		{
			//Open file
			scProduct = new Scanner(productFile);
			//Check file isn't empty
			if(!scProduct.hasNext()) 
			{
				System.err.println("Error: File is empty");
				return product;
			}
			
			while(scProduct.hasNext()) 
			{
				String productHeader = scProduct.nextLine();
			
				Matcher productHeaderMatcher = productHeaderPattern.matcher(productHeader);
		
				if(!productHeaderMatcher.matches()) 
				{
					System.err.format("Product Header does not match:\r%s\n", productHeader);
				}
				else 
				{
					StringTokenizer productTokens = new StringTokenizer(productHeader, "\t");
					String productName = productTokens.nextToken();
					String strProductWeight = productTokens.nextToken();
					strProductWeight = strProductWeight.substring(1, strProductWeight.length()-1);
					double productWeight = Double.parseDouble(strProductWeight); 
					String measurement	= productTokens.nextToken();
					
					System.out.println(productName + "\t" +  productWeight + "\t" + measurement);
					product = new Product(productName, productWeight, measurement);
					
					Product products = makeEventFromString(productHeader); 
					product.addProduct(products);
				}
			}
		} 
		catch(FileNotFoundException fnfex) 
		{
			System.err.format("Error: File %s does not exist%", productFile.getAbsolutePath());
		} 
		finally 
		{
			if(scProduct != null) 
			{
				scProduct.close();
			}
		}
		return product; 
	}
	
	private static Product makeEventFromString(String productLine) 
	{
		StringTokenizer productTokens = new StringTokenizer(productLine, "\t");
		String productName = productTokens.nextToken();
		String strProductWeight = productTokens.nextToken();
		strProductWeight = strProductWeight.substring(1, strProductWeight.length()-1);
		double productWeight = Double.parseDouble(strProductWeight); 
		String measurement	= productTokens.nextToken();
		
		Product product = new Product(productName, productWeight, measurement);
		return  product;
	}		
}
