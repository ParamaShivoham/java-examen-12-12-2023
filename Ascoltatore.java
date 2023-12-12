package pixel_art;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.*;
import javax.swing.*;

public class Ascoltatore implements ActionListener   {

	Client client;
	JTextArea textServer; 
	JTextArea textPort; 
	JTextArea textNumero;
	String carteServer;
	Vector<String> vectlog = new Vector<String>();
			
	Boolean getEnFuncionamiento = false;
	Boolean resetApretado = false;
	
	
	//ArrayList<String> listLog = new ArrayList<String>();

	
	private JButton connect; //GENERADO POR ECLIPSE. CREAR PRIMERO THIS
	private JButton disconnect;
	private JButton get;
	private JButton reset;
	private JScrollPane scrollLog;
	private JButton[] pixelArt;
	private JList<String> JlistLog;
	
	SwingWorker<String, Void> swingWorker = null;

	public Ascoltatore(JTextArea textServer, JTextArea textPort, JTextArea textNumero,
		JButton connect, JButton disconnect, JButton get, JButton reset, JScrollPane scrollLog, JButton[] pixelArt, JList<String> JlistLog) {		
				
		this.textServer = textServer;
		this.textPort = textPort;
		this.textNumero = textNumero;
		this.connect = connect;
		this.disconnect = disconnect;
		this.get = get;
		this.reset = reset;
		this.scrollLog = scrollLog;
		this.pixelArt = pixelArt;
		this.JlistLog = JlistLog;
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) { //CREADO DESDE GRAFICA CON ECLIPSE

		if( e.getSource() == connect ) // COMPARO OBJETOS
		{
			connect.setEnabled(false);
			
			client = new Client(textServer.getText(), textPort.getText());
			client.Connect();
			
			disconnect.setEnabled(true);
			get.setEnabled(true);
			reset.setEnabled(true);
			
		}
		
		else if ( e.getSource() == disconnect )
		{
			disconnect.setEnabled(false);
			
			client.Disconnect();
			
			connect.setEnabled(true);
			
		}
		
		else if ( e.getSource() == get)
		{
				
			swingWorker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {
					getEnFuncionamiento = true;
					get.setEnabled(false);
					disconnect.setEnabled(false);
					
					int numeroGet = Integer.parseInt(textNumero.getText());
					
					if(numeroGet > 0 && numeroGet < 10)
					{carteServer = client.Get(numeroGet);}
					else
					{
						JOptionPane.showMessageDialog(new JFrame(), "Il numero è fuori dei limiti.");
					}
										
					return carteServer;
				}
				
				protected void done() {
										
				if (resetApretado == true)
				{
					resetApretado = false;
				}
				else 
				{
					vectlog.add(carteServer);
					JlistLog.setListData(vectlog);
					for (int i = 2; i<27 ; i++)
					{
						pixelArt[i-2].setBackground(Color.WHITE);
						if (carteServer.charAt(i) == '1' )
						{
							pixelArt[i-2].setBackground(Color.BLACK);
						}
					}	
				}
			
					
					
					get.setEnabled(true);
					disconnect.setEnabled(true);
					
					getEnFuncionamiento = false;

				
					//IMPORTANTE hecho con Listas
					//listLog.add(carteServer);
					//JlistLog.setListData(listLog.toArray(new String[listLog.size()]));
										
											
				}
				
			
			};
			
			swingWorker.execute(); 
			
			//crear primero esta linea. luego con error crear declaracion. pasar parametros del return y corchetes
			// crear la clase en la misma linea. y de ahí crear el void done
			
		}
		
		else if ( e.getSource() == reset )
		{
			
			
			if(getEnFuncionamiento == true)
			{
				resetApretado = true;
			}

			
			textNumero.setText("");
			
			for (int i = 0; i<25 ; i++)
			{
				pixelArt[i].setBackground(Color.WHITE);
			}
			
			//RESETEAR EL LOG
			
			vectlog = new Vector<String>();
			JlistLog.setListData(new Vector<String>());
			
		}

	}
	
	
	
	
}
