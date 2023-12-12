import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class grafica {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Alex Lacueva 2123235");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JPanel south = new JPanel();
		
		JLabel serverlabel = new JLabel("Server Address");
		JLabel portlabel = new JLabel("Port");
		JLabel dimensionelabel = new JLabel("Dimensione");
		
		JTextArea servertext = new JTextArea(1,25);
		JTextArea porttext = new JTextArea(1,6);
		JTextArea dimensionetext = new JTextArea(1,20);
		dimensionetext.setEnabled(false);
		
		JList<String> Jloglist = new JList<String>();
		JList<String> Jpdflist = new JList<String>();
		JList<String> Jmp3list = new JList<String>();
		
		JScrollPane logscroll = new JScrollPane(Jloglist);
		JScrollPane pdfscroll = new JScrollPane(Jpdflist);
		JScrollPane mp3scroll = new JScrollPane(Jmp3list);

		logscroll.setBorder(BorderFactory.createTitledBorder("Log"));
		pdfscroll.setBorder(BorderFactory.createTitledBorder(".pdf"));
		mp3scroll.setBorder(BorderFactory.createTitledBorder(".mp3"));
		
		JButton connectbutton = new JButton("Connect");
		JButton disconnectbutton = new JButton("Disonnect");
		JButton startbutton = new JButton("Start");
		JButton stopbutton = new JButton("Stop");
		JButton clearbutton = new JButton("Clear");
		
		Ascoltatore ascolta = new Ascoltatore(servertext, porttext, dimensionetext, connectbutton, disconnectbutton, startbutton, stopbutton, clearbutton, Jloglist, Jpdflist, Jmp3list);
		connectbutton.addActionListener(ascolta);
		disconnectbutton.addActionListener(ascolta);
		startbutton.addActionListener(ascolta);
		stopbutton.addActionListener(ascolta);
		clearbutton.addActionListener(ascolta);
		
		
		north.add(serverlabel);
		north.add(servertext);
		north.add(portlabel);
		north.add(porttext);
		north.add(connectbutton);
		north.add(disconnectbutton);
		
		center.add(logscroll);
		center.add(pdfscroll);
		center.add(mp3scroll);

		south.add(dimensionelabel);
		south.add(dimensionetext);
		south.add(startbutton);
		south.add(stopbutton);
		south.add(clearbutton);

		frame.add(north, BorderLayout.NORTH);
		frame.add(center, BorderLayout.CENTER);
		frame.add(south, BorderLayout.SOUTH);

		frame.pack();
	}

}
