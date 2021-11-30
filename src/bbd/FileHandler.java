package bbd;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileHandler 
{
	private static List<Products> list = new ArrayList<Products>(); 
	
	public static void saveToFile(String boxCode, int length, int weight, int height)
	{
		list.add(new Products("1",1 )); 
		
		DataOutputStream output =  null;
		try {
			output = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data/bulkboxdata.dat", true)));

			output.writeUTF(boxCode);
			output.writeInt(length);
			output.writeInt(weight);
			output.writeInt(height);
		}
		catch(IOException io)
		{
			io.printStackTrace(); 
		}
		finally 
		{
		   if(output != null)
		   {
			   try
			   {
				   output.close();
			   }
			   catch(IOException e)
			   {
				   e.printStackTrace(); 
			   }
		   }
		}	
	}
	
	public void fileHandler(String boxCode, int length, int weight, int height) throws IOException 
	{
		try ( 
				DataOutputStream output = new DataOutputStream(new FileOutputStream("data/bulkboxdata.dat"));
		    ) 
		{
			output.writeUTF(boxCode);
			output.writeInt(length);
			output.writeInt(weight);
			output.writeInt(height);
		}
	}
	
	public static void readBoxData() throws IOException 
	{
			try ( 
					DataInputStream input =	new DataInputStream(new FileInputStream("data/bulkboxdata.dat"));
			) {
			
				
					System.out.println(input.readUTF());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			
			System.out.println(input.readUTF());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			
			System.out.println(input.readUTF());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			}
	}
	
	public static void main(String[] args) {
		
		saveToFile("Luke", 1, 34, 4); 
		try {
			readBoxData();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
