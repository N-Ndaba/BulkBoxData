package bbd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler 
{
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
	
	public void readBoxData() throws IOException 
	{
			try ( 
					DataInputStream input =	new DataInputStream(new FileInputStream("data/bulkboxdata.dat"));
			) {
			
			System.out.println(input.readUTF());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			System.out.println(input.readInt());
			}
	}
}
