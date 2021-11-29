import java.io.IOException;
import java.util.Scanner;

import bbd.FileHandler;
import bbd.Products;

public class Main 
{
	public static void main(String[] args) 
	{
		FileHandler fileHandler = new FileHandler(); 
		
		
		
		
		
		Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();
        int inputQuantity = scanner.nextInt();
        Products products = new Products(inputName, inputQuantity); 
        products.process();
       // System.out.println("Box Name: " + inputName + " & Quantity: " + inputQuantity);
		
		/*try {
			fileHandler.fileHandler("eKSE", 1, 2, 3);
			fileHandler.fileHandler("Molo", 21, 22, 3);
			//System.out.println("Hallo");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			fileHandler.readBoxData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        scanner.close();
	}
}
