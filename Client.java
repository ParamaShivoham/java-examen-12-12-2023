package pixel_art;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Client {

	Socket socket = null;
	PrintWriter output;
	Scanner input;
	String carteServer = null;
	String ip;
	int port;
	
	public Client() {
		
	}
	
	public Client(String ip, String port) {
		
		this.ip = ip;			
		this.port = Integer.parseInt(port);		
	}
	
	public void Connect()
	{
		try {
			socket = new Socket(ip, port);
			input = new Scanner(socket.getInputStream());
			output = new PrintWriter(socket.getOutputStream());
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void Disconnect()
	{
		output.println("DISCONNECT");
		output.flush();
		try {
			socket.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public String Get(int numeroGet)
	{		
		
		output.println("GET:" + numeroGet);
		output.flush();
		
		
		carteServer = input.nextLine();
	
		if( !input.nextLine().equals("END") )
		{
			System.out.println("Error END not received");
		}
	
		return carteServer;
		
	}
	
	
	
}
