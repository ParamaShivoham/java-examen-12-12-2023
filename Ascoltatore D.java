import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class Ascoltatore implements ActionListener {

	
	
	private JTextArea servertext;
	private JTextArea porttext;
	private JTextArea dimensionetext;
	private JButton connectbutton;
	private JButton disconnectbutton;
	private JButton startbutton;
	private JButton stopbutton;
	private JButton clearbutton;
	private JList Jloglist;
	private JList Jpdflist;
	private JList Jmp3list;
	
	Vector<String[]> receivedVector = new Vector<String[]>();
	Vector<String> logvector = new Vector<String>();
	Vector<String> pdfvector = new Vector<String>();
	Vector<String> mp3vector = new Vector<String>();
	
	Client client;

	public Ascoltatore(JTextArea servertext, JTextArea porttext, JTextArea dimensionetext, JButton connectbutton, JButton disconnectbutton,
			JButton startbutton, JButton stopbutton, JButton clearbutton, JList Jloglist, JList Jpdflist, JList Jmp3list) {
		
		this.servertext = servertext;
		this.porttext =porttext;
		this.dimensionetext = dimensionetext;
		this.connectbutton = connectbutton;
		this.disconnectbutton= disconnectbutton;
		this.startbutton= startbutton;
		this.stopbutton= stopbutton;
		this.clearbutton= clearbutton;
		this.Jloglist= Jloglist;
		this.Jpdflist= Jpdflist;
		this.Jmp3list= Jmp3list;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == connectbutton)
		{
			client = new Client();
			client.connect(servertext.getText(), Integer.parseInt(porttext.getText()));
			System.out.println("estoy en connectutton");

			
		}
		if (e.getSource() == disconnectbutton) {
			System.out.println("estoy en disconnect");

			client.disonnect();
		}
		if (e.getSource() == startbutton) {
			 


			
			SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>() {

				@Override
				protected String doInBackground() throws Exception {
					System.out.println("estoy en startbutton");
					receivedVector = client.start();


					for(String[] v:receivedVector)
					{
						//System.out.println(v);
						//System.out.println(v[0] + v[1] + v[2]);
						
						logvector.add(v[1] + " " + v[2]);

						
						if(v[0].equals("PDF"))
						{
							pdfvector.add(v[1] .split("\\.")[0]);
						}
						else 
						{
							mp3vector.add(v[1].split("\\.")[0]);					
						}
					}
					return null;
				}
				
				protected void done() {
					Jloglist.setListData(logvector);
					Jpdflist.setListData(pdfvector);
					Jmp3list.setListData(mp3vector);
					
					float dimension = 0;
					for(String l:logvector){												
						dimension = dimension + Float.parseFloat(l.split(" ")[1]);						
					}
					System.out.println(dimension);
					dimensionetext.setText(Float.toString(dimension));

					
				}
			
			};
				
			swingWorker.execute();
			
		}
		else if (e.getSource() == stopbutton) {
		
			client.stop();
			
		}
		else if (e.getSource() == clearbutton) {
		
			Vector<String> clear = new Vector<String>();
			clear.add("");
						
			Jloglist.setListData(clear);
			Jpdflist.setListData(clear);
			Jmp3list.setListData(clear);

			
		}
		
		
	}

}
