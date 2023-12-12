package pixel_art;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Grafica {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Alex Lacueva Ventura 2123235");
		frame.setVisible(true);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    

	      
	      
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		
		JLabel server = new JLabel("Server Address");
		JLabel port = new JLabel("Port");
		JLabel numero = new JLabel("Numero:");
		
		JTextArea textServer = new JTextArea(1, 20);
		JTextArea textPort = new JTextArea(1,10);
		JTextArea textNumero = new JTextArea(1,5);

		JList<String> JlistLog = new JList<String>();
		JScrollPane scrollLog = new JScrollPane(JlistLog);
		
		
		TitledBorder title = BorderFactory.createTitledBorder("Log");
		scrollLog.setBorder(title);
	
		JButton connect = new JButton("connect");
		JButton disconnect = new JButton("disconnect");
		JButton get = new JButton("get");
		JButton reset = new JButton("reset");
		
		disconnect.setEnabled(false);
		get.setEnabled(false);
		reset.setEnabled(false);
		
		
		JButton[] pixelArt = new JButton[25];		
        JPanel gridPanel = new JPanel(new GridLayout(5, 5));

 
        int buttonSize = 100;  
      
		for (int i = 0; i<25; i++)
		{
			pixelArt[i] = new JButton();
			pixelArt[i].setPreferredSize(new Dimension(buttonSize, buttonSize));
			pixelArt[i].setEnabled(false);
			pixelArt[i].setBackground(Color.WHITE);
			gridPanel.add(pixelArt[i]);									
		}
		
		
		Ascoltatore ascolta = new Ascoltatore(textServer, textPort, textNumero, 
				connect, disconnect, get, reset, scrollLog, pixelArt, JlistLog);		
		connect.addActionListener(ascolta);
		disconnect.addActionListener(ascolta);
		get.addActionListener(ascolta);
		reset.addActionListener(ascolta);
		
		north.add(server);
		north.add(textServer);
		north.add(port);
		north.add(textPort);
		north.add(connect);
		north.add(disconnect);
		
		center.add(gridPanel);
		center.add(scrollLog);
		
		south.add(numero);
		south.add(textNumero);
		south.add(get);
		south.add(reset);
		
		
		frame.add(north, BorderLayout.NORTH);
		frame.add(center,BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);
		
		frame.pack();		
		

		
		
		
				
		
		
		
		
		
		
	}

}
