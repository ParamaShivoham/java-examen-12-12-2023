import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

public class Client {

	Socket socket = null;
	
	Scanner inputStream = null;
	
	PrintWriter outputStream = null;

	
	public void connect(String ip, int port)
	{
		try {
			socket = new Socket(ip, port);
			inputStream = new Scanner(socket.getInputStream());
			outputStream = new PrintWriter(socket.getOutputStream());			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void disonnect()
	{
		outputStream.print("disconnect");		
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Vector<String[]> start()
	{
		System.out.println("estoy en client start");

		outputStream.println("start");
		outputStream.flush();
		
		
		Vector<String[]> inputVector = new Vector<String[]>();
		while(inputStream.hasNext() == true) {			
						
			String temporal = inputStream.nextLine();
			System.out.println(temporal);
			if (temporal.equals("END"))
			{
				break;
			}
			else if(temporal.equals("INTERRUPTED"))
			{
				inputVector = null;
				return inputVector;
			}
			else {
				inputVector.add(temporal.split(":") );
			}
						
		}
				
		System.out.println(inputVector);		
		return inputVector;
		
	}
	
	public void stop()
	{
		outputStream.println("stop");
		outputStream.flush();
		
		
	}
	
	
}
